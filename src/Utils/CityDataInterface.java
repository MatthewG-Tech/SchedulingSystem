/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        String sqlStatement="select * FROM city WHERE cityId  = " + cityIdInput + "";
        Query.makeQuery(sqlStatement);
        City cityResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int cityId = result.getInt("cityId");
             String cityName = result.getString("city");
             int countryId = result.getInt("countryId");
             cityResult= new City(cityId, cityName, CountryDataInterface.getCountry(countryId));
             return cityResult;
        }
        return null;
    }
    /**
    * Get City By City Name
    * Returns City or null if not found
    */
    public static City getCity(String cityNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM city WHERE city = '" + cityNameInput + "'";
        Query.makeQuery(sqlStatement);
        City cityResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int cityId = result.getInt("cityId");
             String cityName = result.getString("city");
             int countryId = result.getInt("countryId");
             cityResult= new City(cityId, cityName, CountryDataInterface.getCountry(countryId));
             return cityResult;
        }
        return null;
    }
    /**
    * Get All Customers
    * Returns All Customers in observable list
    */
    public static ObservableList<City> getAllCities() throws SQLException, Exception{
        ObservableList<City> customerList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM city";
        Query.makeQuery(sqlStatement);
        City cityResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int cityId = result.getInt("cityId");
             String cityName = result.getString("city");
             int countryId = result.getInt("countryId");
             cityResult= new City(cityId, cityName, CountryDataInterface.getCountry(countryId));
             customerList.add(cityResult);
        }
        return customerList;
    }
}
