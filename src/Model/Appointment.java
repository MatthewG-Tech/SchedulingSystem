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
public class Appointment {
    
    private int appointmentId;
    private Customer customer;
    private User user;
    private String title;
    private String description;
    private String location;
    
    public Appointment(int appointmentId, Customer customer, User user, String title, String description, String location){
        this.appointmentId = appointmentId;
        this.customer = customer;
        this.user = user;
        this.title = title;
        this.description = description;
        this.location = location;
    }
    
    public int getAppointmentId(){
        return appointmentId;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public User getUser(){
        return user;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getDescritpion(){
        return description;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Appointment){
            Appointment comparedAppointment = (Appointment)o;
            if(this.appointmentId == comparedAppointment.getAppointmentId()){
                if(this.customer.equals(comparedAppointment.getCustomer())){
                    if(this.user.equals(comparedAppointment.getUser())){
                        if(this.title.equals(comparedAppointment.getTitle())){
                            if(this.description.equals(comparedAppointment.getDescritpion())){
                                if(this.location.equals(comparedAppointment.getLocation())){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Appointment ID: " + this.appointmentId + " Customer: " + this.customer + " User: " + this.user  + " Title: " + this.title + " Description: " + this.description  + " Location: " + this.location;
    }
}
