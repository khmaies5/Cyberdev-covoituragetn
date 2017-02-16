/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.iservices.IDemandeService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.DemandeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amrouche
 */

public class AjoutReservationController extends Application implements  Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML ChoiceBox cbplaces ;/*= new ChoiceBox(FXCollections.observableArrayList(
    "First", "Second", "Third")
);*/
     int param ;
    int param1 ;
    int param2 ;
    AnnoncesController anc =new AnnoncesController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    cbplaces.setItems(FXCollections.observableArrayList(
    1,2,3,4));    // TODO
    }    
  @FXML
    void envoyerDemandeAction(ActionEvent event) {
        
       anc.initVariable(param, param1, param2);
        param=anc.getParam();
        param1=anc.getParam1();
        param2=anc.getParam2();
        Demande d=new Demande("", new Annonce(param),new User(param1),Integer.parseInt(cbplaces.getValue().toString()), new User(param2));
        IDemandeService service=new DemandeService();
        service.add(d);
        System.out.println("yeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeees");

    }
    
/*void initVariable(int i,int j,int k)
{
    System.out.println(i);
}*/

@Override
    public void start(Stage stage) throws Exception {
Parent root = FXMLLoader.load(getClass().getResource("AjoutReservation.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();       }
    
    public static void main(String[] args) {
        launch(args);
    }
}
