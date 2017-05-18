/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.GroupeService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
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
public class CreerGroupeController extends Application implements Initializable {

    @FXML
    private TextField txtNomGroupe;
    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private TextArea txtDescriptionGroupe;
    @FXML
    private JFXButton btnAjoutFichier;
    @FXML
    private Label LBFichier;

    IAbonnesService abonnesService = new AbonnesService();
    IGroupeService groupeService = new GroupeService();
    LesGroupesController groupesController2 = new LesGroupesController();
    MesGroupesController groupesController = new MesGroupesController();

    private WritableImage image;

    File file;
    TrayNotification tray = new TrayNotification();
    NotificationType type = NotificationType.INFORMATION;

    int idUser = groupesController2.getIdUser();
    User user2 = groupesController.getUserConn();
    User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (idUser == 0) {
            user = user2;
        } else {
            user = new User(idUser);
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        txtNomGroupe.setText("");
        txtDescriptionGroupe.setText("");
        file = null;
        LBFichier.setText("aucun fichier selectionnè");

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void ajouterGroupe(ActionEvent event) throws Exception {

        if ((!txtNomGroupe.getText().equals("")) && (!txtDescriptionGroupe.getText().equals(""))) {
            Groupe groupe = new Groupe(txtNomGroupe.getText(), txtDescriptionGroupe.getText());

            boolean b1 = groupeService.addGroupe(groupe);
            groupe.setId(groupeService.LastInseredId());

            Abonnes abonnee = new Abonnes("admin", user, groupe);
            boolean result3 = abonnesService.addAbonnes(abonnee);
            abonnesService.add(abonnee);
            if (file != null) {

                File f = upload(file, groupeService.LastInseredId());
                boolean result2 = groupeService.updatePath(f.getPath(), groupeService.LastInseredId());
                if ((b1 == true) && (result2 == true) && (result3 == true)) {

                    tray.setTitle("crèation d'un groupe " + txtNomGroupe.getText());
                    tray.setMessage("votre crèation de groupe vient d'être effectuée avec succès");
                    tray.setNotificationType(type.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(3));
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    System.out.println("nooooo");
                    tray.setTitle("Adhèsion au groupe " + txtNomGroupe.getText());
                    tray.setMessage("votre adhésion au groupe vient d'echouer");
                    tray.setNotificationType(type.ERROR);
                    tray.showAndDismiss(Duration.seconds(3));

                }

            } else if ((b1 == true) && (result3 == true)) {

                System.out.println("yessssss");
                tray.setTitle("crèation d'un groupe " + txtNomGroupe.getText());
                tray.setMessage("votre crèation de groupe vient d'être effectuée avec succès");
                tray.setNotificationType(type.SUCCESS);
                tray.showAndDismiss(Duration.seconds(3));
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {

                tray.setTitle("Crèation d'un groupe " + txtNomGroupe.getText());
                tray.setMessage("votre crèation de groupe vient d'echouer");
                tray.setNotificationType(type.ERROR);
                tray.showAndDismiss(Duration.seconds(3));
            }
        }
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
        LBFichier.setText(file.getName());
        tray.setTitle("image enregistrèe ");
        tray.setMessage("votre tèlèchargement d'image vient d'être effectuée avec succès");
        tray.setNotificationType(type.INFORMATION);
        tray.showAndDismiss(Duration.seconds(3));

    }

    public File upload(File file, int idGroupe) throws IOException {

        File f = new File("src/edu/esprit/pi/img/");

        File file4 = new File("src/edu/esprit/pi/img/groupes");
        if (file4.exists() == false) {
            file4.mkdirs();
        }
        boolean result = file4.mkdirs();

        File file2 = new File(file4.getPath() + "/" +idGroupe);

        if (file2.exists()) {
            File file3 = new File(file2.getName() + idGroupe);
            file2.renameTo(file3);
        }
        Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);

        BufferedImage bufferedImage = ImageIO.read(file);

        image = SwingFXUtils.toFXImage(bufferedImage, null);

        file2.getAbsolutePath();

        return file2;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
