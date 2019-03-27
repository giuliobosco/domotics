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
