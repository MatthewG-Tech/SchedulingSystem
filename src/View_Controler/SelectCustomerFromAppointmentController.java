/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.Customer;
import Utils.CentralData;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matthewguerra
 */
public class SelectCustomerFromAppointmentController implements Initializable {

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerId;
    @FXML
    private TableColumn<Customer, String> customerName;

    private Boolean returnToAddScreen = true;
    
    private Appointment selectedAppointment;
    private Appointment changedAppointment;
    private LocalDate localDate;
    private String string;
    private ObservableList obserableList;
    private ZonedDateTime time;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.setItems(CentralData.getCustomers());
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        if(returnToAddScreen){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            AddAppointmentScreenController addAppointmentScreenController = loader.<AddAppointmentScreenController>getController();
            addAppointmentScreenController.setUpAfterCustomerPicked(changedAppointment, localDate, string, obserableList, time);
            stage.show();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            ModifyAppointmentScreenController modifyAppointmentScreenController = loader.<ModifyAppointmentScreenController>getController();
            modifyAppointmentScreenController.setUpAfterCustomerPicked(selectedAppointment, changedAppointment, localDate, string, obserableList, time);
            stage.show();
        }
    }

    @FXML
    private void createButtonAction(ActionEvent event) throws IOException {
        if(returnToAddScreen){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            AddAppointmentScreenController addAppointmentScreenController = loader.<AddAppointmentScreenController>getController();
            addAppointmentScreenController.setCustomer(customerTable.getSelectionModel().getSelectedItem());
            addAppointmentScreenController.setUpAfterCustomerPicked(changedAppointment, localDate, string, obserableList, time);
            stage.show();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            ModifyAppointmentScreenController modifyAppointmentScreenController = loader.<ModifyAppointmentScreenController>getController();
            modifyAppointmentScreenController.setCustomer(customerTable.getSelectionModel().getSelectedItem());
            modifyAppointmentScreenController.setUpAfterCustomerPicked(selectedAppointment, changedAppointment, localDate, string, obserableList, time);
            stage.show();
        }
    }
    /**
     * Tell Controller to return to add screen
     */
    public void returnToAddScreen(){
        
    }
    /**
     * Tell Controller to return to modify screen
     */
    public void returnToModifyScreen(){
        returnToAddScreen = false;
    }
    public void setUp(Appointment chagnedAppointment){
        changedAppointment = chagnedAppointment;
    }
    public void setUp(Appointment chagnedAppointment, LocalDate localDateInput, String stringInput, ObservableList obserableListInput, ZonedDateTime timeInput){
        changedAppointment = chagnedAppointment;
        localDate = localDateInput;
        string = stringInput;
        obserableList = obserableListInput;
        time = timeInput;
    }
    public void setUp(Appointment selectedAppointmentInput, Appointment chagnedAppointment, LocalDate localDateInput, String stringInput, ObservableList obserableListInput, ZonedDateTime timeInput){
        selectedAppointment = selectedAppointmentInput;
        changedAppointment = chagnedAppointment;
        localDate = localDateInput;
        string = stringInput;
        obserableList = obserableListInput;
        time = timeInput;
    }
}
