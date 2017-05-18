/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import static edu.esprit.pi.gui.GestionReservationController.screen;
import edu.esprit.pi.iservices.ControlledScreen;
//import static edu.esprit.pi.gui.GestionReservationController.groupes;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.ReservationService;
import edu.esprit.pi.services.UserService;
import edu.esprit.pi.technique.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class PayementFxmlController  implements Initializable,ControlledScreen {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<Reservation> restab;

    @FXML
    private TableColumn<Reservation, String> cldate;

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
     @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
      @FXML
    private JFXButton btnpaypal;
      int id ;
    String nom ;
    double montant ;
    String depart ;
    String arrivee ;
    String date ;
    ReservationService reservationservice =new ReservationService() ;
      public static List<Reservation> groupes;
      private final ObservableList<Reservation> ListGroupes = FXCollections.observableArrayList();
    UserService serviced = new UserService();
        int i = User.getIdd();
       // System.out.println(i + "firas rania");
        User userConnecte = serviced.Search(i);
    private Connection connection;
    public PayementFxmlController(){
            connection = DataSource.getInstance().getConnection();

    }
      @FXML
    void annulerreservationAction(ActionEvent event) {
         Notifications notificationBuilder = Notifications.create()
                .title("reservation annuléé")
                .text("votre reservation a bien eté annulée")
                 .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("Demande refusée");
                    
                }     
        });
          notificationBuilder.darkStyle();
       notificationBuilder.showConfirm();
        Demande d = new Demande();
       reservationservice.delete(id);
       screen.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);
                    screen.setScreen(ScreensFramework.screen8ID); 

    }
      @FXML
    void payerpaypalaction(ActionEvent event) throws IOException {
         Stage stage ;
       // Parent root ;
        if(event.getSource()==btnpaypal)
        {
            FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("PayPalfxml.fxml"));
         Parent  root = afficher.load();
            stage =new Stage();
      // root=FXMLLoader.load(getClass().getResource("AjoutReservation.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnpaypal.getScene().getWindow());
        stage.showAndWait();
        Scene scene =new Scene(root);
    stage.setScene(scene);
    stage.show();
        }

    }
    private void SetCellTable() {
        
        cldate.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getDate_reservation()));
        clmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        clnbreplace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        cldepart.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getLieuDepart()));
        clarrivee.setCellValueFactory(cellData->new ReadOnlyStringWrapper(cellData.getValue().getAnnonce().getLieuArriver()) );

//        CLDate.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
//   CLNbAdherants.setCellValueFactory(new PropertyValueFactory<>()); 
//         System.out.println(groupeService.getAll());
//User user = new User(1);
        groupes = reservationservice.findallreservationsbyid(userConnecte.getId());
        for (Reservation gr : groupes) {
            ListGroupes.add(gr);
            // System.out.println(gr.getId_demande());
            // System.out.println(gr.getUser().getId());
        }
        restab.setItems(ListGroupes);
        
    }

    @FXML
    void PayerAction(ActionEvent event)throws IOException,
			DocumentException {
        genererpdf();
        envoyerfacture();
        

    }
    void envoyerfacture(){
      String host="smtp.gmail.com";  
  final String user="amrouchefcb8@gmail.com";//change accordingly  
  final String password="amrouche1995";//change accordingly  
    
  String to="erandopi14@gmail.com";//change accordingly  
  //amrouchefcb8@gmail.com
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     props.put("mail.smtp.starttls.enable", "true");
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("facture de reservation");  
     message.setText("bonjour vous trouvez si joint la facture de votre reservation ");  
         MimeBodyPart mbp2 = new MimeBodyPart();
     FileDataSource fichier_joint = new FileDataSource("C:\\Users\\amrouche\\Cyberdev-covoituragetn\\test.pdf");
      mbp2.setDataHandler(new DataHandler(fichier_joint));
      mbp2.setFileName(fichier_joint.getName());
 
      // Créer un conteneur multipart pour les deux contenus
      Multipart mp = new MimeMultipart();
      
      mp.addBodyPart(mbp2);
 
      // Ajouter le Multipart au message
      message.setContent(mp);
    //send the message
  // message.setTLS(true);
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();} 
    }
    void genererpdf() throws IOException,
			FileNotFoundException, DocumentException
    {
    
    PdfReader pdfTemplate = new PdfReader("templatefacture.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream("test.pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);

		stamper.getAcroFields().setField("name", nom);
		stamper.getAcroFields().setField("montant", String.valueOf(montant));
		stamper.getAcroFields().setField("depart",
				depart);
		stamper.getAcroFields().setField("arrivee", arrivee);
                stamper.getAcroFields().setField("date",date);
		stamper.close();
		pdfTemplate.close();
    
    }
    public void recupererDonnee()
    {
    restab.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
Reservation gr =restab.getItems().get(restab.getSelectionModel().getSelectedIndex());
id=gr.getId();
nom =gr.getCreator().getNom() ;
montant=gr.getMontant() ;
depart=gr.getAnnonce().getLieuDepart();
arrivee=gr.getAnnonce().getLieuArriver() ;
date=gr.getDate_reservation() ;

        }
    });
    
    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCellTable();
        recupererDonnee();
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AnnoncesListController.class.getName()).log(Level.SEVERE, null, ex);
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

   /* @Override
    public void start(Stage stage) throws Exception {

Parent root = FXMLLoader.load(getClass().getResource("PayementFxml.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    
    }
    
    
     public static void main(String[] args)throws IOException,
			DocumentException {
         
        launch(args);
    
     }*/

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;       }
    
}
