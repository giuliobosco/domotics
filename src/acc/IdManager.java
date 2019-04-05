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

import java.sql.*;

/**
 *
 * @author guebe
 */
public class IdManager {

    /**
     * This method connects to the database
     * @param user User to log in
     * @param pass Password to log in
     * @param databaseURL Url of the database e.g.:"jdbc:msql://localhost:3306/Demo"
     * @return the connection to the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection connectDB(String user, String pass, String databaseURL) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(databaseURL, user, pass);
        return conn;
    }

    /**
     * This method does a query on the database
     * @param c Connection to the database
     * @param query Query to execute
     * @return Returns the query
     * @throws SQLException
     */
    public ResultSet query(Connection c, String query) throws SQLException{
        // create the java statement
        Statement st = c.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        st.close();

        return rs;
    }

    /**
     * This method does an update on the database
     * @param c Connection to the database
     * @param update Update to execute
     * @throws SQLException
     */
    public void update(Connection c, String update) throws SQLException{
        Statement st = c.createStatement();
        st.executeUpdate(update);
    }

    /**
     * This method controls if the arduiino's ip is changed
     * @param c Connection to the database
     * @param id Arduino's id
     * @param ip Arduino's ip
     * @throws SQLException
     */
    public void checkIp(Connection c, String id, String ip) throws SQLException{
        ResultSet rs = query(c, "SELECT ip from arduino where client_id="+id);
        if(ip != rs.getString("ip")){
            update(c,"update arduino set ip ="+ip+" where client_id="+id);
        }
    }

    /**
     * This method checks if the key exists, if it doesn't exists it creates one
     * @param c Connection to the database
     * @param id Arduino's id
     * @return returns the key
     * @throws SQLException
     */
    public String checkKey(Connection c, String id) throws SQLException{
        ResultSet rs = query(c, "SELECT ip from arduino where client_id="+id);
        if(rs.getString("client_key")!= null){
            return rs.getString("client_key");
        }
        ResultSet ds = query(c, "SELECT ip from arduino where client_id="+id);
        boolean flag = true;
        String key = "";
        while(flag){
            key = createKey();
            flag = false;
            while(ds.next()){
                if(ds.getString("client_key").equals(key)){
                    flag = true;
                }
            }
        }
        return key;
    }

    /**
     * This method creates a random hexadecimal key for the Arduino
     * @return Hexadecimal key
     */
    public String createKey(){
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String result = "";
        for(int i = 0; i < 12; i++){
            int rand = (int)(Math.random()*16);
            result += hex[rand];
        }
        return result;
    }
}
