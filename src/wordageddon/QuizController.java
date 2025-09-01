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
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Question;
import service.DocumentAnalyzer;

/**
 * FXML Controller class
 *
 * @author ludwi
 */
public class QuizController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Timeline timeline;
    List<Question> domande;
    DocumentAnalyzer analyzer;
    private int risposta_data;
    private int numeroDomanda;
    private String tipoDomanda;
    @FXML
    private Label testoDomanda;
    @FXML
    private Button labelDomande;
    @FXML
    private Label tempo;
    private TextField testoRisposta;
    @FXML
    private Button inviaDomanda;
    private Label parolaDomanda;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option3;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option4;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        inviaDomanda.disableProperty().bind((option1.selectedProperty().or(option2.selectedProperty())
        .or(option3.selectedProperty()).or(option4.selectedProperty())).not());
        
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
              if(this.numeroDomanda==10){
        
FXMLLoader loader = new FXMLLoader(getClass().getResource("esitoSchermata.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            esitoController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setRisultati(domande);
                 //ctrl.setDomanda(this.numeroDomanda,"Tipo1","Pesce",stage,scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
            this.domande.get(numeroDomanda-1).setGivenIndex(this.risposta_data);
        System.out.println(this.domande.get(numeroDomanda-1).toString());
//System.out.println("Invio "+this.testoRisposta.getText());
FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
this.timeline.stop();
         Parent root;
        try {
            root = (Parent) loader.load();
            QuizController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setDomanda(this.numeroDomanda,stage,scene,this.domande);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                };
                
                this.tempo.setText(String.format("%02d", secondsDisplay));
              } ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
    
    }    

    @FXML
    private void azioneInvio(ActionEvent event) {
        if(this.numeroDomanda==10){
        
FXMLLoader loader = new FXMLLoader(getClass().getResource("esitoSchermata.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            esitoController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setRisultati(domande);
                 //ctrl.setDomanda(this.numeroDomanda,"Tipo1","Pesce",stage,scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
            this.domande.get(numeroDomanda-1).setGivenIndex(this.risposta_data);
        System.out.println(this.domande.get(numeroDomanda-1).toString());
//System.out.println("Invio "+this.testoRisposta.getText());
FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
this.timeline.stop();
         Parent root;
        try {
            root = (Parent) loader.load();
            QuizController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setDomanda(this.numeroDomanda,stage,scene,this.domande);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
   
    public void setDomanda(int numero,Stage stage,Scene scene,List<Question> questions){
        this.tempo.setText("60");
        this.scene=scene;
        this.stage=stage;
        this.testoDomanda.setText(questions.get(numero).getText());
        this.option1.setText(questions.get(numero).getOptions().get(0));
        this.option2.setText(questions.get(numero).getOptions().get(1));
        this.option3.setText(questions.get(numero).getOptions().get(2));
        this.option4.setText(questions.get(numero).getOptions().get(3));
        this.numeroDomanda=numero+1;
        this.tipoDomanda=tipoDomanda;
        this.labelDomande.setText(this.numeroDomanda+"/10");
        this.domande=questions;
        //this.analyzer=analyzer;
        //this.parolaDomanda.setText(parola);
}

    @FXML
    private void select1(ActionEvent event) {
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
        risposta_data=0;
    }

    @FXML
    private void select3(ActionEvent event) {
        option1.setSelected(false);
        option2.setSelected(false);
        option4.setSelected(false);
        risposta_data=2;
    }

    @FXML
    private void select2(ActionEvent event) {
        option3.setSelected(false);
        option1.setSelected(false);
        option4.setSelected(false);
        risposta_data=1;
    }

    @FXML
    private void select4(ActionEvent event) {
        option2.setSelected(false);
        option3.setSelected(false);
        option1.setSelected(false);
        risposta_data=3;
    }

    @FXML
    private void azioneUscita(ActionEvent event) {
        StringBuffer stringa_domande= new StringBuffer();
        
        for(int i=0;i<10;i++){
            stringa_domande.append(this.domande.get(i).toJson());
            stringa_domande.append("\n");
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Errore Driver");
        }
        try {
            Connection connessione = DriverManager.getConnection("jdbc:postgresql://localhost:5432/wordageddon", "javaus", "jv2025" );
            PreparedStatement stmt = connessione.prepareStatement("INSERT INTO public.sessions(\n" +
"	username, domande, counter, \"timestamp\")\n" +
"	VALUES (?, ?, ?, ?);");
            stmt.setString(1, "username");
            stmt.setString(2, stringa_domande.toString());
            stmt.setInt(3, (this.numeroDomanda-1));
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(stamp.getTime());
            stmt.setDate(4, date);
            stmt.execute();
            stmt.close();
        
        
        } catch (SQLException ex) {
            System.err.println("Errore Scrittura");
                        Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}

