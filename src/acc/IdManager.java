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

import java.sql.*;

/**
 * Manage ACC-Client-ID and ACC-Client-KEY, collecting data from the domotics database.
 *
 * @author guebe
 * @author giuliobosco
 * @version 1.0.1 (2019-04-05)
 */
public class IdManager {

    /**
     * Get the connection to the database.
     *
     * @param username Database username.
     * @param password Database password.
     * @param server   Database server address.
     * @param port     Database port.
     * @param database Database.
     * @return Database connection.
     * @throws SQLException           Error with the sql server.
     * @throws ClassNotFoundException Jdbc Driver not found.
     */
    public Connection getDbConnection(String username, String password, String server, int port, String database) throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + server + ":" + port + "/" + database;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connectionString, username, password);
    }

    /**
     * Execute query on database.
     *
     * @param c     Database connection.
     * @param query Query to execute on the database.
     * @return Query result set.
     * @throws SQLException Error with the sql server.
     */
    public ResultSet query(Connection c, String query) throws SQLException {
        // create the java statement
        Statement st = c.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        st.close();

        return rs;
    }

    /**
     * Execute update query on database.
     *
     * @param connection Database connection.
     * @param update     Update to execute on the database.
     * @throws SQLException
     */
    public void update(Connection connection, String update) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(update);
    }

    /**
     * Check arduino IP address.
     * If the IP is different on the database and the actual, it will be updated.
     *
     * @param connection Database connectino.
     * @param id         Arduino's ACC-Client-ID.
     * @param ip         Arduino's IP Address.
     * @throws SQLException Error with the sql server.
     */
    public void checkIp(Connection connection, String id, String ip) throws SQLException {
        ResultSet rs = query(connection, "SELECT ip from arduino where client_id=" + id);
        if (!ip.equals(rs.getString("ip"))) {
            update(connection, "update arduino set ip =" + ip + " where client_id=" + id);
        }
    }

    /**
     * Check the arduino ACC-Client-Key by the ID.
     *
     * @param connection Connection to the database.
     * @param id         Arduino ACC-Client-ID.
     * @return Arduino ACC-Client-KEY.
     * @throws SQLException Error with the sql server.
     */
    public String getAccClientKey(Connection connection, String id) throws SQLException {
        ResultSet rs = query(connection, "SELECT ip from arduino where client_id=" + id);
        if (rs.getString("client_key") != null) {
            return rs.getString("client_key");
        }
        ResultSet ds = query(connection, "SELECT ip from arduino where client_id=" + id);
        boolean flag = true;
        String key = "";
        while (flag) {
            key = createKey();
            flag = false;
            while (ds.next()) {
                if (ds.getString("client_key").equals(key)) {
                    flag = true;
                }
            }
        }
        return key;
    }

    /**
     * Generate a random ACC-Client-KEY.
     *
     * @return Hexadecimal ACC-Client-KEY.
     */
    public String createKey() {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String result = "";
        for (int i = 0; i < 12; i++) {
            int rand = (int) (Math.random() * 16);
            result += hex[rand];
        }
        return result;
    }
}
