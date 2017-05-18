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
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IInvitationService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Invitation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.services.InvitationService;
import edu.esprit.pi.services.UserService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class MesInvitationsController implements Initializable  ,ControlledScreen{
   public static ScreensController screen;

    @FXML
    private JFXButton btnCreerGroupe;
    @FXML
    private VBox VBoxgroupes;
    @FXML
    private AnchorPane GroupesAnchorPane;
    @FXML
    private AnchorPane rootP;
    @FXML
    private Label LBDescription;
    @FXML
    private Label LBDate;
    @FXML
    private Label LBNomGroupe;
    @FXML
    private Separator separator;
    @FXML
    private Label LBNbAdherant;
    @FXML
    private ImageView groupeImageView;
    @FXML
    private Button btnEntrerGroupe;
    @FXML
    private Label LBUserCeateur;
    @FXML
    private Button btnRefuser;
    @FXML
    private TextField txtRechercher;
    @FXML
    private VBox VBoxMembre;
    @FXML
    private ScrollPane ScrollPaneMembres;
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
    private List<Abonnes> abonnes;
    private List<Groupe> groupes;
    IGroupeService groupeService = new GroupeService();
    IAbonnesService abonnesService = new AbonnesService();
    IInvitationService invitationService = new InvitationService();
    IUserService userService = new UserService();
    User user = new User(User.getIdd());
    List<Invitation> invitations = invitationService.getInvitationbyUser(user.getId());

    /**
     * Initializes the controller class.
     */
    public MesInvitationsController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getallMyInvitationList();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void creerGroupe(ActionEvent event) {
    }

    @FXML
    private void rechercherGroupe(KeyEvent event) {
    }

    public void getallMyInvitationList() {
        invitations = invitationService.getInvitationbyUser(user.getId());

        User UserCreator = null;
        if (!invitations.isEmpty()) {
            for (Invitation in : invitations) {
                Groupe gr = groupeService.findById(in.getGroupe().getId());

                AnchorPane InvitationAnchorPane2 = new AnchorPane();

                InvitationAnchorPane2.setStyle(GroupesAnchorPane.getStyle());
                InvitationAnchorPane2.setEffect(GroupesAnchorPane.getEffect());

                //**********************Titre sujet*******/
                Label LBNomGroupe2 = new Label();
                LBNomGroupe2.setFont(LBNomGroupe.getFont());
                LBNomGroupe2.setTextFill(LBNomGroupe.getTextFill());
                LBNomGroupe2.setLayoutX(LBNomGroupe.getLayoutX());
                LBNomGroupe2.setLayoutY(LBNomGroupe.getLayoutY());
                LBNomGroupe2.setText("Nom du groupe : " + " " + gr.getNom());
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
                User user = userService.findById(in.getCreator().getId());
                Label LBUserCeateur2 = new Label();
                LBUserCeateur2.setFont(LBUserCeateur.getFont());
                LBUserCeateur2.setTextFill(LBUserCeateur.getTextFill());
                LBUserCeateur2.setLayoutX(LBUserCeateur.getLayoutX());
                LBUserCeateur2.setLayoutY(LBUserCeateur.getLayoutY());

                LBUserCeateur2.setText(user.getNom() + " " + user.getPrenom());

                ////********Image User Createur Sujet*****/
                ImageView groupeImageView2 = new ImageView();

                groupeImageView2.setImage(groupeImageView.getImage());
                groupeImageView2.setLayoutX(groupeImageView.getLayoutX());
                groupeImageView2.setLayoutY(groupeImageView.getLayoutY());
                groupeImageView2.setStyle(groupeImageView.getStyle());
                groupeImageView2.setFitWidth(groupeImageView.getFitWidth());
                groupeImageView2.setFitHeight(groupeImageView.getFitHeight());

                /**
                 * **************   NbReponses**
                 */
                Label LBNbAdherant2 = new Label();
                LBNbAdherant2.setFont(LBNbAdherant.getFont());
                LBNbAdherant2.setTextFill(LBNbAdherant.getTextFill());
                LBNbAdherant2.setLayoutX(LBNbAdherant.getLayoutX());
                LBNbAdherant2.setLayoutY(LBNbAdherant.getLayoutY());
                abonnes = abonnesService.findByIdGroupe(gr.getId());

                LBNbAdherant2.setText(abonnes.size() + "adhèrants");

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
                 * ************afficher button accepter
                 */
                Button btnEntrerGroupe2 = new Button();
                btnEntrerGroupe2.setFont(btnEntrerGroupe.getFont());
                btnEntrerGroupe2.setTextFill(btnEntrerGroupe.getTextFill());
                btnEntrerGroupe2.setLayoutX(btnEntrerGroupe.getLayoutX());
                btnEntrerGroupe2.setLayoutY(btnEntrerGroupe.getLayoutY());
                btnEntrerGroupe2.setText(btnEntrerGroupe.getText());

                /**
                 * ************afficher button refuuser
                 */
                JFXButton btnRefuserInvitation2 = new JFXButton();
                btnRefuserInvitation2.setFont(btnRefuser.getFont());
                btnRefuserInvitation2.setTextFill(btnRefuser.getTextFill());
                btnRefuserInvitation2.setLayoutX(btnRefuser.getLayoutX());
                btnRefuserInvitation2.setLayoutY(btnRefuser.getLayoutY());
                btnRefuserInvitation2.setText(btnRefuser.getText());

                InvitationAnchorPane2.getChildren().addAll(btnRefuserInvitation2, LBNomGroupe2, LBDescription2, LBUserCeateur2, groupeImageView2, LBNbAdherant2, LBDate2, separator1, btnEntrerGroupe2);
                VBoxgroupes.getChildren().add(InvitationAnchorPane2);

                btnRefuserInvitation2.setOnMouseClicked(
                        e -> {

                            invitationService.delete(in.getId());
                            TrayNotification tray = new TrayNotification();
                            NotificationType type = NotificationType.INFORMATION;
                            tray.showAndDismiss(Duration.seconds(10));
                            tray.setTitle("abonnement au groupe ");
                            tray.setMessage("votre refus d'abonnement vient d'être effectuée avec succès");
                            tray.setNotificationType(type.SUCCESS);
                            VBoxgroupes.getChildren().clear();
                            VBoxgroupes.getChildren().add(GroupesAnchorPane);
                            getallMyInvitationList();

                        });
                btnEntrerGroupe2.setOnMouseClicked(
                        e -> {
                            Abonnes abonnes = new Abonnes("adhèrant", in.getUser(), in.getGroupe());
                            abonnesService.addAbonnes(abonnes);
                            invitationService.deletebyGroupe(in.getGroupe().getId());
                            TrayNotification tray = new TrayNotification();
                            NotificationType type = NotificationType.INFORMATION;
                            tray.showAndDismiss(Duration.seconds(10));
                            tray.setTitle("abonnement au groupe ");
                            tray.setMessage("votre abonnement vient d'être effectuée avec succès");
                            tray.setNotificationType(type.SUCCESS);
                            VBoxgroupes.getChildren().clear();
                            VBoxgroupes.getChildren().add(GroupesAnchorPane);
                            getallMyInvitationList();

                        });
            }
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
}
