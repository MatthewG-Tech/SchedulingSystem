/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Customer;
import Utils.Time;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        String sqlStatement = "select * FROM customer WHERE customerId  = '" + customerIdInput + "'";
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
        String sqlStatement = "select * FROM customer WHERE customerName  = '" + customerNameInput + "'";
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
    * Get All Customers
    * Returns All Customers in observable list
    */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM customer";
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
             customerList.add(userResult);
        }
        return customerList;
    }
    /**
    * Add Customer
    * Input Customer Id, Customer Name, Address Id, Active Boolean
    */
    public static void addCustomer(Customer customer) throws SQLException, Exception{
        int customerId = customer.getCustomerId();
        String customerName = customer.getCustomerName();
        int addressId = customer.getAddress().getAddressId();
        Boolean active = customer.getActive();
        int activeInt = 0;
        if(active){
            activeInt = 1;
        }
        Date temp = new Date();
        String time = Time.converToDateTimeFormat(temp);
        String sqlStatement = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)\nVALUES (" + customerId + ", '" + customerName +  "', " + addressId +  ", " + activeInt + ", '" + time + "', '" + CentralData.getUser().getUserName() + "', '" + time + "', '" + CentralData.getUser().getUserName() + "');";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Update Customer Name
    * Input Customer Id, New Customer Name
    */
    public static void updateCustomerName(int customerId, String newCustomerName) throws SQLException, Exception{
        String sqlStatement = "UPDATE customer\nSET customerName = '" + newCustomerName + "\nWHERE customerId = " + customerId + ";";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Update Customer Address
    * Input Customer Id, New Address Id
    */
    public static void updateCustomerAddress(int customerId, int newAddressId) throws SQLException, Exception{
        String sqlStatement = "UPDATE customer\nSET addressId = " + newAddressId + "\nWHERE customerId = " + customerId + ";";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Update Customer Address
    * Input Customer Id, New Address Id
    */
    public static void updateCustomerActive(int customerId, Boolean newActive) throws SQLException, Exception{
        int activeInt = 0;
        if(newActive){
            activeInt = 1;
        }
        String sqlStatement = "UPDATE customer\nSET active = " + activeInt + "\nWHERE customerId = " + customerId + ";";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Delete Customer
    * Input Customer Id
    */
    public static void deleteCustomer(int customerId) throws SQLException, Exception{
        String sqlStatement = "DELETE FROM customer WHERE customerId = " + customerId + ";";
        Query.makeQuery(sqlStatement);
    }
}
