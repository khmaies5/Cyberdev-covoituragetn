/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.tests;


//GmapsFX
 import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.LatLongBounds;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsGeocodedWaypoint;
import com.lynden.gmapsfx.service.directions.DirectionsLeg;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.DirectionsSteps;
import com.lynden.gmapsfx.service.directions.DirectionsWaypoint;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.elevation.ElevationResult;
import com.lynden.gmapsfx.service.elevation.ElevationService;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationStatus;
import com.lynden.gmapsfx.service.elevation.LocationElevationRequest;
import com.lynden.gmapsfx.service.elevation.PathElevationRequest;
import com.lynden.gmapsfx.service.geocoding.GeocoderRequest;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import com.lynden.gmapsfx.shapes.Rectangle;
import com.lynden.gmapsfx.shapes.RectangleOptions;
import com.lynden.gmapsfx.zoom.MaxZoomResult;
import com.lynden.gmapsfx.zoom.MaxZoomService;
import com.lynden.gmapsfx.zoom.MaxZoomServiceCallback;
import java.util.List;
import java.util.Locale;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
//end gmapfx imports
 

/**
 *
 * @author khmai
 */
public class MapTest2 extends Application implements MapComponentInitializedListener, 
        ElevationServiceCallback, GeocodingServiceCallback, DirectionsServiceCallback{
    
     protected GoogleMapView mapComponent;
    protected GoogleMap map;
    protected DirectionsPane directions;

    private Button btnZoomIn;
    private Button btnZoomOut;
    private Label lblZoom;
    private Label lblCenter;
    private Label lblClick;
    private ComboBox<MapTypeIdEnum> mapTypeCombo;
	
	private MarkerOptions markerOptions2;
	private Marker myMarker2;
	private Button btnHideMarker;
	private Button btnDeleteMarker;
        private Button btnAddMarker;
        
        
        

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Java version: " + System.getProperty("java.home"));
        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), null);
        mapComponent.addMapInializedListener(this);
                
        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        btnZoomIn = new Button("Zoom In");
        btnZoomIn.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() + 1);
        });
        btnZoomIn.setDisable(true);

        btnZoomOut = new Button("Zoom Out");
        btnZoomOut.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() - 1);
        });
        btnZoomOut.setDisable(true);

        lblZoom = new Label();
        lblCenter = new Label();
        lblClick = new Label();
        
        mapTypeCombo = new ComboBox<>();
        mapTypeCombo.setOnAction( e -> {
           map.setMapType(mapTypeCombo.getSelectionModel().getSelectedItem() );
        });
        mapTypeCombo.setDisable(true);
        
        Button btnType = new Button("Map type");
        btnType.setOnAction(e -> {
            map.setMapType(MapTypeIdEnum.HYBRID);
        });
		
		btnHideMarker = new Button("Hide Marker");
		btnHideMarker.setOnAction(e -> {hideMarker();});
		
		btnDeleteMarker = new Button("Delete Marker");
		btnDeleteMarker.setOnAction(e -> {deleteMarker();});
                btnAddMarker = new Button("Add new Marker");
                btnAddMarker.setOnAction(e -> {addMarker();});
		
        tb.getItems().addAll(btnZoomIn, btnZoomOut, mapTypeCombo,
                new Label("Zoom: "), lblZoom,
                new Label("Center: "), lblCenter,
                new Label("Click: "), lblClick,
				btnHideMarker, btnDeleteMarker, btnAddMarker);

        bp.setTop(tb);
        
        bp.setCenter(mapComponent);

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }
    
    
    DirectionsRenderer renderer;
    
    
    
    @Override
    public void mapInitialized() {
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong center = new LatLong(35.0055847,9.7104058);
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
            checkCenter(center);
        });
        
        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN)
                .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
        
        //[{\"featureType\":\"landscape\",\"stylers\":[{\"saturation\":-100},{\"lightness\":65},{\"visibility\":\"on\"}]},{\"featureType\":\"poi\",\"stylers\":[{\"saturation\":-100},{\"lightness\":51},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.highway\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]
        map = mapComponent.createMap(options,false);
        directions = mapComponent.getDirec();
        
        map.setHeading(123.2);
        
        
         map.fitBounds(new LatLongBounds(new LatLong(30, 120), center));
//        System.out.println("Bounds : " + map.getBounds());

        lblCenter.setText(map.getCenter().toString());
        map.centerProperty().addListener((ObservableValue<? extends LatLong> obs, LatLong o, LatLong n) -> {
            lblCenter.setText(n.toString());
        });

        lblZoom.setText(Integer.toString(map.getZoom()));
        map.zoomProperty().addListener((ObservableValue<? extends Number> obs, Number o, Number n) -> {
            lblZoom.setText(n.toString());
        });
        
        
          map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
          
        });

        btnZoomIn.setDisable(false);
        btnZoomOut.setDisable(false);
        mapTypeCombo.setDisable(false);
        
        mapTypeCombo.getItems().addAll( MapTypeIdEnum.ALL );
        
        GeocodingService gs = new GeocodingService();
        
       
        
        
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
          
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
           

            
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
                    
           // GetlatlongFromMouseCLick(ll,ll2);
          
        });
        
       
         DirectionsService ds = new DirectionsService();
        renderer = new DirectionsRenderer(true, map, directions);
        
        
                    DirectionsWaypoint[] dw;
