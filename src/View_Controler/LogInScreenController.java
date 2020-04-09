/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.Appointment;
import Model.User;
import Utils.AddressDataInterface;
import Utils.AppointmentDataInterface;
import Utils.CentralData;
import Utils.CityDataInterface;
import Utils.CountryDataInterface;
import Utils.CustomerDataInterface;
import Utils.Time;
import static Utils.Time.getTimeZone;
import Utils.UserDataInterface;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matthewguerra
 */
public class LogInScreenController implements Initializable {

    @FXML
    private Label logInScreenLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button logInButton;
    @FXML
    private TextField passwordField;
    @FXML
    private Label userNameLabel;
    @FXML
    private TextField userNameField;
    
    Alert incorrectPassword = new Alert(Alert.AlertType.ERROR);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CentralData.setCountries(CountryDataInterface.getAllCountries());
            CentralData.setCities(CityDataInterface.getAllCities());
            userNameLabel.setAlignment(Pos.CENTER_RIGHT);
            passwordLabel.setAlignment(Pos.CENTER_RIGHT);
            if(System.getProperty("user.language").equals("en")){
                switchToEnglish();
            }else if(System.getProperty("user.language").equals("es")){
                switchToSpanish();
            }
        } catch (Exception ex) {
            Logger.getLogger(LogInScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException, Exception {
        System.out.println("Log In Button Pressed.");
        if(doUserNamePasswordMatch(userNameField.getText(), passwordField.getText())){
            successfulLogIn(event, userNameField.getText());
        }else{
            unsuccessfulLogIn();
        }
    }
    
    private Boolean doUserNamePasswordMatch(String userName, String password) throws SQLException, Exception{
        User result = UserDataInterface.getUser(userName);
        if(result != null){
            return password.equals(UserDataInterface.getUser(userName).getPassword());
        }
        return false;
    }
    
    private void successfulLogIn(ActionEvent event, String userNameInput) throws IOException, Exception{
        CentralData.setUser(UserDataInterface.getUser(userNameInput));
        CentralData.setAddressess(AddressDataInterface.getAllAddresses());
        CentralData.setCutomers(CustomerDataInterface.getAllCustomers());
        CentralData.setAppointments(AppointmentDataInterface.getAllAppointments());
        CentralData.setUserAppointments(AppointmentDataInterface.getAllUserAppointments(CentralData.getUser().getUserId()));
        checkForAppointment();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene ((Pane) loader.load()));
        MenuController menu = loader.<MenuController>getController();
        //modifyOutsourcedController.setUp(partToBeModifiedIndex,temp);
        stage.show();
    }
    
    private void unsuccessfulLogIn(){
        Optional<ButtonType> result = incorrectPassword.showAndWait();
        userNameField.setText("");
        passwordField.setText("");
    }
    
    private void checkForAppointment(){
        ObservableList<Appointment> appointments = CentralData.getUserAppointments();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        ZonedDateTime currentDateTime = ZonedDateTime.of(LocalDateTime.of(localDate, localTime), ZoneId.of(getTimeZone().getID()));
        ZonedDateTime fifteenMinutes = ZonedDateTime.of(LocalDateTime.of(localDate, localTime), ZoneId.of(getTimeZone().getID()));
        fifteenMinutes = fifteenMinutes.plusMinutes(15);
        for(int i = 0; i < appointments.size(); i++){
            Appointment appointment = appointments.get(i);
            if(appointment.getStartTime().isBefore(fifteenMinutes) && appointment.getStartTime().isAfter(currentDateTime)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setHeaderText("Upcoming Appointment");
                
                alert.setContentText("You have an appointment with " + appointment.getCustomer().getCustomerName() + ", in the next 15 minutes");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }
    
    private void switchToEnglish(){
        logInScreenLabel.setText("Log In Screen");
        userNameLabel.setText("User Name");
        userNameField.setPromptText("User Name");
        passwordLabel.setText("Password");
        passwordField.setPromptText("Password");
        logInButton.setText("Log In");
        
        incorrectPassword.setTitle("Log In");
        incorrectPassword.setHeaderText("Incorrect Password");
        incorrectPassword.setContentText("The User Name and Password do not match.");
    }
    
    private void switchToSpanish(){
        logInScreenLabel.setText("Pantalla de Inicialización");
        userNameLabel.setText("Nombre de Usuario");
        userNameField.setPromptText("Nombre de Usuario");
        passwordLabel.setText("Contraseña");
        passwordField.setPromptText("Contraseña");
        logInButton.setText("Inicialización");
        
        incorrectPassword.setTitle("Inicialización");
        incorrectPassword.setHeaderText("Contraseña Incorrecta");
        incorrectPassword.setContentText("El nombre de usuario y la contraseña no coinciden.");
    }
    
}
