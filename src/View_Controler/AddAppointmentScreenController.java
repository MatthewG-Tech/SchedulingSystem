/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.City;
import Model.Customer;
import Utils.AppointmentDataInterface;
import Utils.CentralData;
import Utils.Time;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
    
    
    private Customer selectedCustomer;
    
    private Date selectedTime;
    @FXML
    private TextField locationField;
    @FXML
    private TextField contactField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentIdLabel.setText("Appointment ID: " + (CentralData.getAppointments().size() + 1));
        
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
        Date startTime = selectedTime;
        Date endTime = (Date) selectedTime.clone();
        if(endTime.getMinutes() == 30){
            endTime.setHours(endTime.getHours() + 1);
            endTime.setMinutes(0);
        }else{
            endTime.setMinutes(30);
        }
        Appointment tempAppointment = new Appointment(CentralData.getAppointments().size() + 1, selectedCustomer, CentralData.getUser(), titleField.getText(), descriptionField.getText(), locationField.getText(), contactField.getText(), typeField.getText(), urlField.getText(), startTime, endTime);
        CentralData.addAppointment(tempAppointment);
        AppointmentDataInterface.addAppointment(tempAppointment);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        AppointmentScreenController appointmentScreenController = loader.<AppointmentScreenController>getController();
        stage.show();
        
        System.out.println("Appointment Saved");
    }

    @FXML
    private void selectCustomerButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectCustomerFromAppointment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        SelectCustomerFromAppointmentController selectCustomerFromAppointmentController = loader.<SelectCustomerFromAppointmentController>getController();
        selectCustomerFromAppointmentController.returnToAddScreen();
        stage.show();
    }
    
    public void setCustomer(Customer customer){
        selectedCustomer = customer;
        customerLabel.setText("Customer: "+ customer.getCustomerName());
    }

    @FXML
    private void datePickerAction(ActionEvent event) {
        Date tempTime = Time.convertToDate(datePicker.getValue());
        ArrayList<Date> bussinessHours = Time.getBussinessHours(tempTime);
        selectedTime = null;
        timeDropdown.setText("Pick A Time");
        timeDropdown.getItems().clear();
        for(int i = 0; i < bussinessHours.size(); i++){
            Date temp = bussinessHours.get(i);

            MenuItem choice = new MenuItem();
            timeDropdown.getItems().add(choice);
            String minute = "" + temp.getMinutes();
            if(minute.length() == 1){
                minute = "0" + minute;
            }
            choice.setText(temp.getHours() + ":" + minute);
            //Lambda Function Is used because the number of cities could change in the future and this will not requrie reprograming if more cities are added.
            choice.setOnAction((e)-> {
                String tempMinute = "" + temp.getMinutes();
                if(tempMinute.length() == 1){
                    tempMinute = "0" + tempMinute;
                }
                selectedTime = temp;
                timeDropdown.setText(temp.getHours() + ":" + tempMinute);
            });
        }
        System.out.println("Select Date");
    }
}
