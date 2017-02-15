/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;



import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblerror;
    @FXML
    private Button btnValider;

    @FXML
    private void btnValiderAction(ActionEvent event) throws IOException {
        IUserService service = new UserService();
        User user = new User();
        user = service.authentication(txtUsername.getText(), txtPassword.getText());
        if (user.getId() < 1) {
            lblerror.setText("Login ou Password invalide !!!");
        } else {
            if (user.getRole() == 1) {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                

            } else {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        User.setEtat_compte(1);
        User.setIdd(user.getId());

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

}
