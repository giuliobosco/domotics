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

package portal.authentication;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Check the authentication on the session.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-09 - 2019-05-09)
 */
public class AuthenticationChecker {
    // ------------------------------------------------------------------------------------ Costants
    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Http session to check if authenticated.
     */
    private HttpSession session;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create authentication checker with the http session.
     *
     * @param session Http session to check if authenticated.
     */
    public AuthenticationChecker(HttpSession session) {
        this.session = session;
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Check if the session is valid.
     *
     * @return True if session is valid.
     */
    public boolean isValidSession() {
        String user = null;

        if (session.getAttribute("user") == null) {
            return false;
        }

        user = (String) session.getAttribute("user");

        return true;
    }

    /**
     * Get the username of the session.
     *
     * @return Username of the session.
     * @throws IOException Invalid session.
     */
    public String getSessionUsername() throws IOException {
        if (isValidSession()) {
            return (String) session.getAttribute("user");
        }

        throw new IOException("Session not valid.");
    }

    // ----------------------------------------------------------------------------- General Methods
    // --------------------------------------------------------------------------- Static Components

}
