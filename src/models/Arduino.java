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

import java.sql.SQLException;

/**
 * Domotics arduino.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-05)
 */
public class Arduino {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Ip of of the arduino.
     */
    private String ip;

    /**
     * ACC-Client-KEY.
     */
    private String key;

    /**
     * ACC-Client-ID.
     */
    private String id;

    /**
     * Room of the arduino.
     */
    private Room room;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create arduino with the ip, the ACC-Client-KEY, the ACC-Client-ID and the domotics room.
     *
     * @param ip Ip of the arduino.
     * @param key ACC-Client-KEY.
     * @param id ACC-Client-ID.
     * @param room Room of the arduino.
     */
    public Arduino(String ip, String key, String id, Room room) {
        this.ip = ip;
        this.key = key;
        this.id = id;
        this.room = room;
    }

    /**
     * Create arduino with the ip, the ACC-Client-ID and the room, this will require also an
     * domotics IdManager, for check the ACC-Client-ID, update the IP on the database and generate
     * a new ACC-Client-KEY.
     *
     * @param idManager Domotics Id Manager.
     * @param ip Ip of the arduino.
     * @param id ACC-Client-ID.
     * @param room Room of the arduino.
     * @throws SQLException
     */
    public Arduino(IdManager idManager, String ip, String id, Room room) throws SQLException {
        idManager.checkIp(id, ip);
        this.ip = ip;
        this.key = idManager.getAccClientKey(id);
        this.id = id;
        this.room = room;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components
    
}
