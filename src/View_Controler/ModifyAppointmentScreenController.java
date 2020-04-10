/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.City;
import Model.Customer;
import Model.User;
import Utils.AppointmentDataInterface;
import Utils.CentralData;
import Utils.Time;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matthewguerra
 */
public class ModifyAppointmentScreenController implements Initializable {

    @FXML
    private Label appointmentIdLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField urlField;
    @FXML
    private Label customerLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private SplitMenuButton timeDropdown;
    @FXML
    private TextField contactField;
    
    private Customer selectedCustomer;
    
    private Appointment selectedAppointment;
    
    private ZonedDateTime selectedTime;
    @FXML
    private Label locationLabel;
    
    private String location = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void cancelButtonAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        System.out.println("Add Customer Exit");
        if (result.get() == ButtonType.OK){  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            AppointmentScreenController appointmentScreenController = loader.<AppointmentScreenController>getController();
            stage.show();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws Exception {
        try{
            validateInput(selectedCustomer, titleField.getText(), descriptionField.getText(), contactField.getText(), typeField.getText(), urlField.getText());
            ZonedDateTime startTime = selectedTime;
            ZonedDateTime endTime = selectedTime.plusMinutes(30);
            selectedAppointment.setCustomer(selectedCustomer);
            selectedAppointment.setTitle(titleField.getText());
            selectedAppointment.setDescription(descriptionField.getText());
            selectedAppointment.setLocation(location);
            selectedAppointment.setContact(contactField.getText());
            selectedAppointment.setType(typeField.getText());
            selectedAppointment.setUrl(urlField.getText());
            selectedAppointment.setStartTime(startTime);
            selectedAppointment.setEndTime(endTime);
            AppointmentDataInterface.updateAppointment(selectedAppointment);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            AppointmentScreenController appointmentScreenController = loader.<AppointmentScreenController>getController();
            stage.show();
        }catch(IllegalArgumentException e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(e.getMessage()+"");
            alert.showAndWait();
        }  
        
        System.out.println("Appointment Saved");
    }

    @FXML
    private void selectCustomerButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectCustomerFromAppointment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        SelectCustomerFromAppointmentController selectCustomerFromAppointmentController = loader.<SelectCustomerFromAppointmentController>getController();
        selectCustomerFromAppointmentController.returnToModifyScreen();
        Appointment tempAppointment = new Appointment(selectedAppointment.getAppointmentId(), selectedCustomer, CentralData.getUser(), titleField.getText(), descriptionField.getText(), location, contactField.getText(), typeField.getText(), urlField.getText(), null, null);
        selectCustomerFromAppointmentController.setUp(selectedAppointment, tempAppointment, datePicker.getValue(), timeDropdown.getText(), timeDropdown.getItems(), selectedTime);
        stage.show();

    }
    
    public void setCustomer(Customer customer){
        selectedCustomer = customer;
        customerLabel.setText("Customer: "+ customer.getCustomerName());
        location = customer.getAddress().getCity().getCityName();
        locationLabel.setText("Location: " + location);
        timeDropdown.setText("Pick A Time");
    }
    
