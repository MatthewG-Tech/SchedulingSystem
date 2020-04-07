/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author matthewguerra
 */
public class DBConnection {
    
    
    // JDBC URL
    private static final String protocall = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U07HB5";
    
    private static final String jdbcURL = protocall + vendorName + ipAddress;
    
    // Driver Interface Link
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    
    private static final String username = "U07HB5";
    private static final String password = "53689025285";
    
    public static Connection startConnection(){
        try{
            Class.forName(MYSQLJDBCDriver);
            connection = (Connection)DriverManager.getConnection(jdbcURL, username, password);
            Query.setConnection(connection);
            System.out.println("Connection successfull");
        }catch(ClassNotFoundException e){
            System.out.println("Error" + e.getMessage());
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }
        return connection;
    }
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection closed");
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }
    }
}
