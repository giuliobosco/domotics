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

package acc;

import models.Arduino;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Auto configuration module for domotics acc client.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.1 (2019-05-03)
 */
public class Autoconf {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Id of the arduino.
     */
    private String arduinoId;

    /**
     * Key of communication with the arduino.
     */
    private String arduinoKey;

    /**
     * Address of the domotics server.
     */
    private String serverAddress;

    private int serverPort;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the json configuration for the arduino acc client.
     *
     * @return Json configuration for the arduino acc client.
     */
    public String getJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.arduinoId);
        json.put("key", this.arduinoKey);
        json.put("server_address", this.serverAddress + ":" + this.serverPort);
        return json.toString();
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create auto configuration for acc client.
     *
     * @param idManager Id Manager, to check the IP and get the key.
     * @param arduinoId Id of the arduino.
     * @param arduinoIp Ip of the arduino.
     * @param serverAddress Address of the domotics server.
     * @throws SQLException Error on the MySQL Server.
     */
    public Autoconf(IdManager idManager, String arduinoId, String arduinoIp,
                    String serverAddress, int serverPort) throws SQLException {
        Arduino arduino = new Arduino(idManager, arduinoId, arduinoIp);
        this.arduinoId = arduino.getId();
        this.arduinoKey = arduino.getKey();
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
