/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wordageddon;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class Quiz1Controller implements Initializable {

    @FXML
    private Label testoDomanda;
    @FXML
    private Button labelDomande;
    @FXML
    private Button inviaDomanda;
    @FXML
    private Label tempo;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option3;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void azioneInvio(ActionEvent event) {
    }

    @FXML
    private void select1(ActionEvent event) {
    }

    @FXML
    private void select3(ActionEvent event) {
    }

    @FXML
    private void azioneUscita(ActionEvent event) {
    }
    
}
