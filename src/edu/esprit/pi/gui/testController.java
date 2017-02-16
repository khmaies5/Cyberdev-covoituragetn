/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import edu.esprit.pi.technique.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Sarra
 */
public class testController {
   //  @FXML
   // private JFXDrawer drawer;

  //  @FXML
  //  private JFXHamburger hamburger;

   // @FXML
  //  private AnchorPane root;
    

 


 //   public static AnchorPane rootP;
    private Connection connection;
    

Groupe groupeSelectionne ; 

    public Groupe getGroupeSelectionne() {
      //  System.out.println(groupeSelectionne);
        return groupeSelectionne;
    }

    public void setGroupeSelectionne(Groupe groupeSelectionne) {
        this.groupeSelectionne = groupeSelectionne;
    }
 
   

    public testController() {
        connection = DataSource.getInstance().getConnection();
       // System.out.println(groupeSelectionne);
    }



 
    public void initialize(URL url, ResourceBundle rb) {
      // rootP = root;
  
     //   try {
//             
      //      VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
     //       drawer.setSidePane(box);

    //    } catch (IOException ex) {
   //        Logger.getLogger(ModifierGroupeController.class.getName()).log(Level.SEVERE, null, ex);
   //     }
// 
//        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
//        transition.setRate(-1);
//        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//            transition.setRate(transition.getRate() * -1);
//            transition.play();
//
//            if (drawer.isShown()) {
//                drawer.close();
//            } else {
//                drawer.open();
//            }
//        });
    }
   public void initVariable(Groupe gr) {
groupeSelectionne=gr;
        System.out.println(gr.getNom());
            // setGroupeSelectionne(groupeSelectionne);
           System.out.println(groupeSelectionne);
               System.out.println("fghuji");


  
    }
}
