/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> ColumnNom;
    @FXML
    private TableColumn<User, String> ColumnPrenom;
    @FXML
    private TableColumn<User, String> ColumnTravail;
    @FXML
    private TableColumn<User, String> ColumnEmail;
    @FXML
    private TableColumn<User, String> columnTel;
    @FXML
    private TableColumn<User, Integer> columnCompte;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXButton btnok;
    private User currentUsers;
    List<User> users;
    IUserService service = new UserService();
    ObservableList<User> data = FXCollections.observableArrayList(service.getAll());
    @FXML
    private JFXButton btnDes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnok.setDisable(true);
        tableUser.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableUser.setTableMenuButtonVisible(true);
        ColumnNom.setCellValueFactory(new PropertyValueFactory<User, String>("Nom"));
        ColumnPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("Prenom"));
        ColumnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        ColumnTravail.setCellValueFactory(new PropertyValueFactory<User, String>("Travail"));
        columnCompte.setCellValueFactory(new PropertyValueFactory<User, Integer>("Actif"));
        columnTel.setCellValueFactory(new PropertyValueFactory<User, String>("telephone"));
        tableUser.setItems(data);
        tableUser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label

                currentUsers = (User) newValue;
                btnok.setDisable(false);
                  btnDes.setDisable(false);
            }
        });
        // TODO
    }

    @FXML
    private void btnbackAction(ActionEvent event) {
    }

    @FXML
    private void btnokAction(ActionEvent event) throws IOException {
        //System.out.println(currentUsers);
        btnok.setDisable(true);
        IUserService service = new UserService();
        User user = currentUsers;
        service.delete(user.getId());
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnDesAction(ActionEvent event) throws IOException {
          btnDes.setDisable(true);
          IUserService service = new UserService();
        User user = currentUsers;
        service.Desactiver(user);
         Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
