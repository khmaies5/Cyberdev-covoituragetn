package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXToggleNode;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.elevation.LocationElevationRequest;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.services.PublicationFavoritesService;
import edu.esprit.pi.services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class InterfaceAnnonceController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback, ControlledScreen {

    private GoogleMap map;

    private GeocodingService geocodingService;

    ScreensController screen;

    private MarkerOptions markerOptions;

    protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    private StackPane root;

    @FXML
    private Pane annoncePane;

    @FXML
    private GoogleMapView mapView;

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
    private Separator separator;

    @FXML
    private Label prixLabel1;

    @FXML
    private Label placesLabel1;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label driverNameLabel;

    @FXML
    private Label experianceLabel;

    @FXML
    private ImageView citere1Label;

    @FXML
    private ImageView citere2Label;

    @FXML
    private ImageView citere3Label;

    @FXML
    private ImageView citere4Label;

    @FXML
    private JFXRippler favoriteToggle;

    @FXML
    private JFXSnackbar snackbar;

    @FXML
    private JFXCheckBox favCheckBox;

    @FXML
    private Label tempRest;

    @FXML
    private Label tempRest1;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXRippler optionsRippler;

    @FXML

    private JFXPopup toolbarPopup;

    @FXML
    private Label exit;

    @FXML
    private StackPane titleBurgerContainer;

    @FXML
    private StackPane optionsBurger;

    @FXML
    private JFXListView<?> list1;

    @FXML
    private JFXListView<Label> subList;

    @FXML
    private JFXListView<JFXButton> popupList;

    @FXML
    private Label voitureLabel;

    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXRippler rippler1;

    @FXML
    private JFXPopup popup;
    @FXML
    JFXButton modifierButton = new JFXButton("Modifier");
    @FXML
    JFXButton supprimerButton = new JFXButton("Supprimer");
    @FXML
    JFXButton signalerButton = new JFXButton("Signaler");

    @FXML
    private JFXButton acceptButton;

    @FXML
    private JFXButton closetButton;
    
    

    private String distance;

    Annonce annonces = new Annonce();
    AnonncesService annService = new AnonncesService();
    User userO = new User(User.getIdd());

    @FXML
    void favCheckBoxOnAction(ActionEvent event) {
        User user = new User(User.getIdd());
        addFavorite(user, annonces);

    }

    @FXML
    private void toTextFieldAction(ActionEvent event) {

    }

    public static AnchorPane rootP;

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        DirectionsResult e = results;
        try {
            distance = e.getRoutes().get(0).getLegs().get(0).getDistance().getText();
            distanceLabel.setText("Distance: " + distance);

            System.out.println("Distance total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }

    public void optionsButton() {
        System.out.println("connected user " + userO.getId());

        if (userO.getId() == annonces.getCreator().getId()) {

            popupList.getItems().addAll(modifierButton, supprimerButton);

            modifierButton.setOnMouseClicked(e -> {

                ScreensFramework.annonceId = annonces.getIdAnnonce();
                screen.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
                screen.setScreen(ScreensFramework.screen5ID);

            });
            supprimerButton.setOnMouseClicked(e -> {
                
                dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
                dialog.show(root);

                //
                /*screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                    screen.setScreen(ScreensFramework.screen3ID);*/
            });
            acceptButton.setOnMouseClicked((e) -> {
                dialog.close();
                annService.delete(annonces.getIdAnnonce());
                screen.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
                screen.setScreen(ScreensFramework.screen1ID);
            });
            closetButton.setOnMouseClicked((e) -> {
                dialog.close();

            });
        } else {

popupList.getItems().add(signalerButton);
            signalerButton.setOnMouseClicked(e -> {

                ScreensFramework.annonceId = annonces.getIdAnnonce();
                screen.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
                screen.setScreen(ScreensFramework.screen3ID);

            });

        }

        rippler1.setOnMouseClicked(e -> {

            popup.setSource(rippler1);
            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapView.addMapInializedListener(this);
        popupList.depthProperty().set(1);
        popup.setPopupContainer(root);

        setAnnonceDetails();
        optionsButton();

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //to.bindBidirectional(toTextField.textProperty());
        //from.bindBidirectional(fromTextField.textProperty());

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }

    public void setAnnonceDetails() {
        annonces = annService.findById(ScreensFramework.annonceId); //4 //ScreensFramework.annonceId
        fromToLabel.setText(annonces.getLieuDepart() + "->" + annonces.getLieuArriver());
        /*  if (!distance.isEmpty()){
        distanceLabel.setText("Distance: "+distance);
        }
        else*/
        //distanceLabel.setText("Distance: --");

        prixLabel.setText((int) annonces.getPrix() + "Dt");
        dateDepartLabel.setText("Date Depart: " + annonces.getTripDate().toString());
if(annonces.getNbrPersonne()==0){placesLabel.setText("complet");
reserverButton.setVisible(false);}
else
{    placesLabel.setText("" + annonces.getNbrPersonne());}
        User user = new User();
        UserService serU = new UserService();

        user = serU.findById(annonces.getCreator().getId());
        driverNameLabel.setText(user.getNom() + " " + user.getPrenom());
        experianceLabel.setText(user.getNiv_Experience());
        voitureLabel.setText("Vehicule: aaaaywah");

     /*   Image image = new Image("http://localhost/covoituragetn/" + user.getPhoto_Profil());
        userImageView.setImage(image);
String[] parts = annonces.getCritere().split(";");
/*citere1Label.setImage(new Image("http://localhost/covoituragetn/"+parts[0]));
citere2Label.setImage(new Image("http://localhost/covoituragetn/"+parts[1]));
citere3Label.setImage(new Image("http://localhost/covoituragetn/"+parts[2]));
citere4Label.setImage(new Image("http://localhost/covoituragetn/"+parts[3]));*/
        snackbar.registerSnackbarContainer(root);

        favoriteToggle.setOnMouseClicked((e) -> {
            //int value = Integer.parseInt(favoriteToggle.getText());
            if (e.getButton() == MouseButton.PRIMARY) {

                snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("user added to favorite ", "CLOSE", 3000, true, (b) -> {
                    snackbar.close();
                }));
              

            } else if (e.getButton() == MouseButton.SECONDARY) {

            }

        });
    }

    public void addFavorite(User user, Annonce annonce) {

        IPublicationFavoritesService pubFavService = new PublicationFavoritesService();

        PublicationFavorite pubFav = new PublicationFavorite(user, annonce);

        pubFavService.add(pubFav);
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("user added to favorite ", "CLOSE", 3000, true, (b) -> {
            snackbar.close();
        }));

    }

    @Override
    public void mapInitialized() {

        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        DirectionsRequest request = new DirectionsRequest(annonces.getLieuDepart(), annonces.getLieuArriver(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        // JSObject obj ;

        /* map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
            
        });*/
 /* annoncePane.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            //lblClick.setText(ll.toString());
            
        });*/
        LatLong ll = null;

        System.out.println("longlag ");

    }
  @FXML
    void demandereserverAction(ActionEvent event) throws IOException {
   AjoutReservationController controller =new AjoutReservationController();
   // controller.initVariable(param,param1,param2);
    
    Stage stage ;
       // Parent root ;
        if(event.getSource()==reserverButton)
        {
            FXMLLoader afficher = new FXMLLoader();
        afficher.setLocation(getClass().getResource("AjoutReservation.fxml"));
         Parent  root = afficher.load();
            stage =new Stage();
      // root=FXMLLoader.load(getClass().getResource("AjoutReservation.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(reserverButton.getScene().getWindow());
        stage.showAndWait();
        Scene scene =new Scene(root);
    stage.setScene(scene);
    stage.show();
        }
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {

        screen = screenPage;
    }

}
