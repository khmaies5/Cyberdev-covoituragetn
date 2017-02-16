/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IReponseService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AbonnesService;
import edu.esprit.pi.services.ReponseService;
import edu.esprit.pi.services.SujetService;
import edu.esprit.pi.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Sarra
 */
public class GroupeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane ScrollPaneSujet;
    @FXML
    private VBox VBoxSujets;
    @FXML
    private AnchorPane sujetsAnchorPane;
    @FXML
    private Label LBSujet;
    @FXML
    private Label LBDate;
    @FXML
    private Label LBNomSujet;
    @FXML
    private Separator separator;
    @FXML
    private Label LBNbReponse;
    @FXML
    private ImageView userImageView;
    @FXML
    private Label LBUserCeateurSujet;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton btnInviterMembres;
    @FXML
    private JFXButton Ajouterpublication;
    @FXML
    private Label LBnomGroupe;
    @FXML
    private JFXButton btnDemarrerChat;
    @FXML
    private JFXTextField Recherche;
    @FXML
    private ImageView ImageUserConn;
    @FXML
    private Label LBUserConn;
    @FXML
    private ScrollPane ScrollPaneMembres;
    @FXML
    private VBox VBoxMembre;
    @FXML
    private AnchorPane membresAnchorPane;
    @FXML
    private Label LBMembre;
    @FXML
    private ImageView membreImageView;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXButton btnDesab;
         @FXML
    private JFXButton btnRepondreSujet;
   public static AnchorPane rootP;
  AfficherMesGroupesController groupeController =new AfficherMesGroupesController();
        private List<Sujet> sujets;
        
          private List<Reponse> reponses;
         ISujetService affSujetsService = new SujetService();
          IAbonnesService abonnementService = new AbonnesService();
          IUserService userService = new UserService();
          IReponseService reponseService = new ReponseService();
private Groupe groupe=groupeController.getGroupeSelectionne();
    public GroupeController() {
       // connection = DataSource.getInstance().getConnection();

    }

