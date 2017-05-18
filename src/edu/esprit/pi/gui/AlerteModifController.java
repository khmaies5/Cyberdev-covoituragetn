/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AlerteService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */

public class AlerteModifController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField txtLieuDepart;
    @FXML
    private JFXTextField txtLieuArrivee;
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private JFXDatePicker dateP;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXSlider SliderHeure;
    @FXML
    private Label LId;

    @FXML
    private PieChart pi;

    public AlerteModifController() {
    }
    

    /**
     * Initializes the controller class.
     */
    AlerteService alerteService= new AlerteService();
InterfaceAlerteController a= new InterfaceAlerteController();
 int id=a.getIdAlerte();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Alerte p=alerteService.findById(id);
            String s =p.getDate().toString();
            
            txtLieuDepart.setText(p.getLieuDepart());
            txtLieuArrivee.setText(p.getLieuArrivee());
            LId.setText(Integer.toString(p.getId()));
           dateP.setValue(LocalDate.parse(s));
            SliderHeure.setValue(p.getHeure());
        // TODO
    } 
                @FXML
               
    private void modifierAlerte(ActionEvent event) throws Exception{
         User user=new User(User.getIdd());
      
 Alerte alerte =new Alerte (id,txtLieuDepart.getText(),txtLieuArrivee.getText(),java.sql.Date.valueOf(dateP.getValue()),(int) SliderHeure.getValue(),user);
alerteService.update(alerte);

 Parent affichageAlerte = FXMLLoader.load(getClass().getResource("InterfaceAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();


    }

}
