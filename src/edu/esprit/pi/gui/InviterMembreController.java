/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IInvitationService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.services.InvitationService;
import edu.esprit.pi.services.UserService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class InviterMembreController extends Application implements Initializable {

    @FXML
    private VBox VboxMembres;

    @FXML
    private AnchorPane membreAnchorPane;
    @FXML
    private Label LBUserName;
    @FXML
    private ImageView ImageViewMembre;
    @FXML
    private JFXCheckBox checkBoxMembre;
    @FXML
    private JFXCheckBox CHEKSelectAll;

    @FXML
    private JFXButton btnInviter;
    @FXML
    private Label LBText;
    List<User> userClicked = new ArrayList<>();
    GroupeController groupeController = new GroupeController();
    IUserService user = new UserService();
    IGroupeService groupeService = new GroupeService();
    IAbonnesService abonnementService = new AbonnesService();
    IInvitationService invitationService = new InvitationService();
    List<User> userNoMembre = new ArrayList<>();
    private int idGroupe = groupeController.getIdGroupe();
    private int idUser = groupeController.getIdUser();

    /**
     * Initializes the controller class.
     */
    public InviterMembreController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getallUsersList();
    }

    @FXML
    private void EnvoyerInvitation(ActionEvent event) throws Exception {
        boolean etat_envois = false;

        etat_envois = invitationService.envoyerInvitation(userClicked, idGroupe, idUser);

        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    public void getallUsersList() {

        userNoMembre = abonnementService.GetUsersNonAbonnesAuGroupe(idGroupe);

        for (User user : userNoMembre) {

            //  System.out.println(idAb);
            //   System.out.println(sujet);
            AnchorPane newUsersAnchorPane = new AnchorPane();

            newUsersAnchorPane.setStyle(membreAnchorPane.getStyle());
            newUsersAnchorPane.setEffect(membreAnchorPane.getEffect());

            /**
             * ***Nom User non membre**
             */
            Label LBUserName2 = new Label();
            LBUserName2.setFont(LBUserName.getFont());
            LBUserName2.setTextFill(LBUserName.getTextFill());
            LBUserName2.setLayoutX(LBUserName.getLayoutX());
            LBUserName2.setLayoutY(LBUserName.getLayoutY());

            LBUserName2.setText(user.getNom() + " " + user.getPrenom());
            ////********Image User*****/

            ImageView ImageViewMembre2 = new ImageView();

            ImageViewMembre2.setImage(ImageViewMembre.getImage());
            ImageViewMembre2.setLayoutX(ImageViewMembre.getLayoutX());
            ImageViewMembre2.setLayoutY(ImageViewMembre.getLayoutY());
            ImageViewMembre2.setStyle(ImageViewMembre.getStyle());
            ImageViewMembre2.setFitWidth(ImageViewMembre.getFitWidth());
            ImageViewMembre2.setFitHeight(ImageViewMembre.getFitHeight());

            CheckBox checkBoxMembre2 = new CheckBox();

            checkBoxMembre2.setLayoutX(checkBoxMembre.getLayoutX());
            checkBoxMembre2.setLayoutY(checkBoxMembre.getLayoutY());
            checkBoxMembre2.setPrefWidth(checkBoxMembre.getPrefWidth());
            checkBoxMembre2.setPrefHeight(checkBoxMembre.getPrefHeight());

            newUsersAnchorPane.getChildren().addAll(ImageViewMembre2, LBUserName2, checkBoxMembre2);
            VboxMembres.getChildren().add(newUsersAnchorPane);

            checkBoxMembre2.setOnMouseClicked(
                    e -> {

                        if (checkBoxMembre2.isSelected()) {
                            userClicked.add(user);
                        } else {
                            userClicked.remove(user);
                        }
                    });

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InviterMembre.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
