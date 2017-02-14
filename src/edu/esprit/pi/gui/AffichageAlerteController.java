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
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AlerteService;
import edu.esprit.pi.technique.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */
public class AffichageAlerteController implements Initializable {
        @FXML
    private TableView<Alerte> TableAlerte;
     @FXML
        private TableColumn<Alerte, String> CLLieuDepart;
     @FXML
        private TableColumn<Alerte, String> CLLieuArrivee;
     @FXML
        private TableColumn<Alerte, Date> CLDate;
     @FXML
        private TableColumn<Alerte, Integer> CLHeure;
         public static List<Alerte> lstAlerte;

     private final ObservableList<Alerte> ListAlerte = FXCollections.observableArrayList();
        @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;
     @FXML
    private Label labelLDepart;
              @FXML
    private Label labelLArrivee;
             @FXML
    private Label LDate;
             @FXML
    private Label LHeure;
            
      @FXML
    private Label LId;
             @FXML
    private JFXTextField txtLieuDepart;
           @FXML
    private JFXTextField txtLieuArrivee;
                      @FXML
 private JFXTextField txtChercher;
              @FXML
    private JFXDatePicker dateP;
    
              @FXML
    private JFXSlider SliderHeure;   
  @FXML
    private JFXButton btnModifier;
   @FXML
    private JFXButton btnSupprimer;
     @FXML
    private JFXButton btncreerAlerte;

   
       private Connection connection;
    private PreparedStatement ps;
     IAlerteService alerteService= new AlerteService();

    public AffichageAlerteController() {
                        connection = DataSource.getInstance().getConnection();

    }
     

    private void  SetCellTable()
    {
   CLLieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieuDepart"));
      CLLieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieuArrivee"));
        CLDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        CLHeure.setCellValueFactory(new PropertyValueFactory<>("heure")); 
          lstAlerte = new ArrayList<>();
           User user=new User(2);
             
          lstAlerte = alerteService.getAll(user.getId());
           for (Alerte alerte : lstAlerte) {
              ListAlerte.add(alerte);
                      TableAlerte.setItems(ListAlerte);
            }
          
    }
    
 
      private void setCellValueFromTableToText(){
    TableAlerte.setOnMouseClicked(new  EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
            labelLDepart.setVisible(true);
             labelLArrivee.setVisible(true);
              LDate.setVisible(true);
               LHeure.setVisible(true);
                 txtLieuDepart.setVisible(true);
        txtLieuArrivee.setVisible(true);
        dateP.setVisible(true);
        SliderHeure.setVisible(true);
        btnModifier.setVisible(true);
        btnSupprimer.setVisible(true);
        
            
            Alerte p=TableAlerte.getItems().get(TableAlerte.getSelectionModel().getSelectedIndex());
            String s =p.getDate().toString();
            
            txtLieuDepart.setText(p.getLieuDepart());
            txtLieuArrivee.setText(p.getLieuArrivee());
            LId.setText(Integer.toString(p.getId()));
           dateP.setValue(LocalDate.parse(s));
            SliderHeure.setValue(p.getHeure());
        
        }
    });
    
    }


      @FXML
    void rechercherAlerte(KeyEvent event) throws IOException {
         
      //  TableAlerte.setVisible(true);
        
        lstAlerte = new ArrayList<>();
        AlerteService s=new AlerteService();
        User user=new User(2);
        
       lstAlerte=s.rechercher(txtChercher.getText(),user.getId());
       // System.out.println(lstAlerte);
          CLLieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieuDepart"));
      CLLieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieuArrivee"));
        CLDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        CLHeure.setCellValueFactory(new PropertyValueFactory<>("heure")); 
                               TableAlerte.setItems(ListAlerte);

    ListAlerte.clear();
        
               for (Alerte alerte : lstAlerte) {
                ListAlerte.add(alerte);
            }}
                     
               @FXML
               
    private void modifierAlerte(ActionEvent event) throws Exception{
         User user=new User(2);
        int id=Integer.parseInt(LId.getText());
         System.out.println(id);
 Alerte alerte =new Alerte (id,txtLieuDepart.getText(),txtLieuArrivee.getText(),java.sql.Date.valueOf(dateP.getValue()),(int) SliderHeure.getValue(),user);
 alerteService.update(alerte);
//SetCellTable();
 Parent affichageAlerte = FXMLLoader.load(getClass().getResource("AffichageAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();


    }
     @FXML
    private void supprimerAlerte(ActionEvent event) throws Exception{
        int id=Integer.parseInt(LId.getText());
         alerteService.delete(id);
    Parent affichageAlerte = FXMLLoader.load(getClass().getResource("AffichageAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
    }
       @FXML
    private void creerAlerte(ActionEvent event) throws Exception{
     Parent affichageAlerte = FXMLLoader.load(getClass().getResource("Alerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     SetCellTable();
        setCellValueFromTableToText();
          labelLDepart.setVisible(false);
             labelLArrivee.setVisible(false);
              LDate.setVisible(false);
               LHeure.setVisible(false);
        txtLieuDepart.setVisible(false);
        txtLieuArrivee.setVisible(false);
         dateP.setVisible(false);
        SliderHeure.setVisible(false);
            btnModifier.setVisible(false);
        btnSupprimer.setVisible(false);
    //  chercherAlerte();
                
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AffichageAlerteController.class.getName()).log(Level.SEVERE, null, ex);
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
}