int i = 0;
i++;
               dw = new DirectionsWaypoint[2];
        dw[0] = new DirectionsWaypoint("Ras Jebel");
       dw[1] = new DirectionsWaypoint("Géant Tunis City, Cebalat");
       //dw[i] = new DirectionsWaypoint(ll);
        
       
        DirectionsRequest dr = new DirectionsRequest("Géant Tunis City, Cebalat", "Megrine Ryad", TravelModes.DRIVING,dw);
        ds.getRoute(dr, this, renderer);
        
        LatLong[] location = new LatLong[1];
        location[0] = new LatLong(-19.744056, -43.958699);
        LocationElevationRequest loc = new LocationElevationRequest(location);
        ElevationService es = new ElevationService();
        es.getElevationForLocations(loc, this);
    }
    
    
     /*public void GetlatlongFromMouseCLick(LatLong ll, LatLong ll2){
             DirectionsService ds = new DirectionsService();
        renderer = new DirectionsRenderer(true, map, directions);
        
        
                    DirectionsWaypoint[] dw;
int i = 0;
i++;
               dw = new DirectionsWaypoint[0];
        dw[0] = new DirectionsWaypoint("Ras Jebel");
       //dw[1] = new DirectionsWaypoint("Géant Tunis City, Cebalat");
       //dw[i] = new DirectionsWaypoint(ll);
        
       
        DirectionsRequest dr = new DirectionsRequest("Géant Tunis City, Cebalat", "Megrine Ryad", TravelModes.DRIVING,dw);
        ds.getRoute(dr, this, renderer);
        }*/
    
    private void hideMarker() {
//		System.out.println("deleteMarker");
		
		boolean visible = myMarker2.getVisible();
		
		//System.out.println("Marker was visible? " + visible);
		
		myMarker2.setVisible(! visible);

//				markerOptions2.visible(Boolean.FALSE);
//				myMarker2.setOptions(markerOptions2);
//		System.out.println("deleteMarker - made invisible?");
	}
	
	private void deleteMarker() {
		//System.out.println("Marker was removed?");
		map.removeMarker(myMarker2);
	}
        
        private void addMarker(){
       /*     markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(35.0055847,9.7104058);
        markerOptions2.position(markerLatLong2)
                .title("My new Marker")
                .visible(true);

        myMarker2 = new Marker(markerOptions2);
            map.addMarker(myMarker2);*/
        }
	
    private void checkCenter(LatLong center) {
//        System.out.println("Testing fromLatLngToPoint using: " + center);
//        Point2D p = map.fromLatLngToPoint(center);
//        System.out.println("Testing fromLatLngToPoint result: " + p);
//        System.out.println("Testing fromLatLngToPoint expected: " + mapComponent.getWidth()/2 + ", " + mapComponent.getHeight()/2);
    }

    @Override
    public void elevationsReceived(ElevationResult[] ers, ElevationStatus es) {
 if(es.equals(ElevationStatus.OK)){
            for(ElevationResult e : ers){
                System.out.println(" Elevation on "+ e.getLocation().toString() + " is " + e.getElevation());
            }
        }
    }

    @Override
    public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
 if(gs.equals(GeocoderStatus.OK)){
            for(GeocodingResult e : grs){
                System.out.println(e.getVariableName());
                System.out.println("GEOCODE: " + e.getFormattedAddress() + "\n" + e.toString());
            }
            
        }
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
if(ds.equals(DirectionStatus.OK)){
            mapComponent.getMap().showDirectionsPane();
            System.out.println("OK");
            
            DirectionsResult e = dr;
            GeocodingService gs = new GeocodingService();
            
            System.out.println("SIZE ROUTES: " + e.getRoutes().size() + "\n" + "ORIGIN: " + e.getRoutes().get(0).getLegs().get(0).getStartLocation());
            //gs.reverseGeocode(e.getRoutes().get(0).getLegs().get(0).getStartLocation().getLatitude(), e.getRoutes().get(0).getLegs().get(0).getStartLocation().getLongitude(), this);
            System.out.println("LEGS SIZE: " + e.getRoutes().get(0).getLegs().size());
            System.out.println("WAYPOINTS " +e.getGeocodedWaypoints().size());
            /*double d = 0;
            for(DirectionsLeg g : e.getRoutes().get(0).getLegs()){
                d += g.getDistance().getValue();
                System.out.println("DISTANCE " + g.getDistance().getValue());
            }*/
            try{
                System.out.println("Distancia total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
            } catch(Exception ex){
                System.out.println("ERRO: " + ex.getMessage());
            }
            System.out.println("LEG(0)");
            System.out.println(e.getRoutes().get(0).getLegs().get(0).getSteps().size());
            /*for(DirectionsSteps ds : e.getRoutes().get(0).getLegs().get(0).getSteps()){
                System.out.println(ds.getStartLocation().toString() + " x " + ds.getEndLocation().toString());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(ds.getStartLocation())
                        .title(ds.getInstructions())
                        .animation(Animation.DROP)
                        .visible(true);
                Marker myMarker = new Marker(markerOptions);
                map.addMarker(myMarker);
            }
                    */
            System.out.println(renderer.toString());
        }
    }
    
    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        launch(args);
    }

}
    
    
    

