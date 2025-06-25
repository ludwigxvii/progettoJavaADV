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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import wordageddon.classi.*;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class MenuController implements Initializable {
    Scene scene;
    Stage stage;
    ObservableList<User> listaClassifica;
    DifficultState diffState;
    LangState lState;
    User utenteAttuale;
    @FXML
    private ToggleButton bestScoreLabel;
    @FXML
    private ToggleButton lastScoreLabel;
    @FXML
    private Label namelabel;
    
    @FXML
    private TableView<User> tabellaClassifica;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, Integer> scoreColumn;
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
    private Button playButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding buttons = (easyButton.selectedProperty().or(normalButton.selectedProperty()).or(difficultbutton.selectedProperty()))
                .and(itButton.selectedProperty().or(engButton.selectedProperty())).not();
        playButton.disableProperty().bind(buttons);
        
        this.listaClassifica=FXCollections.observableArrayList();
        try {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Errore Driver");
        }
            Connection connessione = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wordageddon", "javaus", "jv2025" );
             
            try (PreparedStatement stmt = connessione.prepareStatement("SELECT * FROM public.wordageddonusers ORDER BY totalscore DESC")) {
                Boolean positive = stmt.execute();
                ResultSet result = stmt.getResultSet();
                if(positive){
                    
                    while(result.next()){
                        this.listaClassifica.add(new User(result.getString("username"),result.getString("email"),
                                result.getInt("totalscore"),result.getInt("lastscore")));
                        //this.mappaUsers.put(result.getString("username"),result.getInt("totalscore"));
                        
                    }
                    this.listaClassifica.stream().forEach(s->System.out.println(s));
                    userColumn.setCellValueFactory(new PropertyValueFactory("nome"));
                    scoreColumn.setCellValueFactory(new PropertyValueFactory("totalscore"));
                    tabellaClassifica.setItems(this.listaClassifica);
                }
            }
         

         } catch (SQLException ex) {
        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }    
    public void setUser(Stage stage,Scene scene,String username,String email,int totalscore,int lastscore){
        this.utenteAttuale= new User(username,email,totalscore,lastscore);
    bestScoreLabel.setText(Integer.toString(totalscore));
    lastScoreLabel.setText(Integer.toString(lastscore));
    namelabel.setText(username);
    this.stage=stage;
    this.scene=scene;
    }

    @FXML
    private void easySelect(ActionEvent event) {
        normalButton.setSelected(false);
        difficultbutton.setSelected(false);
        this.diffState=diffState.FACILE;
    }

    @FXML
    private void normalSelect(ActionEvent event) {
         easyButton.setSelected(false);
        difficultbutton.setSelected(false);
        this.diffState=diffState.NORMALE;
    }

    @FXML
    private void difficultSelect(ActionEvent event) {
         normalButton.setSelected(false);
        easyButton.setSelected(false);
        this.diffState=diffState.DIFFICILE;
    }

    @FXML
    private void engSelect(ActionEvent event) {
        itButton.setSelected(false);
        this.lState=lState.INGLESE;
    }

    @FXML
    private void itSelect(ActionEvent event) {
        engButton.setSelected(false);
        this.lState=lState.ITALIANO;
    }

    @FXML
    private void iniziaPartita(ActionEvent event) {
        System.out.println("Difficoltà: "+this.diffState+" Lingua: "+this.lState+"\n"+this.utenteAttuale.toString());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            GameController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setUser(stage,scene,this.utenteAttuale);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
