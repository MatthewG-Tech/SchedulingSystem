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
import java.time.LocalTime;
import java.time.ZoneId;
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
public class AddAppointmentScreenController implements Initializable {

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
    
    private ZonedDateTime selectedTime;
    @FXML
    private Label locationLabel;
    
    private String location = "";
    
    private int appointmentId = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Appointment> appointments = CentralData.getAppointments();
        for(int i = 0; i < appointments.size(); i++){
            Appointment appointmet = appointments.get(i);
            if(appointmet.getAppointmentId() > appointmentId){
                appointmentId = appointmet.getAppointmentId();
            }
        }
        appointmentIdLabel.setText("Appointment ID: " + (appointmentId + 1));
        
        timeDropdown.setText("Pick A Time");
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
    private void saveButtonAction(ActionEvent event) throws IOException, Exception {
        try{
            validateInput(selectedCustomer, titleField.getText(), descriptionField.getText(), contactField.getText(), typeField.getText(), urlField.getText());
            ZonedDateTime startTime = selectedTime;
            ZonedDateTime endTime = selectedTime.plusMinutes(30);
            Appointment tempAppointment = new Appointment(CentralData.getAppointments().size() + 1, selectedCustomer, CentralData.getUser(), titleField.getText(), descriptionField.getText(), location, contactField.getText(), typeField.getText(), urlField.getText(), startTime, endTime);
            CentralData.addAppointment(tempAppointment);
            AppointmentDataInterface.addAppointment(tempAppointment);

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
        selectCustomerFromAppointmentController.returnToAddScreen();
        Appointment tempAppointment = new Appointment(appointmentId + 1, selectedCustomer, CentralData.getUser(), titleField.getText(), descriptionField.getText(), location, contactField.getText(), typeField.getText(), urlField.getText(), null, null);
        selectCustomerFromAppointmentController.setUp(tempAppointment, datePicker.getValue(), timeDropdown.getText(), timeDropdown.getItems(), selectedTime);
        stage.show();
    }
    
    public void setCustomer(Customer customer){
        if(customer != null){
            selectedCustomer = customer;
            customerLabel.setText("Customer: "+ customer.getCustomerName());
            location = customer.getAddress().getCity().getCityName();
            locationLabel.setText("Location: " + location);
            timeDropdown.setText("Pick A Time");
        }
    }

    @FXML
    private void datePickerAction(ActionEvent event) {
        selectedTime = null;
        timeDropdown.setText("Pick A Time");
        setDropdown();
        System.out.println("Select Time");
    }
    
    public void setUpAfterCustomerPicked(Appointment changedAppointment, LocalDate localDate, String string, ObservableList observableList, ZonedDateTime time){
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
    private void setDropdown(){
        LocalDate localDate = datePicker.getValue();
        if(location.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Times");
            alert.setContentText("You must select a customer first");
            alert.showAndWait();
        }else{
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
        ObservableList<Appointment> appointments = CentralData.getUserAppointments();
        for(int i = 0; i<appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getStartTime().equals(time)){
                return false;
            }
        }
        return true;
    }
    public Boolean customerHasAnotherApointmentAtThisTime(ZonedDateTime time, Customer customer){
        ObservableList<Appointment> appointments = CentralData.getAppointments();
        for(int i = 0; i<appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getStartTime().equals(time) && appointment.getCustomer().equals(customer)){
                return false;
            }
        }
        return true;
    }
}
