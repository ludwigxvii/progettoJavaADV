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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class esitoController implements Initializable {

    @FXML
    private Label testoDomanda;
    @FXML
    private Button terminaPartita;
    @FXML
    private TableView<?> tabellaRisultati;
    @FXML
    private TableColumn<?, ?> numeroDomanda;
    @FXML
    private TableColumn<?, ?> tipoDomanda;
    @FXML
    private TableColumn<?, ?> rispostaData;
    @FXML
    private TableColumn<?, ?> rispostaEsatta;
    @FXML
    private TableColumn<?, ?> score;

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
    
}
