package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXToggleNode;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
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
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import edu.esprit.pi.iservices.ControlledScreen;
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.services.PublicationFavoritesService;
import edu.esprit.pi.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class InterfaceAnnonceController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback,ControlledScreen {

    private GoogleMap map;

    private GeocodingService geocodingService;

    ScreensController screen;

    private MarkerOptions markerOptions;

    protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;
    
   

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

@FXML
    private AnchorPane root;

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
  

    Annonce annonces = new Annonce();
        AnonncesService annService = new AnonncesService();

    @FXML
    void favCheckBoxOnAction(ActionEvent event) {
        User user = new User(6);
addFavorite(user, annonces);
    }
    
    

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        
    }
   

    public static AnchorPane rootP;
    
    
      @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
                annonces = annService.findById(ScreensFramework.annonceId);
                
                System.out.println(annonces);
                         mapView.addMapInializedListener(this);


        fromToLabel.setText(annonces.getLieuDepart()+"->"+annonces.getLieuArriver());
        
        distanceLabel.setText("Distance: todo");
        prixLabel.setText(""+annonces.getPrix());
        dateDepartLabel.setText("Date Depart: "+annonces.getTripDate().toString());
        
        placesLabel.setText(""+annonces.getNbrPersonne());
        User user = new User();
        UserService serU = new UserService();
        
        user = serU.findById(annonces.getCreator().getId());
        driverNameLabel.setText(user.getNom()+" "+user.getPrenom());
        experianceLabel.setText("experiance: todo rania");
        		snackbar.registerSnackbarContainer(root);

        favoriteToggle.setOnMouseClicked((e) -> {
			//int value = Integer.parseInt(favoriteToggle.getText());
			if (e.getButton() == MouseButton.PRIMARY) {
                            
				snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("user added to favorite ","CLOSE",3000, true,(b)->{snackbar.close();}));
                                System.out.println("snackbar");
                                
			} else if (e.getButton() == MouseButton.SECONDARY) {
				
			}

		});
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
    
    
    public void addFavorite(User user,Annonce annonce){
        
                      IPublicationFavoritesService pubFavService= new PublicationFavoritesService(); 



         PublicationFavorite pubFav =new PublicationFavorite (user,annonce);

        pubFavService.add(pubFav);
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("user added to favorite ","CLOSE",3000, true,(b)->{snackbar.close();}));
        
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
        
       
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    screen=screenPage;
    }

}
