/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.City;
import Model.User;
import Utils.AppointmentReport;
import Utils.CentralData;
import Utils.Time;
import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
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
public class ReportScreenController implements Initializable {

    @FXML
    private TableColumn<AppointmentReport, String> column1;
    @FXML
    private TableColumn<AppointmentReport, String> column2;
    @FXML
    private TableColumn<AppointmentReport, String> column3;
    @FXML
    private SplitMenuButton dropDownMenu;
    
    private User selectedUser;
    @FXML
    private TableView<AppointmentReport> reportTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dropDownMenu.hide();
        dropDownMenu.setVisible(false);
        dropDownMenu.setManaged(false);
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));
        column2.setCellValueFactory(new PropertyValueFactory<>("type"));
        column3.setCellValueFactory(new PropertyValueFactory<>("detail"));
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        ReportMenuController reportMenuController = loader.<ReportMenuController>getController();
        stage.show();
    }
    @FXML
    private void dropDownAction(ActionEvent event) {
        
    }
    
    public void setUpAppointmentsByMonth(){
        column1.setText("Month");
        column2.setText("Type");
        column3.setText("Count");
        ObservableList<Appointment> allAppointments = CentralData.getAppointments();
        ObservableList<String> types = FXCollections.observableArrayList();
        for(int i = 0; i < allAppointments.size(); i++){
            Appointment appointment = allAppointments.get(i);
            if(!types.contains(appointment.getType().toLowerCase())){
                types.add(appointment.getType().toLowerCase());
            }
        }
        ObservableList<Month> months = FXCollections.observableArrayList();
        for(int i = 0; i < allAppointments.size(); i++){
            Appointment appointment = allAppointments.get(i);
            if(!months.contains(appointment.getStartTime().getMonth())){
                months.add(appointment.getStartTime().getMonth());
            }
        }
        ObservableList<AppointmentReport> appointmentReports = FXCollections.observableArrayList();
        for(int m = 0; m < months.size(); m++){
            Month month = months.get(m);
            for(int t = 0; t < types.size(); t++){
                String type = types.get(t);
                int count = 0;
                for(int a = 0; a < allAppointments.size(); a++){
                    Appointment appointment = allAppointments.get(a);
                    if(appointment.getStartTime().getMonth() == month && appointment.getType().toLowerCase().equals(type)){
                        count++;
                    }
                }
                appointmentReports.add(new AppointmentReport(""+month, type.toLowerCase(), ""+count));
            }
        }
        reportTable.setItems(appointmentReports);
    }
    public void setUpConsultantAppointment(){
        column1.setText("Title");
        column2.setText("Type");
        column3.setText("Time");
        dropDownMenu.setVisible(true);
        dropDownMenu.setManaged(true);
        ObservableList<User> users = CentralData.getUsers();
        selectedUser = users.get(0);
        dropDownMenu.setText(selectedUser.getUserName());
        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            MenuItem choice = new MenuItem();
            dropDownMenu.getItems().add(choice);
            choice.setText(user.getUserName());
            //Lambda Function Is used because the number of users can be changed.
            choice.setOnAction((e)-> {
                selectedUser = user;
                dropDownMenu.setText(user.getUserName());
                ObservableList<AppointmentReport> appointmentReports = FXCollections.observableArrayList();
                ObservableList<Appointment> allAppointments = CentralData.getAppointments();
                for(int a = 0; a < allAppointments.size(); a++){
                    Appointment appointment = allAppointments.get(a);
                    if(appointment.getUser().getUserId() == selectedUser.getUserId()){
                        appointmentReports.add(new AppointmentReport(appointment.getTitle(), appointment.getType().toLowerCase(), Time.formatString(appointment.getStartTime())));
                    } 
                }
        reportTable.setItems(appointmentReports);
            });
        }
        ObservableList<AppointmentReport> appointmentReports = FXCollections.observableArrayList();
        ObservableList<Appointment> allAppointments = CentralData.getAppointments();
        for(int i = 0; i < allAppointments.size(); i++){
            Appointment appointment = allAppointments.get(i);
            if(appointment.getUser().getUserId() == selectedUser.getUserId()){
                appointmentReports.add(new AppointmentReport(appointment.getTitle(), appointment.getType().toLowerCase(), Time.formatString(appointment.getStartTime())));
            } 
        }
        reportTable.setItems(appointmentReports);
    }
    public void setUpAppointmentsPerLocaiton(){
        column1.setText("Location");
        column2.setText("Type");
        column3.setText("Count");
        ObservableList<Appointment> allAppointments = CentralData.getAppointments();
        ObservableList<String> types = FXCollections.observableArrayList();
        for(int i = 0; i < allAppointments.size(); i++){
            Appointment appointment = allAppointments.get(i);
            if(!types.contains(appointment.getType().toLowerCase())){
                types.add(appointment.getType().toLowerCase());
            }
        }
        ObservableList<City> cities = CentralData.getCities();
        ObservableList<AppointmentReport> appointmentReports = FXCollections.observableArrayList();
        for(int c = 0; c < cities.size(); c++){
            City city = cities.get(c);
            for(int t = 0; t < types.size(); t++){
                String type = types.get(t);
                int count = 0;
                for(int a = 0; a < allAppointments.size(); a++){
                    Appointment appointment = allAppointments.get(a);
                    if(appointment.getCustomer().getAddress().getCity().getCityId() == city.getCityId() && appointment.getType().toLowerCase().equals(type)){
                        count++;
                    }
                }
                appointmentReports.add(new AppointmentReport(city.getCityName(), type, ""+count));
            }
        }
        reportTable.setItems(appointmentReports);
    }

    
}
