/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wordageddon;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Question;
import service.DocumentAnalyzer;
import service.QuestionGenerator;
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
    DifficultState newtextdiffState;
    LangState newtextlState;
    User utenteAttuale;
    ArrayList<TextInfo> listatesti = new ArrayList<>(); 
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
    @FXML
    private Tab adminTab;
    @FXML
    private ToggleButton easyButton1;
    @FXML
    private ToggleButton normalButton1;
    @FXML
    private ToggleButton difficultbutton1;
    @FXML
    private ToggleButton engButton1;
    @FXML
    private ToggleButton itButton1;
    @FXML
    private Button saveButton;
    @FXML
    private TextArea testoDaCaricare;
    @FXML
    private TextField nomeFilelabel;
    @FXML
    private TextField titololabel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (ObjectInputStream inputS = new ObjectInputStream(new FileInputStream("testi.dat"))) {
            while(true){
                TextInfo letto = (TextInfo) inputS.readObject();
                if(letto==null)break;
                this.listatesti.add(letto);
            System.out.println("Testo letto: " + letto.getNome());
            }
             
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fine della Lettura");
            
        }
        System.out.print("Testi caricati:[");
        this.listatesti.stream().forEach(s->System.out.print(s.getFilename()+", "));
        System.out.print("]");
           
        adminTab.setDisable(true);
        adminTab.setText("");
        BooleanBinding buttons = (easyButton.selectedProperty().or(normalButton.selectedProperty()).or(difficultbutton.selectedProperty()))
                .and(itButton.selectedProperty().or(engButton.selectedProperty())).not();
        playButton.disableProperty().bind(buttons);
        BooleanBinding buttons2 = (easyButton1.selectedProperty().or(normalButton1.selectedProperty()).or(difficultbutton1.selectedProperty()))
                .and(itButton1.selectedProperty().or(engButton1.selectedProperty())).not().or(testoDaCaricare.textProperty().isEmpty())
                .or(nomeFilelabel.textProperty().isEmpty()).or(titololabel.textProperty().isEmpty());
        saveButton.disableProperty().bind(buttons2);
        this.listaClassifica=FXCollections.observableArrayList();
        /*try {
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
        }*/
        
    }    
    public void setUser(Stage stage,Scene scene,String username,String email,int totalscore,int lastscore){
        this.utenteAttuale= new User(username,email,totalscore,lastscore);
        if(utenteAttuale.getNome()=="admin"){
        adminTab.setDisable(false);
        adminTab.setText("Admin");
        }
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
        ArrayList<TextInfo> testi=new ArrayList<>();
                this.listatesti.stream().filter(s->{return Boolean.
                        logicalAnd(s.getLang().equals(this.lState), s.getDiff().equals(this.diffState));}).forEach(testo->testi.add(testo));
        ArrayList<String> testo_selezionato=new ArrayList<>();
        Random random = new Random();
        int indice_random=random.nextInt(testi.size());
        String nomeTesto=testi.get(indice_random).getNome();
        testo_selezionato.add(testi.get(indice_random).getFilename()+".txt");
        //testi.add("ita1.txt");
        testi.forEach(s->System.out.println(s+", "));
        DocumentAnalyzer analyzer = new DocumentAnalyzer(testo_selezionato,"ITALIANO");
        QuestionGenerator generator = new QuestionGenerator(analyzer);
        //analyzer.getGlobalFrequencies().entrySet().forEach((s)->System.out.println("keyset: "+s+"\n"));
        List<Question> generatedQuestions = generator.generateQuestions(10);
        
        System.out.println("Difficoltà: "+this.diffState+" Lingua: "+this.lState+"\n"+this.utenteAttuale.toString());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
         Parent root;
        try {
            root = (Parent) loader.load();
            GameController ctrl = loader.getController();
                scene = new Scene(root);
                 stage.setScene(scene);
                 ctrl.setUser(stage,scene,this.utenteAttuale,analyzer,generatedQuestions,testo_selezionato,nomeTesto);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inserisciTesto(ActionEvent event) {
        //public TextInfo(String nome, String filename, DifficultState diff, LangState lang, String filestopw) 
        TextInfo nuovoTesto = new TextInfo(titololabel.getText(),nomeFilelabel.getText(),
                this.newtextdiffState,this.newtextlState,testoDaCaricare.getText());
        this.listatesti.add(nuovoTesto);
        System.out.println("testo aggiunto: \n"+nuovoTesto.toString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resources\\texts\\"+nomeFilelabel.getText()+".txt"))) {
            writer.write(testoDaCaricare.getText());
            writer.newLine(); // Aggiunge un a capo
            System.out.println("Testo archiviato");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream outputS = new ObjectOutputStream(new FileOutputStream("testi.dat"))) {
            int n=0;
            this.listatesti.stream().forEach(testo->{
                try {
                    outputS.writeObject(testo);
                } catch (IOException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            System.out.println("Info Testo Aggiunte");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void easySelectadm(ActionEvent event) {
        normalButton1.setSelected(false);
        difficultbutton1.setSelected(false);
        this.newtextdiffState=diffState.FACILE;
    }

    @FXML
    private void normalSelectadm(ActionEvent event) {
        difficultbutton1.setSelected(false);
        easyButton1.setSelected(false);
        this.newtextdiffState=diffState.NORMALE;
    }

    @FXML
    private void difficultSelectadm(ActionEvent event) {
        normalButton1.setSelected(false);
        easyButton1.setSelected(false);
        this.newtextdiffState=diffState.DIFFICILE;
    }

    @FXML
    private void engSelectadm(ActionEvent event) {
        itButton1.setSelected(false);
        this.newtextlState=LangState.INGLESE;
    }

    @FXML
    private void itSelectadm(ActionEvent event) {
        engButton1.setSelected(false);
        this.newtextlState=LangState.ITALIANO;
    }
}
