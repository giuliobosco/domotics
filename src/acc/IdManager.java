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

import jdbc.JdbcConnector;

import java.sql.*;

/**
 * Manage ACC-Client-ID and ACC-Client-KEY, collecting data from the domotics database.
 *
 * @author paologuebeli
 * @author giuliobosco
 * @version 1.1.2 (2019-04-05)
 */
public class IdManager {

    /**
     * ACC-Client-KEY
     */
    public final int CLIENT_KEY_LENGTH = 12;

    /**
     * Jdbc connection manager.
     */
    private JdbcConnector connector;

    /**
     * Create the IdManager with the jdbc connector.
     *
     * @param connector Jdbc connection manager.
     */
    public IdManager(JdbcConnector connector) {
        this.connector = connector;
    }

    /**
     * Check arduino IP address.
     * If the IP is different on the database and the actual, it will be updated.
     *
     * @param id Arduino's ACC-Client-ID.
     * @param ip Arduino's IP Address.
     * @throws SQLException Error with the sql server.
     */
    public void checkIp(String id, String ip) throws SQLException {
        ResultSet rs = this.connector.query("SELECT ip FROM arduino WHERE client_id=" + id);
        if (!ip.equals(rs.getString("ip"))) {
            this.connector.update("UPDATE arduino SET ip =" + ip + " WHERE client_id=" + id);
        }
    }

    /**
     * Check the arduino ACC-Client-Key by the ID.
     *
     * @param id Arduino ACC-Client-ID.
     * @return Arduino ACC-Client-KEY.
     * @throws SQLException Error with the sql server.
     */
    public String getAccClientKey(String id) throws SQLException {
        ResultSet rs = this.connector.query("SELECT client_key FROM domotics.arduino WHERE client_id='" + id + "';");
        rs.next();
        String clientKey = rs.getString("client_key");
        if (clientKey != null && clientKey.length() == CLIENT_KEY_LENGTH) {
            return rs.getString("client_key");
        }
        rs.close();

        rs = this.connector.query("SELECT client_key FROM domotics.arduino WHERE client_id='" + id + "';");
        boolean foundEqualClientKey = true;
        while (foundEqualClientKey) {
            clientKey = IdManager.createKey();
            foundEqualClientKey = false;
            while (rs.next()) {
                if (rs.getString("client_key").equals(clientKey)) {
                    foundEqualClientKey = true;
                }
            }
        }

        this.connector.update("UPDATE domotics.arduino SET client_key ='" + clientKey + "' WHERE client_id='" + id + "';");
        return clientKey;
    }

    /**
     * Generate a random ACC-Client-KEY.
     *
     * @return Hexadecimal ACC-Client-KEY.
     */
    public static String createKey() {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String result = "";
        for (int i = 0; i < 12; i++) {
            int rand = (int) (Math.random() * 16);
            result += hex[rand];
        }
        return result;
    }

    /**
     * Test the class IdManager.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        System.out.println(IdManager.createKey());
    }
}
