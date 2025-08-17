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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class adminScreenController implements Initializable {

    @FXML
    private ToggleButton easyButton;
    @FXML
    private ToggleButton normalButton;
    @FXML
    private ToggleButton difficultbutton;
    @FXML
    private ToggleButton engButton;
    @FXML
    private ToggleButton itButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextArea testoDaCaricare;
    @FXML
    private TextField nomeFilelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void easySelect(ActionEvent event) {
    }

    @FXML
    private void normalSelect(ActionEvent event) {
    }

    @FXML
    private void difficultSelect(ActionEvent event) {
    }

    @FXML
    private void engSelect(ActionEvent event) {
    }

    @FXML
    private void itSelect(ActionEvent event) {
    }

    @FXML
    private void iniziaPartita(ActionEvent event) {
    }
    
}
