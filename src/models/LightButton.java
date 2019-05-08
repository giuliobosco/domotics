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

import jdbc.DomoticsJdbcC;
import jdbc.JdbcConnector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Domotics Light button.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.2 (2019-05-03 - 2019-05-08)
 */
public class LightButton {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Pin of the button on the arduino.
     */
    private int buttonPin;

    /**
     * Light of the button on the arduino.
     */
    private Light light;

    /**
     * Arduino of the button and the light.
     */
    private Arduino arduino;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the pin of the button on the arduino.
     *
     * @return Pin of the button on the arduino.
     */
    public int getButtonPin() {
        return this.buttonPin;
    }

    /**
     * Get the light of the button on the arduino.
     *
     * @return Light of the button on the arduino.
     */
    public Light getLight() {
        return this.light;
    }

    /**
     * Get the arduino of the button and the light.
     *
     * @return Arduino of the button and the light.
     */
    public Arduino getArduino() {
        return this.arduino;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create Light button with the pin of the button, ip of the arduino, key of the arduino,
     * connector to domotics database.
     *
     * @param buttonPin Pin of the button.
     * @param ip        Ip of the arduino.
     * @param key       Key of communication with the arduino.
     * @param connector Connector to domotics database.
     * @throws SQLException           Error on the MySQL domotics database.
     * @throws ClassNotFoundException MySQL Driver class not found.
     * @throws IOException            Error with the http get request.
     */
    public LightButton(int buttonPin, String ip, String key, JdbcConnector connector) throws SQLException, ClassNotFoundException, IOException {
        this.arduino = new Arduino(connector, ip, key);
        checkPin(buttonPin, connector);
        this.buttonPin = buttonPin;
        loadLight(connector);
    }

    /**
     * Create Light button with the pin of the button and the id of the arduino. Using the connector
     * to domotics database.
     *
     * @param buttonPin Pin of the button.
     * @param id        Id of the arduino.
     * @param connector Connector to domotics database.
     * @throws SQLException           Error on the MySQL domotics database.
     * @throws ClassNotFoundException MySQL Drier class not found.
     * @throws IOException            Error with the http get request.
     */
    public LightButton(int buttonPin, String id, JdbcConnector connector) throws SQLException, ClassNotFoundException, IOException {
        this.arduino = new Arduino(connector, id);
        checkPin(buttonPin, connector);
        this.buttonPin = buttonPin;
        loadLight(connector);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Check if the pin is valid.
     *
     * @param pin       Pin to check.
     * @param connector Connector to domotics database.
     * @throws SQLException Error on the MySQL database.
     * @throws IOException  Error with the http get request.
     */
    private void checkPin(int pin, JdbcConnector connector) throws SQLException, IOException {
        String query = "SELECT * FROM domotics.lightButton WHERE pin='" + pin + "' AND arduino='" + this.arduino.getId() + "';";
        ResultSet rs = connector.query(query);

        if (rs.next()) {
            int dbPin = rs.getInt("pin");
            if (dbPin != pin) {
                String message = "On arduino: " + this.arduino.getId() + " no light button on pin: " + pin;
                throw new IOException(message);
            }
        } else {
            throw new SQLException("Pin not found");
        }
    }

    /**
     * Load the light of the button.
     *
     * @param connector Connector to domotics database.
     * @throws SQLException           Error on the MySQL
     * @throws ClassNotFoundException MySQL Driver class not found.
     */
    private void loadLight(JdbcConnector connector) throws SQLException, ClassNotFoundException {
        String query = "SELECT l.pin, l.arduino FROM domotics.lightButton lb JOIN light l on lb.lightPin = l.pin WHERE lb.pin='" + this.buttonPin + "' AND lb.arduino='" + this.arduino.getId() + "';";
        ResultSet rs = connector.query(query);
        rs.next();
        int pin = rs.getInt("pin");
        Arduino arduino = new Arduino(connector, rs.getString("arduino"));
        this.light = new Light(pin, arduino, connector);
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

    /**
     * Test the class Light Button.
     *
     * @param args Command line Arguments.
     * @throws Exception All errors.
     */
    public static void main(String[] args) throws Exception {
        JdbcConnector connector = DomoticsJdbcC.getConnector();
        connector.openConnection();
        LightButton lightButton = new LightButton(6, "127.0.0.1", "000000000001", connector);
        System.out.println(lightButton.getLight().getJson());
        System.out.println(lightButton.getButtonPin());
    }
}
