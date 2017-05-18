/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static edu.esprit.pi.gui.GroupeController.groupe;
import static edu.esprit.pi.gui.GroupeController.sujetSelectionnè;
import static edu.esprit.pi.gui.LesGroupesController.groupeSelectionne2;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IReponseService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.ReponseService;
import edu.esprit.pi.services.SujetService;
import edu.esprit.pi.services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.HBox;
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
public class SujetController implements Initializable,ControlledScreen {
ScreensController screen;
    @FXML
    private ScrollPane ScrollPaneSujet;
    @FXML
    private VBox VBoxAllPage;
    @FXML
    private AnchorPane AnchorePaneSujet;
    @FXML
    private Label LBTitre;
    @FXML
    private Label LBSujet;
    @FXML
    private HBox HboxImages;
    @FXML
    private ImageView IMageSujet;
    @FXML
    private ImageView ImageViewCreatorSujet;
    @FXML
    private Label LBUserCeateurSujet;
    @FXML
    private Separator separator2;
    @FXML
    private AnchorPane AnchorePaneReponses;
    @FXML
    private VBox VBoxreponses;
    @FXML
    private AnchorPane AnchorPaneReponse;
    @FXML
    private ImageView userImageView;
    @FXML
    private Label LBReponse;
    @FXML
    private Label LBUserCeateur;
    @FXML
    private Label LBDate;
    @FXML
    private Separator separator;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    @FXML
    private AnchorPane AnchorPaneRepondre;
    @FXML
    private ImageView ImageViewUserConn;
    @FXML
    private Label LBUserConn;
    @FXML
    private Separator separator1;
    @FXML
    private Button btnRepondreSujet;
    @FXML
    private TextField TXTRepondre;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private ScrollPane ScrollPaneMembres;
    @FXML
    private VBox VBoxMembre;
    @FXML
    private AnchorPane membresAnchorPane;
    @FXML
    private AnchorPane AnchorePaneImage;
    @FXML
    private Label LBMembre;
    @FXML
    private ImageView membreImageView;
    @FXML
    private Label LBAucunSujet;
    @FXML
    private Label LBDatePubSujet;
    GroupeController groupeController = new GroupeController();
    private Sujet sujetSelectionne = groupeController.getSujetSelectionnè();
    private Sujet sujet;

    private List<Sujet> sujets;
    private Abonnes abonnement = groupeController.getAbonnement();
    private List<Reponse> reponses;
    ISujetService affSujetsService = new SujetService();
    IAbonnesService abonnementService = new AbonnesService();
    IUserService userService = new UserService();
    IReponseService reponseService = new ReponseService();
    int idUserConn = 1;
    public static Reponse reponseSelectionnè;
    public static Groupe groupe;
//groupe=sujet.getGroupe();

    public static Groupe getGroupe() {
        return groupe;
    }

