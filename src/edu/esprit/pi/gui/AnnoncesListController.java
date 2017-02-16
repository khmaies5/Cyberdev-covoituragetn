package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.tests.AnnonceTest;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnnoncesListController extends Application implements Initializable,ControlledScreen {

   

    
    private List<Annonce> annonces;
   ScreensController screen;

   
       @FXML
    private BorderPane root;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane annoncesAnchorPane;


    @FXML
    private Label fromToLabel;

    @FXML
    private JFXButton reserverButton;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private Label dateDepartLabel;

     @FXML
    private Label placesLabel;

    @FXML
    private ImageView userImageView;


    @FXML
    private Label prixLabel1;

    @FXML
    private Label placesLabel1;

    @FXML
    private JFXButton ajouterOfferButton;

    @FXML
    private JFXButton ajouterDemandeButton;
    
   @FXML
    private Label driverNameLabel;

    @FXML
    private Label experianceLabel;
    
    
       @FXML
    private Separator separator;
    
    @FXML
    private Parent mainWindow;
    
     @FXML
    private VBox annoncesVBox;
     
      @FXML
    private ImageView test;
     
     
       //  public static BorderPane rootP;

     

   
   
    
    
    

    
    
    
    
    
    
    private void openDashboard() throws IOException {
        
    }
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // rootP = root;
        

        ajouterOfferButton.setOnAction(e -> {
        
       
        screen.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);

        screen.setScreen(ScreensFramework.screen2ID);
        });
        
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AnnoncesListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       

        getallAnnoncesList();

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
    
    public void getallAnnoncesList(){
        AnonncesService annService = new AnonncesService();
        annonces = annService.getAll();
        
        if(!annonces.isEmpty()){
            
            for (Annonce annonce : annonces) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(fromToLabel.getFont());
        fromToLabel2.setTextFill(fromToLabel.getTextFill());
        fromToLabel2.setLayoutX(fromToLabel.getLayoutX());
        fromToLabel2.setLayoutY(fromToLabel.getLayoutY());
        fromToLabel2.setText(annonce.getLieuDepart()+"->"+annonce.getLieuArriver());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateDepartLabel.getFont());
        dateDepartLabel2.setTextFill(dateDepartLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateDepartLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateDepartLabel.getLayoutY());
        dateDepartLabel2.setText("Date Depart: "+annonce.getTripDate().toString());
        //distanceLabel
        Label distanceLabel2 = new Label();
        distanceLabel2.setFont(distanceLabel.getFont());
        distanceLabel2.setTextFill(distanceLabel.getTextFill());
        distanceLabel2.setLayoutX(distanceLabel.getLayoutX());
        distanceLabel2.setLayoutY(distanceLabel.getLayoutY());
        distanceLabel2.setText(distanceLabel.getText());
        //prixLabel
        Label prixLabel2 = new Label();
        prixLabel2.setFont(prixLabel.getFont());
        prixLabel2.setTextFill(prixLabel.getTextFill());
        prixLabel2.setLayoutX(prixLabel.getLayoutX());
        prixLabel2.setLayoutY(prixLabel.getLayoutY());
        prixLabel2.setText(""+annonce.getPrix());
        
        //prixLabel1
        Label prixLabel4 = new Label();
        prixLabel4.setFont(prixLabel1.getFont());
        prixLabel4.setTextFill(prixLabel1.getTextFill());
        prixLabel4.setLayoutX(prixLabel1.getLayoutX());
        prixLabel4.setLayoutY(prixLabel1.getLayoutY());
        prixLabel4.setText(prixLabel1.getText());
        //tempDepartLabel
        /*Label tempDepartLabel2 = new Label();
        tempDepartLabel2.setFont(tempDepartLabel.getFont());
        tempDepartLabel2.setTextFill(tempDepartLabel.getTextFill());
        tempDepartLabel2.setLayoutX(tempDepartLabel.getLayoutX());
        tempDepartLabel2.setLayoutY(tempDepartLabel.getLayoutY());
        tempDepartLabel2.setText("Temp Depart: "+annonce.getTripDate().toString());*/
        
        //userImageView
        ImageView userImageView2 = new ImageView();
        
        userImageView2.setImage(userImageView.getImage());
        userImageView2.setLayoutX(userImageView.getLayoutX());
        userImageView2.setLayoutY(userImageView.getLayoutY());
        userImageView2.setStyle(userImageView.getStyle());
        userImageView2.setFitWidth(userImageView.getFitWidth());
        userImageView2.setFitHeight(userImageView.getFitHeight());
        
        //drivername
        Label driverNameLabel2 = new Label();
        driverNameLabel2.setFont(driverNameLabel.getFont());
        driverNameLabel2.setStyle(driverNameLabel.getStyle());
        driverNameLabel2.setTextFill(driverNameLabel.getTextFill());
        driverNameLabel2.setLayoutX(driverNameLabel.getLayoutX());
        driverNameLabel2.setLayoutY(driverNameLabel.getLayoutY());
        driverNameLabel2.setText(annonce.getCreator().getNom()+" "+annonce.getCreator().getPrenom());
        //driverexperiance
        Label experiancLabel1 = new Label();
        experiancLabel1.setFont(experianceLabel.getFont());
        
        experiancLabel1.setTextFill(experianceLabel.getTextFill());
        experiancLabel1.setLayoutX(experianceLabel.getLayoutX());
        experiancLabel1.setLayoutY(experianceLabel.getLayoutY());
        experiancLabel1.setText("experience todo");
        
        
        //tempDepartLabel1
        Label placesLabel2 = new Label();
        placesLabel2.setFont(placesLabel.getFont());
        placesLabel2.setTextFill(placesLabel.getTextFill());
        placesLabel2.setLayoutX(placesLabel.getLayoutX());
        placesLabel2.setLayoutY(placesLabel.getLayoutY());
        placesLabel2.setText(""+annonce.getNbrPersonne());
        //placesLabel2
         Label placesLabel3 = new Label();
        placesLabel3.setFont(placesLabel1.getFont());
        placesLabel3.setTextFill(placesLabel1.getTextFill());
        placesLabel3.setLayoutX(placesLabel1.getLayoutX());
        placesLabel3.setLayoutY(placesLabel1.getLayoutY());
        placesLabel3.setText(placesLabel1.getText());
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
        //reserverButton
       /* JFXButton reserverButton2 = new JFXButton();
        reserverButton2.setFont(reserverButton.getFont());
        reserverButton2.setTextFill(reserverButton.getTextFill());
        reserverButton2.setLayoutX(reserverButton.getLayoutX());
        reserverButton2.setLayoutY(reserverButton.getLayoutY());
        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
        reserverButton2.setText(reserverButton.getText());*/
        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,distanceLabel2,prixLabel2,prixLabel4,separator1,placesLabel2,placesLabel3,userImageView2,driverNameLabel2,experiancLabel1);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
        newAnnoncesAnchorPane.setOnMouseClicked(e -> {
            		

            ScreensFramework.annonceId=annonce.getIdAnnonce();
           screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                    screen.setScreen(ScreensFramework.screen3ID);

                 
        });
        
                
            }
        }
    }
    
    
    
    
    
   

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;
    }

}
