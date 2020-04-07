/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author matthewguerra
 */
public class CountryDataInterface {
    /**
    * Get Country By Country ID
    * Returns Country or null if not found
    */
    public static Country getCountry(int countryIdInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE countryId  = '" + countryIdInput + "'";
        Query.makeQuery(sqlStatement);
        Country userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int countryId = result.getInt("countryId");
             String countryName = result.getString("countryName");
             userResult= new Country(countryId, countryName);
             return userResult;
        }
        return null;
    }
    /**
    * Get Country By Country Name
    * Returns Country or null if not found
    */
    public static Country getCountry(String countryNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE countryName  = '" + countryNameInput + "'";
        Query.makeQuery(sqlStatement);
        Country userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int countryId = result.getInt("countryId");
             String countryName = result.getString("countryName");
             userResult= new Country(countryId, countryName);
             return userResult;
        }
        return null;
    }
}
