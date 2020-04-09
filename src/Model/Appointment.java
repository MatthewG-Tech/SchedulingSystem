/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;
import java.util.Date;

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
    private String contact;
    private String type;
    private String url;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public Appointment(int appointmentId, Customer customer, User user, String title, String description, String location, String contact, String type, String url, LocalDateTime startTime, LocalDateTime endTime){
        this.appointmentId = appointmentId;
        this.customer = customer;
        this.user = user;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
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
    
    public String getContact(){
        return contact;
    }
    
    public String getType(){
        return type;
    }
    
    public String getUrl(){
        return url;
    }
    
    public LocalDateTime getStartTime(){
        return startTime;
    }
    
    public LocalDateTime getEndTime(){
        return endTime;
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
    
    public void setContact(String contact){
        this.contact = contact;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
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
                                    if(this.contact.equals(comparedAppointment.getContact())){
                                        if(this.type.equals(comparedAppointment.getType())){
                                            if(this.url.equals(comparedAppointment.getUrl())){
                                                if(this.startTime.equals(comparedAppointment.getStartTime())){
                                                    if(this.endTime.equals(comparedAppointment.getEndTime())){
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
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
        return "Appointment ID: " + this.appointmentId + " Customer: " + this.customer + " User: " + this.user  + " Title: " + this.title + " Description: " + this.description  + " Location: " + this.location  + " Location: " + this.contact  + " Location: " + this.type  + " Location: " + this.url  + " Location: " + this.startTime  + " Location: " + this.endTime;
    }
}
