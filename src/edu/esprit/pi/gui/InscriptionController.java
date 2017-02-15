/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;



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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class InscriptionController implements Initializable {

    @FXML
    private Label lblResultat;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private DatePicker dpDatenais;
    @FXML
    private RadioButton rbHomme;
    @FXML
    private RadioButton rbFemme;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCin;
    @FXML
    private TextField txtTravail;
    @FXML
    private TextField txtTelephone;
    @FXML
     private ComboBox<String> comboGouvernorat;
     ObservableList <String> list = FXCollections.observableArrayList("Sfax","Tunis","Ariana","mahdia","manouba","Monastir","Nabeul","zaghouan");

    @FXML
    private Button btnadd;

    @FXML
    private void btnaddAction(ActionEvent event) throws IOException{
        IUserService service = new UserService();
        User user = new User();
        if (service.RechLogin(txtLogin.getText())== true) {
            lblResultat.setText("Login existe deja !!!");
        }else if (!txtEmail.getText().contains("@")) {
            lblResultat.setText("Email non valide!!");
//        }else if (txtPassword.getText().length()< 8) {
//            lblResultat.setText("Le mot de passe doit contenir au moins 8 caractéres");
//        }else if (txtTelephone.getText().length() <8 || txtTelephone.getText().length()>8) {
//           lblResultat.setText("Numero Telephone different à 8!!!");
//        }else if ("".equals(txtNom.getText())) {lblResultat.setText("Saisir nom");  
//        }else if ("".equals(txtPrenom.getText())) {lblResultat.setText("Saisir prenom");
//        }else if ("".equals(txtLogin.getText())) {lblResultat.setText("Saisir Login");
//        }else if ("".equals(txtPassword.getText())) {lblResultat.setText("Saisir password");
   }else if ( txtEmail.getText().isEmpty()) {lblResultat.setText("Saisir Email");
        
        }else{
         
        user.setNom(txtNom.getText());
        user.setPrenom(txtPrenom.getText());
        user.setLogin(txtLogin.getText());
        user.setPassword(txtPassword.getText());

        user.setDate_naissance(java.sql.Date.valueOf(dpDatenais.getValue()));
        if (rbHomme.isSelected()) {
            user.setSexe(rbHomme.getText());
        } else {
            user.setSexe(rbFemme.getText());
        }
        user.setEmail(txtEmail.getText());
        user.setCin(txtCin.getText());
        user.setTravail(txtTravail.getText());
        user.setTelephone(txtTelephone.getText());
        user.setGouvernorat(comboGouvernorat.getValue());
        
        service.add(user);
        lblResultat.setText("Inscription valide !!");
        }
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboGouvernorat.setItems(list);
        // TODO       
    }

}
