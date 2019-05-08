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
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

/**
 * Domotics Light.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.2.4 (2019-04-05 - 2019-05-08)
 */
public class Light {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * Light on status.
     */
    public final int LIGHT_ON = 1;

    /**
     * Light off status.
     */
    public final int LIGHT_OFF = 0;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Pin of the light on the arduino.
     */
    private int pin;

    /**
     * Arduino of the light.
     */
    private Arduino arduino;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the pin of the light on the arduino.
     *
     * @return Pin of the light on the arduino.
     */
    public int getPin() {
        return this.pin;
    }

    /**
     * Get the arduino of the light.
     *
     * @return Arduino of the light.
     */
    public Arduino getArduino() {
        return this.arduino;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the light with the pin and his arduino.
     *
     * @param pin     Pin of the light on the arduino.
     * @param arduino Arduino of the light.
     */
    public Light(int pin, Arduino arduino) {
        this.pin = pin;
        this.arduino = arduino;
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the object as JSON Object.
     *
     * @return JSON Object Object.
     */
    public JSONObject getJson() {
        JSONObject jo = new JSONObject();
        jo.put("pin", this.pin);
        jo.put("arduino", this.arduino.getJson());
        return jo;
    }

    /**
     * Get Light as JSON string.
     * @return Light as JSON string.
     */
    public String getJsonString() {
        return getJson().toString();
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Get request string by the status of the light.
     *
     * @param status Status of the light to insert in the string request. (0-1)
     * @return Url get request string.
     */
    private String getRequestString(int status) {
        return this.getRequestString() + "&set=" + status;
    }

    /**
     * Get the request string of the status of the light.
     *
     * @return Url get request string.
     */
    private String getRequestString() {
        return "http://" + this.arduino.getIp() + ":8080/acc?key=" + this.arduino.getKey() + "&pin=" + this.pin;
    }

    /**
     * Turn the light on.
     *
     * @throws IOException Http get request error.
     */
    public void turnOn() throws IOException {
        try {
            GetRequest.get(this.getRequestString(1));
        } catch (MalformedURLException mue) {

        }
    }

    /**
     * Turn the light off.
     *
     * @throws IOException Http get request error.
     */
    public void turnOff() throws IOException {
        try {
            GetRequest.get(this.getRequestString(0));
        } catch (MalformedURLException mue) {

        }
    }

    /**
     * Get the actual status of the arduino.
     *
     * @return Status of the arduino. -1 if IOException or NumberFormatException.
     * @throws IOException Error while http request.
     */
    public int getStatus() throws IOException {
        try {
            String response = GetRequest.get(this.getRequestString());
            JSONObject jo = new JSONObject(response);
            return Integer.parseInt(jo.getString("message"));
        } catch (MalformedURLException | NumberFormatException ignored) {
            return -1;
        }
    }

    /**
     * Toggle the status of the light.
     *
     * @throws IOException Error while http request.
     */
    public void toggleLight() throws IOException {
        if (getStatus() == this.LIGHT_ON) {
            turnOff();
        } else {
            turnOn();
        }
    }

    // --------------------------------------------------------------------------- Static Components

    /**
     * Main method of the class, used for test.
     * <ul>
     * <li>turnOn()</li>
     * <li>turnOff()</li>
     * <li>getJson()</li>
     * </ul>
     *
     * @param args Command line arguments.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Arduino arduino = new Arduino(DomoticsJdbcC.getIdManager(), "000000000000", "127.0.0.1");
        // new Light(13, arduino).turnOn();
        // new Light(13, arduino).turnOff();
        //System.out.println(new Light(13, arduino).getJson());
        System.out.println(new Light(13, arduino).getStatus());
    }
}
