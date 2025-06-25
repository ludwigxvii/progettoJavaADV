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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;
import wordageddon.classi.User;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class GameController implements Initializable {
    private User utenteAttuale;
    private Scene scene;
    private Stage stage;
    private Timeline timeline;
    @FXML
    private TextArea zonaTesto;
    @FXML
    private Label textName;
    @FXML
    private Label timeLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        long startTime = System.currentTimeMillis();
        this.timeline =
                
                new Timeline(new KeyFrame(Duration.millis(1000), e -> {
                    long secondsDisplay;
                long elapsedTime = System.currentTimeMillis() - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                secondsDisplay = 120-elapsedSeconds;
                if (secondsDisplay<=0){
                
              this.timeline.stop();
              System.out.println("TEMPO SCADUTO");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            QuizController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setDomanda(0,"Tipo1","Cavallo",stage,scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
                };
                
                this.timeLabel.setText(Math.toIntExact(secondsDisplay/60)+":"+String.format("%02d", (secondsDisplay)%60));
              } ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }    
    public void setUser(Stage stage,Scene scene,User user){
        this.utenteAttuale=user;
        this.stage=stage;
        this.scene=scene;
    }
}
