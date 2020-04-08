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
public class AddCustomerScreenController implements Initializable {

    @FXML
    private Label customerIdLabel;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField addressField;
    @FXML
    private SplitMenuButton cityDropdown;
    @FXML
    private TextField address2Field;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField phoneField;
    
    private int selectedCityId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerIdLabel.setText("Customer ID: " + (CentralData.getCustomers().size() + 1));
        cityDropdown.setText("Pick A City");
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
        System.out.println("Add Customer Exit");
        if (result.get() == ButtonType.OK){  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            CustomerScreenController customerScreenController = loader.<CustomerScreenController>getController();
            stage.show();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws Exception {
        Address tempAddress = new Address((CentralData.getAddresses().size() + 1) , addressField.getText(), address2Field.getText(), CentralData.getCity(selectedCityId), postalCodeField.getText(), phoneField.getText());
        Customer tempCustomer = new Customer((CentralData.getCustomers().size() + 1), customerNameField.getText(), tempAddress, true);
        AddressDataInterface.addAddress(tempAddress);
        CustomerDataInterface.addCustomer(tempCustomer);
        CentralData.addAddress(tempAddress);
        CentralData.addCustomer(tempCustomer);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        CustomerScreenController customerScreenController = loader.<CustomerScreenController>getController();
        stage.show();
        
        System.out.println("Customer Saved");
    }
    
}
