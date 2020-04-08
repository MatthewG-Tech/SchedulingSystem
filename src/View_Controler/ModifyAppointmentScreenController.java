/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
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
    private SplitMenuButton cityDropdown;
    @FXML
    private TextField urlField;
    @FXML
    private TextField endField;
    @FXML
    private TextField startField;
    @FXML
    private Label customerLabel;
    
    private Customer selectedCustomer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            AppointmentScreenController appointmentScreenController = loader.<AppointmentScreenController>getController();
            stage.show();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
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
        
    }
    
}
