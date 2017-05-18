/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.tests;



import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.technique.DataSource;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author khmai
 */
public class AnnonceTest extends Application {
    public static int annonceId;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/InterfaceAnnonce.fxml"));
        Scene scene = new Scene(root);
       scene.getStylesheets().add(getClass().getResource("/resources/css/jfoenix-fonts.css").toExternalForm());
		//scene.getStylesheets().add(getClass().getResource("../resources/css/jfoenix-design.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("../resources/css/jfoenix-main-demo.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }
    
    
    
    
     public static void main(String[] args) throws ParseException{
      //  Calendar calendar = Calendar.getInstance();
     /* Date startDate = new Date();

        
        System.out.println(startDate.getTime());
  
         Annonce ann = new Annonce(5,new java.sql.Date(startDate.getTime()),null, "test", "test","test", 4, 200, "test", new User(4));
         
         IService service = new AnonncesService();
        service.add(ann);*/
       // System.out.println(service.getAll());
        
       // System.out.println(service.findById(2));
       //  service.delete(2);
        /* AnonncesService service = new AnonncesService();
         
         Annonce ann = (Annonce) service.findById(6);
         
         Annonce ann2 = new Annonce(ann.getIdAnnonce(),ann.getTripDate(),ann.getAnnonceDate(), ann.getLieuDepart(), ann.getLieuArriver(),ann.getTypeAnnonce(), ann.getNbrPersonne(), ann.getPrix(), ann.getCritere(),ann.getCreator());
         ann.setLieuDepart("sousse");
         service.Update(ann2);
         System.out.println(ann2);*/
                 launch(args);
                 
                 

     }
}
