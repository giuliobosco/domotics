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
import acc.IdManager;
import jdbc.JdbcConnector;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Domotics arduino.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.3.6 (2019-04-15 - 2019-05-08)
 */
public class Arduino {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * ACC-Client-ID.
     */
    private String id;

    /**
     * Ip of of the arduino.
     */
    private String ip;

    /**
     * ACC-Client-KEY.
     */
    private String key;

    /**
     * Root password of the arduino.
     */
    private String rootPassword;

    /**
     * Room of the arduino.
     */
    private Room room;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the ACC-Client-ID.
     *
     * @return ACC-Client-ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the ip of the arduino.
     *
     * @return ACC-Client-ID.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Get the ACC-Client-KEY.
     *
     * @return ACC-Client-KEY.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the room of the arduino.
     *
     * @return Room of the arduino.
     */
    public Room getRoom() {
        return room;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create arduino with the ip, the ACC-Client-KEY, the ACC-Client-ID and the domotics room.
     *
     * @param ip           Ip of the arduino.
     * @param key          ACC-Client-KEY.
     * @param id           ACC-Client-ID.
     * @param rootPassword Root password of the arduino.
     * @param room         Room of the arduino.
     */
    public Arduino(String id, String ip, String key, String rootPassword, Room room) {
        this.id = id;
        this.ip = ip;
        this.key = key;
        this.rootPassword = rootPassword;
        this.room = room;
    }

    /**
     * Create arduino with ACC-Client-ID and the Ip of the arduino, this will require also an
     * domotics IdManager, for check the ACC-Client-ID, update the IP on the database, generate a
     * new ACC-Client-KEY and get the room of the Arduino.
     *
     * @param idManager Domotics Id Manager.
     * @param id        ACC-Client-ID.
     * @param ip        Ip of the arduino.
     * @throws SQLException Error with MySQL Server.
     */
    public Arduino(IdManager idManager, String id, String ip) throws SQLException {
        idManager.checkIp(id, ip);
        this.ip = ip;
        this.key = idManager.getAccClientKey(id);
        this.id = id;
        this.room = idManager.getRoomById(id);
    }

    /**
     * Create the room from the database with the ACC-Client-ID of the arduino.
     *
     * @param connector Connection to the MySQL Server.
     * @param id            ACC-Client-ID.
     * @throws SQLException           Error on the MySQL Server.
     */
    public Arduino(JdbcConnector connector, String id) throws SQLException {
        String query = "SELECT * FROM domotics.arduino WHERE client_id='" + id + "';";
        ResultSet resultSet = connector.query(query);

        this.set(resultSet);
    }

    /**
     * Create Arduino with ip of the arduino and key of the arduino.
     *
     * @param connector Connector to domotics MySQL database.
     * @param ip Ip of the arduino.
     * @param key Key of communication between ACC-Client and ACC-Server.
     * @throws SQLException Error on MySQL Server.
     */
    public Arduino(JdbcConnector connector, String ip, String key) throws SQLException {
        String query = "SELECT  * FROM domotics.arduino WHERE ip='" + ip + "' AND client_key='" + key + "';";
        ResultSet resultSet = connector.query(query);

        this.set(resultSet);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Set the parameters of the arduino from the Result set.
     * The result set have to be of one row, with the id, ip, key, rootPassword and the room.
     *
     * @param resultSet Result set for set the parameters of the arduino.
     * @throws SQLException Error on the MySQL Server.
     */
    private void set(ResultSet resultSet) throws SQLException {
        resultSet.next();
        this.id = resultSet.getString("client_id");
        this.ip = resultSet.getString("ip");
        this.key = resultSet.getString("client_key");
        this.rootPassword = resultSet.getString("root_password");
        this.room = Room.get(resultSet.getString("room"));
    }

    /**
     * Get Arduino as JSON Object.
     *
     * @return Arduino JSON Object.
     */
    public JSONObject getJson() {
        JSONObject jo = new JSONObject();
        jo.put("id", this.getId());
        jo.put("ip", this.getIp());
        jo.put("room", this.getRoom().getJson());
        return jo;
    }

    /**
     * Get Arduino as JSON string.
     *
     * @return Arduino as JSON string.
     */
    public String getJsonString() {
        return this.getJson().toString();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Check if the arduino is alive.
     *
     * @return True if the arduino is alive.
     * @throws IOException Error with the http get request.
     */
    public boolean isAlive() throws IOException {
        try {
            String urlString = "http://" + this.getIp() + ":8080/alive";
            String response = GetRequest.get(urlString);

            if (response.contains("\"status\":\"OK\"")) {
                return true;
            }
        } catch (MalformedURLException ignored) {

        }

        return false;
    }

    // --------------------------------------------------------------------------- Static Components

    /**
     * Get the arduinos from the sql result set, of the query to the domotics database.
     *
     * @param sqlResultSet Sql result set.
     * @return Arduinos in the result set.
     * @throws SQLException Error on the database.
     */
    public static List<Arduino> getArduinos(ResultSet sqlResultSet) throws SQLException {
        List<Arduino> arduinos = new ArrayList<>();

        while (sqlResultSet.next()) {
            arduinos.add(new Arduino(
                    sqlResultSet.getString("client_id"),
                    sqlResultSet.getString("ip"),
                    sqlResultSet.getString("client_key"),
                    sqlResultSet.getString("root_password"),
                    Room.get(sqlResultSet.getString("room"))
            ));
        }

        return arduinos;
    }

    /**
     * Get all the arduino in a room.
     *
     * @param jdbcConnector Connection to the MySQL domotics database.
     * @param roomName      Name of the room.
     * @return List of arduino in the room.
     * @throws SQLException Error on the MySQL Server.
     */
    public static List<Arduino> getArduinosByRoom(JdbcConnector jdbcConnector, String roomName) throws SQLException {
        String query = "SELECT * FROM domotics.arduino WHERE room='" + roomName + "';";
        ResultSet resultSet = jdbcConnector.query(query);

        return Arduino.getArduinos(resultSet);
    }

    /**
     * Test the class Arduino.
     *
     * @param args Command line arguments.
     * @throws SQLException           Error on the MySQL Server.
     * @throws ClassNotFoundException MySQL Driver class
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcConnector jdbcConnector = new JdbcConnector("root", "1234qwer", "localhost", "domotics");
        jdbcConnector.openConnection();
        IdManager idManager = new IdManager(jdbcConnector);
        Arduino arduino = new Arduino(idManager, "156EA1165EE4", "10.20.4.103");
        System.out.println(arduino.getId());
        System.out.println(arduino.getKey());
        System.out.println(arduino.getIp());
        System.out.println(arduino.getRoom().getName());

        // getArduinos
        String query = "SELECT * FROM domotics.arduino";
        List<Arduino> arduinos = Arduino.getArduinos(jdbcConnector.query(query));

        for (Arduino a : arduinos) {
            System.out.println(a.getId());
        }

        System.out.println(new Arduino(jdbcConnector, "156EA1165EE4").getId());

        arduinos = getArduinosByRoom(jdbcConnector, "A100");

        for (Arduino a : arduinos) {
            System.out.println(a.getId());
        }

        arduino = new Arduino(jdbcConnector, "10.20.4.102", "000000000000");
        System.out.println(arduino.getId());
    }
}
