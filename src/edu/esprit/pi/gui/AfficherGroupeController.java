package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.services.SujetService;
//import covoiturage.iservices.IAlerteService;
//import covoiturage.models.Alerte;
//import covoiturage.services.AlerteService;
import edu.esprit.pi.technique.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficherGroupeController implements Initializable {
    @FXML
    private Pane SujetPane;
    IGroupeService groupeService = new GroupeService();

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;
  @FXML
    private AnchorPane sujetsAnchorPane;
         @FXML
    private Label titreLabel;
             @FXML
    private Label topicLabel;
  // @FXMLViewFlowContext
//private ViewFlowContext context;
    
   
    @FXML
    private Parent mainWindow;
      @FXML
   private JFXButton btnInviterMembre; 
     @FXML
   private VBox SujetsVBox;
    @FXML
    private Label LBNomGroupe;
  AfficherMesGroupesController groupeController =new AfficherMesGroupesController();
        private List<Sujet> sujets;
    public static AnchorPane rootP;
      ISujetService affSujets = new SujetService();
    private Connection connection;
private Groupe groupe=groupeController.getGroupeSelectionne();
    public AfficherGroupeController() {
        connection = DataSource.getInstance().getConnection();

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
      // groupe=groupeController.initVariable();
       // groupeController.getGroupeSelectionne();
      
        
       
        try {
           

            groupe=groupeController.getGroupeSelectionne();
            LBNomGroupe.setText(groupe.getNom());
            System.out.println(groupe);
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AfficherGroupeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         getallSujetsList();
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
public void getallSujetsList(){
  
        sujets = affSujets.getbyIdGroupe(groupe.getId());
        System.out.println(sujets);
        if(!sujets.isEmpty()){
            
            for (Sujet sujet : sujets) {
                
       
                AnchorPane newSujetAnchorPane = new AnchorPane();
        newSujetAnchorPane.setStyle(newSujetAnchorPane.getStyle());
        newSujetAnchorPane.setEffect(newSujetAnchorPane.getEffect());

      
        //Titrelabel         
        Label titreLabel = new Label();
        titreLabel.setFont(titreLabel.getFont());
        titreLabel.setTextFill(titreLabel.getTextFill());
        titreLabel.setLayoutX(titreLabel.getLayoutX());
        titreLabel.setLayoutY(titreLabel.getLayoutY());
        titreLabel.setText(sujet.getObjet());
       //topiclabel
        Label topicLabel = new Label();
        topicLabel.setFont(topicLabel.getFont());
        topicLabel.setTextFill(topicLabel.getTextFill());
        topicLabel.setLayoutX(topicLabel.getLayoutX());
        topicLabel.setLayoutY(topicLabel.getLayoutY());
        topicLabel.setText(sujet.getTopic());
        
        //RepondreButton
//        JFXButton reserverButton2 = new JFXButton();
//        reserverButton2.setFont(reserverButton.getFont());
//        reserverButton2.setTextFill(reserverButton.getTextFill());
//        reserverButton2.setLayoutX(reserverButton.getLayoutX());
//        reserverButton2.setLayoutY(reserverButton.getLayoutY());
//        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
//        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
//        reserverButton2.setText(reserverButton.getText());
//        
        
        newSujetAnchorPane.getChildren().addAll(titreLabel,topicLabel);
        SujetsVBox.getChildren().add(newSujetAnchorPane);
      //  newSujetAnchorPane.setOnMouseClicked(e -> {
            		

           // ScreensFramework.annonceId=sujet.getId()
         //  screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
          //          screen.setScreen(ScreensFramework.screen3ID);

                 
       // });
        
                
            }
        }
    } 
 @FXML
    private void inviter(ActionEvent event) throws Exception {

     
    }
}
