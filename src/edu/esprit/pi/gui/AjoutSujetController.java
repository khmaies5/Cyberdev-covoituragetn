/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.SujetService;
import edu.esprit.pi.services.UserService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class AjoutSujetController implements Initializable {

    @FXML
    private JFXButton btnAjouterSujet;
    @FXML
    AnchorPane rootP;
    @FXML
    private JFXButton btnAjoutFichier;
    @FXML
    private TextField TXTTitre;
    @FXML
    private Label LBSujet;
    @FXML
    private Label LBTitre;
    @FXML
    private TextArea TXTSujet;
    private Text actionStatus;
    GroupeController groupeController = new GroupeController();
    private TextArea txtArea;
    ISujetService sujetService = new SujetService();
    IAbonnesService abonnementService = new AbonnesService();
    IUserService userService = new UserService();
    int idGroupe = groupeController.getIdGroupe();
    int idUser;
    private WritableImage image;

    List<File> files;
    TrayNotification tray = new TrayNotification();
    NotificationType type = NotificationType.INFORMATION;

    public AjoutSujetController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void AjouterSujet(ActionEvent event) throws Exception {
        Sujet sujet;
        idGroupe = groupeController.getIdGroupe();

        idUser = groupeController.getIdUser();

        if ((!TXTTitre.getText().equals("")) && (!TXTSujet.getText().equals(""))) {
            if (files != null) {

                sujet = new Sujet(TXTTitre.getText(), TXTSujet.getText(), new Groupe(idGroupe), abonnementService.findByIdGroupeAndIdUser(idGroupe, idUser));

                boolean result1 = sujetService.addSujet(sujet);
                File f = upload(files, sujetService.LastInseredId());
                boolean result2 = sujetService.updatePath(f.getPath(), sujetService.LastInseredId());
                if ((result1 == true) && (result2 == true)) {
                    tray.setTitle("crèation d'un sujet " + TXTTitre.getText());
                    tray.setMessage("votre crèation de sujet vient d'être effectuée avec succès");
                    tray.setNotificationType(type.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(3));
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {

                    tray.setTitle("Adhèsion au groupe " + TXTTitre.getText());
                    tray.setMessage("votre adhésion au groupe vient d'echouer");
                    tray.setNotificationType(type.ERROR);
                    tray.showAndDismiss(Duration.seconds(3));

                }

            } else {
                sujet = new Sujet(TXTTitre.getText(), TXTSujet.getText(), new Groupe(idGroupe), abonnementService.findByIdGroupeAndIdUser(idGroupe, idUser));

                if (sujetService.addSujet(sujet)) {

                    System.out.println("yessssss");
                    tray.setTitle("crèation d'un sujet " + TXTTitre.getText());
                    tray.setMessage("votre crèation de sujet vient d'être effectuée avec succès");
                    tray.setNotificationType(type.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(3));

                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } else {
                    tray.setTitle("Adhèsion au groupe " + TXTTitre.getText());
                    tray.setMessage("votre adhésion au groupe vient d'echouer");
                    tray.setNotificationType(type.ERROR);
                    tray.showAndDismiss(Duration.seconds(3));

                }

            }
        }
        if (TXTTitre.getText().equals("")) {
            TXTTitre.setStyle("-fx-border-color: red");
            if (System.nanoTime() == 300000000);
            TXTTitre.setStyle("-fx-border-color: white");
        }
        if (TXTSujet.getText().equals("")) {
            TXTSujet.setStyle("-fx-border-color: red");
            if (System.nanoTime() == 300000000);
            TXTSujet.setStyle("-fx-border-color: white");
        }

    }

    public boolean shutdown() {

        return true;
    }

    @FXML
    private void ajoutFichier(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser Example");
        File homeDir = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(homeDir);
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        fileChooser.setInitialFileName("Choose file");

        files = fileChooser.showOpenMultipleDialog(null);
        tray.setTitle("image(s) enregistrèe(s) ");
        tray.setMessage("votre tèlèchargement d'image(s) vient d'être effectuée avec succès");
        tray.setNotificationType(type.INFORMATION);
        tray.showAndDismiss(Duration.seconds(3));

    }

    public File upload(List<File> files, int idSujet) throws IOException {

        File f = new File("src/edu/esprit/pi/img/");
        File file4 = new File("src/edu/esprit/pi/img/sujets/" + idSujet);
        boolean result = file4.mkdirs();
        System.out.println(file4.toPath());
        for (File file : files) {
            File file2 = new File(file4.getPath() + "/" + file.getName());

            Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);

            BufferedImage bufferedImage = ImageIO.read(file);

            image = SwingFXUtils.toFXImage(bufferedImage, null);

            file2.getAbsolutePath();

        }
        return file4;
    }

    public static void main(String[] args) {
        launch(args);

    }
}
