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
import java.time.LocalDateTime;
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
        String sqlStatement="select * FROM appointment WHERE appointmentId = " + appointmentIdInput + "";
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
            LocalDateTime startTime = Time.convertToLocalDateTime(result.getString("start"));
            LocalDateTime endTime = Time.convertToLocalDateTime(result.getString("end"));
            //Date startTime = Time.convertToDate(result.getString("start"));
            //Date endTime = Time.convertToDate(result.getString("end"));
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
            LocalDateTime startTime = Time.convertToLocalDateTime(result.getString("start"));
            LocalDateTime endTime = Time.convertToLocalDateTime(result.getString("end"));
            //Date startTime = Time.convertToDate(result.getString("start"));
            //Date endTime = Time.convertToDate(result.getString("end"));
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
            LocalDateTime startTime = Time.convertToLocalDateTime(result.getString("start"));
            LocalDateTime endTime = Time.convertToLocalDateTime(result.getString("end"));
            //Date startTime = Time.convertToDate(result.getString("start"));
            //Date endTime = Time.convertToDate(result.getString("end"));
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
            LocalDateTime startTime = Time.convertToLocalDateTime(result.getString("start"));
            LocalDateTime endTime = Time.convertToLocalDateTime(result.getString("end"));
            //Date startTime = Time.convertToDate(result.getString("start"));
            //Date endTime = Time.convertToDate(result.getString("end"));
            userResult= new Appointment(appointmentId, CustomerDataInterface.getCustomer(customerId), UserDataInterface.getUser(userId), title, description, location, contact, type, url, startTime, endTime);
            customerList.add(userResult);
        }
        return customerList;
    }
    /**
    * Add Appointment
    * Input Appointment
    */
    public static void addAppointment(Appointment appointmentInput) throws SQLException, Exception{
        int appointmentId = appointmentInput.getAppointmentId();
        int customerId = appointmentInput.getCustomer().getCustomerId();
        int userId = appointmentInput.getUser().getUserId();
        String title = appointmentInput.getTitle();
        String description = appointmentInput.getDescritpion();
        String location = appointmentInput.getLocation();
        String contact = appointmentInput.getContact();
        String type = appointmentInput.getType();
        String url = appointmentInput.getUrl();
        String start = Time.converToDateTimeFormat(appointmentInput.getStartTime());
        String end = Time.converToDateTimeFormat(appointmentInput.getEndTime());
        LocalDateTime temp = LocalDateTime.now();
        String time = Time.converToDateTimeFormat(temp);
        String sqlStatement = "INSERT INTO appointment (appointmentId, customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)\nVALUES (" + appointmentId + ", " + customerId + ", " + userId + ", '" + title + "', '" + description +  "', '" + location +  "', '" + contact +  "', '" + type +  "', '" + url +  "', '" + start +  "', '" + end + "', '" + time + "', '" + CentralData.getUser().getUserName() + "', '" + time + "', '" + CentralData.getUser().getUserName() + "');";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Update Appointment 
    * Input Appointment
    */
    public static void updateAppointment(Appointment appointmentInput) throws SQLException, Exception{
        int appointmentId = appointmentInput.getAppointmentId();
        int customerId = appointmentInput.getCustomer().getCustomerId();
        int userId = appointmentInput.getUser().getUserId();
        String title = appointmentInput.getTitle();
        String description = appointmentInput.getDescritpion();
        String location = appointmentInput.getLocation();
        String contact = appointmentInput.getContact();
        String type = appointmentInput.getType();
        String url = appointmentInput.getUrl();
        String start = Time.converToDateTimeFormat(appointmentInput.getStartTime());
        String end = Time.converToDateTimeFormat(appointmentInput.getEndTime());
        LocalDateTime temp = LocalDateTime.now();
        String time = Time.converToDateTimeFormat(temp);
        String sqlStatement = "UPDATE appointment\nSET appointmentId = " + appointmentId + ", customerId = " + customerId + ", userId = " + userId + ", title = '" + title + "', description = '" + description +  "', location = '" + location +  "', contact = '" + contact +  "', type = '" + type +  "', url = '" + url +  "', start = '" + start +  "', end = '" + end + "', lastUpdate = '" + time + "', lastUpdateBy = '" + CentralData.getUser().getUserName() + "'\nWHERE appointmentId = " + appointmentId + ";";
        Query.makeQuery(sqlStatement);
    }
    /**
    * Delete Appointment
    * Input Appointment Id
    */
    public static void deleteAppointment(int appointmentId) throws SQLException, Exception{
        String sqlStatement = "DELETE FROM appointment WHERE appointmentId = " + appointmentId + ";";
        Query.makeQuery(sqlStatement);
    }
}
