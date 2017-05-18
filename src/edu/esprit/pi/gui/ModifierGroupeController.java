/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.services.GroupeService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class ModifierGroupeController extends Application implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private Label IDGroupe;
    @FXML
    private JFXButton btnAjoutFichier;
    @FXML
    private TextField txtNomGroupe;
    @FXML
    private TextArea txtDescriptionGroupe;
    @FXML
    private ImageView IMGroupe;

    IGroupeService groupeService = new GroupeService();

    GroupeController groupeController = new GroupeController();
    Groupe groupeSelectionne = groupeController.getGroupe();
    private WritableImage image;

    File file;
    TrayNotification tray = new TrayNotification();
    NotificationType type = NotificationType.INFORMATION;

    public ModifierGroupeController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtNomGroupe.setText(groupeSelectionne.getNom());

        txtDescriptionGroupe.setText(groupeSelectionne.getDescription());
        if (groupeSelectionne.getPathImage() == null) {
            IMGroupe.setImage(IMGroupe.getImage());
        } else {
            try {
                File file = new File(groupeSelectionne.getPathImage());

                URL url2 = file.toURI().toURL();

                Image image = new Image(url2.toString());
                IMGroupe.setImage(image);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ModifierGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void modifierGroupe(ActionEvent event) throws IOException {

        if (file == null) {
            Groupe groupe = new Groupe(groupeSelectionne.getId(), txtNomGroupe.getText(), txtDescriptionGroupe.getText(), groupeSelectionne.getPathImage());
            System.out.println(groupe.getPathImage());
            groupeService.update(groupe);
            System.out.println(groupe);
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } else {
            File f = upload(file, groupeSelectionne.getId());

            Groupe groupe = new Groupe(groupeSelectionne.getId(), txtNomGroupe.getText(), txtDescriptionGroupe.getText(), f.getPath());

            groupeService.Groupeupdate(groupe);

            ((Node) (event.getSource())).getScene().getWindow().hide();

        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        txtNomGroupe.setText(groupeSelectionne.getNom());
        txtDescriptionGroupe.setText(groupeSelectionne.getDescription());
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ModifierGroupe.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ajoutFichier(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser Example");
        File homeDir = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(homeDir);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        fileChooser.setInitialFileName("Choose file");

        file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        tray.setTitle("image enregistrèe ");
        tray.setMessage("votre tèlèchargement d'image vient d'être effectuée avec succès");
        tray.setNotificationType(type.INFORMATION);
        tray.showAndDismiss(Duration.seconds(3));

        URL url2 = file.toURI().toURL();

        Image image = new Image(url2.toString());
        IMGroupe.setImage(image);

    }

    public File upload(File file, int idGroupe) throws IOException {

        File f = new File("src/edu/esprit/pi/img/");

        File file4 = new File("src/edu/esprit/pi/img/groupes");
        if (file4.exists() == false) {
            file4.mkdirs();
        }
        boolean result = file4.mkdirs();

        File file2 = new File(file4.getPath() + "/" + idGroupe);

        //      Files.copy(file4.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);

        BufferedImage bufferedImage = ImageIO.read(file);

        image = SwingFXUtils.toFXImage(bufferedImage, null);

        file2.getAbsolutePath();

        return file2;
    }

}