/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          getallMemberList(); 
getallSujetsList();
    }    

    @FXML
    private void invitermembres(ActionEvent event) {
    }

    @FXML
    private void btnAjouterpublication(ActionEvent event) {
    }

    @FXML
    private void DemarrerChat(ActionEvent event) {
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }
    public void getallSujetsList(){
  
        sujets = affSujetsService.getbyIdGroupe(groupe.getId());
     System.out.println(sujets);
        if(!sujets.isEmpty()){
               System.out.println(sujets);
            for (Sujet sujet : sujets) {
                   int idAb =sujet.getCreator().getId();
       System.out.println(idAb);
          System.out.println(sujet);
        
                  AnchorPane newsujetsAnchorPane = new AnchorPane();
                  
        newsujetsAnchorPane.setStyle(sujetsAnchorPane.getStyle());
        newsujetsAnchorPane.setEffect(sujetsAnchorPane.getEffect());

      
      //**********************Titre sujet*******/
       Label LBNomSujet2 = new Label();
        LBNomSujet2.setFont(LBNomSujet.getFont());
        LBNomSujet2.setTextFill(LBNomSujet.getTextFill());
        LBNomSujet2.setLayoutX(LBNomSujet.getLayoutX());
        LBNomSujet2.setLayoutY(LBNomSujet.getLayoutY());
        LBNomSujet2.setText("Titre:"+" "+sujet.getObjet());
        /******************contenu sujet************/
        Label topicLabel2 = new Label();
        topicLabel2.setFont(LBSujet.getFont());
        topicLabel2.setTextFill(LBSujet.getTextFill());
        topicLabel2.setLayoutX(LBSujet.getLayoutX());
        topicLabel2.setLayoutY(LBSujet.getLayoutY());
        topicLabel2.setText(sujet.getTopic());
        /*****Nom User creacteur sujet***/
                 Label newUserCeateurSujet = new Label();
        newUserCeateurSujet.setFont(LBUserCeateurSujet.getFont());
        newUserCeateurSujet.setTextFill(LBUserCeateurSujet.getTextFill());
        newUserCeateurSujet.setLayoutX(LBUserCeateurSujet.getLayoutX());
        newUserCeateurSujet.setLayoutY(LBUserCeateurSujet.getLayoutY());
              
    
        Abonnes ab=abonnementService.findById(idAb);

              
                     User UserCreator = userService.findById(ab.getUser().getId());
                    
         newUserCeateurSujet.setText(UserCreator.getNom()+" "+UserCreator.getPrenom());
         ////********Image User Createur Sujet*****/
         
                    ImageView userImageView2 = new ImageView();
        
        userImageView2.setImage(userImageView.getImage());
        userImageView2.setLayoutX(userImageView.getLayoutX());
        userImageView2.setLayoutY(userImageView.getLayoutY());
        userImageView2.setStyle(userImageView.getStyle());
        userImageView2.setFitWidth(userImageView.getFitWidth());
        userImageView2.setFitHeight(userImageView.getFitHeight());
        
        
        /****************   NbReponses***/
   
 reponses=reponseService.GetReponseOfSujet(sujet.getId());
 int nbreponses=reponses.size();
        Label LBNbReponse2 = new Label();
        LBNbReponse2.setFont(LBNbReponse.getFont());
        LBNbReponse2.setTextFill(LBNbReponse.getTextFill());
        LBNbReponse2.setLayoutX(LBNbReponse.getLayoutX());
        LBNbReponse2.setLayoutY(LBNbReponse.getLayoutY());
        if(nbreponses==0) {   LBNbReponse2.setText("aucune réponse");}
        else    {   LBNbReponse2.setText(nbreponses+"réponse(s)");}
//         //  LBNbReponse2.setText("nbreponses");
//                System.out.println(nbreponses);
               /***************Data Annonce *****/
                  //datedepartlabel
        Label LBDate2 = new Label();
        LBDate2.setFont(LBDate.getFont());
        LBDate2.setTextFill(LBDate.getTextFill());
        LBDate2.setLayoutX(LBDate.getLayoutX());
        LBDate2.setLayoutY(LBDate.getLayoutY());
        LBDate2.setText("Date de publication: "+sujet.getDate_publication().toString());
        
        /****separator***/
                //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
        
                /**************afficher button repondre*/
                
   JFXButton repondreButton2 = new JFXButton();
        repondreButton2.setFont(btnRepondreSujet.getFont());
        repondreButton2.setTextFill(btnRepondreSujet.getTextFill());
        repondreButton2.setLayoutX(btnRepondreSujet.getLayoutX());
        repondreButton2.setLayoutY(btnRepondreSujet.getLayoutY());
        repondreButton2.setButtonType(btnRepondreSujet.getButtonType());
        repondreButton2.setRipplerFill(btnRepondreSujet.getRipplerFill());
        repondreButton2.setId(Integer.toString(sujet.getId()));
          //   newsujetsAnchorPane.getChildren().addAll(LBNomSujet2,topicLabel2,LBDate2,newUserCeateurSujet,LBNbReponse2,userImageView2);
    
   newsujetsAnchorPane.getChildren().addAll(LBNomSujet2,topicLabel2,newUserCeateurSujet,userImageView2,LBNbReponse2,LBDate2,separator1,repondreButton2);
      VBoxSujets.getChildren().add(newsujetsAnchorPane);
   //     newsujetsAnchorPane.setOnMouseClicked(e -> {
            		
//
//           ScreensFramework.annonceId=sujet.getId()
//           screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
//               screen.setScreen(ScreensFramework.screen3ID);
//
//                 
//   });
//        
                
            }}}
      public void getallMemberList(){}
//      newsujetsAnchorPane.setOnMouseClicked(e -> {
//            		
//
//            ScreensFramework.annonceId=sujet.getIdAnnonce();
//           screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
//                    screen.setScreen(ScreensFramework.screen3ID);
//
//                 
//        });
        
}
