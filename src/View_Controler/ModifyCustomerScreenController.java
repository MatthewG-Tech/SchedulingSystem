/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Address;
import Model.City;
import Model.Customer;
import Utils.AddressDataInterface;
import Utils.CentralData;
import Utils.CustomerDataInterface;
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
public class ModifyCustomerScreenController implements Initializable {

    @FXML
    private Label customerIdLabel;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField address2Field;
    @FXML
    private SplitMenuButton cityDropdown;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;
    
    private Customer selectedCustomer;
    
    private int selectedCityId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for(int i = 1; i <= CentralData.getCities().size(); i++){
            City temp = CentralData.getCity(i);

            MenuItem choice = new MenuItem(temp.getCityName());
            cityDropdown.getItems().add(choice);
            //Lambda Function Is used because the number of cities could change in the future and this will not requrie reprograming if more cities are added.
            choice.setOnAction((e)-> {
                selectedCityId = temp.getCityId();
                cityDropdown.setText(temp.getCityName());
            });
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        System.out.println("Modify Customer Exit");
        if (result.get() == ButtonType.OK){  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            CustomerScreenController customerScreenController = loader.<CustomerScreenController>getController();
            //appointmentScreenController.setUp(partToBeModifiedIndex,temp);
            stage.show();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws Exception {
        Address tempAddress = selectedCustomer.getAddress();
        tempAddress.setAddress(addressField.getText());
        tempAddress.setAddress2(address2Field.getText());
        tempAddress.setCity(CentralData.getCity(selectedCityId));
        tempAddress.setPostalCode(postalCodeField.getText());
        tempAddress.setPhone(phoneField.getText());
        selectedCustomer.setCustomerName(customerNameField.getText());
        selectedCustomer.setAddress(tempAddress);

        AddressDataInterface.updateAddress(tempAddress);
        CustomerDataInterface.updateCustomer(selectedCustomer);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        CustomerScreenController customerScreenController = loader.<CustomerScreenController>getController();
        stage.show();
        
        System.out.println("Customer Saved");
    }
    
    public void setUp(Customer customer){
        selectedCustomer = customer;
        selectedCityId = customer.getAddress().getCity().getCityId();
        customerIdLabel.setText("Customer ID: " + selectedCustomer.getCustomerId());
        customerNameField.setText(selectedCustomer.getCustomerName());
        addressField.setText(selectedCustomer.getAddress().getAddress());
        address2Field.setText(selectedCustomer.getAddress().getAddress2());
        postalCodeField.setText(selectedCustomer.getAddress().getPostalCode());
        phoneField.setText(selectedCustomer.getAddress().getPhone());
        cityDropdown.setText(selectedCustomer.getAddress().getCity().getCityName());
        System.out.println(selectedCustomer);
    }
    
}
