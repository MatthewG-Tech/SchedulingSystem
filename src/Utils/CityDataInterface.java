/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.City;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author matthewguerra
 */
public class CityDataInterface {
    /**
    * Get City By City ID
    * Returns City or null if not found
    */
    public static City getCity(int cityIdInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE cityId  = '" + cityIdInput + "'";
        Query.makeQuery(sqlStatement);
        City userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int cityId = result.getInt("cityId");
             String cityName = result.getString("cityName");
             int countryId = result.getInt("countryId");
             userResult= new City(cityId, cityName, CountryDataInterface.getCountry(countryId));
             return userResult;
        }
        return null;
    }
    /**
    * Get City By City Name
    * Returns City or null if not found
    */
    public static City getCity(String cityNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE cityName  = '" + cityNameInput + "'";
        Query.makeQuery(sqlStatement);
        City userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int cityId = result.getInt("cityId");
             String cityName = result.getString("cityName");
             int countryId = result.getInt("countryId");
             userResult= new City(cityId, cityName, CountryDataInterface.getCountry(countryId));
             return userResult;
        }
        return null;
    }
}
