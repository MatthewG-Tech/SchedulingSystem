/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Utils.CentralData;
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
public class AddCustomerScreenController implements Initializable {

    @FXML
    private Label customerIdLabel;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField address2Label;
    @FXML
    private SplitMenuButton cityDropdown;
    @FXML
    private TextField postalCodeLabel;
    @FXML
    private TextField phoneLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerIdLabel.setText("Customer ID: " + (CentralData.getCustomers().size() + 1));
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
            //appointmentScreenController.setUp(partToBeModifiedIndex,temp);
            stage.show();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
    }
    
}
