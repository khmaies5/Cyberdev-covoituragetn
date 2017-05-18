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
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.PublicationFavoritesService;
import edu.esprit.pi.technique.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class AffichagePubFavoritesController implements Initializable {
 
        @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
          @FXML
    private Label LId;
    
    @FXML
    private AnchorPane root;
            @FXML
    private TableView<PublicationFavorite> TablePubFav;

     @FXML
        private TableColumn<?, ?> CLDateEnregistrement;
       @FXML
       private TableColumn<?,? > clDateV;
         @FXML
         private TableColumn<?, ?> CLLieuDepart;
           @FXML
           private TableColumn<?, ?> CLLieuArrivee;
      
   
       private Connection connection;
    
    IPublicationFavoritesService pubFavService= new PublicationFavoritesService();
    public static List<PublicationFavorite> lstPubFav;

     private final ObservableList<PublicationFavorite> ListPubFav = FXCollections.observableArrayList();
    public AffichagePubFavoritesController() {
                        connection = DataSource.getInstance().getConnection();

    }
     @FXML
    private void ajouterPubFavorites(ActionEvent event) throws Exception{
        
       User user=new User(User.getIdd());
       Annonce  annonce =new Annonce(3);
         PublicationFavorite pubFav =new PublicationFavorite (user,annonce);
        pubFavService.add(pubFav);  
    }
      
    private void  SetCellTable()
    {
   CLDateEnregistrement.setCellValueFactory(new PropertyValueFactory<>("date_enregistrement"));
   clDateV.setCellValueFactory(new PropertyValueFactory<>("acreator"));
 //clDateV.setCellValueFactory(new PropertyValueFactory<>("trip_date"));

//   CLLieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieuDepart"));
//   CLLieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieuArriver"));
//  
          lstPubFav = new ArrayList<>();
           User user=new User(User.getIdd());
                                         

          lstPubFav = pubFavService.getAll(user.getId());
              System.out.println(lstPubFav);
           for (PublicationFavorite pubFav : lstPubFav) {
              ListPubFav.add(pubFav);
                      TablePubFav.setItems(ListPubFav);

            }
          
    }


    
    
    
     

   
 
     
               

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   SetCellTable();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AffichagePubFavoritesController.class.getName()).log(Level.SEVERE, null, ex);
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
