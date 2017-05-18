/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.services.PublicationFavoritesService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class addPubFavController implements Initializable,ControlledScreen{
     ScreensController screen;
private List<Annonce> lstannonces;
    @FXML
    private VBox annoncesVBox;
    @FXML
    private JFXButton ajouterOfferButton;
    @FXML
    private AnchorPane annoncesAnchorPane;
    @FXML
    private Label fromToLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label dateDepartLabel;
    @FXML
    private Separator separator;
    @FXML
    private Label prixLabel1;
     @FXML
    private Label  nomLabel;
    @FXML
    private Label placesLabel;
    @FXML
    private ImageView likeImage;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(addPubFavController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        getallAnnonce();

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
    public void getallAnnonce(){
           AnonncesService annonceService = new AnonncesService();
            User user=new User(User.getIdd());
        lstannonces = annonceService.GetAnnonceNotFavoris(user.getId());
   
        
        if(!lstannonces.isEmpty()){
            
            for (Annonce annonce : lstannonces) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(fromToLabel.getFont());
        fromToLabel2.setTextFill(fromToLabel.getTextFill());
        fromToLabel2.setLayoutX(fromToLabel.getLayoutX());
        fromToLabel2.setLayoutY(fromToLabel.getLayoutY());
        fromToLabel2.setText(annonce.getLieuDepart()+"->"+annonce.getLieuArriver());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateDepartLabel.getFont());
        dateDepartLabel2.setTextFill(dateDepartLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateDepartLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateDepartLabel.getLayoutY());
        dateDepartLabel2.setText("Date Depart: "+annonce.getTripDate());

        //prixLabel
        Label prixLabel3 = new Label();
        prixLabel3.setFont(prixLabel.getFont());
        prixLabel3.setTextFill(prixLabel.getTextFill());
        prixLabel3.setLayoutX(prixLabel.getLayoutX());
        prixLabel3.setLayoutY(prixLabel.getLayoutY());
        prixLabel3.setText(""+annonce.getPrix());
           Label prixLabel4 = new Label();
        prixLabel4.setFont(prixLabel1.getFont());
        prixLabel4.setTextFill(prixLabel1.getTextFill());
        prixLabel4.setLayoutX(prixLabel1.getLayoutX());
        prixLabel4.setLayoutY(prixLabel1.getLayoutY());
        prixLabel4.setText(prixLabel1.getText());
///////////////////
        Label placesLabel2 = new Label();
        placesLabel2.setFont(placesLabel.getFont());
        placesLabel2.setTextFill(placesLabel.getTextFill());
        placesLabel2.setLayoutX(placesLabel.getLayoutX());
        placesLabel2.setLayoutY(placesLabel.getLayoutY());
        placesLabel2.setText("places disponible: "+annonce.getNbrPersonne());
      
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
     
ImageView likeimg1 = new ImageView();
        
        likeimg1.setImage(likeImage.getImage());
        likeimg1.setLayoutX(likeImage.getLayoutX());
        likeimg1.setLayoutY(likeImage.getLayoutY());
        likeimg1.setStyle(likeImage.getStyle());
        likeimg1.setFitWidth(likeImage.getFitWidth());
        likeimg1.setFitHeight(likeImage.getFitHeight());
        
//        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,prixLabel3,prixLabel4,placesLabel2,separator1,likeimg1);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
          
        newAnnoncesAnchorPane.setOnMouseClicked(e -> {
            
            System.out.println(annonce.getIdAnnonce());
        

            
                 
       });
        likeimg1.setOnMouseClicked(e -> {
            
            System.out.println(annonce.getIdAnnonce());
        
 PublicationFavorite pubFavoris=new PublicationFavorite(user,annonce);
            IPublicationFavoritesService pubfavService=new PublicationFavoritesService();
pubfavService.add(pubFavoris);
            new  myDownloader().start();    
       
            Parent affichageAlerte;
                    try {
                        affichageAlerte = FXMLLoader.load(getClass().getResource("PubFavlist.fxml"));
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
    }
    
    @FXML
    void retour(MouseEvent event) throws IOException {
                  Parent affichageAlerte = FXMLLoader.load(getClass().getResource("PubFavList.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
 
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
    class myDownloader extends Thread{
    public void run (){
    
//    try{Thread.sleep(5000);}
//    catch (InterruptedException ex) {
//            Logger.getLogger(addPubFavController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    Notifications notificationBuilder=Notifications.create()
            .title("publication Favorite")
            .text("Vous avez ajouté une publication à votre liste des favoris")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT)
            .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("clicked on notif");      
 
 
        }
    }
            
            
            );
    notificationBuilder.darkStyle();
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
                notificationBuilder.show();

        }
    });
    }
    
    
    }

    public addPubFavController() {
    }
    
}
