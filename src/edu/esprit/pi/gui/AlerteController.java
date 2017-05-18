package edu.esprit.pi.gui;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import static edu.esprit.pi.gui.AffichageAlerteController.lstAlerte;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AlerteService;
import edu.esprit.pi.technique.DataSource;
import static edu.esprit.pi.gui.TextFieldValidation.isTextFieldEmpty;
import static java.awt.Color.red;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AlerteController implements Initializable ,ControlledScreen{
ObservableList<Data> list=FXCollections.observableArrayList();
    ScreensController screen;
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
    private Label LId;
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private PieChart pi;
    @FXML
    private JFXHamburger hamburger;
      @FXML
    private JFXSnackbar snackbar;
    @FXML
    private AnchorPane root;
        @FXML
    private JFXTextField txtLieuDepart;
           @FXML
    private JFXTextField txtLieuArrivee;
              @FXML
    private JFXDatePicker dateP;
                 @FXML
    private JFXButton btnMesAlertes;
    
              @FXML
    private JFXSlider SliderHeure;         
     @FXML
    private JFXButton btnEnregistrer;
 
       @FXML
    private JFXButton btnAnnuler;

    public static AnchorPane rootP;
  //  private Connection connection;
 //   private PreparedStatement ps;
     AlerteService alerteService= new AlerteService();
     

//     
//  AffichageAlerteController alerteController =new AffichageAlerteController();
//private Alerte alerte=alerteController.getAlerteSelectionne();

    public AlerteController() {
               // connection = DataSource.getInstance().getConnection();
    }
    
    private void  SetCellTable()
    {
   CLLieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieuDepart"));
      CLLieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieuArrivee"));
        CLDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        CLHeure.setCellValueFactory(new PropertyValueFactory<>("heure")); 
          lstAlerte = new ArrayList<>();
           User user=new User(1);
             
          lstAlerte = alerteService.getAll(user.getId());
           for (Alerte alerte : lstAlerte) {
              ListAlerte.add(alerte);
                      TableAlerte.setItems(ListAlerte);
            }
          
    }

    @FXML
 private void annuler(ActionEvent event) {
        txtLieuDepart.setText("");
        txtLieuArrivee.setText("");
        dateP.setValue(LocalDate.now());
        SliderHeure.setValue(0);
      
        
    }
    
    
    @FXML
    private void ajouterAlerte(ActionEvent event) throws Exception{

 
    User user=new User(User.getIdd());
     Alerte alerte =new Alerte (txtLieuDepart.getText(),txtLieuArrivee.getText(),java.sql.Date.valueOf(dateP.getValue()),(int) SliderHeure.getValue(),user);
     if(isTextFieldEmpty(txtLieuDepart)&& isTextFieldEmpty(txtLieuArrivee)  ) 
     {
         alerteService.add(alerte);
 
          Parent affichageAlerte = FXMLLoader.load(getClass().getResource("InterfaceAlerte.fxml"));
     Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
        } 
     else 
     {
           
     Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Veuillez remplir tous les champs");

alert.showAndWait();
     
     }



   

    }


    
 

//         @FXML
//    private void modifierAlerte(ActionEvent event) throws Exception{
//         User user=new User(2);
//        int id=Integer.parseInt(LId.getText());
//         System.out.println(id);
// Alerte alerte =new Alerte (id,txtLieuDepart.getText(),txtLieuArrivee.getText(),java.sql.Date.valueOf(dateP.getValue()),(int) SliderHeure.getValue(),user);
// alerteService.update(alerte);
// // SetCellTable();
//    }
//    @FXML
//    private void supprimerAlerte(ActionEvent event) throws Exception{
//        int id=Integer.parseInt(LId.getText());
//         alerteService.delete(id);
//        //  SetCellTable();
//    }
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        IAlerteService pdao=new AlerteService();
//        List<Alerte> pers = null;
//        pers = pdao.getAll();
//
//  ObservableList<PieChart.Data> lis =FXCollections.observableArrayList();
//     
//  pdao.getAll().stream().collect(Collectors.groupingBy(f->f.getLieuDepart(), Collectors.counting())).forEach((t,count)->lis.add(new PieChart.Data(t, count)));
//        pi.setData(lis);      
//        pi.setAnimated(true);
//        
        
        
 dateP.setValue(LocalDate.now());
                RequiredFieldValidator validator1=new RequiredFieldValidator();
       txtLieuDepart.getValidators().add(validator1);
      validator1.setMessage("Champ obligatoire");
      RequiredFieldValidator validator2=new RequiredFieldValidator();
       txtLieuArrivee.getValidators().add(validator2);
      validator2.setMessage("Champ obligatoire");
      
      txtLieuDepart.focusedProperty().addListener(new ChangeListener<Boolean>(){
        @Override
        public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue){
            if (!newValue)
            {
                txtLieuDepart.validate();
            }
        }
    });
         txtLieuArrivee.focusedProperty().addListener(new ChangeListener<Boolean>(){
        @Override
        public void changed(ObservableValue<? extends Boolean> observale, Boolean oldValue, Boolean newValue){
            if (!newValue)
            {
                txtLieuArrivee.validate();
            }
        }
    });
        rootP = root;


        String[] possiblewords={"Tunis","Bizerte","Béja","Monastir","Sousse","Sfax"
                , "Kairouan"
                , "Gabès"
                , "Gafsa"
                , "Kasserine"
                , "Hammamet"
                , " Médenine"
                , "Nabeul"
                , "Tataouine"
                , "Mahdia"
                , "Sidi Bouzid"
                , "Jendouba"
                , "Tozeur"
                , "Zaghouan"
                , "Kébili"
                , "Tabarka"
                ,"Jerba"
                , "Kélibia"};
        TextFields.bindAutoCompletion(txtLieuDepart, possiblewords);
         TextFields.bindAutoCompletion(txtLieuArrivee, possiblewords);
       
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AlerteController.class.getName()).log(Level.SEVERE, null, ex);
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
    class myDownloader extends Thread{
    public void run (){
    
    try{Thread.sleep(5000);}
    catch (InterruptedException ex) {
            Logger.getLogger(AlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    Notifications notificationBuilder=Notifications.create()
            .title("alerte")
            .text("pub")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT)
            .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("clicked on notif");        }
    }
            
            
            );
    notificationBuilder.darkStyle();
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
                notificationBuilder.show();

        }
    });
    }
    
    
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
               screen=screenPage;

    }
}
    

