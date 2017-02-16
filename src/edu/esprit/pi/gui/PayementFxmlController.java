/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
//import static edu.esprit.pi.gui.GestionReservationController.groupes;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.services.ReservationService;
import edu.esprit.pi.technique.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class PayementFxmlController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<Reservation> restab;

    @FXML
    private TableColumn<?, ?> cldate;

    @FXML
    private TableColumn<?, ?> clmontant;

    @FXML
    private TableColumn<?, ?> clnbreplace;

    @FXML
    private TableColumn<Reservation, String> cldepart;

    @FXML
    private TableColumn<Reservation,String> clarrivee;

    @FXML
    private JFXButton btnpayer;
    
    ReservationService reservationservice =new ReservationService() ;
      public static List<Reservation> groupes;
      private final ObservableList<Reservation> ListGroupes = FXCollections.observableArrayList();
    
    private Connection connection;
    public PayementFxmlController(){
            connection = DataSource.getInstance().getConnection();

    }
    
    private void SetCellTable() {
        
        cldate.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        clmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        clnbreplace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        cldepart.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getLieuArriver()));
        clarrivee.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getTypeAnnonce()) );

//        CLDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
//   CLNbAdherants.setCellValueFactory(new PropertyValueFactory<>()); 
//         System.out.println(groupeService.getAll());
//User user = new User(1);
        groupes = reservationservice.findallreservationsbyid(1);
        for (Reservation gr : groupes) {
            ListGroupes.add(gr);
            // System.out.println(gr.getId_demande());
            // System.out.println(gr.getUser().getId());
        }
        restab.setItems(ListGroupes);
        
    }

    @FXML
    void PayerAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCellTable();
    }    

    @Override
    public void start(Stage stage) throws Exception {

Parent root = FXMLLoader.load(getClass().getResource("PayementFxml.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    
    }
    
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
