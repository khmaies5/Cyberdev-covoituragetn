/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.validation.RequiredFieldValidator;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author khmai
 */




public class AjouterAnnoncesController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
    
   
    
    private GoogleMap map;
    
    private GeocodingService geocodingService;

   
    
     private MarkerOptions markerOptions;
     
     protected DirectionsService directionsService;
     
    protected DirectionsPane directionsPane;
    
    

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private JFXDatePicker dateAnnoncePicker;

    @FXML
    private JFXDatePicker timeAnnoncePicker;
    
    
    @FXML
    private DatePicker test;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private GoogleMapView mapview;
    
     @FXML
    private GoogleMapView mapView;
    
    @FXML
    private JFXTextField fromTextField;
    
     @FXML
    private JFXTextField toTextField;
     
     @FXML
    JFXTextField prixTextField;
     
      @FXML
    JFXTextField numberTextField;
      
       @FXML
    private JFXSlider numberSlider;
       
    @FXML
    private JFXSlider prixSlider;

    public static AnchorPane rootP;
    
    @FXML
    private void toTextFieldAction(ActionEvent event) {
        
        System.out.println("to text field action "+event.toString());
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
    
    @FXML
    private void submitAnnonceButtonAction(ActionEvent event) {
        
        String s = dateAnnoncePicker.getValue().toString()+" "+timeAnnoncePicker.getTime().toString();
        String from = fromTextField.getText();
        String to = toTextField.getText();
        float prix = (float)  prixSlider.getValue() ;
        int number =(int) numberSlider.getValue();

    
        Date d = null;
        
        try {
             d = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(s);
            
        } catch (ParseException ex) {
            Logger.getLogger(AjouterAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Annonce ann = new Annonce(6, d,null, from, to,"test", number, prix, "test", new User(4));
       
         IService service = new AnonncesService();
        service.add(ann);
        
               System.out.println("to text field action "+event.toString());
               
               System.out.println("date and time "+ d);
 
    }
    
  /*  public Date dateTime(Date date, Date time) {

    Calendar aDate = Calendar.getInstance();
    aDate.setTime(date);

    Calendar aTime = Calendar.getInstance();
    aTime.setTime(time);

    Calendar aDateTime = Calendar.getInstance();
    aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
    aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
    aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
    aDateTime.set(Calendar.HOUR, aTime.get(Calendar.HOUR));
    aDateTime.set(Calendar.MINUTE, aTime.get(Calendar.MINUTE));

    return aDateTime.getTime();
}   */
    
    public void setDirection(){
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rootP = root;
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("ce champ est obligatoire");
        toTextField.getValidators().add(validator);
        fromTextField.getValidators().add(validator);
       
        dateAnnoncePicker.setValue(LocalDate.now());
        timeAnnoncePicker.setTime(LocalTime.now());

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AjouterAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
        
   toTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            System.out.println("Textfield on focus");
        }
        else
        {
            System.out.println("Textfield out focus");
            if(toTextField.getText()!= ""){
            setDirection();
            }
            toTextField.validate();
        }
    }
});
      fromTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (!newPropertyValue)
        {
            fromTextField.validate();
        }
    }
});
         
           
      
        
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
    
     @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732,11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        
    }
    
    /*    @FXML
    public void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;

            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
               
AddMarker(latLong,address.get());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                AddMarker(latLong,address.get());
            }
            
            map.setCenter(latLong);

        });
    }*/
    
    private void AddMarker(LatLong l,String address){
        
                   
	 Marker myMarker = null;
           markerOptions = new MarkerOptions();
        markerOptions.position(l)
                .title("My new Marker")
                .visible(true);

        myMarker = new Marker(markerOptions);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content(address)
                .position(l);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
                map.addMarker(myMarker);
        
    }
    
}
