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

package ldap;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.IOException;
import java.util.Hashtable;

/**
 * LDAP connector, checks if an username is right connected and return the DirContext.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-02-27 - 2019-03-07 fix comments, javadoc, constructors)
 */
public class LdapConnector {

    // ------------------------------------------------------------------------------------ Costants

    /**
     * Default LDAP server port.
     * Value: 389.
     */
    public static final int DEFAULT_PORT = 389;

    /**
     * Default security authentication.
     * Value: "simple".
     */
    public static final String DEFAULT_SECURITY_AUTHENTICATION = "simple";

    /**
     * Default initial context factory connection.
     * Value: "com.sun.jndi.ldap.LdapCtxFactory".
     */
    public static final String DEFAULT_INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * LDAP server address.
     */
    private String domain;

    /**
     * LDAP server port.
     */
    private int port;

    /**
     * LDAP base ou.
     */
    private String base;

    /**
     * LDAP connection security type.
     */
    private String security;

    // --------------------------------------------------------------------------- Getters & Setters

    /**
     * Se the LDAP server address.
     *
     * @param domain LDAP server address.
     */
    private void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Get the LDAP server address.
     *
     * @return LDAP server address.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Set the LDAP server port.
     *
     * @param port LDAP server port.
     * @throws IOException Port not valid.
     */
    private void setPort(int port) throws IOException {
        // check if the port is in the right range for network logical port
        if (port > 0 && port < 65535) {
            this.port = port;
        } else {
            throw new IOException("The port:" + port + "is not valid. Must be in between 1-65535");
        }
    }

    /**
     * Get the LDAP server port.
     *
     * @return LDAP server port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Set the LDAP base ou.
     *
     * @param base LDAP base ou.
     */
    private void setBase(String base) {
        this.base = base;
    }

    /**
     * Get the LDAP base ou.
     *
     * @return LDAP base ou.
     */
    public String getBase() {
        return this.base;
    }

    /**
     * Set the LDAP connection security type.
     *
     * @param security LDAP connection security type.
     */
    private void setSecurity(String security) {
        this.security = security;
    }

    /**
     * Get the LDAP connection security type.
     *
     * @return LDAP connection security type.
     */
    public String getSecurity() {
        return this.security;
    }

    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the LDAP connector.
     *
     * @param domain   LDAP server address.
     * @param port     LDAP server port.
     * @param base     LDAP base ou.
     * @param security LDAP connection security type.
     * @throws IOException Port not valid.
     */
    public LdapConnector(String domain, int port, String base, String security) throws IOException {
        this.setDomain(domain);
        this.setBase(base);
        this.setPort(port);
        this.setSecurity(security);
    }

    /**
     * Create the LDAP connector.
     *
     * @param domain LDAP server address.
     * @param port   LDAP server port.
     * @param base   LDAP base ou.
     * @throws IOException Port not valid.
     */
    public LdapConnector(String domain, int port, String base) throws IOException {
        this(domain, port, base, DEFAULT_SECURITY_AUTHENTICATION);
    }

    /**
     * Create the LDAP connector.
     *
     * @param domain   LDAP server address.
     * @param base     LDAP base ou.
     * @param security LDAP Security type.
     */
    public LdapConnector(String domain, String base, String security) {
        try {
            this.setDomain(domain);
            this.setBase(base);
            this.setPort(DEFAULT_PORT);
            this.setSecurity(security);
        } catch (IOException ignored) {
            // ignored because the port default port is in the range (and is a constant).
        }
    }


    /**
     * Create the LDAP connector.
     *
     * @param domain LDAP server address.
     * @param base   LDAP base ou.
     */
    public LdapConnector(String domain, String base) {
        this(domain, base, DEFAULT_SECURITY_AUTHENTICATION);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the connection string to the LDAP server.
     * Like "ldap://host.local:389".
     *
     * @return Connection string to the LDAP server.
     */
    private String getConnectionString() {
        return "ldap://" + getDomain() + ":" + getPort();
    }

    /**
     * Get the DN for ldap.
     * Like "CN=username, OU=ldapExample,DC=host,DC=local".
     *
     * @param username Username of the DN.
     * @return DN String.
     */
    private String getDn(String username) {
        return "CN=" + username + "," + getBase();
    }

    /**
     * Get the hashtable environment of the connection.
     *
     * @param username Username of the connection.
     * @param password Password of the connection.
     * @return Hashtable Environment of the connection.
     */
    private Hashtable<String, String> getEnvironment(String username, String password) {
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, DEFAULT_INITIAL_CONTEXT_FACTORY);
        environment.put(Context.PROVIDER_URL, getConnectionString());
        environment.put(Context.SECURITY_AUTHENTICATION, getSecurity());
        environment.put(Context.SECURITY_PRINCIPAL, getDn(username));
        environment.put(Context.SECURITY_CREDENTIALS, password);

        return environment;
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Check the user and connect it, get the Dir context.
     * Handle the AuthenticationException, used for the wrong credentials, separately from the
     * NamingException, used for connection errors.
     *
     * @param username Username of the connection.
     * @param password Password of the connection.
     * @return Dir context of the connection.
     * @throws NamingException Error with the connection to the LDAP server. Wrong credentials or
     *                         connection error.
     */
    public DirContext getDirContext(String username, String password) throws NamingException {
        return new InitialDirContext(getEnvironment(username, password));
    }

    // --------------------------------------------------------------------------- Static Components

    /**
     * Main method of the class, for test LdapConnector.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // set the test LDAP domain
        String domain = "cpt.local";
        // set the test LDAP OU three
        String base = "OU=3,OU=I,OU=IN,OU=SAM,OU=allievi,DC=CPT,DC=local";
        // create the LDAP Connector with the domain and the base
        LdapConnector ldacC = new LdapConnector(domain, base);
        
        // try to connect to LDAP
        try {
            ldacC.getDirContext("user", "pass");
            // user is authenticated
            System.out.println("Authenticated");
        } catch (AuthenticationException ae) {
            // authentication failed
            System.out.println("Authentication Error");
            ae.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }
}
