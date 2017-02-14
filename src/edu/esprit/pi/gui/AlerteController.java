package edu.esprit.pi.gui;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AlerteService;
import edu.esprit.pi.technique.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlerteController implements Initializable {

    
     @FXML
    private TableView<Alerte> TableAlerte;
     @FXML
        private TableColumn<Alerte, String> CLLieuDepart;
     @FXML
        private TableColumn<Alerte, String> CLLieuArrivee;
     @FXML
        private TableColumn<Alerte, Date> CLDate;
     @FXML
        private TableColumn<Alerte, Integer> CLHeure;
         public static List<Alerte> lstAlerte;

     private final ObservableList<Alerte> ListAlerte = FXCollections.observableArrayList();
   
      @FXML
    private Label LId;
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;
        @FXML
    private JFXTextField txtLieuDepart;
           @FXML
    private JFXTextField txtLieuArrivee;
              @FXML
    private JFXDatePicker dateP;
                 @FXML
    private JFXButton btnMesAlertes;
    
              @FXML
    private JFXSlider SliderHeure;         
     @FXML
    private JFXButton btnEnregistrer;
 
       @FXML
    private JFXButton btnAnnuler;

    public static AnchorPane rootP;
    private Connection connection;
    private PreparedStatement ps;
     IAlerteService alerteService= new AlerteService();
  

    public AlerteController() {
                connection = DataSource.getInstance().getConnection();
    }

    @FXML
 private void annuler(ActionEvent event) {
        txtLieuDepart.setText("");
        txtLieuArrivee.setText("");
        dateP.setValue(LocalDate.now());
        SliderHeure.setValue(0);
        
    }
  
    
    @FXML
    private void ajouterAlerte(ActionEvent event) throws Exception{

   
    User user=new User(2);
     Alerte alerte =new Alerte (txtLieuDepart.getText(),txtLieuArrivee.getText(),java.sql.Date.valueOf(dateP.getValue()),(int) SliderHeure.getValue(),user);
        alerteService.add(alerte);
        
 Parent affichageAlerte = FXMLLoader.load(getClass().getResource("AffichageAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
     
   
    
 
   

    }
      @FXML
    private void afficherMesAlertes(ActionEvent event) throws Exception{
     Parent affichageAlerte = FXMLLoader.load(getClass().getResource("AffichageAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
   }
        @FXML
    private void chercherAlerte(ActionEvent event) throws Exception{
    
       Parent affichageAlerte = FXMLLoader.load(getClass().getResource("ChercherAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
    
    
    }
  
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
//            SetCellTable();
//            setCellValueFromTableToText();

        
      
       
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });
    }

}
