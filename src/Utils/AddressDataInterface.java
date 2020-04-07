/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author matthewguerra
 */
public class AddressDataInterface {
    /**
    * Get Address By Address Id
    * Returns Address or null if not found
    */
    public static Address getAddress(int addressIdInput) throws SQLException, Exception{
        String sqlStatement="select * FROM address WHERE addressId  = '" + addressIdInput + "'";
        Query.makeQuery(sqlStatement);
        Address addressResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int addressId = result.getInt("addressId");
            String address = result.getString("address");
            String address2 = result.getString("address2");
            int cityId = result.getInt("cityId");
            String postalCode = result.getString("postalCode");
            String phone = result.getString("phone");
            
            addressResult= new Address(addressId, address, address2,  CentralData.getCity(cityId), postalCode, phone);
            return addressResult;
        }
        return null;
    }
    /**
    * Get Address By Address Name
    * Returns Address or null if not found
    */
    public static Address getAddress(String addressNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM address WHERE addressName  = '" + addressNameInput + "'";
        Query.makeQuery(sqlStatement);
        Address addressResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int addressId = result.getInt("addressId");
            String address = result.getString("address");
            String address2 = result.getString("address2");
            int cityId = result.getInt("cityId");
            String postalCode = result.getString("postalCode");
            String phone = result.getString("phone");
            
            addressResult= new Address(addressId, address, address2, CentralData.getCity(cityId), postalCode, phone);
            return addressResult;
        }
        return null;
    }
    /**
    * Get All Addresses
    * Returns all Addresses in observable list
    */
    public static ObservableList<Address> getAllAddresses() throws SQLException, Exception{
        ObservableList<Address> addressList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM address";
        Query.makeQuery(sqlStatement);
        Address addressResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int addressId = result.getInt("addressId");
            String address = result.getString("address");
            String address2 = result.getString("address2");
            int cityId = result.getInt("cityId");
            String postalCode = result.getString("postalCode");
            String phone = result.getString("phone");
             
            addressResult = new Address(addressId, address, address2, CentralData.getCity(cityId), postalCode, phone);
            addressList.add(addressResult);
        }
        return addressList;
    }
}
