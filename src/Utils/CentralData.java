/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Address;
import Model.Appointment;
import Model.City;
import Model.Country;
import Model.Customer;
import Model.User;
import javafx.collections.ObservableList;

/**
 *
 * @author matthewguerra
 */
public class CentralData {
    private static User user;
    private static ObservableList<Appointment> userAppointments;
    private static ObservableList<Customer> customers;
    private static ObservableList<Address> addresses;
    private static ObservableList<City> cities;
    private static ObservableList<Country> countries;
    
    /**
    * Set User
    * 
    */
    public static void setUser(User userInput){
        user = userInput;
    }
    /**
    * Set User Appointments
    * 
    */
    public static void setUserAppointments(ObservableList<Appointment> userAppointmentsInput){
        userAppointments = userAppointmentsInput;
    }
    /**
    * Set Customers
    * 
    */
    public static void setCutomers(ObservableList<Customer> customersInput){
        customers = customersInput;
    }
    /**
    * Set Addresses
    * 
    */
    public static void setAddressess(ObservableList<Address> adddressesInput){
        addresses = adddressesInput;
    }
    /**
    * Set Cities
    * 
    */
    public static void setCities(ObservableList<City> citiesInput){
        cities = citiesInput;
    }
    /**
    * Set Countries
    * 
    */
    public static void setCountries(ObservableList<Country> countriesInput){
        countries = countriesInput;
    }
    /**
    * Add User Appointment
    * 
    */
    public static void addUserAppointment(Appointment appointmentInput){
        userAppointments.add(appointmentInput);
    }
    /**
    * Add Customer
    * 
    */
    public static void addCustomer(Customer customerInput){
        customers.add(customerInput);
    }
    /**
    * Add Address
    * 
    */
    public static void addAddress(Address addressInput){
        addresses.add(addressInput);
    }
    /**
    * Remove User Appointment
    * 
    */
    public static void removeUserAppointment(Appointment appointmentIndex){
        userAppointments.remove(appointmentIndex);
    }
    /**
    * Remove Customer
    * 
    */
    public static void removeCustomer(Customer customerIndex){
        customers.remove(customerIndex);
    }
    /**
    * Remove Address
    * 
    */
    public static void removeAddress(Address addressIndex){
        addresses.remove(addressIndex);
    }
    /**
    * Get User
    * 
    */
    public static User getUser(){
        return user;
    }
    /**
    * Get User Appointments
    * 
    */
    public static ObservableList<Appointment> getUserAppointments(){
        return userAppointments;
    }
    /**
    * Get Customers
    * 
    */
    public static ObservableList<Customer> getCustomers(){
        return customers;
    }
    /**
    * Get Customer by Id
    * 
    */
    public static Customer getCustomer(int id){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerId() == id){
                return customers.get(i);
            }
        }
        return null;
    }
    /**
    * Get Addresses
    * 
    */
    public static ObservableList<Address> getAddresses(){
        return addresses;
    }
    /**
    * Get Addresses by Id
    * 
    */
    public static Address getAddress(int id){
        for(int i = 0; i < addresses.size(); i++){
            if(addresses.get(i).getAddressId() == id){
                return addresses.get(i);
            }
        }
        return null;
    }
    /**
    * Get Cities
    * 
    */
    public static ObservableList<City> getCities(){
        return cities;
    }
    /**
    * Get City by Id
    * 
    */
    public static City getCity(int id){
        for(int i = 0; i < cities.size(); i++){
            if(cities.get(i).getCityId() == id){
                return cities.get(i);
            }
        }
        return null;
    }
    /**
    * Get Countries
    * 
    */
    public static ObservableList<Country> getCountries(){
        return countries;
    }
    /**
    * Get Country by Id
    * 
    */
    public static Country getCounty(int id){
        for(int i = 0; i < countries.size(); i++){
            if(countries.get(i).getCountryId() == id){
                return countries.get(i);
            }
        }
        return null;
    }
}
