/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sqlStatement="select * FROM user WHERE addressId  = '" + addressIdInput + "'";
        Query.makeQuery(sqlStatement);
        Address userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int addressId = result.getInt("addressId");
             String address = result.getString("address");
             String address2 = result.getString("address2");
             String cityId = result.getString("cityId");
             String postalCode = result.getString("postalCode");
             String phone = result.getString("phone");
             
             userResult= new Address(addressId, address, address2, CityDataInterface.getCity(cityId), postalCode, phone);
             return userResult;
        }
        return null;
    }
    /**
    * Get Address By Address Name
    * Returns Address or null if not found
    */
    public static Address getAddress(String addressNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM user WHERE addressName  = '" + addressNameInput + "'";
        Query.makeQuery(sqlStatement);
        Address userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int addressId = result.getInt("addressId");
             String address = result.getString("address");
             String address2 = result.getString("address2");
             String cityId = result.getString("cityId");
             String postalCode = result.getString("postalCode");
             String phone = result.getString("phone");
             
             userResult= new Address(addressId, address, address2, CityDataInterface.getCity(cityId), postalCode, phone);
             return userResult;
        }
        return null;
    }
}
