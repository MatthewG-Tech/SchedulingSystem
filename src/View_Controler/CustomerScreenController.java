/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
public class CustomerScreenController implements Initializable {

    @FXML
    private TableColumn<Customer, Integer> customerId;
    @FXML
    private TableColumn<Customer, String> customerName;
    @FXML
    private TableView<Customer> customerTable;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.setItems(CentralData.getCustomers());
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        MenuController menuController = loader.<MenuController>getController();
        stage.show();
    }

    @FXML
    private void modifyButtonAction(ActionEvent event) throws IOException {
        if(customerTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyCustomerScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            ModifyCustomerScreenController modifyCustomerScreenController = loader.<ModifyCustomerScreenController>getController();
            modifyCustomerScreenController.setUp(customerTable.getSelectionModel().getSelectedItem());
            stage.show();
        }
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws Exception {
        Customer temp = customerTable.getSelectionModel().getSelectedItem();
        if(temp != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                CustomerDataInterface.deleteCustomer(temp.getCustomerId());
                AddressDataInterface.deleteAddress(temp.getAddress().getAddressId());
                CentralData.removeCustomer(temp);
                CentralData.removeAddress(temp.getAddress());
                customerTable.setItems(CentralData.getCustomers());
            }
        }
        System.out.println("Customer Delete");
    }

    @FXML
    private void createButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        AddCustomerScreenController addCustomerScreenController = loader.<AddCustomerScreenController>getController();
        stage.show();
    }
    
}
