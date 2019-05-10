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

import jdbc.JdbcConnector;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Domotics room.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.5 (2019-05-03 - 2019-05-10)
 */
public class Room {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Name of the room.
     */
    private String name;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the name of the arduino.
     *
     * @return Name of the room.
     */
    public String getName() {
        return this.name;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the room with his name.
     *
     * @param name Name of the room.
     */
    public Room(String name) {
        this.name = name;
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the Room as JSON Object.
     *
     * @return Room as JSON Object.
     */
    public JSONObject getJson() {
        JSONObject jo = new JSONObject();
        jo.put("name", this.getName());
        return jo;
    }

    /**
     * Get the Room as JSON String.
     *
     * @return Room as JSON String.
     */
    public String getJsonString() {
        return getJson().toString();
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

    /**
     * Get the rooms from the sql result set, of the query to the domotics database.
     *
     * @param sqlResultSet Sql result set.
     * @return Rooms in the result set.
     * @throws SQLException Error on the database.
     */
    public static List<Room> getRooms(ResultSet sqlResultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();

        while (sqlResultSet.next()) {
            rooms.add(new Room(sqlResultSet.getString("name")));
        }

        return rooms;
    }

    /**
     * Get room by name.
     *
     * @param name Name of the room.
     * @return Room.
     */
    public static Room get(String name) {
        return new Room(name);
    }

    /**
     * Get the room by name from the domotics database.
     *
     * @param name          Name of the room.
     * @param jdbcConnector Connection to the database.
     * @return Room created from the database.
     * @throws SQLException           Error on the MySQL server.
     */
    public static Room get(String name, JdbcConnector jdbcConnector) throws SQLException {
        String query = "SELECT * FROM domotics.room WHERE name = '" + name + "';";

        ResultSet resultSet = jdbcConnector.query(query);

        while (resultSet.next()) {
            Room room = get(resultSet.getString("name"));
            resultSet.close();
            jdbcConnector.closeStatement();
            return room;
        }

        resultSet.close();
        jdbcConnector.closeStatement();
        return null;
    }

    /**
     * Test if the get method works.
     *
     * @param args Command line arguments.
     * @throws SQLException           Error with the MySQL Server.
     * @throws ClassNotFoundException MySQL Driver class not found.
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcConnector jdbc = new JdbcConnector("root", "1234qwer", "localhost", "domotics");
        jdbc.openConnection();
        System.out.println(get("A102", jdbc).getName());
        jdbc.closeConnection();
    }

}
