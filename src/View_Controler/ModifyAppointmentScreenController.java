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
import java.io.IOException;
import java.net.URL;
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
public class ModifyAppointmentScreenController implements Initializable {

    @FXML
    private Label appointmentIdLabel;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
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
    
    private Appointment selectedAppointment;
    
    private int selectedCityId;
    @FXML
    private TextField contactField;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        /*
        for(int i = 1; i <= CentralData.getCities().size(); i++){
            City temp = CentralData.getCity(i);

            MenuItem choice = new MenuItem(temp.getCityName());
            cityDropdown.getItems().add(choice);
            //Lambda Function Is used because the number of cities could change in the future and this will not requrie reprograming if more cities are added.
            choice.setOnAction((e)-> {
                selectedCityId = temp.getCityId();
                cityDropdown.setText(temp.getCityName());
            });
        }*/
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
        Appointment tempAppointment = new Appointment(CentralData.getAppointments().size() + 1, selectedCustomer, CentralData.getUser(), titleField.getText(), descriptionField.getText(), locationField.getText(), "NEED TO FIX", typeField.getText(), urlField.getText(), new Date(), new Date());
        selectedAppointment.setCustomer(selectedCustomer);
        selectedAppointment.setTitle(titleField.getText());
        selectedAppointment.setDescription(descriptionField.getText());
        selectedAppointment.setLocation(locationField.getText());
        selectedAppointment.setContact(contactField.getText());
        selectedAppointment.setType(typeField.getText());
        selectedAppointment.setUrl(urlField.getText());
        //selectedAppointment.setStartTime();
        //selectedAppointment.setEndTime();
        AppointmentDataInterface.updateAppointment(tempAppointment);
        
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
        selectCustomerFromAppointmentController.returnToModifyScreen();
        stage.show();
    }
    
    public void setCustomer(Customer customer){
        selectedCustomer = customer;
        customerLabel.setText("Customer: "+ customer.getCustomerName());
    }
    
    public void setUp(Appointment appointment){
        selectedAppointment = appointment;
        appointmentIdLabel.setText("Appointment ID: " + selectedAppointment.getAppointmentId());
        titleField.setText(selectedAppointment.getTitle());
        descriptionField.setText(selectedAppointment.getDescritpion());
        typeField.setText(selectedAppointment.getType());
        contactField.setText(selectedAppointment.getContact());
        locationField.setText(selectedAppointment.getLocation());
        urlField.setText(selectedAppointment.getUrl());
        
    }
    
}
