/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Appointment;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author matthewguerra
 */
public class AppointmentDataInterface {
    /**
    * Get Appointment By Appointment Id
    * Returns Appointment or null if not found
    */
    public static Appointment getAppointment(int appointmentIdInput) throws SQLException, Exception{
        String sqlStatement="select * FROM appointment WHERE appointmentId  = " + appointmentIdInput + "";
        Query.makeQuery(sqlStatement);
        Appointment userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userId = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            Date startTime = result.getDate("startTime");
            Date endTime = result.getDate("endTime");
            userResult= new Appointment(appointmentId, CustomerDataInterface.getCustomer(customerId), UserDataInterface.getUser(userId), title, description, location, contact, type, url, startTime, endTime);
            return userResult;
        }
        return null;
    }
    /**
    * Get Appointment By Appointment Title
    * Returns Appointment or null if not found
    */
    public static Appointment getAppointment(String appointmentTitleInput) throws SQLException, Exception{
        String sqlStatement="select * FROM appointment WHERE title  = '" + appointmentTitleInput + "'";
        Query.makeQuery(sqlStatement);
        Appointment userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userId = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            Date startTime = result.getDate("startTime");
            Date endTime = result.getDate("endTime");
            userResult= new Appointment(appointmentId, CustomerDataInterface.getCustomer(customerId), UserDataInterface.getUser(userId), title, description, location, contact, type, url, startTime, endTime);
            return userResult;
        }
        return null;
    }
    /**
    * Get All Appointments
    * Returns all Appointments in observable list
    */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception{
        ObservableList<Appointment> customerList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM appointment";
        Query.makeQuery(sqlStatement);
        Appointment userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userId = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            Date startTime = result.getDate("startTime");
            Date endTime = result.getDate("endTime");
            userResult= new Appointment(appointmentId, CustomerDataInterface.getCustomer(customerId), UserDataInterface.getUser(userId), title, description, location, contact, type, url, startTime, endTime);
            customerList.add(userResult);
        }
        return customerList;
    }
    /**
    * Get All Appointments with user id
    * Returns all Appointments in observable list
    */
    public static ObservableList<Appointment> getAllUserAppointments(int userIdInput) throws SQLException, Exception{
        ObservableList<Appointment> customerList = FXCollections.observableArrayList();
        String sqlStatement = "select * FROM appointment WHERE userId = " + userIdInput + ";";
        Query.makeQuery(sqlStatement);
        Appointment userResult;
        ResultSet result = Query.getResult();
        while(result.next()){
            int appointmentId = result.getInt("appointmentId");
            int customerId = result.getInt("customerId");
            int userId = result.getInt("userId");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String contact = result.getString("contact");
            String type = result.getString("type");
            String url = result.getString("url");
            Date startTime = result.getDate("startTime");
            Date endTime = result.getDate("endTime");
            userResult= new Appointment(appointmentId, CustomerDataInterface.getCustomer(customerId), UserDataInterface.getUser(userId), title, description, location, contact, type, url, startTime, endTime);
            customerList.add(userResult);
        }
        return customerList;
    }
}
