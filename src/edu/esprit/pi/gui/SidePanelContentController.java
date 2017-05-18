package edu.esprit.pi.gui;


import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.ControlledScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import edu.esprit.pi.gui.ScreensFramework;
import edu.esprit.pi.models.User;
import javafx.scene.Node;


public class SidePanelContentController  {

     

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

  

        @FXML
    void DemandeAction(ActionEvent event) {
 {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen7ID, ScreensFramework.screen7File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen7ID);}
    }

    @FXML
    void ReservationAction(ActionEvent event) {
{  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen8ID);}
    }
    
       @FXML
    void retourOnAction(ActionEvent event)  {
       
         {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen5ID);}
          
           
    }
    
      @FXML
    private void changecolor(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        FXMLLoader loader= new FXMLLoader();
        switch(btn.getText())
        {
            case "Mes alertes":
                //loader.setLocation(getClass().getResource("/Alerte.fxml"));
                {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen9ID, ScreensFramework.screen9File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen9ID);}
                break;
                
            case "Mes publications favorites":
                //AlerteController.rootP.setStyle("-fx-background-color:#0000FF");
               {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen10ID, ScreensFramework.screen10File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen10ID);}
                break;
            case "Mes utilisateurs favoris":
              {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen11ID, ScreensFramework.screen11File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen11ID);}
                break;
             case "Tous les groupes":
                   {  System.out.println("hello");
                       RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen16ID, ScreensFramework.screen16File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen16ID);}
        break;
            case "Mes groupes":
             //   AjouterAnnoncesController.rootP.setStyle("-fx-background-color:#0000FF");
             {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen17ID, ScreensFramework.screen17File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen17ID);}
                break;
                
            case "Mes invitations":
     {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen18ID, ScreensFramework.screen18File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen18ID);}
        break;
      
            
            case "Profil":
                
     { System.out.println("fdsfsfs");
         RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen20ID, ScreensFramework.screen20File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen20ID);}
        break;
        }
    }


    @FXML
    private void exit(ActionEvent event) {
       // System.exit(0);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(400);
                ((Node) (event.getSource())).getScene().getWindow().setWidth(600);
        RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen6ID);
            User.setIdd(0);
        User.setEtat_compte(0);
        
    }

   
    
}
