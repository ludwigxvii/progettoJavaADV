/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package wordageddon;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author ludwi
 */
public class FXMLDocumentController implements Initializable {
    Stage stage;
    Scene scene;
    @FXML
    private Label label;
    @FXML
    private Button loginButton;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = (Parent) loader.load();
            //QuizController ctrl = loader.getController();
            LoginController ctrl = loader.getController();
            
            scene = new Scene(root);
            stage.setScene(scene);
            ctrl.setLogin(stage,scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerAction(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = (Parent) loader.load();
            //QuizController ctrl = loader.getController();
            RegisterController ctrl = loader.getController();
            
            scene = new Scene(root);
            stage.setScene(scene);
            ctrl.setReg(stage,scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setStart(Stage stage,Scene scene){
    this.stage=stage;
    this.scene=scene;
    }
}
