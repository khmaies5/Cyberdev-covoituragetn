package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AfficherMesGroupesController implements Initializable {

    @FXML
    private TableView<Groupe> TableGroupe;
    @FXML
    private TableColumn<?, ?> CLNom;
    @FXML
    private TableColumn<?, ?> CLDescription;
    @FXML
    private TableColumn<?, ?> CLDate;
    @FXML
    private TableColumn<?, ?> CLRole;
    
    IGroupeService groupeService = new GroupeService();
    IAbonnesService abonnesService = new AbonnesService();
    public static List<Groupe> groupes;
    public static List<Abonnes> abonnes;
    private final ObservableList<Groupe> ListGroupes = FXCollections.observableArrayList();
    
   
   
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
//            @FXML
//        
//   private Label LBNomGroupe;
//           @FXML
//   private Label LBDescriptionGroupe;

    @FXML
    private JFXButton btnCreerGroupe;
   @FXML
   private JFXButton btnRetour;
  @FXML
  private JFXButton btnEnregistrer;
    @FXML
    private Label IDGroupe;
        @FXML
    private Label LabelDonnesGroupe;

         @FXML
    private JFXButton btnannulerModif;
    @FXML
    private JFXButton btnModifierGroupe;
    @FXML
    private JFXButton btnDesabonnerGroupe;
     @FXML
    private JFXButton btnEntrerGroupe;
       @FXML
    private JFXButton btnRetourTousLesGroues;

    public static AnchorPane rootP;

    private Connection connection;
    User user = new User(1);
public static Groupe groupeSelectionne;

    public Groupe getGroupeSelectionne() {
        return groupeSelectionne;
    }

    public void setGroupeSelectionne(Groupe groupeSelectionne) {
        this.groupeSelectionne = groupeSelectionne;
    }
    public AfficherMesGroupesController() {
        connection = DataSource.getInstance().getConnection();

    }

    private void SetCellTable() {

        CLNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CLDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        CLDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
       
        User user = new User(1);

        groupes = groupeService.getGroupbyUser(user);
        for (Groupe gr : groupes) {
            ListGroupes.add(gr);
         
        }
        TableGroupe.setItems(ListGroupes);

    }
 

    @FXML
    private void annulerModif(ActionEvent event) {
        txtNomGroupe.setText(groupeSelectionne.getNom());
        txtDescriptionGroupe.setText(groupeSelectionne.getDescription());

    }

    @FXML
    private void creerGroupe(ActionEvent event) throws Exception {

        Parent creerGroupe = FXMLLoader.load(getClass().getResource("CreerGroupe.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }
        @FXML
    private void desabonner(ActionEvent event) throws Exception {
         abonnesService.desabonner(groupeSelectionne, user);
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
        stage.show();
    }
    @FXML
    private void entrerGroupe(ActionEvent event) throws Exception{
         
        try {
           GroupeController controller = new GroupeController();
           //  controller.initVariable(groupeSelectionne);
        FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("Groupe.fxml"));
         Parent  afficherGroupe = afficher.load();

        Scene sceneAffichage = new Scene(afficherGroupe);
      
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(sceneAffichage);
                    stage.show();

                } catch (IOException ex) {
                                        Logger.getLogger(AfficherMesGroupesController.class.getName()).log(Level.SEVERE, null, ex);

                    Logger.getLogger(AfficherMesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
  public Groupe  initVariable(){
      System.out.println(IDGroupe);return groupeSelectionne;};
    @FXML
    private void ReturnouerListe(ActionEvent event) throws Exception{
   
  
    
  
Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
     Scene sceneAffichage = new Scene(creerGroupe);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    
    stage.setScene(sceneAffichage);
      stage.show();
    }
    
                 @FXML
    private void retournerTousLesgroupes(ActionEvent event) throws Exception {
 
 
Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherListeGroupes.fxml"));
    Scene sceneAffichage = new Scene(creerGroupe);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    
    stage.setScene(sceneAffichage);
      stage.show();
    }
             @FXML
    private void enregistrer(ActionEvent event) throws Exception {
 //int id=Integer.parseInt(IDGroupe.getText());
       // System.out.println(id);
 Groupe groupe =new Groupe (groupeSelectionne.getId(),txtNomGroupe.getText(),txtDescriptionGroupe.getText());
         System.out.println(groupe);
 groupeService.update(groupe);
Parent creerGroupe = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
    Scene sceneAffichage = new Scene(creerGroupe);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    
    stage.setScene(sceneAffichage);
      stage.show();
    }

    @FXML
    private void modifierGroupe(ActionEvent event) throws Exception {
     
    
  
//Parent creerGroupe = FXMLLoader.load(getClass().getResource("ModifierGroupe.fxml"));
//     Scene sceneAffichage = new Scene(creerGroupe);
//     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
//    
//    stage.setScene(sceneAffichage);
//      stage.show();
hideElmementAffichageTableau();
afficherElmementModif();
    }

    private void setCellValueFromTableToText() {

        TableGroupe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 groupeSelectionne = TableGroupe.getItems().get(TableGroupe.getSelectionModel().getSelectedIndex());
           LabelDonnesGroupe.setText("le groupe sèlectionnè est: "+groupeSelectionne.getNom()+" "+"vous voulez:");
             System.out.println(groupeSelectionne.getId());
           IDGroupe.setText(Integer.toString(groupeSelectionne.getId()));
                   btnEntrerGroupe.setVisible(true);
                 btnDesabonnerGroupe.setVisible(true);
        btnModifierGroupe.setVisible(true);
      
              }});
        
      

    }
    private void hideElmementAffichageTableau()
    {
  
        btnDesabonnerGroupe.setVisible(false);
        btnModifierGroupe.setVisible(false);
        IDGroupe.setVisible(false);
TableGroupe.setVisible(false);
LabelDonnesGroupe.setVisible(false);
btnCreerGroupe.setVisible(false);
btnRetourTousLesGroues.setVisible(false);
btnDesabonnerGroupe.setVisible(false);
btnModifierGroupe.setVisible(false);
 btnEntrerGroupe.setVisible(false);
    }
   
     private void hideElmementModif()
    {
     txtDescriptionGroupe.setVisible(false);
     txtNomGroupe.setVisible(false);
     btnEnregistrer.setVisible(false);
    btnEntrerGroupe.setVisible(false);
    btnRetour.setVisible(false);
        
        IDGroupe.setVisible(false);
          
btnannulerModif.setVisible(false);




//LBDescriptionGroupe.setVisible(false);
//LBNomGroupe.setVisible(false);
    }
    private void afficherElmementModif()
    {
 txtDescriptionGroupe.setVisible(true);
txtNomGroupe.setVisible(true);
btnEnregistrer.setVisible(true);
txtNomGroupe.setText(groupeSelectionne.getNom());
txtDescriptionGroupe.setText(groupeSelectionne.getDescription());
btnRetour.setVisible(true);
btnannulerModif.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
        SetCellTable();
        btnDesabonnerGroupe.setVisible(false);
btnModifierGroupe.setVisible(false);
 btnEntrerGroupe.setVisible(false);
setCellValueFromTableToText();
hideElmementModif();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AfficherMesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
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
