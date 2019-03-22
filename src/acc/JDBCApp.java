package acc;

import java.sql.*;

public class JDBCApp {
    public static void main(String[] args) {
        try {
            String query = "SELECT * FROM dati";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/domotics", "root", "root");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
           	String sname = rs.getString("nome");
            	System.out.println(sname);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {

        }

    }
}