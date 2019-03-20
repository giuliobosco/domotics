package acc;

import java.sql.*;

public class JDBCApp {
    public static void main(String[] args) {
        try {
            System.out.println("c");
            String query = "SELECT * FROM luogo";
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/esercizio1", "root", "rooot");

            System.out.println("a");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            rs.next();
            String sname = rs.getString(1);
            System.out.println(sname);
            System.out.println("c");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {

        }

    }
}