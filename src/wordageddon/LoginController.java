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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label testoDomanda;
    @FXML
    private TextField testoUsername;
    @FXML
    private Button inviaDomanda;
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField testoPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void azioneInvio(ActionEvent event) {
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
         Parent root;
            try {
                root = (Parent) loader.load();
                //QuizController ctrl = loader.getController();
         MenuController ctrl = loader.getController();
       
         ctrl.setUser(stage,scene,"username","email",0,0);
        scene = new Scene(root);
                 stage.setScene(scene);
                 //ricorda di levare l'annotazione da qui sotto
        //stmt.close();
                 stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        /*try {
        Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
        System.err.println("Errore Driver");
        }
        try {
        Connection connessione = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wordageddon", "javaus", "jv2025" );
        PreparedStatement stmt = connessione.prepareStatement("SELECT * FROM public.wordageddonusers WHERE username = ? AND password = ?;");
        stmt.setString(1, testoUsername.getText());
        stmt.setString(2, testoPass.getText());
        Boolean positive = stmt.execute();
        ResultSet result;
        if(positive){
        result=stmt.getResultSet();
        result.next();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root;
        try {
        root = (Parent) loader.load();
        //QuizController ctrl = loader.getController();
        MenuController ctrl = loader.getController();
        
        ctrl.setUser(stage,scene,result.getString("username"),result.getString("email"),result.getInt("totalscore"),result.getInt("lastscore"));
        scene = new Scene(root);
        stage.setScene(scene);
        
        stmt.close();
        stage.show();
        } catch (IOException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        
        
        } catch (SQLException ex) {
        System.err.println("Errore Login");
        //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        testoDomanda.setText("Username Inesistente!");
        }*/
    }
    
    public void setLogin(Stage stage,Scene scene){ 
        this.scene=scene;
        this.stage=stage;
    }
}
