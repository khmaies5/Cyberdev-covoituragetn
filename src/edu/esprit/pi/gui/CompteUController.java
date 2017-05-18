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
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.UserService;
//import edu.esprit.cov.technique.ConfigurationJava;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class CompteUController implements Initializable ,ControlledScreen {
    ScreensController screen;

    @FXML
    private ImageView photoProfil;
    @FXML
    private Label LblNomPrenom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lbldp;
    @FXML
    private Label lbltel;
    @FXML
    private Label lbltravail;
    @FXML
    private Label lbladress;

    @FXML
    private JFXButton btnDec;
    @FXML
    private JFXButton btnDesactiver;
     private Image image;
    private String path;
    File file;
    @FXML
    private JFXButton btnUtilisateurs;
     @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;


    @FXML
    void btnDesactiverAction(ActionEvent event) throws IOException {
        IUserService service = new UserService();
        int i = User.getIdd();
        System.out.println(i + "firas rania");
        User userConnecte = service.Search(i);
        service.Desactiver(userConnecte);
        userConnecte.setEtat_compte(0);
        User.setIdd(0);
        Stage stage = new Stage();
         RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen6ID);
        System.out.println("etat compte :" + User.getEtat_compte());
        System.out.println("idd :" + User.getIdd());

    }
    @FXML
    private Button btnModifier;

    @FXML
    private void btnModifierAction(ActionEvent event) throws IOException {
       {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen21ID, ScreensFramework.screen21File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen21ID);}
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IUserService service = new UserService();
        int i = User.getIdd();
        System.out.println(i + "firas rania");

        User userConnecte = service.Search(i);

        
        String path= userConnecte.getPhoto_Profil();
        System.out.println(path);
        file = new File(path);
        try {
            BufferedImage bufferdimage = ImageIO.read(file);
            WritableImage image= SwingFXUtils.toFXImage(bufferdimage, null);
            photoProfil.setImage(image);
        } catch (Exception e) {
        }
        
        photoProfil.setImage(new Image(file.toURI().toString()));
        LblNomPrenom.setText( userConnecte.getNom()+" "+userConnecte.getPrenom());
       // photoProfil.setImage(image);
        // image= new Image(path);
//         String mm = "";
//        
//
//        if (userConnecte.getPhoto_Profil().startsWith("https://")) {
//            mm = userConnecte.getPhoto_Profil().trim();
//        } else {
//            mm = (ConfigurationJava.profilsPath + userConnecte.getPhoto_Profil()).trim();
//        }
//        image =new Image(mm);
//        photoProfil.setImage(image);
     
        

//        Calendar c = Calendar.getInstance();
//        java.util.Date d = c.getTime();
        //  java.sql.Date dd;
        //  dd = (Date) userConnecte.getDate_naissance();
//        // end date
        //   lbldp.setText(new Date(userConnecte.getDate_naissance().toString("yyyy-mm-dd")));
//        String s;
//        Format formatter;
//       Date date = new Date(i);

// 2012-12-01
//        formatter  = new SimpleDateFormat("yyyy-MM-dd");
//        s = formatter.format(date);
        System.out.println(userConnecte.getDate_naissance());
        lbldp.setText(userConnecte.getDate_naissance()+"");
        lbltel.setText(userConnecte.getTelephone());
        lblEmail.setText(userConnecte.getEmail());
        lbltravail.setText(userConnecte.getTravail());
        lbladress.setText(userConnecte.getGouvernorat());
  try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AnnoncesListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;
    }

   

   
}
