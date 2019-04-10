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

/**
 * Domotics Light.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-05)
 */
public class Light {
    // ------------------------------------------------------------------------------------ Costants
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
     * @param pin Pin of the light on the arduino.
     * @param arduino Arduino of the light.
     */
    public Light(int pin, Arduino arduino) {
        this.pin = pin;
        this.arduino = arduino;
    }

    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
