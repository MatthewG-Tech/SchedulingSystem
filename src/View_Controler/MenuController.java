/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Utils.AppointmentDataInterface;
import Utils.CentralData;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
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
public class MenuController implements Initializable {

    @FXML
    private RadioButton sortByWeekButton;
    @FXML
    private RadioButton sortByMonthButton;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentId;
    @FXML
    private TableColumn<Appointment, String> appointmentTitle;
    @FXML
    private TableColumn<Appointment, String> appointmentLocation;
    @FXML
    private TableColumn<Appointment, String> appointmentContact;
    @FXML
    private TableColumn<Appointment, String> appointmentType;
    @FXML
    private TableColumn<Appointment, Date> appointmentTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CentralData.setUserAppointments(AppointmentDataInterface.getAllUserAppointments(CentralData.getUser().getUserId()));
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sortByWeekButton.setSelected(true);
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentTable.setItems(CentralData.getUserAppointments());
    }    

    @FXML
    private void userButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        CustomerScreenController userScreenController = loader.<CustomerScreenController>getController();
        //userScreenController.setUp(partToBeModifiedIndex,temp);
        stage.show();
    }

    @FXML
    private void appointmentsButtonAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        AppointmentScreenController appointmentScreenController = loader.<AppointmentScreenController>getController();
        //appointmentScreenController.setUp(partToBeModifiedIndex,temp);
        stage.show();
    }
    
    
    @FXML
    private void exitButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        System.out.println("Main Exit");
        if (result.get() == ButtonType.OK){  
            Platform.exit();
        }
    }

    @FXML
    private void sortByWeekButtonAction(ActionEvent event) {
        sortByWeekButton.setSelected(true);
        sortByMonthButton.setSelected(false);
    }

    @FXML
    private void sortByMonthButtonAction(ActionEvent event) {
        sortByWeekButton.setSelected(false);
        sortByMonthButton.setSelected(true);
    }
}
