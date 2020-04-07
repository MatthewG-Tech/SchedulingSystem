/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        String sqlStatement = "select * FROM country WHERE countryId  = '" + countryIdInput + "'";
        Query.makeQuery(sqlStatement);
        Country countryResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int countryId = result.getInt("countryId");
             String countryName = result.getString("country");
             countryResult= new Country(countryId, countryName);
             return countryResult;
        }
        return null;
    }
    /**
    * Get Country By Country Name
    * Returns Country or null if not found
    */
    public static Country getCountry(String countryNameInput) throws SQLException, Exception{
        String sqlStatement = "select * FROM country WHERE countryName  = '" + countryNameInput + "'";
        Query.makeQuery(sqlStatement);
        Country countryResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int countryId = result.getInt("countryId");
             String countryName = result.getString("country");
             countryResult= new Country(countryId, countryName);
             return countryResult;
        }
        return null;
    }
    /**
    * Get All Customers
    * Returns All Customers in observable list
    */
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception{
        ObservableList<Country> customerList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM country";
        Query.makeQuery(sqlStatement);
        Country countryResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int countryId = result.getInt("countryId");
             String countryName = result.getString("country");
             countryResult = new Country(countryId, countryName);
             customerList.add(countryResult);
        }
        return customerList;
    }
}
