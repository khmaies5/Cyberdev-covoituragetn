/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class ModifierProfilController implements Initializable ,ControlledScreen {
    ScreensController screen;

    @FXML
    private JFXButton photoProfil;
    @FXML
    private JFXDatePicker dpDatenais;
    @FXML
    private JFXTextField txtCin;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTravail;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtNom;
   
    @FXML
    private ImageView imgview;
    @FXML
    private JFXComboBox<String> comboGouvernorat;
    //  ObservableList<String> list = FXCollections.observableArrayList("Sfax", "Tunis", "Ariana", "mahdia", "manouba", "Monastir", "Nabeul", "zaghouan");
    ObservableList<String> list = FXCollections.observableArrayList("Sfax", "Tunis", "Ariana", "mahdia", "manouba", "Monastir", "Nabeul", "zaghouan");
    @FXML
    private JFXRadioButton rbFemme;
    @FXML
    private JFXRadioButton rbHomme;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXButton btnok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboGouvernorat.setItems(list);
        // TODO
    }

    @FXML
    private void photoProfilAction(ActionEvent event) {
    }

    @FXML
    private void btnbackAction(ActionEvent event) throws IOException {
     {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen20ID, ScreensFramework.screen20File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen20ID);}
    }

    @FXML
    private void btnokAction(ActionEvent event) throws IOException {
        IUserService service = new UserService();
        User user = new User();
        user.setId(User.getIdd());
        user.setNom(txtNom.getText());
        user.setPrenom(txtPrenom.getText());
        user.setCin(txtCin.getText());
        user.setTelephone(txtTelephone.getText());
        user.setTravail(txtTravail.getText());
        user.setDate_naissance(java.sql.Date.valueOf(dpDatenais.getValue()));
        user.setGouvernorat(comboGouvernorat.getValue());
        if (rbHomme.isSelected()) {
            user.setSexe(rbHomme.getText());
        } else {
            user.setSexe(rbFemme.getText());
        }
        user.setEmail(txtEmail.getText());
        service.Update(user);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification");
        alert.setHeaderText(null);
        alert.setContentText("votre profil a été mis à jour!!");

        alert.showAndWait();
       

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }

}
