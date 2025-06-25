/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wordageddon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class QuizController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Timeline timeline;
    private int numeroDomanda;
    private String tipoDomanda;
    @FXML
    private Label testoDomanda;
    @FXML
    private Button labelDomande;
    @FXML
    private Label tempo;
    @FXML
    private TextField testoRisposta;
    @FXML
    private Button inviaDomanda;
    @FXML
    private Label parolaDomanda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testoRisposta.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            testoRisposta.setText(newValue.replaceAll("[^\\d]", ""));
        }
    });
        inviaDomanda.disableProperty().bind(testoRisposta.textProperty().isEmpty());
        long startTime = System.currentTimeMillis();
        this.timeline =
                
                new Timeline(new KeyFrame(Duration.millis(1000), e -> {
                    long secondsDisplay;
                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                secondsDisplay = 60-elapsedSeconds;
                if (secondsDisplay<=0){
                
              this.timeline.stop();
              System.out.println("TEMPO SCADUTO");
                };
                
                this.tempo.setText(String.format("%02d", secondsDisplay));
              } ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
    
    }    

    @FXML
    private void azioneInvio(ActionEvent event) {
        if(this.numeroDomanda==10){
        System.out.println("Invio "+this.testoRisposta.getText());
FXMLLoader loader = new FXMLLoader(getClass().getResource("esitoSchermata.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            //QuizController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 //ctrl.setDomanda(this.numeroDomanda,"Tipo1","Pesce",stage,scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
System.out.println("Invio "+this.testoRisposta.getText());
FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            QuizController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setDomanda(this.numeroDomanda,"Tipo1","Pesce",stage,scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
   
    public void setDomanda(int numero,String tipoDomanda,String parola,Stage stage,Scene scene){
        this.tempo.setText("60");
        this.scene=scene;
        this.stage=stage;
        this.numeroDomanda=numero+1;
        this.tipoDomanda=tipoDomanda;
        this.labelDomande.setText(this.numeroDomanda+"/10");
        this.parolaDomanda.setText(parola);
}
}

