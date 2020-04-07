/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author matthewguerra
 */
public class Customer {
    
    private int customerId;
    private String customerName;
    private Address address;
    private Boolean active;
    
    public Customer(int customerId, String customerName, Address address, Boolean active){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.active = active;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public Address getAddress(){
        return address;
    }
    
    public Boolean getActive(){
        return active;
    }
    
    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }
    
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    
    public void setAddress(Address address){
        this.address = address;
    }
    
    public void setActive(Boolean active){
        this.active = active;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Customer){
            Customer comparedCustomer = (Customer)o;
            if(this.customerId == comparedCustomer.getCustomerId()){
                if(this.customerName.equals(comparedCustomer.getCustomerName())){
                    if(this.address.equals(comparedCustomer.getAddress())){
                        if(this.active.equals(comparedCustomer.getActive())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Customer ID: " + this.customerId + " Customer Name: " + this.customerName + " Address: " + this.address + " Active: " + this.active;
    }
}
