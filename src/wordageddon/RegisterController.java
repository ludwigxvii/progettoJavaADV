/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wordageddon;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class RegisterController implements Initializable {

    @FXML
    private Label testoRegistrazione;
    @FXML
    private TextField testoUsername;
    @FXML
    private Button inviaReg;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField testoMail;
    @FXML
    private TextField testoPass;
    @FXML
    private TextField testoPass2;
    @FXML
    private Label password1;
    @FXML
    private Label password2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void azioneInvio(ActionEvent event) {
        if(!testoPass.getText().equals(testoPass2.getText())){
        testoRegistrazione.setText("Le password non Coincidono!");
        return;
        }
        if(testoUsername.getText().length()>20){
        testoRegistrazione.setText("Username Troppo Lungo!");
        return;
        }
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!testoMail.getText().matches(regexPattern)){
        testoRegistrazione.setText("Formato E-mail Incorretto!");
        System.err.println(testoMail.getText());
        return;
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Errore Driver");
        }
        try {
            Connection connessione = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wordageddon", "javaus", "jv2025" );
            PreparedStatement stmt = connessione.prepareStatement("INSERT INTO public.wordageddonusers(\n" +
"	username, password, email)\n" +
"	VALUES (?, ?, ?);");
            stmt.setString(1, testoUsername.getText());
            stmt.setString(2, testoPass.getText());
            stmt.setString(3, testoMail.getText());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Errore Scrittura");
            testoRegistrazione.setText("Username esistente!");
        }
    }
    
}