    public void setUp(Appointment appointment){
        selectedAppointment = appointment;
        appointmentIdLabel.setText("Appointment ID: " + appointment.getAppointmentId());
        selectedCustomer = appointment.getCustomer();
        customerLabel.setText("Customer: "+ selectedCustomer.getCustomerName());
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescritpion());
        typeField.setText(appointment.getType());
        contactField.setText(appointment.getContact());
        location = appointment.getLocation();
        locationLabel.setText("Locaiton: " + appointment.getLocation());
        urlField.setText(appointment.getUrl());
        datePicker.setValue(selectedAppointment.getStartTime().toLocalDate());
        selectedTime = selectedAppointment.getStartTime();
        String minute = "" + selectedTime.getMinute();
        int hour = selectedTime.getHour();
        String ending = "AM";
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        if(hour > 11){
            ending = "PM";
            if(hour > 12){
                hour -= 12;
            }
        }else if(hour == 0){
            hour = 12;
        }
        timeDropdown.setText(hour + ":" + minute + " " + ending);
        setDropdown();
    }
    public void setUpAfterCustomerPicked(Appointment modifyingInput, Appointment changedAppointment, LocalDate localDate, String string, ObservableList observableList, ZonedDateTime time){
        selectedAppointment = modifyingInput;
        appointmentIdLabel.setText("Appointment ID: " + changedAppointment.getAppointmentId());
        if(changedAppointment.getTitle() != null){
            titleField.setText(changedAppointment.getTitle());
        }
        if(changedAppointment.getDescritpion() != null){
            descriptionField.setText(changedAppointment.getDescritpion());
        }
        if(changedAppointment.getType() != null){
            typeField.setText(changedAppointment.getType());
        }
        if(changedAppointment.getContact() != null){
            contactField.setText(changedAppointment.getContact());
        }
        if(changedAppointment.getUrl() != null){
            urlField.setText(changedAppointment.getUrl());
        }
    }
    @FXML
    private void datePickerAction(ActionEvent event) {
        timeDropdown.setText("Pick A Time");
        selectedTime = null;
        setDropdown();
        System.out.println("Select Time");
    }
    
    private void setDropdown(){
        LocalDate localDate = datePicker.getValue();
        String zoneId = "";
        if(location.equals("London")){
            zoneId = "Europe/London";
        }else if(location.equals("New York")){
            zoneId = "US/Eastern";
        }else if(location.equals("Phoenix")){
            zoneId = "America/Phoenix";
        }
        ArrayList<ZonedDateTime> bussinessHours = Time.getBussinessHours(localDate, zoneId);
        timeDropdown.getItems().clear();
        for(int i = 0; i < bussinessHours.size(); i++){
            ZonedDateTime tempLocalDateTime = bussinessHours.get(i);

            MenuItem choice = new MenuItem();
            timeDropdown.getItems().add(choice);
            String minute = "" + tempLocalDateTime.getMinute();
            int hour = tempLocalDateTime.getHour();
            String ending = "AM";
            if(minute.length() == 1){
                minute = "0" + minute;
            }
            if(hour > 11){
                ending = "PM";
                if(hour > 12){
                    hour -= 12;
                }
            }else if(hour == 0){
                hour = 12;
            }
            choice.setText(hour + ":" + minute + " " + ending);
            //Lambda Function Is used because the number of times can be changed.
            choice.setOnAction((e)-> {
                String tempMinute = "" + tempLocalDateTime.getMinute();
                int tempHour = tempLocalDateTime.getHour();
                String tempEnding = "AM";
                if(tempMinute.length() == 1){
                    tempMinute = "0" + tempMinute;
                }
                if(tempHour > 11){
                    tempEnding = "PM";
                    if(tempHour > 12){
                        tempHour -= 12;
                    }
                }else if(tempHour == 0){
                    tempHour = 12;
                }
                selectedTime = tempLocalDateTime;
                timeDropdown.setText(tempHour + ":" + tempMinute + " " + tempEnding);
            });
        }
    }
    public Boolean validateInput(Customer customer, String title, String description, String location, String contact, String type, String url, ZonedDateTime startTime, ZonedDateTime endTime){
        return true;
    }
    public Boolean validateInput(Customer customer, String title, String description, String contact, String type, String url){
        if(customer == null){
            throw new IllegalArgumentException("You must select a customer");
        }
        if(title.equals("")){
            throw new IllegalArgumentException("You must enter a title");
        }
        if(description.equals("")){
            throw new IllegalArgumentException("You must enter a description");
        }
        if(contact.equals("")){
            throw new IllegalArgumentException("You must enter a contact");
        }
        if(type.equals("")){
            throw new IllegalArgumentException("You must enter a type");
        }
        if(url.equals("")){
            throw new IllegalArgumentException("You must enter a url");
        }
        if(selectedTime == null){
            throw new IllegalArgumentException("You must select a date and time");
        }
        if(selectedTime.isBefore(ZonedDateTime.now())){
            throw new IllegalArgumentException("You must select a date and time that is after the current time");
        }
        if(!userHasAnotherApointmentAtThisTime(selectedTime)){
            throw new IllegalArgumentException("The consultant already has an appointment booked at this time");
        }
        if(!customerHasAnotherApointmentAtThisTime(selectedTime, customer)){
            throw new IllegalArgumentException("The customer already has an appointment booked at this time");
        }
        return true;
    }
    public Boolean userHasAnotherApointmentAtThisTime(ZonedDateTime time){
        ObservableList<Appointment> appointments = CentralData.getAppointments();
        for(int i = 0; i<appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getStartTime().equals(time) && appointment.getUser().equals(selectedAppointment.getUser()) && appointment.getAppointmentId() != selectedAppointment.getAppointmentId()){
                return false;
            }
        }
        return true;
    }
    public Boolean customerHasAnotherApointmentAtThisTime(ZonedDateTime time, Customer customer){
        ObservableList<Appointment> appointments = CentralData.getAppointments();
        for(int i = 0; i<appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getStartTime().equals(time) && appointment.getCustomer().equals(customer) && appointment.getAppointmentId() != selectedAppointment.getAppointmentId()){
                return false;
            }
        }
        return true;
    }
}
