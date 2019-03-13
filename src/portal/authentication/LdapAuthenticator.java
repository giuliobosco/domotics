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

import ldap.LdapConnector;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import java.io.IOException;

/**
 * Ldap authenticator for the portal.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-02-28)
 */
public class LdapAuthenticator implements Authenticator {

    // ------------------------------------------------------------------------------------ Costants

    /**
     * LDAP server domain, used in the LdapConnector.
     */
    private static final String DOMAIN = "cpt.local";

    /**
     * LDAP server port, used in the LdapConnector.
     */
    private static final int PORT = 389;

    /**
     * LDAP base ou, used in the LdapConnector.
     */
    private static final String BASE_OU = "DC=CPT,DC=local";

    /**
     * LDAP connection security, used in the LdapConnector.
     */
    private static final String SECURITY = "simple";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * LDAP base sub ou, inserted before the BASE_OU.
     */
    private String subOu;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Get the LDAP base ou, with sub ou.
     *
     * @return LDAP base ou, with sub ou.
     */
    private String getBaseOu() {
        return this.subOu + BASE_OU;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create LDAP Authenticator, with the sub ou string.
     *
     * @param subOu Sub ou string inserted before the LDAP BASE_OU.
     */
    public LdapAuthenticator(String subOu) {
        if (subOu != null && subOu.trim().length() > 0) {
            this.subOu = subOu + ",";
        } else {
            this.subOu = "";
        }
    }

    /**
     * Create the LDAP Authenticator.
     */
    public LdapAuthenticator() {
        this(null);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the LDAP connector.
     *
     * @return Ldap Connector with the parameters of the costants.
     * @throws IOException LDAP server port not valid.
     */
    private LdapConnector getLdapConnector() throws IOException {
        return new LdapConnector(DOMAIN, PORT, getBaseOu(), SECURITY);
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Checks the user credentials.
     * Handle the AuthenticationException, used for the wrong credentials, separately from the
     * NamingException, used for connection errors.
     *
     * @param username Username to authenticate.
     * @param password Password of the username to authenticate.
     * @return True if the user has right credentials.
     * @throws NamingException Wrong credentials.
     */
    @Override
    public boolean authenticate(String username, String password) throws NamingException, IOException {
        getLdapConnector().getDirContext(username, password);

        return true;
    }

    // --------------------------------------------------------------------------- Static Components

    /**
     * Main method of the class, for test LdapAuthenticator.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Authenticator authenticator = new LdapAuthenticator("OU=3,OU=I,OU=IN,OU=SAM,OU=allievi");
        try {
            authenticator.authenticate("user", "pass");
            // user is authenticated
            System.out.println("Authenticated");
        } catch (AuthenticationException ae) {
            // authentication failed
            System.out.println("Authentication failed");
            ae.printStackTrace();
        } catch (NamingException | IOException e) {
            e.printStackTrace();
        }
    }
}
