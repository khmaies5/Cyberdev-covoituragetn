package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.services.GroupeService;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficherListeGroupesController implements Initializable {

    @FXML
    private TableView<Groupe> TableGroupe;
    @FXML
    private TableColumn<?, ?> CLNom;
    @FXML
    private TableColumn<?, ?> CLDescription;
    @FXML
    private TableColumn<?, ?> CLDate;
    @FXML
    private TableColumn<?, ?> CLNbAdherants;

    IGroupeService groupeService = new GroupeService();

    public static List<Groupe> groupes;

    private final ObservableList<Groupe> ListGroupes = FXCollections.observableArrayList();;
   
   
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;
//        @FXML
//        
//    private JFXTextField txtNomGroupe;
//           @FXML
//    private JFXTextArea txtDescriptionGroupe;

    @FXML
 private JFXButton btnCreerGroupe;
//    @FXML
//   private JFXButton btnAnnuler;
//    @FXML
//    private JFXButton btnModifier;
    @FXML
  private Label IDGroupe;
        @FXML
    private JFXButton btnModifierGroupe;
    
                 @FXML
    private JFXButton btnReturnouerListe;
    public static AnchorPane rootP;
    
    private Connection connection;

    public AfficherListeGroupesController() {
        connection = DataSource.getInstance().getConnection();

    }

    private void SetCellTable() {

        CLNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CLDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        CLDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

//   CLNbAdherants.setCellValueFactory(new PropertyValueFactory<>()); 
//         System.out.println(groupeService.getAll());
        groupes = groupeService.getAll();
        for (Groupe gr : groupes) 
        {
            ListGroupes.add(gr);
        }
        TableGroupe.setItems(ListGroupes);

    }
//    @FXML
// private void annuler(ActionEvent event) {
//        txtNomGroupe.setText("");
//        txtDescriptionGroupe.setText("");
//       
//    }  

    @FXML
    private void creerGroupe(ActionEvent event) throws Exception{
  
Parent creerGroupe = FXMLLoader.load(getClass().getResource("CreerGroupe.fxml"));
     Scene sceneAffichage = new Scene(creerGroupe);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    
    stage.setScene(sceneAffichage);
      stage.show();
    }
//    @FXML
//    private void modifierGroupe(ActionEvent event) throws Exception{
//         
//       int id=Integer.parseInt(IDGroupe.getText());
//        System.out.println(id);
// Groupe groupe =new Groupe (id,txtNomGroupe.getText(),txtDescriptionGroupe.getText());
//         System.out.println(groupe);
// groupeService.update(groupe);
//Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherListeGroupes.fxml"));
//    Scene sceneAffichage = new Scene(creerGroupe);
//     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
//    
//    stage.setScene(sceneAffichage);
//      stage.show();
//    }
        @FXML
    private void mesGroupe(ActionEvent event) throws Exception{
         
   Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
     Scene sceneAffichage = new Scene(creerGroupe);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    
    stage.setScene(sceneAffichage);
      stage.show();
    }
  private void setCellValueFromTableToText(){
    TableGroupe.setOnMouseClicked(new  EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            Groupe gr = TableGroupe.getItems().get(TableGroupe.getSelectionModel().getSelectedIndex());
           // txtDescriptionGroupe.setText(gr.getDescription());
           // txtNomGroupe.setText(gr.getNom());
            IDGroupe.setText(Integer.toString(gr.getId()));
        }
       
    });
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
        SetCellTable();
        // setCellValueFromTableToText(); 

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeGroupesController.class.getName()).log(Level.SEVERE, null, ex);
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
