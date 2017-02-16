/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.services.AnonncesService;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class DefaultInterfaceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML private TextField txdepart ;
     @FXML private TextField txdestination ;
    //  @FXML private TextField txdate ;
     @FXML private Button btnrechercher ;
     
     
      
      
      @FXML private void rechercherannonce(ActionEvent event)
      {
          
          String depart =txdepart.getText();
          String destination=txdestination.getText() ;
        //  Date date=txdate.getText();
          String date1 = "24/06/2006";
       IAnnonceService serv1=new AnonncesService();
         List<Annonce> lst =new ArrayList();
             lst= serv1.rechercherannonceselontrajet(depart,destination,date1);
  lst.stream().forEach(System.out::println);
         //lst= serv1.rechercherannonceselontrajet("fnfgngfn","nfgngfnfgngf",date1);
       //System.out.println(lst);      
      
      
      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
