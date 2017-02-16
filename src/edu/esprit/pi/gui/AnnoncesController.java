/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khmai
 */




public class AnnoncesController extends Application implements Initializable  {

    
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private WebView mapview;

    public static AnchorPane rootP;
    
    @FXML 
    private Button btnshowreservation;
    
    int param=1 ;
    int param1=1 ;
    int param2=1 ;

    public int getParam() {
        return param;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rootP = root;
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @FXML private void showreservation(ActionEvent event) throws IOException
    {AjoutReservationController controller =new AjoutReservationController();
   // controller.initVariable(param,param1,param2);
    
    Stage stage ;
       // Parent root ;
        if(event.getSource()==btnshowreservation)
        {
            FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("AjoutReservation.fxml"));
         Parent  root = afficher.load();
            stage =new Stage();
      // root=FXMLLoader.load(getClass().getResource("AjoutReservation.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnshowreservation.getScene().getWindow());
        stage.showAndWait();
        Scene scene =new Scene(root);
    stage.setScene(scene);
    stage.show();
        }
    
    
    }
    void initVariable(int i,int j,int k)
{
    System.out.println(i);
}
       public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
Parent root = FXMLLoader.load(getClass().getResource("Annonces.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    }

    
    
}
