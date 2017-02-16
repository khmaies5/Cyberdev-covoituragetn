/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.iservices.IDemandeService;
import edu.esprit.pi.iservices.IReservationService;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.DemandeService;
import edu.esprit.pi.services.ReservationService;
import edu.esprit.pi.technique.DataSource;
import edu.esprit.pi.models.Annonce;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class GestionReservationController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Demande> TableDemande;
    
    @FXML
    private TableColumn<?, ?> clIdDemande;
    @FXML
    private TableColumn<?, ?> clEtat;
    @FXML
    private TableColumn<Demande, String> clIdAnnonce;
     @FXML
    private TableColumn<Demande, String> clarrivée;
    @FXML
    private TableColumn<Demande,String> clIdUser;
    @FXML
    private TableColumn<?, ?> clNbrePlaces;
    
    @FXML    
    private TextField txAnnonce;
    @FXML    
    private TextField txUser;
    @FXML    
    private TextField txEtat;
    @FXML    
    private TextField txnbrePlaces;
    
    int idUser;
    int idDemande;
    int idAnnonces;
    int nbreplacess;
    String etat;

    @FXML
    void onAcceptAction(ActionEvent event) throws IOException {
        
        System.out.println(idDemande);
        System.out.println(idUser);
        System.out.println(idAnnonces);
        
        Demande d = new Demande();
        Date date1 = new Date();
        String s = date1.toString();
        demandeservice.acceptReservation(d, idDemande);
        IReservationService reservationservice = new ReservationService();
        Reservation reservation = new Reservation(s, etat, 500, "par cheque", new User(idUser), nbreplacess, new Annonce(idAnnonces));
        reservationservice.add(reservation);

//SetCellTable();
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("GestionReservation.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();
        
        GeneratePDF p;
        p = new GeneratePDF(txUser.getText(), txEtat.getText(), txEtat.getText(), 500, nbreplacess);
        
    }

    @FXML
    void ondenyaction(ActionEvent event) throws IOException {
        
        Demande d = new Demande();
        demandeservice.declineReservation(d, idDemande);
        demandeservice.delete(idDemande);
//SetCellTable();
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("GestionReservation.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();
        
    }
    
    IDemandeService demandeservice = new DemandeService();
    public static List<Demande> groupes;
    private final ObservableList<Demande> ListGroupes = FXCollections.observableArrayList();
    
    private Connection connection;
    
    public GestionReservationController() {
        connection = DataSource.getInstance().getConnection();
        
    }

    private void SetCellTable() {
        
        clIdDemande.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        clEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        clIdAnnonce.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getLieuArriver()));
    /*    firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Person, String> p) {
         // p.getValue() returns the Person instance for a particular TableView row
         return p.getValue().firstNameProperty();
     }
  });*/
        clIdUser.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUser().getNom()));
clarrivée.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getTypeAnnonce()) );
        /*clIdUser.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                TableCell<User,String> cell = new TableCell<User,String>(){
                    protected void updateitem(Demande d ,boolean bln)
                    {
                    if(d!=null)
                    {
                        setText(d.getUser().getNom());
                    
                    }
                    }
                
                
                };
                return cell;
            }
        });*/


        clNbrePlaces.setCellValueFactory(new PropertyValueFactory<>("NbrPlaces"));

//        CLDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
//   CLNbAdherants.setCellValueFactory(new PropertyValueFactory<>()); 
//         System.out.println(groupeService.getAll());
//User user = new User(1);
        groupes = demandeservice.getAll();
        for (Demande gr : groupes) {
            ListGroupes.add(gr);
                  

            // System.out.println(gr.getId_demande());
            // System.out.println(gr.getUser().getId());
        }
     TableDemande.setItems(ListGroupes);
       
        
    }

    private void setCellValueFromTableToText() {
        TableDemande.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Demande gr = TableDemande.getItems().get(TableDemande.getSelectionModel().getSelectedIndex());
                txAnnonce.setText(gr.getAnnonce().getLieuArriver());
                txUser.setText(gr.getUser().getNom());
                txEtat.setText(gr.getEtat());
                txnbrePlaces.setText(Integer.toString(gr.getNbrPlaces()));
                idDemande = gr.getId_demande();
                idUser = gr.getUser().getId();
                idAnnonces = gr.getAnnonce().getIdAnnonce();
                nbreplacess = gr.getNbrPlaces();
                etat = gr.getEtat();
                
            }
            
        });
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCellTable();
        setCellValueFromTableToText();
    }    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GestionReservation.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
