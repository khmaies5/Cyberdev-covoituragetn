
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.services.SujetService;
import edu.esprit.pi.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;

/**
 *
 * @author Nacef Fethi
 */
public class Covoiturage extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
              ISujetService sujetService= new SujetService();
                IUserService user = new UserService(); 
                IGroupeService groupeService = new GroupeService();
            IAbonnesService    abonnementService=new AbonnesService ();
           
                // System.out.println(sujetService.findById(3));
   //System.out.println(abonnementService.findByIdGroupe(4));
   
 Parent root = FXMLLoader.load(getClass().getResource("AfficherMesGroupes.fxml"));
// Parent root = FXMLLoader.load(getClass().getResource("ChatView.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
  //     User  UserCreator=user.findByIdAbonnement(3);
    
    //Parent root = FXMLLoader.load(getClass().getResource("AffichageAlerte.fxml"));
    //groupeService.getGroupbyUser(u);
    Scene scene = new Scene(root);
        
  stage.setScene(scene);
      stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
