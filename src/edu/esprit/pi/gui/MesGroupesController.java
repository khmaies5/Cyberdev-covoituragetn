/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class MesGroupesController implements Initializable,ControlledScreen  {
   public static ScreensController screen;

    @FXML
    private TextField txtRechercher;
    @FXML
    private Button btnCreerGroupe;
    @FXML
    private VBox VBoxgroupes;
    @FXML
    private AnchorPane GroupesAnchorPane;
    @FXML
    private Label LBDescription;
    @FXML
    private Label LBDate;
    @FXML
    private Label LB1;
    @FXML
    private Separator separator;
    @FXML
    private Label LBNbAdherant;
    @FXML
    private ImageView groupeImageView;
    @FXML
    private Label LBUserCeateur;
    @FXML
    private Label LBNbSujets;
    @FXML
    private Label LBNomGroupe;
    @FXML
    private Label LB2;
    @FXML
    private Button btnEntrerGroupe;
    @FXML
    private ScrollPane ScrollPaneMembres;
    @FXML
    private VBox VBoxMembre;
    @FXML
    private AnchorPane membresAnchorPane;
    @FXML
    private Label LBMembre;
    @FXML
    private ImageView membreImageView;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    IAbonnesService abonnesService = new AbonnesService();
    private List<Abonnes> abonnes;
    private List<Groupe> groupes;
    IGroupeService groupeService = new GroupeService();
    IUserService userService = new UserService();
   User user ;
    public static Groupe groupeSelectionne;
    public static User userConn;
    public static String recherche = null;
LesGroupesController groupeController=new LesGroupesController();
    public static String getRecherche() {
        return recherche;
    }

    public static User getUserConn() {
        return userConn;
    }

    public static Groupe getGroupeSelectionne() {
        return groupeSelectionne;
    }

    public MesGroupesController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 user=new User(User.getIdd());
 userConn=user;
        groupes = groupeService.getGroupbyUser(user);
        try {

            getallMyGroupeList(groupes);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void rechercherGroupe(KeyEvent event) throws IOException {

        recherche = txtRechercher.getText();
        if ("".equals(recherche)) {
            groupes = groupeService.getGroupbyUser(user);
            VBoxgroupes.getChildren().clear();
            VBoxgroupes.getChildren().add(GroupesAnchorPane);
            getallMyGroupeList(groupes);
        } else {
            List<Groupe> lstGroupe = new ArrayList<>();
            lstGroupe = groupeService.rechercherMesGroupes(txtRechercher.getText(), user.getId());

            Set set = new HashSet();
            set.addAll(lstGroupe);
            ArrayList distinctList = new ArrayList(set);
            System.out.println(distinctList);
            VBoxgroupes.getChildren().clear();
            VBoxgroupes.getChildren().add(GroupesAnchorPane);

            getallMyGroupeList(distinctList);
        }
    }

    @FXML
    private void creerGroupe(ActionEvent event) throws IOException {

        CreerGroupeController controller = new CreerGroupeController();

        Stage stage;

        FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("CreerGroupe.fxml"));
        Parent root2 = afficher.load();
        stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnCreerGroupe.getScene().getWindow());
        stage.showAndWait();
        getallMyGroupeList(groupes);
    }

    public void getallMyGroupeList(List<Groupe> groupes) throws MalformedURLException {

        User UserCreator = null;
        if (!groupes.isEmpty()) {
            for (Groupe gr : groupes) {

                abonnes = abonnesService.findByIdGroupe(gr.getId());
                AnchorPane GroupesAnchorPane2 = new AnchorPane();

                GroupesAnchorPane2.setStyle(GroupesAnchorPane.getStyle());
                GroupesAnchorPane2.setEffect(GroupesAnchorPane.getEffect());

                /**
                 * *************Data Annonce ****
                 */
                Label newLB1 = new Label();
                newLB1.setFont(LB1.getFont());
                newLB1.setTextFill(LB1.getTextFill());
                newLB1.setLayoutX(LB1.getLayoutX());
                newLB1.setLayoutY(LB1.getLayoutY());
                newLB1.setText(LB1.getText());

                /**
                 * *************Data Annonce ****
                 */
                Label newLB2 = new Label();
                newLB2.setFont(LB2.getFont());
                newLB2.setTextFill(LB2.getTextFill());
                newLB2.setLayoutX(LB2.getLayoutX());
                newLB2.setLayoutY(LB2.getLayoutY());
                newLB2.setText(LB2.getText());

                //**********************Titre sujet*******/
                Label LBNomGroupe2 = new Label();
                LBNomGroupe2.setFont(LBNomGroupe.getFont());
                LBNomGroupe2.setTextFill(LBNomGroupe.getTextFill());
                LBNomGroupe2.setLayoutX(LBNomGroupe.getLayoutX());
                LBNomGroupe2.setLayoutY(LBNomGroupe.getLayoutY());
                LBNomGroupe2.setText("Nom du groupe : " + " " + gr.getNom());

                /**
                 * ****************contenu sujet***********
                 */
                Label LBDescription2 = new Label();
                LBDescription2.setFont(LBDescription.getFont());
                LBDescription2.setTextFill(LBDescription.getTextFill());
                LBDescription2.setLayoutX(LBDescription.getLayoutX());
                LBDescription2.setLayoutY(LBDescription.getLayoutY());
                LBDescription2.setText(gr.getDescription());
                /**
                 * ***Nom User creacteur sujet**
                 */
                Label LBUserCeateur2 = new Label();
                LBUserCeateur2.setFont(LBUserCeateur.getFont());
                LBUserCeateur2.setTextFill(LBUserCeateur.getTextFill());
                LBUserCeateur2.setLayoutX(LBUserCeateur.getLayoutX());
                LBUserCeateur2.setLayoutY(LBUserCeateur.getLayoutY());

                for (Abonnes ab : abonnes) {
                    if (ab.getRoleUser() == "admin") {

                    }
                    UserCreator = userService.findById(ab.getUser().getId());

                    LBUserCeateur2.setText(UserCreator.getNom() + " " + UserCreator.getPrenom());
                }

                ////********Image User Createur Sujet*****/
                ImageView groupeImageView2 = new ImageView();

                groupeImageView2.setLayoutX(groupeImageView.getLayoutX());
                groupeImageView2.setLayoutY(groupeImageView.getLayoutY());
                groupeImageView2.setStyle(groupeImageView.getStyle());
                groupeImageView2.setFitWidth(groupeImageView.getFitWidth());
                groupeImageView2.setFitHeight(groupeImageView.getFitHeight());
                if (gr.getPathImage() == null) {
                    groupeImageView2.setImage(groupeImageView.getImage());
                } else {
                    File file = new File(gr.getPathImage());

                    System.out.println(file);
                    URL url = file.toURI().toURL();

                    Image image = new Image(url.toString());
                    groupeImageView2.setImage(image);
                }
                /**
                 * ************** NbReponses**
                 */
                Label LBNbAdherant2 = new Label();
                LBNbAdherant2.setFont(LBNbAdherant.getFont());
                LBNbAdherant2.setTextFill(LBNbAdherant.getTextFill());
                LBNbAdherant2.setLayoutX(LBNbAdherant.getLayoutX());
                LBNbAdherant2.setLayoutY(LBNbAdherant.getLayoutY());
                LBNbAdherant2.setText(abonnes.size() + "adhèrants");

                /**
                 * *************Data Annonce ****
                 */
                Label LBDate2 = new Label();
                LBDate2.setFont(LBDate.getFont());
                LBDate2.setTextFill(LBDate.getTextFill());
                LBDate2.setLayoutX(LBDate.getLayoutX());
                LBDate2.setLayoutY(LBDate.getLayoutY());
                LBDate2.setText("Date de crèation: " + gr.getDateCreation().toString());

                /**
                 * **separator**
                 */
                //separator
                Separator separator1 = new Separator();
                separator1.setLayoutX(separator.getLayoutX());
                separator1.setLayoutY(separator.getLayoutY());
                separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
                separator1.setOrientation(Orientation.VERTICAL);

                /**
                 * ************afficher button repondre
                 */
                Button btnEntrerGroupe2 = new Button();
                btnEntrerGroupe2.setFont(btnEntrerGroupe.getFont());
                btnEntrerGroupe2.setTextFill(btnEntrerGroupe.getTextFill());
                btnEntrerGroupe2.setLayoutX(btnEntrerGroupe.getLayoutX());
                btnEntrerGroupe2.setLayoutY(btnEntrerGroupe.getLayoutY());
                btnEntrerGroupe2.setText(btnEntrerGroupe.getText());

                GroupesAnchorPane2.getChildren().addAll(newLB2, newLB1, LBNomGroupe2, LBDescription2, LBUserCeateur2, groupeImageView2, LBNbAdherant2, LBDate2, separator1, btnEntrerGroupe2);
                VBoxgroupes.getChildren().addAll(GroupesAnchorPane2);

                btnEntrerGroupe2.setOnMouseClicked(e -> {
                    
                    groupeSelectionne = gr;
                    GroupeController controller = new GroupeController();
System.out.println(groupeSelectionne);
                 

                    {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen22ID, ScreensFramework.screen22File);
                    RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen22ID);}

                });

            }
        } else {
            AnchorPane GroupesAnchorPane2 = new AnchorPane();
            Label LBAucunGroupe = new Label();
            LBAucunGroupe.setFont(LB2.getFont());
            LBAucunGroupe.setTextFill(LB2.getTextFill());
            LBAucunGroupe.setLayoutX(LBDescription.getLayoutX());
            LBAucunGroupe.setLayoutY(LBDescription.getLayoutY());
            LBAucunGroupe.setText("aucun groupe trouvè");
            LBAucunGroupe.setFont(Font.font("Aharoni Bold", 20));

            GroupesAnchorPane2.getChildren().addAll(LBAucunGroupe);
            VBoxgroupes.getChildren().add(GroupesAnchorPane2);
        }
    }

  @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }

//    @Override
//    public void start(Stage stage) throws Exception {
// Parent root = FXMLLoader.load(getClass().getResource("MesGroupes.fxml"));
//       
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//      
//        stage.show();    }
//       public static void main(String[] args) {
//        launch(args);
//    }
}
