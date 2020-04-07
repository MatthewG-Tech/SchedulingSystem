/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author matthewguerra
 */
public class CustomerDataInterface {
    /**
    * Get Customer By Customer Id
    * Returns Customer or null if not found
    */
    public static Customer getCustomer(int customerIdInput) throws SQLException, Exception{
        String sqlStatement="select * FROM customer WHERE customerId  = '" + customerIdInput + "'";
        Query.makeQuery(sqlStatement);
        Customer userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int customerId = result.getInt("customerId");
             String customerName = result.getString("customerName");
             int addressId = result.getInt("addressId");
             int activeInt = result.getInt("active");
             Boolean active = false;
             if(activeInt == 1){
                 active = true;
             }
             userResult= new Customer(customerId, customerName, AddressDataInterface.getAddress(addressId), active);
             return userResult;
        }
        return null;
    }
    /**
    * Get Customer By Customer Name
    * Returns Customer or null if not found
    */
    public static Customer getCustomer(String customerNameInput) throws SQLException, Exception{
        String sqlStatement="select * FROM customer WHERE customerName  = '" + customerNameInput + "'";
        Query.makeQuery(sqlStatement);
        Customer userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
             int customerId = result.getInt("customerId");
             String customerName = result.getString("customerName");
             int addressId = result.getInt("addressId");
             int activeInt = result.getInt("active");
             Boolean active = false;
             if(activeInt == 1){
                 active = true;
             }
             userResult= new Customer(customerId, customerName, AddressDataInterface.getAddress(addressId), active);
             return userResult;
        }
        return null;
    }
}
