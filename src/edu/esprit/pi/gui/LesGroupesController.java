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
import static edu.esprit.pi.gui.MesGroupesController.recherche;
import static edu.esprit.pi.gui.MesGroupesController.userConn;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
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
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class LesGroupesController implements Initializable ,ControlledScreen{
   public static ScreensController screen;

    @FXML
    private JFXButton btnCreerGroupe;
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
    private Label LB2;
    @FXML
    private Label LBNomGroupe;
    @FXML
    private Separator separator;
    @FXML
    private Label LBNbAdherant;
    @FXML
    private ImageView groupeImageView;
    @FXML
    private Label LBUserCeateur;
    @FXML
    private Button btnadherer;
    @FXML
    private Label LBNbSujets;
    @FXML
    private TextField txtRechercher;
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
    @FXML
    private Label LBEtatAbonn;

    IGroupeService groupeService = new GroupeService();
    IUserService userService = new UserService();
    ISujetService sujetService = new SujetService();
    User user = new User(User.getIdd());
    IAbonnesService abonnesService = new AbonnesService();
    private List<Abonnes> abonnes;
    private List<Groupe> groupes;
  public static  int  idUser;
    public static Groupe groupeSelectionne2;
    Stage stage = new Stage();

    public static Groupe getGroupeSelectionne2() {
        return groupeSelectionne2;
    }

    public static int getIdUser() {
        return idUser;
    }

    public LesGroupesController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 idUser= user.getId();
        userConn = user;
        groupes = groupeService.getAll();
        try {
            getallMyGroupeList(groupes);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(LesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void creerGroupe(ActionEvent event) throws IOException {

        //   userConn=user;
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
       VBoxgroupes.getChildren().clear();
            VBoxgroupes.getChildren().add(GroupesAnchorPane);
        getallMyGroupeList(groupes);
    }

    @FXML
    private void rechercherGroupe(KeyEvent event) throws MalformedURLException {
        recherche = txtRechercher.getText();
        if ("".equals(recherche)) {
            groupes = groupeService.getGroupbyUser(user);
            VBoxgroupes.getChildren().clear();
            VBoxgroupes.getChildren().add(GroupesAnchorPane);
            getallMyGroupeList(groupes);
        } else {
            List<Groupe> lstGroupe = new ArrayList<>();
            lstGroupe = groupeService.rechercherTousLesGroupes(txtRechercher.getText(), user.getId());

            Set set = new HashSet();
            set.addAll(lstGroupe);
            ArrayList distinctList = new ArrayList(set);
          
            VBoxgroupes.getChildren().clear();
            VBoxgroupes.getChildren().add(GroupesAnchorPane);

            getallMyGroupeList(distinctList);
        }
    }

    public void getallMyGroupeList(List<Groupe> groupes) throws MalformedURLException {

        User UserCreator = null;
 groupes = groupeService.getAll();
        if (!groupes.isEmpty()) {
            for (Groupe gr : groupes) {

                abonnes = abonnesService.findByIdGroupe(gr.getId());
                AnchorPane GroupesAnchorPane2 = new AnchorPane();
                System.out.println(abonnes);
                GroupesAnchorPane2.setStyle(GroupesAnchorPane.getStyle());
                GroupesAnchorPane2.setPrefSize(GroupesAnchorPane.getPrefWidth(), GroupesAnchorPane.getPrefHeight());

                /**
                 * ************label nom Titre ****
                 */
                Label newLB1 = new Label();
                newLB1.setFont(LB1.getFont());
                newLB1.setTextFill(LB1.getTextFill());
                newLB1.setLayoutX(LB1.getLayoutX());
                newLB1.setLayoutY(LB1.getLayoutY());
                newLB1.setText(LB1.getText());

                /**
                 * *************Descip Titre ****
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
                LBNomGroupe2.setText(gr.getNom());
                System.out.println(LBNomGroupe2);
                /**
                 * ****************contenu sujet***********
                 */
                Label LBDescription2 = new Label();
                LBDescription2.setFont(LBDescription.getFont());
                LBDescription2.setTextFill(LBDescription.getTextFill());
                LBDescription2.setLayoutX(LBDescription.getLayoutX());
                LBDescription2.setLayoutY(LBDescription.getLayoutY());
                LBDescription2.setText(gr.getDescription());
                System.out.println(LBDescription2);
                /**
                 * ***Nom User creacteur sujet**
                 */
                Label LBUserCeateur2 = new Label();
                LBUserCeateur2.setFont(LBUserCeateur.getFont());
                LBUserCeateur2.setTextFill(LBUserCeateur.getTextFill());
                LBUserCeateur2.setLayoutX(LBUserCeateur.getLayoutX());
                LBUserCeateur2.setLayoutY(LBUserCeateur.getLayoutY());
                System.out.println(gr);
                for (Abonnes ab : abonnes) {
                    if (ab.getRoleUser() == "admin") {
                        System.out.println(ab);
                    }
                    UserCreator = userService.findById(ab.getUser().getId());
               
                    LBUserCeateur2.setText(UserCreator.getNom() + " " + UserCreator.getPrenom());
                }
                ////********Image User Createur Sujet*****/

                ImageView groupeImageView2 = new ImageView();

                //   groupeImageView2.setImage(groupeImageView.getImage());
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
                     * ************** NbAdherants**
                     */
                    Label LBNbAdherant2 = new Label();
                    LBNbAdherant2.setFont(LBNbAdherant.getFont());
                    LBNbAdherant2.setTextFill(LBNbAdherant.getTextFill());
                    LBNbAdherant2.setLayoutX(LBNbAdherant.getLayoutX());
                    LBNbAdherant2.setLayoutY(LBNbAdherant.getLayoutY());
                    LBNbAdherant2.setText(abonnes.size() + " adhèrant(s)");

                    System.out.println(LBNbAdherant2);
                    /**
                     * **Label nb Sujet**
                     */
                    List<Sujet> sujets = sujetService.getbyIdGroupe(gr.getId());
                    Label newLBNbSujets = new Label();
                    newLBNbSujets.setFont(LBNbSujets.getFont());
                    newLBNbSujets.setTextFill(LBNbSujets.getTextFill());
                    newLBNbSujets.setLayoutX(LBNbSujets.getLayoutX());
                    newLBNbSujets.setLayoutY(LBNbSujets.getLayoutY());

                    newLBNbSujets.setText(sujets.size() + " sujet(s)");
                    /**
                     * *************Data Annonce ****
                     */
                    //datedepartlabel
                    Label LBDate2 = new Label();
                    LBDate2.setFont(LBDate.getFont());
                    LBDate2.setTextFill(LBDate.getTextFill());
                    LBDate2.setLayoutX(LBDate.getLayoutX());
                    LBDate2.setLayoutY(LBDate.getLayoutY());
                    LBDate2.setText("Date de crèation: " + gr.getDateCreation().toString());
                    System.out.println(LBDate2);
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
                    Button btnAdhererGroupe2 = new Button();
                    btnAdhererGroupe2.setFont(btnadherer.getFont());
                    //  btnAdhererGroupe2.setTextFill(btnadherer.getTextFill());
                    btnAdhererGroupe2.setLayoutX(btnadherer.getLayoutX());
                    btnAdhererGroupe2.setLayoutY(btnadherer.getLayoutY());
                    btnAdhererGroupe2.setPrefSize(btnAdhererGroupe2.getPrefWidth(), btnAdhererGroupe2.getPrefHeight());
                    //        btnAdhererGroupe2.setRipplerFill(btnAdhererGroupe2.getRipplerFill());
                    //       btnAdhererGroupe2.setButtonType(btnadherer.getButtonType());
                    //    btnAdhererGroupe2.setRipplerFill(btnadherer.getRipplerFill());
                    /**
                     * **Label abonnement *
                     */
                    Label LBAbonnement = new Label();
                    LBAbonnement.setLayoutX(LBEtatAbonn.getLayoutX());
                    LBAbonnement.setLayoutY(LBEtatAbonn.getLayoutY());
                    LBAbonnement.setFont(Font.font("Aharoni Bold", 14));
                    LBAbonnement.setTextFill(LBEtatAbonn.getTextFill());
                    Abonnes abonnement = abonnesService.findByIdGroupeAndIdUser(gr.getId(), user.getId());

                    if (abonnement != null) {
                        if (abonnement.isEtatAbonnement() == 1) {
                            // btnAdhererGroupe2.setDisable(true);
                            // btnAdhererGroupe2.setVisible(false);
                            LBAbonnement.setText("Vous êtes dèjas abonneè");
                            btnAdhererGroupe2.setText(" Entrer  ");
                            btnAdhererGroupe2.setFont(btnadherer.getFont());
                            btnAdhererGroupe2.setStyle(btnadherer.getStyle());
                            btnAdhererGroupe2.setPrefSize(btnAdhererGroupe2.getPrefWidth(), btnAdhererGroupe2.getPrefHeight());

                        } else {
                            //  btnAdhererGroupe2.setDisable(true);
                            //  btnAdhererGroupe2.setVisible(false);
                            LBAbonnement.setText("      Veuillez vous rèabonner");
                            btnAdhererGroupe2.setText("s'abonner");
                            btnAdhererGroupe2.setFont(btnadherer.getFont());
                            btnAdhererGroupe2.setStyle(btnadherer.getStyle());
                            btnAdhererGroupe2.setPrefSize(btnAdhererGroupe2.getPrefWidth(), btnAdhererGroupe2.getPrefHeight());

                        }
                    } else if (abonnement == null) {
                        LBAbonnement.setText("Veuillez vous abonner");
                        btnAdhererGroupe2.setText("s'abonner");
                        btnAdhererGroupe2.setTextFill(btnadherer.getTextFill());
                        btnAdhererGroupe2.setFont(btnadherer.getFont());
                        btnAdhererGroupe2.setStyle(btnadherer.getStyle());
                    }
                    GroupesAnchorPane2.getChildren().addAll(newLBNbSujets, newLB1, newLB2, LBAbonnement, LBNomGroupe2, LBDescription2, LBUserCeateur2, groupeImageView2, LBNbAdherant2, LBDate2, separator1, btnAdhererGroupe2);
                    VBoxgroupes.getChildren().add(GroupesAnchorPane2);

                    btnAdhererGroupe2.setOnMouseClicked(
                            e -> {
                                Parent afficher;
                                Scene sceneAffichage;
                                Stage stage = new Stage();
                                TrayNotification tray = new TrayNotification();
                                NotificationType type = NotificationType.INFORMATION;
                                tray.showAndDismiss(Duration.seconds(3));

                                groupeSelectionne2 = gr;
                                if (abonnement == null) {
                                    if (abonnesService.addAbonnes(new Abonnes("adherant", user, gr))) {

                                        tray.setTitle("Adhèsion au groupe " + gr.getNom());
                                        tray.setMessage("votre adhésion vient d'être effectuée avec succès");
                                        tray.setNotificationType(type.SUCCESS);

                                        try {
                                            afficher = FXMLLoader.load(getClass().getResource("Groupe.fxml"));

                                            sceneAffichage = new Scene(afficher);
                                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                            stage.setScene(sceneAffichage);
                                            stage.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(LesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {

                                        tray.setTitle("Adhèsion au groupe " + gr.getNom());
                                        tray.setMessage("votre adhésion au groupe vient d'echouer");
                                        tray.setNotificationType(type.ERROR);
                                        tray.showAndDismiss(Duration.seconds(3));

                                    }
                                } else if (abonnement.isEtatAbonnement() == 1) {

                                    try {
                                        afficher = FXMLLoader.load(getClass().getResource("Groupe.fxml"));

                                        sceneAffichage = new Scene(afficher);
                                        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                        stage.setScene(sceneAffichage);
                                        stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(LesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else if (abonnement.isEtatAbonnement() == 0) {

                                    if (abonnesService.ReAbonnes(new Abonnes(abonnement.getId()))) {

                                        tray.setTitle("Rèabonnement au groupe " + gr.getNom());
                                        tray.setMessage("votre rèadhésion vient d'être effectuée avec succès");
                                        tray.setNotificationType(type.INFORMATION);

                                        try {
                                            afficher = FXMLLoader.load(getClass().getResource("Groupe.fxml"));

                                            sceneAffichage = new Scene(afficher);
                                            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                                            stage.setScene(sceneAffichage);
                                            stage.show();
                                        } catch (IOException ex) {
                                            Logger.getLogger(LesGroupesController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {

                                        tray.setTitle("Adhèsion au groupe " + gr.getNom());
                                        tray.setMessage("votre rèadhésion au groupe vient d'echouer");
                                        tray.setNotificationType(type.ERROR);
                                        tray.showAndDismiss(Duration.seconds(3));

                                    }

                                }
                            }
                    );

                }
            }else {
            AnchorPane GroupesAnchorPane2 = new AnchorPane();
            Label LBAucunGroupe = new Label();
            LBAucunGroupe.setFont(LB2.getFont());
            LBAucunGroupe.setTextFill(LB2.getTextFill());
            LBAucunGroupe.setLayoutX(LBDescription.getLayoutX());
            LBAucunGroupe.setLayoutY(LBDescription.getLayoutY());
            LBAucunGroupe.setText("aucun groupe crèe");
            LBAucunGroupe.setFont(Font.font("Aharoni Bold", 20));

            GroupesAnchorPane2.getChildren().addAll(LBAucunGroupe);
            VBoxgroupes.getChildren().add(GroupesAnchorPane2);
        }
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
}