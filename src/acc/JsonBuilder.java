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

import org.json.JSONObject;

/**
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-08 - 2019-05-08)
 */
public class JsonBuilder {
    // ------------------------------------------------------------------------------------ Costants

    public static String ERROR = "ERROR";

    public static String OK = "OK";

    // ---------------------------------------------------------------------------------- Attributes
    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors
    // -------------------------------------------------------------------------------- Help Methods
    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

    /**
    * Puts the status and the message in a JSON
    *
    * @param status String of the status
    * @param message String of the message
    *
    * @return json in String format
    */
    public static String getJsonResponse(String status, String message) {
        JSONObject jo = new JSONObject();
        jo.put("status", status);
        jo.put("message", message);
        return jo.toString();
    }

    public static String getJsonResponseError(String message) {
        return getJsonResponse(ERROR, message);
    }

    public static String getJsonResponseOk(String message) {
        return getJsonResponse(OK, message);
    }
}
