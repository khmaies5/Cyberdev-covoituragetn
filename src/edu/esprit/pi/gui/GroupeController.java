/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static edu.esprit.pi.gui.MesGroupesController.recherche;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IReponseService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.services.ReponseService;
import edu.esprit.pi.services.SujetService;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class GroupeController implements Initializable,ControlledScreen {

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane ScrollPaneSujet;
    @FXML
    private VBox VBoxSujets;
    @FXML
    private AnchorPane sujetsAnchorPane;
    @FXML
    private ImageView userImageView;
    @FXML
    private Label LBSujet1;
    @FXML
    private Label LBUserCeateurSujet;
    @FXML
    private Label LBNomSujet;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Button btnInviterMembres;
    @FXML
    private Button Ajouterpublication;
    @FXML
    private Label LBnomGroupe;
    @FXML
    private Button btnDemarrerChat;
    @FXML
    private ImageView ImageUserConn;
    @FXML
    private Label LBUserConn;
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
    private Button btnModifier;
    @FXML
    private Button btnDesab;
    @FXML
    private ImageView IMageGroupe;
    @FXML
    private Label LBdescription;
    @FXML
    private Separator separator;
    @FXML
    private Button btnRepondreSujet;
    @FXML
    private Label LBDate;
    @FXML
    private Label LBAucunSujet;
    @FXML
    private Label LBNbReponse;
    @FXML
    private TextField txtRecherche;
    MesGroupesController groupeController = new MesGroupesController();
    LesGroupesController groupeController2 = new LesGroupesController();

    ScreensController screen;
    public static Abonnes abonnement;

    public static Abonnes getAbonnement() {
        return abonnement;
    }
    private Groupe groupe2 = groupeController2.getGroupeSelectionne2();
    private Groupe groupe1 = groupeController.getGroupeSelectionne();

    User user = new User(User.getIdd());

    public static Sujet sujetSelectionnè;
    public static Groupe groupe;

    public static Sujet getSujetSelectionnè() {
        return sujetSelectionnè;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public static int idUser;
    public static int idGroupe;

    public static int getIdUser() {
        return idUser;
    }

    public static int getIdGroupe() {
        return idGroupe;
    }

    private List<Sujet> sujets;

    private List<Reponse> reponses;
    ISujetService affSujetsService = new SujetService();
    IAbonnesService abonnementService = new AbonnesService();
    IUserService userService = new UserService();
    IReponseService reponseService = new ReponseService();
    IGroupeService groupeService = new GroupeService();

    public GroupeController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (groupe1 == null) {
            groupe = groupe2;
        } else {
            groupe = groupe1;
        }
      

        idGroupe = groupe.getId();
        LBnomGroupe.setText(groupe.getNom());
        LBdescription.setText(groupe.getDescription());

        if (groupe.getPathImage() == null) {
            IMageGroupe.setImage(IMageGroupe.getImage());
        } else {
            try {
                System.out.println("111");
                File file = new File(groupe.getPathImage());
 System.out.println("2222");
                URL url2 = file.toURI().toURL();
 System.out.println("3333");
                Image image = new Image(url2.toString());
                System.out.println("4444");
                IMageGroupe.setImage(image);
                System.out.println("5555");
            } catch (MalformedURLException ex) {
                Logger.getLogger(GroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getallMemberList();
           System.out.println("66666");
        groupe.getId();
   System.out.println("7777");
        sujets = affSujetsService.getbyIdGroupe(groupe.getId());
           System.out.println("8888");
        getallSujetsList(sujets);
               System.out.println("9999");
        abonnement = abonnementService.findByIdGroupeAndIdUser(idGroupe, User.getIdd());
       System.out.println("1000");
        System.out.println(idGroupe); System.out.println(idUser);
        System.out.println(abonnement);
        if ("admin".equals(abonnement.getRoleUser())) {
      System.out.println("1111");
            btnModifier.setDisable(false);
                  System.out.println("12222");
            btnDesab.setDisable(true);
                  System.out.println("12222");
        } else if ("adhèrant".equals(abonnement.getRoleUser())) {
      System.out.println("133333");
            btnModifier.setDisable(true);
            
            btnDesab.setDisable(false);
            // btnDesab.setMnemonicParsing(true);
        }
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(GroupeController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void invitermembres(ActionEvent event) throws IOException {
        InviterMembreController controller = new InviterMembreController();

        idGroupe = groupe.getId();

        idUser = user.getId();
        Stage stage;

        FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("InviterMembre.fxml"));
        Parent root2 = afficher.load();
        stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnInviterMembres.getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    private void btnAjouterpublication(ActionEvent event) throws IOException {

        try {
            idGroupe = groupe.getId();

            Stage stage;

            FXMLLoader afficher = new FXMLLoader();
            afficher.setLocation(getClass().getResource("AjoutSujet.fxml"));

            Parent root2 = afficher.load();

            stage = new Stage();
            Window parent = btnInviterMembres.getScene().getWindow();
            final EventHandler<WindowEvent> oldHandler = parent.getOnCloseRequest();
            stage.setScene(new Scene(root2));

            stage.initModality(Modality.APPLICATION_MODAL);
            System.out.println(btnInviterMembres.getScene().getWindow());
            stage.initOwner(btnInviterMembres.getScene().getWindow());
            stage.showAndWait();
            getallSujetsList(sujets);

        } catch (IOException ex) {
            Logger.getLogger(GroupeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void DemarrerChat(ActionEvent event) throws IOException {

//           SampleController controller =new SampleController();
//
//          
//            idGroupe=groupe.getId();
//     
//               idUser =user.getId();
//    Stage stage ;
//     
//            FXMLLoader afficher = new FXMLLoader();
//        afficher.setLocation(getClass().getResource("Sample.fxml"));
//         Parent  root2 = afficher.load();
//            stage =new Stage();
//   
//        stage.setScene(new Scene(root2));
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(btnInviterMembres.getScene().getWindow());
//        stage.showAndWait();
    }

    @FXML
    private void rechercher(KeyEvent event) throws MalformedURLException {

        recherche = txtRecherche.getText();
        if (!"".equals(recherche)) {
            List<Sujet> lstSujets = new ArrayList<>();
            lstSujets = affSujetsService.rechercherMessujets(txtRecherche.getText(), groupe);

            Set set = new HashSet();
            set.addAll(lstSujets);
            ArrayList distinctList = new ArrayList(set);

            VBoxSujets.getChildren().clear();
            VBoxSujets.getChildren().add(sujetsAnchorPane);

            getallSujetsList(distinctList);
        } else {
            sujets = affSujetsService.getbyIdGroupe(groupe.getId());
            VBoxSujets.getChildren().clear();
            VBoxSujets.getChildren().add(sujetsAnchorPane);
            getallSujetsList(sujets);
        }

    }

    public void getallSujetsList(List<Sujet> sujets) {

        if (!sujets.isEmpty()) {

            System.out.println(sujets);
            for (Sujet sujet : sujets) {
                int idAb = sujet.getCreator().getId();

                AnchorPane newsujetsAnchorPane = new AnchorPane();

                newsujetsAnchorPane.setStyle(sujetsAnchorPane.getStyle());
                newsujetsAnchorPane.setEffect(sujetsAnchorPane.getEffect());

                //**********************Titre sujet*******/
                Label LBNomSujet2 = new Label();
                LBNomSujet2.setFont(LBNomSujet.getFont());
                LBNomSujet2.setTextFill(LBNomSujet.getTextFill());
                LBNomSujet2.setLayoutX(LBNomSujet.getLayoutX());
                LBNomSujet2.setLayoutY(LBNomSujet.getLayoutY());
                LBNomSujet2.setText("Titre:" + " " + sujet.getObjet());
                /**
                 * ****************contenu sujet***********
                 */
                Label topicLabel2 = new Label();
                topicLabel2.setFont(LBSujet1.getFont());
                topicLabel2.setTextFill(LBSujet1.getTextFill());
                topicLabel2.setLayoutX(LBSujet1.getLayoutX());
                topicLabel2.setLayoutY(LBSujet1.getLayoutY());
                topicLabel2.setText(sujet.getTopic());
                /**
                 * ***Nom User creacteur sujet**
                 */
                Label newUserCeateurSujet = new Label();
                newUserCeateurSujet.setFont(LBUserCeateurSujet.getFont());
                newUserCeateurSujet.setTextFill(LBUserCeateurSujet.getTextFill());
                newUserCeateurSujet.setLayoutX(LBUserCeateurSujet.getLayoutX());
                newUserCeateurSujet.setLayoutY(LBUserCeateurSujet.getLayoutY());

                Abonnes ab = abonnementService.findById(idAb);

                User UserCreator = userService.findById(ab.getUser().getId());

                newUserCeateurSujet.setText(UserCreator.getNom() + " " + UserCreator.getPrenom());
                ////********Image User Createur Sujet*****/

                ImageView userImageView2 = new ImageView();

                userImageView2.setImage(userImageView.getImage());
                userImageView2.setLayoutX(userImageView.getLayoutX());
                userImageView2.setLayoutY(userImageView.getLayoutY());
                userImageView2.setStyle(userImageView.getStyle());
                userImageView2.setFitWidth(userImageView.getFitWidth());
                userImageView2.setFitHeight(userImageView.getFitHeight());

                /**
                 * **************   NbReponses**
                 */
                reponses = reponseService.GetReponseOfSujet(sujet.getId());
                int nbreponses = reponses.size();
                Label LBNbReponse2 = new Label();
                LBNbReponse2.setFont(LBNbReponse.getFont());
                LBNbReponse2.setTextFill(LBNbReponse.getTextFill());
                LBNbReponse2.setLayoutX(LBNbReponse.getLayoutX());
                LBNbReponse2.setLayoutY(LBNbReponse.getLayoutY());
                if (nbreponses == 0) {
                    LBNbReponse2.setText("aucune réponse");
                } else {
                    LBNbReponse2.setText(nbreponses + "réponse(s)");
                }

                /**
                 * *************Data Annonce ****
                 */
                //datedepartlabel
                Label LBDate2 = new Label();
                LBDate2.setFont(LBDate.getFont());
                LBDate2.setTextFill(LBDate.getTextFill());
                LBDate2.setLayoutX(LBDate.getLayoutX());
                LBDate2.setLayoutY(LBDate.getLayoutY());
                LBDate2.setText("Date de publication: " + sujet.getDatePublication().toString());

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
                Button repondreButton2 = new Button();
                repondreButton2.setFont(btnRepondreSujet.getFont());
                repondreButton2.setTextFill(btnRepondreSujet.getTextFill());
                repondreButton2.setLayoutX(btnRepondreSujet.getLayoutX());
                repondreButton2.setLayoutY(btnRepondreSujet.getLayoutY());
                repondreButton2.setText(btnRepondreSujet.getText());
                repondreButton2.setStyle(btnRepondreSujet.getStyle());
                newsujetsAnchorPane.getChildren().addAll(LBNomSujet2, topicLabel2, newUserCeateurSujet, userImageView2, LBNbReponse2, LBDate2, separator1, repondreButton2);
                VBoxSujets.getChildren().add(newsujetsAnchorPane);
                newsujetsAnchorPane.setOnMouseClicked(e -> {

                });

                repondreButton2.setOnMouseClicked(
                        e -> {

                            sujetSelectionnè = sujet;

                            SujetController controller = new SujetController();

                            Parent afficher;
                            Scene sceneAffichage;
                            Stage stage = new Stage();

                            try {
                                afficher = FXMLLoader.load(getClass().getResource("Sujet.fxml"));

                                sceneAffichage = new Scene(afficher);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                stage.setScene(sceneAffichage);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(MesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

            }

        } else {

            AnchorPane GroupesAnchorPane2 = new AnchorPane();
            Label LBAucunSujet2 = new Label();

            LBAucunSujet2.setLayoutX(LBAucunSujet.getLayoutX());
            LBAucunSujet2.setLayoutY(LBAucunSujet.getLayoutX());
            LBAucunSujet2.setText("aucun sujet crèe");
            LBAucunSujet2.setFont(Font.font("Aharoni Bold", 20));

            GroupesAnchorPane2.getChildren().addAll(LBAucunSujet);
            VBoxSujets.getChildren().add(GroupesAnchorPane2);

        }

    }

    public void getallMemberList() {
        List<Abonnes> membres = abonnementService.findByIdGroupe(groupe.getId());

        if (!membres.isEmpty()) {

            for (Abonnes abonnes : membres) {

                User membre = userService.findById(abonnes.getUser().getId());
if(membre.getId()!=User.getIdd()){
                AnchorPane newmembreAnchorPane = new AnchorPane();

                newmembreAnchorPane.setStyle(membresAnchorPane.getStyle());
                newmembreAnchorPane.setEffect(membresAnchorPane.getEffect());

                /**
                 * ***Nom User mMembres**
                 */
                Label newUserMembre = new Label();
                newUserMembre.setFont(LBMembre.getFont());
                newUserMembre.setTextFill(LBMembre.getTextFill());
                newUserMembre.setLayoutX(LBMembre.getLayoutX());
                newUserMembre.setLayoutY(LBMembre.getLayoutY());

                newUserMembre.setText(membre.getNom() + " " + membre.getPrenom());
                ////********Image membres*****/

                ImageView userImageView2 = new ImageView();

                userImageView2.setImage(membreImageView.getImage());
                userImageView2.setLayoutX(membreImageView.getLayoutX());
                userImageView2.setLayoutY(membreImageView.getLayoutY());
                userImageView2.setStyle(membreImageView.getStyle());
                userImageView2.setFitWidth(membreImageView.getFitWidth());
                userImageView2.setFitHeight(membreImageView.getFitHeight());

                newmembreAnchorPane.getChildren().addAll(newUserMembre, userImageView2);
                VBoxMembre.getChildren().add(newmembreAnchorPane);
            }      else {  
                    Label newUserMembre = new Label();
                     AnchorPane newmembreAnchorPane = new AnchorPane();
                    newUserMembre.setText("aucun membre");
 newmembreAnchorPane.getChildren().addAll(newUserMembre);
                VBoxMembre.getChildren().add(newmembreAnchorPane);}}
      
            
        }

    }

    @FXML
    private void desabonner(ActionEvent event) throws Exception {
        abonnementService.desabonner(groupe, user);
        TrayNotification tray = new TrayNotification();
        NotificationType type = NotificationType.INFORMATION;
        tray.showAndDismiss(Duration.seconds(10));
        tray.setTitle("dèsabonnement du groupe " + groupe.getNom());
        tray.setMessage("votre dèsabonnement vient d'être effectuée avec succès");
        tray.setNotificationType(type.INFORMATION);

        {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen22ID, ScreensFramework.screen22File);
                    RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen22ID);}
    }

    public void VerifRole() {
        Abonnes abonnement = abonnementService.findByIdGroupeAndIdUser(idGroupe, idUser);

        if ("adherant".equals(abonnement.getRoleUser())) {

            btnModifier.setDisable(false);
            btnDesab.setDisable(true);
        } else if ("admin".equals(abonnement.getRoleUser())) {

            btnModifier.setDisable(true);
            btnDesab.setDisable(false);

        }
    }

    @FXML
    private void modifier(ActionEvent event) throws Exception {
        ModifierGroupeController controller = new ModifierGroupeController();

        Stage stage;

        FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("ModifierGroupe.fxml"));
        Parent root2 = afficher.load();
        stage = new Stage();

        stage.setScene(new Scene(root2));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnInviterMembres.getScene().getWindow());
        stage.showAndWait();

        idUser = user.getId();

        idGroupe = groupe.getId();
        groupe2 = groupeService.findById(idGroupe);
        LBnomGroupe.setText(groupe2.getNom());
        LBdescription.setText(groupe2.getDescription());

        if (groupe.getPathImage() == null) {
            IMageGroupe.setImage(IMageGroupe.getImage());
        } else {
            try {
                File file = new File(groupe.getPathImage());

                System.out.println(file);
                URL url2 = file.toURI().toURL();

                Image image = new Image(url2.toString());
                IMageGroupe.setImage(image);
            } catch (MalformedURLException ex) {
                Logger.getLogger(GroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }

}
