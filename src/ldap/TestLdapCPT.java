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

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Test LDAP authentication on CPT (Centro Professionale Trevano) LAN.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-02-22)
 */
public class TestLdapCPT {

    // --------------------------------------------------------------------------- Static Components

    /**
     * Main method, first command line argument is the username and the second argument is the
     * password.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        // check if args is bigger than 1
        if (args.length > 1) {
            String domain = "cpt.local";
            String username = args[0];
            String password = args[1];
            String base = "OU=3,OU=I,OU=IN,OU=SAM,OU=allievi,DC=CPT,DC=local";
            String dn = "CN=" + username + "," + base;
            String ldapURL = "ldap://" + domain +":389"; // Setup environment for authenticating
            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            environment.put(Context.PROVIDER_URL, ldapURL);
            environment.put(Context.SECURITY_AUTHENTICATION, "simple");
            environment.put(Context.SECURITY_PRINCIPAL, dn);
            environment.put(Context.SECURITY_CREDENTIALS, password);
            try {
                DirContext authContext = new InitialDirContext(environment);
                // user is authenticated
                System.out.println("Authenticated");
            } catch(AuthenticationException ex) {
                // Authentication failed
                System.out.println("Authentication Error");
                ex.printStackTrace();
            } catch(NamingException ex) {
                ex.printStackTrace();
            }
        }
    }
}
