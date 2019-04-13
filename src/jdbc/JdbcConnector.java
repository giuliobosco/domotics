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

import java.sql.*;

/**
 * Manage connection to databases with JDBC Driver.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-04-05)
 */
public class JdbcConnector {
    // ------------------------------------------------------------------------------------ Costants

    /**
     * MySQL Default connection port.
     */
    public static final int DEFAULT_PORT = 3306;

    // ---------------------------------------------------------------------------------- Attributes

    /**
     * Database username.
     */
    private String username;

    /**
     * Database password.
     */
    private String password;

    /**
     * Database server host name.
     */
    private String host;

    /**
     * Database server port.
     */
    private int port;

    /**
     * Database.
     */
    private String database;

    /**
     * Connection to the database.
     */
    private Connection connection;

    // --------------------------------------------------------------------------- Getters & Setters
    // -------------------------------------------------------------------------------- Constructors

    /**
     * Create the jdbc connector with username, password, database hostname server, server port and
     * database.
     *
     * @param username Database username.
     * @param password Database password.
     * @param host     Database server host name.
     * @param port     Database server port.
     * @param database Database.
     */
    public JdbcConnector(String username, String password, String host, int port, String database) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.database = database;
        this.connection = null;
    }


    /**
     * Create the jdbc connector with username, password, database hostname server and database.
     * Using the MySQL default port (3306).
     *
     * @param username Database username.
     * @param password Database password.
     * @param host     Database server host name.
     * @param database Database.
     */
    public JdbcConnector(String username, String password, String host, String database) {
        this(username, password, host, DEFAULT_PORT, database);
    }

    // -------------------------------------------------------------------------------- Help Methods

    /**
     * Get the connection to the database.
     *
     * @param username Database username.
     * @param password Database password.
     * @param host     Database server address.
     * @param port     Database port.
     * @param database Database.
     * @return Database connection.
     * @throws SQLException           Error with the sql server.
     * @throws ClassNotFoundException Jdbc Driver not found.
     */
    protected Connection getDbConnection(String username, String password, String host, int port, String database) throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, username, password);
    }

    // ----------------------------------------------------------------------------- General Methods

    /**
     * Open the connection to the database.
     *
     * @throws SQLException           Error with the sql server.
     * @throws ClassNotFoundException Jdbc Driver not found.
     */
    public void openConnection() throws SQLException, ClassNotFoundException {
        if (this.connection == null) {
            this.connection = getDbConnection(this.username, this.password, this.host, this.port, this.database);
        }
    }

    /**
     * Execute query on database.
     *
     * @param query Query to execute on the database.
     * @return Query result set.
     * @throws SQLException Error with the sql server.
     */
    public ResultSet query(String query) throws SQLException {
        // create the java statement
        Statement st = this.connection.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        st.close();

        return rs;
    }

    /**
     * Execute update query on database.
     *
     * @param update Update to execute on the database.
     * @throws SQLException Error with the sql server.
     */
    public void update(String update) throws SQLException {
        Statement st = this.connection.createStatement();
        st.executeUpdate(update);
    }

    // --------------------------------------------------------------------------- Static Components

}
