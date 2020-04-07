/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        //customerAddress.setCellValueFactory(new PropertyValueFactory<>(""));
        //customerAddress2.setCellValueFactory(new PropertyValueFactory<>("price"));
        //customerCity.setItems(Inventory.getAllParts());
        //customerPostalCode.setItems(Inventory.getAllParts());
        //customerPhone.setItems(Inventory.getAllParts());
        //customerTable.setItems();
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        MenuController menuController = loader.<MenuController>getController();
        //appointmentScreenController.setUp(partToBeModifiedIndex,temp);
        stage.show();
    }

    @FXML
    private void modifyButtonAction(ActionEvent event) {
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
    }

    @FXML
    private void createButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        AddCustomerScreenController addCustoemrScreenController = loader.<AddCustomerScreenController>getController();
        //appointmentScreenController.setUp(partToBeModifiedIndex,temp);
        stage.show();
    }
    
}
