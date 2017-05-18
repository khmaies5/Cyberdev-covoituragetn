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
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.models.UserFavoris;
import edu.esprit.pi.services.UserFavorisService;
import edu.esprit.pi.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nacef Fethi
 */

public class AddUserFavorisController implements Initializable,ControlledScreen {
    ScreensController screen;
  private List<User> lstUser;
  
    @FXML
    private VBox userfavVBox;
    @FXML
    private AnchorPane userfavAnchorPane;
    @FXML
    private JFXButton ajouterUserFav;
    @FXML
    private Label nomLabel;
    @FXML
    private Separator separator;
    @FXML
    private Label gouverneratLabel;
    @FXML
    private Label nbrRecommendationLabel;
    @FXML
    private ImageView sexeImage;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
//         @FXML
//    private JFXCheckBox chkUSer;
             @FXML
    private Label emptyLabel;
              @FXML
    private ImageView likeimg;

    public AddUserFavorisController() {
    }

    /**
     * Initializes the controller class.
     */
        //User uco=new User();
          UserFavorisController controller= new UserFavorisController();
          int idUserConn=User.getIdd();
          IUserFavorisService userfavService=new UserFavorisService();
                  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            System.out.println(idUserConn);
    try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AddUserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       

        getallUser();

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
     public void getallUser(){
            UserService uService = new UserService();

      lstUser =uService.GetUsersNotFavoris(User.getIdd());


        if(!lstUser.isEmpty()){
            
            for (User user : lstUser) {
                
       
                AnchorPane newUserFavAnchorPane = new AnchorPane();
        newUserFavAnchorPane.setStyle(userfavAnchorPane.getStyle());
        newUserFavAnchorPane.setEffect(userfavAnchorPane.getEffect());

       //nomLabbel
        Label nomLabel1 = new Label();
        nomLabel1.setFont(nomLabel.getFont());
        nomLabel1.setTextFill(nomLabel.getTextFill());
        nomLabel1.setLayoutX(nomLabel.getLayoutX());
        nomLabel1.setLayoutY(nomLabel.getLayoutY());
        nomLabel1.setText("Nom: "+user.getPrenom()+" "+user.getNom());

        //gouverneratLabel
        Label gouverneratLabel1 = new Label();
        gouverneratLabel1.setFont(gouverneratLabel.getFont());
        gouverneratLabel1.setTextFill(gouverneratLabel.getTextFill());
        gouverneratLabel1.setLayoutX(gouverneratLabel.getLayoutX());
        gouverneratLabel1.setLayoutY(gouverneratLabel.getLayoutY());
      gouverneratLabel1.setText("Gouvernerat: "+user.getGouvernorat());
        //nbr recomendation
        
           Label nbrRecommendationLabel1 = new Label();
        nbrRecommendationLabel1.setFont(nbrRecommendationLabel.getFont());
        nbrRecommendationLabel1.setTextFill(nbrRecommendationLabel.getTextFill());
        nbrRecommendationLabel1.setLayoutX(nbrRecommendationLabel.getLayoutX());
        nbrRecommendationLabel1.setLayoutY(nbrRecommendationLabel.getLayoutY());
        nbrRecommendationLabel1.setText("Nombre des publications: "+uService.nbrepub(user.getId()));
// image view 
//ImageView userImageView2 = new ImageView();
//        if(userFav.getUserRecommendes().getSexe()=="femme")
//        {
//        userImageView2.setImage(sexeImage.getImage());
//        }
//        else
//        {
//            // userImageView2.setImage(new Image("@../img/male.png"));
//        }
//        userImageView2.setLayoutX(sexeImage.getLayoutX());
//        userImageView2.setLayoutY(sexeImage.getLayoutY());
//        userImageView2.setStyle(sexeImage.getStyle());
//        userImageView2.setFitWidth(sexeImage.getFitWidth());
//        userImageView2.setFitHeight(sexeImage.getFitHeight());
       //separator

        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
       //suppB
//        JFXButton addButton = new JFXButton();
//        addButton.setFont(ajouterUserFav.getFont());
//        addButton.setTextFill(ajouterUserFav.getTextFill());
//        addButton.setLayoutX(ajouterUserFav.getLayoutX());
//        addButton.setLayoutY(ajouterUserFav.getLayoutY());
//        addButton.setButtonType(JFXButton.ButtonType.RAISED);
//        addButton.setRipplerFill(ajouterUserFav.getRipplerFill());
//        addButton.setText(ajouterUserFav.getText());
        
//          JFXCheckBox supprimerButton2 = new JFXCheckBox();
//        supprimerButton2.setFont(chkUSer.getFont());
//        supprimerButton2.setTextFill(chkUSer.getTextFill());
//        supprimerButton2.setLayoutX(chkUSer.getLayoutX());
//        supprimerButton2.setLayoutY(chkUSer.getLayoutY());
//         supprimerButton2.setText(chkUSer.getText());
ImageView likeimg1 = new ImageView();
        
        likeimg1.setImage(likeimg.getImage());
        likeimg1.setLayoutX(likeimg.getLayoutX());
        likeimg1.setLayoutY(likeimg.getLayoutY());
        likeimg1.setStyle(likeimg.getStyle());
        likeimg1.setFitWidth(likeimg.getFitWidth());
        likeimg1.setFitHeight(likeimg.getFitHeight());
        
        newUserFavAnchorPane.getChildren().addAll(nomLabel1,gouverneratLabel1,separator1,nbrRecommendationLabel1,likeimg1);
        userfavVBox.getChildren().add(newUserFavAnchorPane);
          
        newUserFavAnchorPane.setOnMouseClicked(e -> {
            
            System.out.println(user.getId());
        

                 
       });
        //supprimerButton2
        likeimg1.setOnMouseClicked(e -> {
     
            System.out.println(user.getId());
          System.out.println(idUserConn);
        UserFavoris userfav=new UserFavoris(user,new User(idUserConn));
            IUserFavorisService ufavService=new UserFavorisService();
ufavService.add(userfav);
   new  myDownloader().start(); 
           Parent affichageAlerte;      
                    try {
                        affichageAlerte = FXMLLoader.load(getClass().getResource("UserFavoris.fxml"));
                          Scene sceneAffichage = new Scene(affichageAlerte);
     Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneAffichage);
      stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AddUserFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        

       });
        
                
            }
        }
        else 
        {
           System.out.println("vide");
         
      emptyLabel.setVisible(true);
        emptyLabel.setText("Il n'y a pas d'utilisateur");
        }
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
screen=screenPage;    }
        class myDownloader extends Thread{
    public void run (){
    
//    try{Thread.sleep(5000);}
//    catch (InterruptedException ex) {
//            Logger.getLogger(addPubFavController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    Notifications notificationBuilder=Notifications.create()
            .title("User Favoris")
            .text("Vous avez ajouté un utilisateur à votre liste des favoris")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.TOP_RIGHT)
            .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("clicked on notif");      
 
 
        }
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

  
}