    public static Reponse getReponseSelectionnè() {
        return reponseSelectionnè;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            sujet = affSujetsService.findById(sujetSelectionne.getId());
            reponses = reponseService.GetReponseOfSujet(sujet.getId());

            getSujet(sujet);

            getReponses(reponses);
            System.out.println(reponses);
                        System.out.println("aywah  "+abonnement);

            getUserConn();
            getallMemberList();
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);

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

        } catch (MalformedURLException ex) {
            Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getSujet(Sujet sujet) {

        int idAb = sujet.getCreator().getId();

        //**********************Titre sujet*******/
        LBTitre.setText("Titre:" + " " + sujet.getObjet());
        /**
         * ****************contenu sujet***********
         */

        LBSujet.setText(sujet.getTopic());
        /**
         * ***Nom User creacteur sujet**
         */

        Abonnes ab = abonnementService.findById(idAb);

        User UserCreator = userService.findById(ab.getUser().getId());

        LBUserCeateurSujet.setText(UserCreator.getNom() + " " + UserCreator.getPrenom());

        /**
         * ************** NbReponses**
         */
        LBDatePubSujet.setText("Date de publication: " + sujet.getDatePublication().toString());
//        if (sujet.getPathFiles() != null) {
//            System.out.println(sujet.getPathFiles());
//            File file = new File(sujet.getPathFiles());
//            File[] files = null;
//            File directoryToScan = new File(sujet.getPathFiles());
//            files = directoryToScan.listFiles();
//            for (File nom : files) {
//
//                try {
//
//                    URL urlImage = null;
//                    System.out.println("123");
//                    urlImage = file.toURI().toURL();
//                    System.out.println("222222");
//                    ImageView SujetImageView2 = new ImageView();
//                    System.out.println("33333");
//                    Image image = new Image(urlImage.toString());
//                    System.out.println("44444444");
//                    SujetImageView2.setImage(image);
//                    System.out.println("5555555");
//                    newImageAnchorPane.getChildren().addAll(SujetImageView2);
//                    System.out.println("66666666");
//                    //   HboxImages.getChildren().add(newImageAnchorPane);
//                    System.out.println("777777");
//                } catch (MalformedURLException ex) {
//                    Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }

        //  }
    }

    public void getallMemberList() {

        List<Abonnes> membres = abonnementService.findByIdGroupe(sujet.getGroupe().getId());

        if (!membres.isEmpty()) {

            for (Abonnes abonnes : membres) {

                User membre = userService.findById(abonnes.getUser().getId());
                System.out.println(membre);
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
            }
        }

    }

    public void getReponses(List<Reponse> reponses2) throws MalformedURLException {
        reponses = reponseService.GetReponseOfSujet(sujet.getId());
        VBoxreponses.getChildren().clear();
        VBoxreponses.getChildren().add(AnchorPaneReponse);

        if (!reponses.isEmpty()) {

            for (Reponse reponse : reponses) {

                int idAb = sujet.getCreator().getId();

                AnchorPane newReponsePane = new AnchorPane();
                newReponsePane.setStyle(AnchorPaneReponse.getStyle());
                newReponsePane.setEffect(AnchorPaneReponse.getEffect());

                Label LBReponseSujet2 = new Label();
                LBReponseSujet2.setFont(LBReponse.getFont());
                LBReponseSujet2.setTextFill(LBReponse.getTextFill());
                LBReponseSujet2.setLayoutX(LBReponse.getLayoutX());
                LBReponseSujet2.setLayoutY(LBReponse.getLayoutY());
                LBReponseSujet2.setText(reponse.getReponseSujet());

                /**
                 * ***Nom User creacteur sujet**
                 */
                Label newUserCeateurReponse = new Label();
                newUserCeateurReponse.setFont(LBUserCeateur.getFont());
                newUserCeateurReponse.setTextFill(LBUserCeateur.getTextFill());
                newUserCeateurReponse.setLayoutX(LBUserCeateur.getLayoutX());
                newUserCeateurReponse.setLayoutY(LBUserCeateur.getLayoutY());

                Abonnes ab = abonnementService.findById(idAb);

                User UserCreator = userService.findById(ab.getUser().getId());

                newUserCeateurReponse.setText(UserCreator.getNom() + " " + UserCreator.getPrenom());
                ////********Image User Createur Sujet*****/

                ImageView userImageView2 = new ImageView();

                userImageView2.setLayoutX(userImageView.getLayoutX());
                userImageView2.setLayoutY(userImageView.getLayoutY());
                userImageView2.setStyle(userImageView.getStyle());
                userImageView2.setFitWidth(userImageView.getFitWidth());
                userImageView2.setFitHeight(userImageView.getFitHeight());
                if (UserCreator.getPhoto_Profil() != null) {
                    File file = new File(UserCreator.getPhoto_Profil());

                    System.out.println(file);
                    URL url = file.toURI().toURL();

                    Image image = new Image(url.toString());
                    ImageViewUserConn.setImage(image);
                } else {
                    userImageView2.setImage(userImageView.getImage());
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
                LBDate2.setText("publiè le: " + sujet.getDatePublication().toString());

                /**
                 * **separator**
                 */
                //separator
                Separator separatorUserCreator = new Separator();
                separatorUserCreator.setLayoutX(separator.getLayoutX());
                separatorUserCreator.setLayoutY(separator.getLayoutY());
                separatorUserCreator.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
                separatorUserCreator.setOrientation(Orientation.VERTICAL);

                /**
                 * ************afficher button repondre
                 */
                Button btnModif2 = new Button();
                btnModif2.setFont(btnModif.getFont());
                btnModif2.setTextFill(btnModif.getTextFill());
                btnModif2.setLayoutX(btnModif.getLayoutX());
                btnModif2.setLayoutY(btnModif.getLayoutY());
                btnModif2.setText(btnModif.getText());
                btnModif2.setStyle(btnModif.getStyle());
                btnModif2.setScaleX(btnModif.getScaleX());
                btnModif2.setScaleY(btnModif.getScaleY());
                btnModif2.setPrefWidth(btnModif.getPrefWidth());
                btnModif2.setPrefHeight(btnModif.getPrefHeight());
                Button btnSupp2 = new Button();
                btnSupp2.setFont(btnSupp.getFont());
                btnSupp2.setTextFill(btnSupp.getTextFill());
                btnSupp2.setLayoutX(btnSupp.getLayoutX());
                btnSupp2.setLayoutY(btnSupp.getLayoutY());
                btnSupp2.setText(btnSupp.getText());
                btnSupp2.setStyle(btnSupp.getStyle());
                btnSupp2.setScaleX(btnSupp.getScaleX());
                btnSupp2.setScaleY(btnSupp.getScaleY());
                btnSupp2.setPrefWidth(btnSupp2.getPrefWidth());
                btnSupp2.setPrefHeight(btnSupp2.getPrefHeight());
                if (idUserConn == UserCreator.getId()) {
                    btnSupp2.setVisible(true);
                    btnSupp2.setDisable(false);
                    btnModif2.setVisible(true);
                    btnModif2.setDisable(false);
                } else {
                    btnSupp2.setVisible(false);
                    btnSupp2.setDisable(true);
                    btnModif2.setVisible(false);
                    btnModif2.setDisable(true);
                }
                newReponsePane.getChildren().addAll(LBReponseSujet2, newUserCeateurReponse, userImageView2, LBDate2, separatorUserCreator, btnModif2, btnSupp2);
                VBoxreponses.getChildren().add(newReponsePane);

                btnModif2.setOnMouseClicked(
                        e -> {

                            try {
                                reponseSelectionnè = reponse;
                                ModifierReponseController controller = new ModifierReponseController();

                                Stage stage;

                                FXMLLoader afficher = new FXMLLoader();
                                afficher.setLocation(getClass().getResource("ModifierReponse.fxml"));
                                Parent root2 = afficher.load();
                                stage = new Stage();

                                stage.setScene(new Scene(root2));
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.initOwner(btnModif2.getScene().getWindow());
                                stage.showAndWait();
                                VBoxreponses.getChildren().clear();
                                VBoxreponses.getChildren().add(AnchorPaneReponse);

                                getReponses(reponses);
                            } catch (IOException ex) {
                                Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                btnSupp2.setOnMouseClicked(
                        e -> {

                            try {
                                reponseSelectionnè = reponse;
                                reponseService.delete(reponseSelectionnè.getId());
                                VBoxreponses.getChildren().clear();
                                VBoxreponses.getChildren().add(newReponsePane);

                                getReponses(reponses);
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

            }
        } else {

            LBAucunSujet.setText("aucune rèponse crèe");

            LBAucunSujet.setVisible(true);

        }

    }

    public void getUserConn() throws MalformedURLException {

       // Abonnes ab = abonnementService.findById(idUuserCOn);
      //  System.out.println("fffffffff       "+ab.getId());
        User UserConn = userService.findById(User.getIdd());

        LBUserConn.setText(UserConn.getNom() + " " + UserConn.getPrenom());
        if (UserConn.getPhoto_Profil() != null) {

            {
                File file = new File(UserConn.getPhoto_Profil());

                System.out.println(file);
                URL url = file.toURI().toURL();

                Image image = new Image(url.toString());
                ImageViewUserConn.setImage(image);
            }

        }

    }

    @FXML
    private void repondrebuton(ActionEvent event) throws IOException {

        if (TXTRepondre.getText().equals("")) {
            TXTRepondre.setStyle("-fx-border-color: red");
        } else {
            TrayNotification tray = new TrayNotification();
            NotificationType type = NotificationType.INFORMATION;
            tray.showAndDismiss(Duration.seconds(3));

            Reponse reponse = new Reponse(TXTRepondre.getText(), new Sujet(sujet.getId()), abonnement);

            if (reponseService.addReponse(reponse)) {

                tray.setTitle("ajout de la rèponse au sujet" + sujet.getObjet());
                tray.setMessage("votre ajout de rèponse vient d'être effectuée avec succès");
                tray.setNotificationType(type.SUCCESS);
                TXTRepondre.setText("");
            } else {

                tray.setTitle("ajout de la rèponse au sujet " + sujet.getObjet());
                tray.setMessage("votre ajout de rèponse vient d'echouer");
                tray.setNotificationType(type.ERROR);
                tray.showAndDismiss(Duration.seconds(3));

            }
            getReponses(reponses);
        }

    }

    @FXML
    void repondre(MouseEvent event) {
        TXTRepondre.setStyle("-fx-border-color: white");

    }

    @FXML
    void repondrekey(KeyEvent event) {
        TXTRepondre.setStyle("-fx-border-color: white");
    }

    @FXML
    void retournerGroupe(ActionEvent event) {
        GroupeController controller = new GroupeController();

        Parent afficher;
        Scene sceneAffichage;
        Stage stage = new Stage();

        {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen22ID, ScreensFramework.screen22File);
        RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen22ID);}
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
}
