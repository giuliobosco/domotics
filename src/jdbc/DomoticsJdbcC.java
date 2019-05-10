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

package jdbc;

import acc.IdManager;

import java.sql.SQLException;

/**
 * Get the JDBC connector for the domotics database.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-04-21 2019-04-29)
 */
public class DomoticsJdbcC {

    /**
     * Domotics database username.
     */
    private static final String USERNAME = "root";

    /**
     * Domotics database password.
     */
    private static final String PASSWORD = "1234qwer";

    /**
     * Domotics database host.
     */
    private static final String HOSTNAME = "localhost";

    /**
     * Domotics database port.
     */
    private static final int PORT = 3306;

    /**
     * Domotics database name.
     */
    private static final String DATABASE = "domotics";

    /**
     * Get the JDBC Connector for the domotics database.
     *
     * @return JDBC Connector for the domotics database.
     */
    public static JdbcConnector getConnector() {
        return new JdbcConnector(USERNAME, PASSWORD, HOSTNAME, PORT, DATABASE);
    }

    /**
     * Get the domotics id mananger with the domotics JDBC Connector for the domotics datatabase.
     *
     * @return Domotics id mananger with the domotics JDBC Connector for the domotics datatabase.
     * @throws SQLException           Error on the MySQL Database.
     * @throws ClassNotFoundException MySQL jdbc Driver not found.
     */
    public static IdManager getIdManager() throws SQLException, ClassNotFoundException {
        JdbcConnector jdbcConnector = getConnector();
        jdbcConnector.openConnection();
        return new IdManager(jdbcConnector);
    }

}
