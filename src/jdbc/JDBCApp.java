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
 * Test program for connect with JDBC Driver to the mysql domotics database.
 *
 * @author paologuebeli
 * @author giuliobosco
 * @version 1.1 (2019-03-01 - 2019-03-23)
 */
public class JDBCApp {

    /**
     * Main method of the class, execute query on db.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // query to execute on db
            String query = "SELECT * FROM dati";
            // load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create connection on db
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/domotics", "root", "root");

            // create statement for query
            Statement st = con.createStatement();
            // execute query and load result in result set
            ResultSet rs = st.executeQuery(query);

            // for each row in result set
            while (rs.next()) {
                // print the name
                String sname = rs.getString("nome");
                System.out.println(sname);
            }

            // close connection
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {

        }

    }
}
