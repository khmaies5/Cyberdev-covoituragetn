/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Upload;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.UserService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class InscriptionController implements Initializable ,ControlledScreen{
   public static ScreensController screen;

    @FXML
    private ComboBox<String> comboGouvernorat;
    ObservableList<String> list = FXCollections.observableArrayList("Sfax", "Tunis", "Ariana", "mahdia", "manouba", "Monastir", "Nabeul", "zaghouan");
    @FXML
    private Label lblResultat;
    @FXML
    private Button photoProfil;
    @FXML
    private ImageView imgview;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtCin;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTravail;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXDatePicker dpDatenais;
    @FXML
    private JFXRadioButton rbFemme;
    @FXML
    private JFXRadioButton rbHomme;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXButton btnRetour;

    private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
    private FileChooser.ExtensionFilter extFilterJPEG;
    private FileChooser.ExtensionFilter extFilterjpeg;
    private FileChooser.ExtensionFilter extFilterPNG;
    private FileChooser.ExtensionFilter extFilterpng;
    private Upload up;
    private File file = new File("");
    private Image image;
    private String photoPro;

    @FXML
    private void btnaddAction(ActionEvent event) throws IOException {
        IUserService service = new UserService();
        User user = new User();
        if (service.RechLogin(txtLogin.getText()) == true) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Login");
            alert.setContentText("Login déja existe!");

            alert.showAndWait();
        } else if (!txtEmail.getText().contains("@")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Email");
            alert.setContentText("Email non valide ne contient pas @!");
//        }else if (txtPassword.getText().length()< 8) {
//            lblResultat.setText("Le mot de passe doit contenir au moins 8 caractéres");
//        }else if (txtTelephone.getText().length() <8 || txtTelephone.getText().length()>8) {
//           lblResultat.setText("Numero Telephone different à 8!!!");
//        }else if ("".equals(txtNom.getText())) {lblResultat.setText("Saisir nom");  
//        }else if ("".equals(txtPrenom.getText())) {lblResultat.setText("Saisir prenom");
//        }else if ("".equals(txtLogin.getText())) {lblResultat.setText("Saisir Login");
//        }else if ("".equals(txtPassword.getText())) {lblResultat.setText("Saisir password");
        } else if (txtEmail.getText().isEmpty()) {
           Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Email");
            alert.setContentText("Email vide saisir un email!!");

//        } else if (file.isFile()) {
//              user.setPhoto_Profil(file.getAbsolutePath());
//           // up.upload(file, "profils");
//           // user.setPhoto_Profil(file.getName());
////                     Image image = new Image(user.getPhoto_Profil());
////                    imgview.setImage(image);

        } else {
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
            user.setPhoto_Profil(file.getAbsolutePath());
                      service.add(user);
            try{
                
            String host ="smtp.gmail.com" ;
            String userS = "javapidev@gmail.com";
            String pass = "28549245";
            String to = "rania.gargouri@esprit.tn";
            String from = "javapidev@gmail.com";
            String subject = "One of your orders is still awaiting payment: please don’t forget to pay!";
            String messageText = "Thank you for choosing us. It seems that your order was interrupted during checkout";
            boolean sessionDebug = false;

            Properties props = System.getProperties();
            
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); 
            msg.setSentDate(new java.util.Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, userS, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setHeaderText(null);
            alert.setContentText("Inscription valide!!!");

            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboGouvernorat.setItems(list);

        photoProfil.setOnMouseClicked((MouseEvent event1) -> {
//            FileChooser fileChooser = new FileChooser();
//
//            //Set extension filter
//            extFilterJPG
//                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
//            extFilterjpg
//                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
//            extFilterjpeg
//                    = new FileChooser.ExtensionFilter("jpg files (*.JPEG)", "*.JPEG");
//            extFilterJPEG
//                    = new FileChooser.ExtensionFilter("jpg files (*.jpeg)", "*.jpeg");
//            extFilterPNG
//                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
//            extFilterpng
//                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
//
//            fileChooser.getExtensionFilters()
//                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterjpeg, extFilterJPEG);
//            up = new Upload();
//            file = fileChooser.showOpenDialog(null);

        });
        // TODO       
    }

    @FXML
    private void photoProfilAction(ActionEvent event) throws IOException {
     FileChooser   filechooser = new FileChooser();
        //set les extensions pour qu'il n'accepte que les image avec l'extension .png et .jpg
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        filechooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        //show open file dialog
        file = filechooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        System.out.println(file.getAbsolutePath());
        photoPro=file.getAbsolutePath();
        image = SwingFXUtils.toFXImage(bufferedImage, null);
        imgview.setImage(image);
    }

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
       {  LoginController.screen.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
           LoginController.screen.setScreen(ScreensFramework.screen6ID);}
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;
    }

}
