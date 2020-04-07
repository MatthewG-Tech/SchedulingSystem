/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controler;

import Model.User;
import Utils.UserDataInterface;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
        // TODO
        userNameLabel.setAlignment(Pos.CENTER_RIGHT);
        passwordLabel.setAlignment(Pos.CENTER_RIGHT);
        if(System.getProperty("user.language").equals("en")){
            switchToEnglish();
        }else if(System.getProperty("user.language").equals("es")){
            switchToSpanish();
        }
    }    

    @FXML
    private void logInButtonAction(ActionEvent event) throws IOException, Exception {
        System.out.println("Log In Button Pressed.");
        if(doUserNamePasswordMatch(userNameField.getText(), passwordField.getText())){
            successfulLogIn(event);
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
    
    private void successfulLogIn(ActionEvent event) throws IOException{
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText("Upcoming Appointment");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
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
