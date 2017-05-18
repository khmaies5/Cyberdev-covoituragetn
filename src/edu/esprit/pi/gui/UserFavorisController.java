/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IUserFavorisService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.models.UserFavoris;
import edu.esprit.pi.services.UserFavorisService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.transform.Source;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */

public class UserFavorisController implements Initializable , ControlledScreen{
    ScreensController screen;
  private List<UserFavoris> lstUserFav;
    @FXML
    private VBox userVBox;
    @FXML
    private AnchorPane userAnchorPane;
    @FXML
    private JFXButton supprimerUserFav;
    @FXML
    private JFXButton  addUFav;
   @FXML
    private ImageView sexeImage;
  
  @FXML
    private Button pubUser;
    @FXML
    private Label nomLabel;
     @FXML
    private ImageView suppImage;
    @FXML
    private Separator separator;
    @FXML
    private Label gouverneratLabel;
     @FXML
    private Label Labelvide;
       @FXML
    private Label dateEnregistrement;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
      @FXML
    private Label nomUserConect;
   
public static int iduserfav;

    public static int getIduserfav() {
        return iduserfav;
    }
 User user=new User(User.getIdd());

   
 int idUserConn=user.getId();

    public int getIdUserConn() {
        return idUserConn;
    }

    public UserFavorisController() {
    }


    
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    void addUserFav(ActionEvent event) throws IOException {

            System.out.println(idUserConn);
            
                 {  RechercheListeAnnonceController.screen.loadScreen(ScreensFramework.screen14ID, ScreensFramework.screen14File);
           RechercheListeAnnonceController.screen.setScreen(ScreensFramework.screen14ID);}
 
    }
             

    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(UserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        getallUserFav();

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
 
        public void getallUserFav(){
            UserFavorisService userService = new UserFavorisService();
        
           lstUserFav=userService.findByIduserconnecter(user.getIdd());
      
        
        if(!lstUserFav.isEmpty()){
            Labelvide.setVisible(false);
            
            for (UserFavoris userFav : lstUserFav) {
           nomUserConect.setText(userFav.getUserConnect().getPrenom()+" "+userFav.getUserConnect().getNom());   

                AnchorPane newUserFavAnchorPane = new AnchorPane();
        newUserFavAnchorPane.setStyle(userAnchorPane.getStyle());
        newUserFavAnchorPane.setEffect(userAnchorPane.getEffect());

       //nomLabbel
        Label nomLabel1 = new Label();
        nomLabel1.setFont(nomLabel.getFont());
        nomLabel1.setTextFill(nomLabel.getTextFill());
        nomLabel1.setLayoutX(nomLabel.getLayoutX());
        nomLabel1.setLayoutY(nomLabel.getLayoutY());
        nomLabel1.setText("Nom: "+userFav.getUserRecommendes().getPrenom()+" "+userFav.getUserRecommendes().getNom());

        //gouverneratLabel
        Label gouverneratLabel1 = new Label();
        gouverneratLabel1.setFont(gouverneratLabel.getFont());
        gouverneratLabel1.setTextFill(gouverneratLabel.getTextFill());
        gouverneratLabel1.setLayoutX(gouverneratLabel.getLayoutX());
        gouverneratLabel1.setLayoutY(gouverneratLabel.getLayoutY());
       gouverneratLabel1.setText("Gouvernerat: "+userFav.getUserRecommendes().getGouvernorat());
        //date enregistrement
           Label dateEnregistrementLabel = new Label();
        dateEnregistrementLabel.setFont(dateEnregistrement.getFont());
        dateEnregistrementLabel.setTextFill(dateEnregistrement.getTextFill());
        dateEnregistrementLabel.setLayoutX(dateEnregistrement.getLayoutX());
        dateEnregistrementLabel.setLayoutY(dateEnregistrement.getLayoutY());
        dateEnregistrementLabel.setText("date d'enregistrement: "+userFav.getDateEnregistrement());
// image view 
ImageView userImageView2 = new ImageView();
      
        userImageView2.setLayoutX(sexeImage.getLayoutX());
        userImageView2.setLayoutY(sexeImage.getLayoutY());
        userImageView2.setStyle(sexeImage.getStyle());
        userImageView2.setFitWidth(sexeImage.getFitWidth());
        userImageView2.setFitHeight(sexeImage.getFitHeight());
       //separator

        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
      
////        
//        Button supprimerButton2 = new Button();
//        supprimerButton2.setFont(supp.getFont());
//        supprimerButton2.setTextFill(supp.getTextFill());
//        supprimerButton2.setLayoutX(supp.getLayoutX());
//        supprimerButton2.setLayoutY(supp.getLayoutY());
////      //  supprimerButton2.setButtonType(Button.ButtonType.RAISED);
////     //   supprimerButton2.setRipplerFill(supprimerUserFav.getRipplerFill());
//        supprimerButton2.setText(supp.getText());
ImageView deleteimg = new ImageView();
        
        deleteimg.setImage(suppImage.getImage());
        deleteimg.setLayoutX(suppImage.getLayoutX());
        deleteimg.setLayoutY(suppImage.getLayoutY());
        deleteimg.setStyle(suppImage.getStyle());
        deleteimg.setFitWidth(suppImage.getFitWidth());
        deleteimg.setFitHeight(suppImage.getFitHeight());
        
           Button listPub = new Button();
        listPub.setFont(pubUser.getFont());
        listPub.setTextFill(pubUser.getTextFill());
        listPub.setLayoutX(pubUser.getLayoutX());
        listPub.setLayoutY(pubUser.getLayoutY());
      listPub.setStyle("-fx-background-color: #2196f3");
        listPub.setText(pubUser.getText());
      
        
        newUserFavAnchorPane.getChildren().addAll(nomLabel1,gouverneratLabel1,dateEnregistrementLabel,separator1,deleteimg,userImageView2,listPub);
        userVBox.getChildren().add(newUserFavAnchorPane);
          
        newUserFavAnchorPane.setOnMouseClicked(e -> {
            
            System.out.println(userFav.getId());
        


                 
       });
        deleteimg.setOnMouseClicked(e -> {
            
            System.out.println(userFav.getId());
        
userService.delete(userFav.getId());

       Parent affichageAlerte;
                    try {
                        affichageAlerte = FXMLLoader.load(getClass().getResource("UserFavoris.fxml"));
                   Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    

       });
         listPub.setOnMouseClicked(e -> {
           
            
        
     AnnoncesListController annonceController=new AnnoncesListController();
      iduserfav=userFav.getUserRecommendes().getId();
             System.out.println(iduserfav);
      
       Parent affichageAlerte;
                    try {
                     affichageAlerte = FXMLLoader.load(getClass().getResource("AnnoncesList.fxml"));
                   Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(UserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    

       });
                
            }
            
        }
       
        else{
           System.out.println("vide");
     

      Labelvide.setVisible(true);
        Labelvide.setText("Vous n'avez aucun utilisateur dans la liste ");
          
        }
               
       
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
    
}
