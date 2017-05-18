/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.IReponseService;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.services.ReponseService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class ModifierReponseController extends Application implements Initializable {

    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private TextArea txtReponse;

 SujetController sujetcontroller=new SujetController();
 Reponse reponse=sujetcontroller.getReponseSelectionnè();
    IReponseService reponseServise=new ReponseService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtReponse.setText(reponse.getReponseSujet());
    }    

    @FXML
    private void modifierGroupe(ActionEvent event) {
          if (!txtReponse.getText().equals("")) 
          {  Reponse newReponse=new Reponse(reponse.getId(),txtReponse.getText());
        reponseServise.update(newReponse);
          ((Node) (event.getSource())).getScene().getWindow().hide();}
          else {txtReponse.setStyle("-fx-border-color: red");}
    }

    @FXML
    private void annuler(ActionEvent event) {
          txtReponse.setText("");
      

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
