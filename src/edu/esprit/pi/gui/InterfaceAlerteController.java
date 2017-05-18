/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static edu.esprit.pi.gui.AlerteController.lstAlerte;
import static edu.esprit.pi.gui.GestionReservationController.screen;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AlerteService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class InterfaceAlerteController implements Initializable,ControlledScreen {
 private List<Alerte> lstAlerte;
    @FXML
    private VBox alertesVBox;
    @FXML
    private AnchorPane alerteAnchorPane;

    @FXML
    private Label fromToLabel;
  @FXML
    private JFXButton btnAlerte;
   @FXML
    private ImageView supp;
                     @FXML
 private JFXTextField txtRecherhe;

    @FXML
    private Button btnModif;
    @FXML
    private Label HeureLabel;

    @FXML
    private Label dateLabel;

  
    @FXML
    private Separator separator;


    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    public static int idAlerte;

    public static int getIdAlerte() {
        return idAlerte;
    }

    public InterfaceAlerteController() {
    }
    
 User user=new User(User.getIdd());
      AlerteService alerteService = new AlerteService();

    @FXML
    void creerAlerte(ActionEvent event) throws IOException {

          
            
                    RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen12ID, ScreensFramework.screen12File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen12ID);
 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                if(!alerteService.comparerPublicationAlerte(alerteService.getAll()).isEmpty())
{NotificationType type = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Veuillez consulter la liste des annonces");
            tray.setMessage(" on a trouvé une annonce adéquate à votre alerte créé ");
            tray.setNotificationType(type);
            tray.showAndDismiss(Duration.seconds(5));}

         try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                lstAlerte=alerteService.getAll(user.getId());

        getAllAlerteList(lstAlerte);

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
  
    public void getAllAlerteList( List<Alerte> lstAlerte){
          
       
      
        
        if(!lstAlerte.isEmpty()){
           // Labelvide.setVisible(false);
            
            for (Alerte alerte : lstAlerte) {
         //  nomUserConect.setText(userFav.getUserConnect().getPrenom()+" "+userFav.getUserConnect().getNom());   

                AnchorPane newUserFavAnchorPane = new AnchorPane();
        newUserFavAnchorPane.setStyle(alerteAnchorPane.getStyle());
         newUserFavAnchorPane.setPrefHeight(alerteAnchorPane.getPrefHeight());
        newUserFavAnchorPane.setEffect(alerteAnchorPane.getEffect());

       //nomLabbel
        Label nomLabel1 = new Label();
        nomLabel1.setFont(fromToLabel.getFont());
        nomLabel1.setTextFill(fromToLabel.getTextFill());
        nomLabel1.setLayoutX(fromToLabel.getLayoutX());
        nomLabel1.setLayoutY(fromToLabel.getLayoutY());
        nomLabel1.setText(alerte.getLieuDepart()+"->"+alerte.getLieuArrivee());

        //gouverneratLabel
        Label gouverneratLabel1 = new Label();
        gouverneratLabel1.setFont(dateLabel.getFont());
        gouverneratLabel1.setTextFill(dateLabel.getTextFill());
        gouverneratLabel1.setLayoutX(dateLabel.getLayoutX());
        gouverneratLabel1.setLayoutY(dateLabel.getLayoutY());
        gouverneratLabel1.setText("date: "+alerte.getDate());
        //date enregistrement
           Label dateEnregistrementLabel = new Label();
        dateEnregistrementLabel.setFont(HeureLabel.getFont());
        dateEnregistrementLabel.setTextFill(HeureLabel.getTextFill());
        dateEnregistrementLabel.setLayoutX(HeureLabel.getLayoutX());
        dateEnregistrementLabel.setLayoutY(HeureLabel.getLayoutY());
        dateEnregistrementLabel.setText("Heure: "+alerte.getHeure());

       //separator

        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
      
//      
ImageView suppImageView2 = new ImageView();
        
        suppImageView2.setImage(supp.getImage());
        suppImageView2.setLayoutX(supp.getLayoutX());
        suppImageView2.setLayoutY(supp.getLayoutY());
        suppImageView2.setStyle(supp.getStyle());
        suppImageView2.setFitWidth(supp.getFitWidth());
        suppImageView2.setFitHeight(supp.getFitHeight());
        
//     
    Button modifbtn = new Button();
        modifbtn.setFont(btnModif.getFont());
        modifbtn.setTextFill(btnModif.getTextFill());
        modifbtn.setLayoutX(btnModif.getLayoutX());
        modifbtn.setLayoutY(btnModif.getLayoutY());
    modifbtn.setStyle("-fx-background-color: #2196f3");
        modifbtn.setText(btnModif.getText());

        
          
        newUserFavAnchorPane.getChildren().addAll(nomLabel1,gouverneratLabel1,dateEnregistrementLabel,separator1,suppImageView2,modifbtn);
        alertesVBox.getChildren().add(newUserFavAnchorPane);
          
        newUserFavAnchorPane.setOnMouseClicked(e -> {
            
            System.out.println(alerte.getId());
        


                 
       });

        
                      suppImageView2.setOnMouseClicked(e -> {
            
            System.out.println(alerte.getId());
        
alerteService.delete(alerte.getId());

       Parent affichageAlerte;
                    try {
                        affichageAlerte = FXMLLoader.load(getClass().getResource("InterfaceAlerte.fxml"));
                   Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    

       });
               
           modifbtn.setOnMouseClicked(e -> {
            
           
         AlerteModifController alerteController=new AlerteModifController();
      idAlerte=alerte.getId();
             System.out.println(idAlerte);

       Parent affichageAlerte;
                    try {
                        affichageAlerte = FXMLLoader.load(getClass().getResource("AlerteModif.fxml"));
                   Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    

       });
         
                
            }
            
        }
       
        else{
           System.out.println("vide");
     

     // Labelvide.setVisible(true);
     //   Labelvide.setText("Vous n'avez aucun utilisateur dans la liste ");
          
        }
               
       
    }  
          @FXML
    void rechercherAlerte(KeyEvent event) throws IOException {

if("".equals(txtRecherhe.getText()))
{    lstAlerte=alerteService.getAll(user.getId());
alertesVBox.getChildren().clear();
              alertesVBox.getChildren().add(alerteAnchorPane);
           getAllAlerteList(lstAlerte);}
      else{
            List<Alerte> lAlerte = new ArrayList<>();
            lAlerte = alerteService.rechercher(txtRecherhe.getText(), user.getId());
            alertesVBox.getChildren().clear();
             alertesVBox.getChildren().add(alerteAnchorPane);
            
            getAllAlerteList(lAlerte);
       }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;     }
}
