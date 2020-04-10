/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.City;
import Utils.AppointmentDataInterface;
import Utils.CentralData;
import Utils.Time;
import static Utils.Time.getTimeZone;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author matthewguerra
 */
public class AppointmentScreenController implements Initializable {
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
    private TableColumn<Appointment, String> appointmentTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentTime.setText("Time in Local Time");
        appointmentTime.setCellValueFactory(new Callback<CellDataFeatures<Appointment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Appointment, String> p) {
                return new ReadOnlyObjectWrapper(Time.formatString(p.getValue().getStartTime()));
            }
         });
        ObservableList<Appointment> temp = CentralData.getAppointments();
        appointmentTable.setItems(temp);
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        MenuController menuController = loader.<MenuController>getController();
        stage.show();
    }

    @FXML
    private void modifyButtonAction(ActionEvent event) throws IOException {
        Appointment temp = appointmentTable.getSelectionModel().getSelectedItem();
        if(temp != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAppointmentScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene ((Pane) loader.load()));
            ModifyAppointmentScreenController modifyAppointmentScreenController = loader.<ModifyAppointmentScreenController>getController();
            modifyAppointmentScreenController.setUp(appointmentTable.getSelectionModel().getSelectedItem());
            stage.show();
        }
        System.out.println("Appointment Modify");
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws Exception {
        Appointment temp = appointmentTable.getSelectionModel().getSelectedItem();
        if(temp != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                CentralData.removeAppointment(temp);
                AppointmentDataInterface.deleteAppointment(temp.getAppointmentId());
                appointmentTable.setItems(CentralData.getAppointments());
            }
        }
        System.out.println("Appointment Delete");
    }

    @FXML
    private void createButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointmentScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        AddAppointmentScreenController addAppointmentScreenController = loader.<AddAppointmentScreenController>getController();

        stage.show();
        System.out.println("Appointment Create");
    }

    @FXML
    private void generateReportAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        ReportMenuController reportMenu = loader.<ReportMenuController>getController();
        stage.show();
    }


    
}
