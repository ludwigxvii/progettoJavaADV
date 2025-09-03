/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wordageddon;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Question;
import wordageddon.classi.User;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class esitoController implements Initializable {
    private Scene scene;
    private Stage stage;
    @FXML
    private Label testoDomanda;
    @FXML
    private Button terminaPartita;
    @FXML
    private TableView<Question> tabellaRisultati;
    @FXML
    private TableColumn<?, ?> tipoDomanda;
    @FXML
    private TableColumn<?, ?> rispostaData;
    @FXML
    private TableColumn<?, ?> rispostaEsatta;
    private User username;
    @FXML
    private AnchorPane scoreTotale;
    @FXML
    private Label testoDomanda1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoDomanda.setCellValueFactory(new PropertyValueFactory("text"));
        rispostaData.setCellValueFactory(new PropertyValueFactory("givenValue"));
        rispostaEsatta.setCellValueFactory(new PropertyValueFactory("correctValue"));
    }    
    public void setRisultati(List<Question> listadomande, User username,Stage stage,Scene scene){
        this.username=username;
        ObservableList<Question> tab = FXCollections.observableArrayList();
        tab.addAll(listadomande);
    tabellaRisultati.setItems(tab);
    
    }
    @FXML
    private void azioneInvio(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
         Parent root;
            try {
                root = (Parent) loader.load();
                //QuizController ctrl = loader.getController();
         MenuController ctrl = loader.getController();
       
         ctrl.setUser(stage,scene,this.username.getNome(),this.username.getEmail(),0,0);
        scene = new Scene(root);
                 stage.setScene(scene);
                 //ricorda di levare l'annotazione da qui sotto
        //stmt.close();
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Errore Driver");
        }
        try {
            Connection connessione = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wordageddon", "javaus", "jv2025" );
        
        try (PreparedStatement stmt = connessione.prepareStatement("DELETE FROM public.sessions\n" +
"	WHERE username=username;")) {
        stmt.execute();
            stmt.close();
        
        }
        } catch (SQLException ex) {
            System.err.println("Errore Scrittura");
                        Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    
}
