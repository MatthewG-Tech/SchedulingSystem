/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author matthewguerra
 */
public class AppointmentDataInterface {
    /**
    * Get User By User Name
    * Returns User or null if not found
    
    public static User getUser(String userNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE userName  = '" + userNameInput + "'";
        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int userId = result.getInt("userId");
             String userName = result.getString("userName");
             String password = result.getString("password");
             userResult= new User(userId, userName, password);
             return userResult;
        }
        return null;
    }*/
}
