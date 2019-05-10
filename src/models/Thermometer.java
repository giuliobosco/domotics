/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package models;

import acc.GetRequest;
import jdbc.DomoticsJdbcC;
import jdbc.JdbcConnector;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Domotics thermometer.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-05-09 - 2019-05-10)
 */
public class Thermometer {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Arduino of the thermometer.
     */
    private Arduino arduino;

    /**
     * Pin of the thermometer on arduino.
     */
    private String pin;

    /**
     * Type of the thermometer.
     */
    private String type;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create new controller with pin and the arduino id, using the connector.
     *
     * @param pin Pin of the thermometer on the arduino.
     * @param arduinoId Id of the arduino.
     * @param connector Connector to MySQL domotics database.
     * @throws SQLException Error on the MySQL domotics server.
     */
    public Thermometer(String pin, String arduinoId, JdbcConnector connector) throws SQLException {
        String query = "SELECT * FROM domotics.sensor WHERE pin='" + pin + "' AND arduino='" + arduinoId + "';";
        ResultSet rs = connector.query(query);

        if (rs.next()) {
            this.pin = rs.getString("pin");
            this.type = rs.getString("type");
            this.arduino = new Arduino(connector, rs.getString("arduino"));
        } else {
            throw new SQLException("No thermometer");
        }
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the value of the thermometer.
     *
     * @return Value of the thermometer.
     * @throws IOException Error while requesting the value to arduino.
     */
    public double getValue() throws IOException {
        String response = GetRequest.get(getRequestString());
        JSONObject jo = new JSONObject(response);

        if (jo.get("message") != null) {
            String tempString = jo.getString("message");
            try {
                return Float.parseFloat(tempString);
            } catch (NumberFormatException nfe) {
            }
        }

        throw new IOException("Response error");
    }

    /**
     * Get the value of the thermometer with defined decimals numbers.
     *
     * @param decimals Decimals of the value.
     * @return Value of the thermometer.
     * @throws IOException Error while requesting the value to the arduino.
     */
    public double getValue(int decimals) throws IOException {
        double temperature = getValue();

        double mult = Math.pow(10, decimals);

        temperature *= mult;
        temperature = (int) temperature;
        temperature /= mult;

        return temperature;
    }

    /**
     * Get the request string of the status of the thermometer.
     *
     * @return Url get request string.
     */
    private String getRequestString() {
        return "http://" + this.arduino.getIp() + ":8080/acc?key=" + this.arduino.getKey() + "&pin=" + this.pin;
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

    /**
     * Get the temperature of the room.
     *
     * @param room Room.
     * @param connector Connector to MySQL domotics database.
     * @return Temperature of the room.
     * @throws SQLException Error on the MySQL domotics database.
     * @throws IOException Error while requesting the value to arduino.
     */
    public static double getTemperatureByRoom(Room room, JdbcConnector connector) throws SQLException, IOException {
        String query = "SELECT * FROM domotics.sensor s JOIN arduino a on s.arduino = a.client_id WHERE s.type='temperature' AND a.room='" + room.getName() +"';";
        ResultSet rs = connector.query(query);

        if (rs.next()) {
            String arduinoId = rs.getString("arduino");
            String pin = rs.getString("pin");

            Thermometer t = new Thermometer(pin, arduinoId, connector);
            return t.getValue(1);
        }
        throw new SQLException("No temperature");
    }

    /**
     * Main method of the class, used to test the methods of the class.
     *
     * @param args Command line arguments.
     * @throws Exception Errors.
     */
    public static void main(String[] args) throws Exception {
        JdbcConnector connector = DomoticsJdbcC.getConnector();
        connector.openConnection();

        Thermometer t = new Thermometer("A1", "000000000002", connector);
        System.out.println(t.pin);
        System.out.println(t.type);
        System.out.println(t.arduino.getJsonString());
        System.out.println(t.getValue(1));
        System.out.println(Thermometer.getTemperatureByRoom(new Room("A101"), connector));
    }
}
