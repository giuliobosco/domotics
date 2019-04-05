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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Domotics room.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-04-05)
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

}
