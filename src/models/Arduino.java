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

import acc.IdManager;
import jdbc.JdbcConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Domotics arduino.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.2 (2019-04-05)
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
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create arduino with the ip, the ACC-Client-KEY, the ACC-Client-ID and the domotics room.
     *
     * @param ip   Ip of the arduino.
     * @param key  ACC-Client-KEY.
     * @param id   ACC-Client-ID.
     * @param rootPassword Root password of the arduino.
     * @param room Room of the arduino.
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

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
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
     * Test the class Arduino.
     *
     * @param args Command line arguments.
     * @throws SQLException Error on the MySQL Server.
     * @throws ClassNotFoundException MySQL Driver class
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JdbcConnector jdbcConnector = new JdbcConnector("root", "1234qwer", "localhost", "domotics");
        jdbcConnector.openConnection();
        IdManager idManager = new IdManager(jdbcConnector);
        Arduino arduino = new Arduino(idManager, "156EA1165EE4", "10.20.4.103");
        System.out.println(arduino.id);
        System.out.println(arduino.key);
        System.out.println(arduino.rootPassword);
        System.out.println(arduino.ip);
        System.out.println(arduino.room.getName());
    }
}
