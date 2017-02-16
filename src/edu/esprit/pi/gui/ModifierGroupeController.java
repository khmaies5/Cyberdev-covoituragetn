package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static edu.esprit.pi.gui.AfficherMesGroupesController.groupeSelectionne;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;

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

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModifierGroupeController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;
    
    @FXML
    private JFXTextField txtNomGroupe;
    @FXML
    private JFXTextArea txtDescriptionGroupe;
    @FXML
    private Label IDGroupe;
 
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXButton btnRetour;
    @FXML
    private JFXButton btnAnnuler;

    public static AnchorPane rootP;
    private Connection connection;
    IAbonnesService abonnesService = new AbonnesService();
    IGroupeService groupeService = new GroupeService();

Groupe groupeSelectionne ; 

  AfficherMesGroupesController groupeController =new AfficherMesGroupesController();

 
   

    public ModifierGroupeController() {
        connection = DataSource.getInstance().getConnection();
          
//System.out.println(groupeSelectionne);
     
  

    }


    @FXML
    private void annuler(ActionEvent event) {
        txtNomGroupe.setText(groupeSelectionne.getNom());
        txtDescriptionGroupe.setText(groupeSelectionne.getDescription());

    }

    @FXML
    private void modifierGroupe(ActionEvent event) throws Exception {

        int id = Integer.parseInt(IDGroupe.getText());
        System.out.println(id);
        Groupe groupe = new Groupe(id, txtNomGroupe.getText(), txtDescriptionGroupe.getText());
        System.out.println(groupe);
        groupeService.update(groupe);
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("ModifierGroupe.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    private void ReturnouerListe(ActionEvent event) throws Exception {

        Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       rootP = root;

        try {
           
      txtNomGroupe.setText(groupeSelectionne.getNom());
   
 txtDescriptionGroupe.setText(groupeSelectionne.getDescription());
       
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);

        } catch (IOException ex) {
           Logger.getLogger(ModifierGroupeController.class.getName()).log(Level.SEVERE, null, ex);
      }
 
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

}
