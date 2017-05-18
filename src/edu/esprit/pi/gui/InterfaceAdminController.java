/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.services.PublicationFavoritesService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class InterfaceAdminController implements Initializable {
 private List<PublicationFavorite> lstPubFav;
    @FXML
    private VBox adminVBox;
    @FXML
    private AnchorPane adminAnchorPane;
    @FXML
    private Label UserLabel;
    @FXML
    private Label annonceLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Separator separator;
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
            Logger.getLogger(InterfaceAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        getAllPubList();

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
        // TODO
       
       public void getAllPubList(){
           
       
     IPublicationFavoritesService pubFavService = new PublicationFavoritesService();
          
        lstPubFav = pubFavService.getAll();
      
        
        if(!lstPubFav.isEmpty()){
      
            
            for (PublicationFavorite pubFav : lstPubFav) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(adminAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(adminAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(annonceLabel.getFont());
        fromToLabel2.setTextFill(annonceLabel.getTextFill());
        fromToLabel2.setLayoutX(annonceLabel.getLayoutX());
        fromToLabel2.setLayoutY(annonceLabel.getLayoutY());
        fromToLabel2.setText(pubFav.getAcreator().getLieuDepart()+"->"+pubFav.getAcreator().getLieuArriver());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateLabel.getFont());
        dateDepartLabel2.setTextFill(dateLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateLabel.getLayoutY());
        dateDepartLabel2.setText("Date d'enregistrement: "+pubFav.getDateEnregistrement());

        //prixLabel
        Label prixLabel3 = new Label();
        prixLabel3.setFont(UserLabel.getFont());
        prixLabel3.setTextFill(UserLabel.getTextFill());
        prixLabel3.setLayoutX(UserLabel.getLayoutX());
        prixLabel3.setLayoutY(UserLabel.getLayoutY());
        prixLabel3.setText(" "+pubFav.getCreator().getNom()+" "+ pubFav.getCreator().getPrenom());
      

        
        
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
 
        
//        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,prixLabel3,separator1);
        adminVBox.getChildren().add(newAnnoncesAnchorPane);
          
   
            }}}
}

