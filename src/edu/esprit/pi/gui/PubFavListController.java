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
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.PublicationFavoritesService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class PubFavListController implements Initializable, ControlledScreen {
      ScreensController screen;
 private List<PublicationFavorite> lstPubFav;
        @FXML
    private Label dateEnregistrement;
    @FXML
    private ImageView suppImage;
    @FXML
    private VBox annoncesVBox;
    @FXML
    private AnchorPane annoncesAnchorPane;
    @FXML
    private Label prixLabel1;
    @FXML
    private Label dateDepartLabel;
    @FXML
    private Separator separator;
    @FXML
    private Label prixLabel;
    @FXML
    private Label placesLabel;
    @FXML
    private Label fromToLabel;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
   
    @FXML
    private JFXButton ajouterOfferButton;
   
   @FXML
    private Label Labelvide;

    public PubFavListController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(PubFavListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       

        getallAnnoncesList();

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

     public void getallAnnoncesList(){
        IPublicationFavoritesService pubFavService = new PublicationFavoritesService();
            User user=new User(User.getIdd());
        lstPubFav = pubFavService.getAll(user.getIdd());
      
        
        if(!lstPubFav.isEmpty()){
      
            
            for (PublicationFavorite pubFav : lstPubFav) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(fromToLabel.getFont());
        fromToLabel2.setTextFill(fromToLabel.getTextFill());
        fromToLabel2.setLayoutX(fromToLabel.getLayoutX());
        fromToLabel2.setLayoutY(fromToLabel.getLayoutY());
        fromToLabel2.setText(pubFav.getAcreator().getLieuDepart()+"->"+pubFav.getAcreator().getLieuArriver());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateDepartLabel.getFont());
        dateDepartLabel2.setTextFill(dateDepartLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateDepartLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateDepartLabel.getLayoutY());
        dateDepartLabel2.setText("Date de Départ: "+pubFav.getAcreator().getTripDate());

        //prixLabel
        Label prixLabel3 = new Label();
        prixLabel3.setFont(prixLabel.getFont());
        prixLabel3.setTextFill(prixLabel.getTextFill());
        prixLabel3.setLayoutX(prixLabel.getLayoutX());
        prixLabel3.setLayoutY(prixLabel.getLayoutY());
        prixLabel3.setText(""+pubFav.getAcreator().getPrix());
        //////
                  Label dateEnregistrementLabel = new Label();
        dateEnregistrementLabel.setFont(dateEnregistrement.getFont());
        dateEnregistrementLabel.setTextFill(dateEnregistrement.getTextFill());
        dateEnregistrementLabel.setLayoutX(dateEnregistrement.getLayoutX());
        dateEnregistrementLabel.setLayoutY(dateEnregistrement.getLayoutY());
        dateEnregistrementLabel.setText("Date d'enregistrement: "+pubFav.getDateEnregistrement());
        /////
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
        placesLabel2.setText("places disponibles: "+pubFav.getAcreator().getNbrPersonne());
        
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
     
ImageView deleteimg1 = new ImageView();
        
        deleteimg1.setImage(suppImage.getImage());
        deleteimg1.setLayoutX(suppImage.getLayoutX());
        deleteimg1.setLayoutY(suppImage.getLayoutY());
        deleteimg1.setStyle(suppImage.getStyle());
        deleteimg1.setFitWidth(suppImage.getFitWidth());
        deleteimg1.setFitHeight(suppImage.getFitHeight());
        
//        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,prixLabel3,prixLabel4,placesLabel2,separator1,deleteimg1,dateEnregistrementLabel);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
          
        newAnnoncesAnchorPane.setOnMouseClicked(e -> {
            
            System.out.println(pubFav.getId());
        
 
        //    ScreensFramework.annonceId=annonce.getId();
           
            
//         screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
//                    screen.setScreen(ScreensFramework.screen3ID);

                 
       });
        deleteimg1.setOnMouseClicked(e -> {
            
            System.out.println(pubFav.getId());
        
pubFavService.delete(pubFav.getId());
               
       
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
                      else{
           System.out.println("vide");
     

      Labelvide.setVisible(true);
        Labelvide.setText("Vous n'avez aucune publication dans la liste ");
          
        }
    }
    

            @FXML
    void ajouterPubFav(ActionEvent event) throws IOException {

                    RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen13ID, ScreensFramework.screen13File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen13ID);
 
    }
      

        @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;
    }


}
