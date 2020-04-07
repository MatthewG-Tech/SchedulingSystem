/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author matthewguerra
 */
public class Query {
    private static String query;
    private static Statement statement;
    private static ResultSet result;
    private static Connection connection;
    
    public static void setConnection(Connection connectionInput){
        connection = connectionInput;
    }
    
    public static void makeQuery(String queryInput){
        query = queryInput;
        try{
            statement=connection.createStatement();
            // determine query execution
            if(query.toLowerCase().startsWith("select"))
                result=statement.executeQuery(query);
             if(query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update"))
                statement.executeUpdate(query);
            
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    public static ResultSet getResult(){
        return result;
    }
}