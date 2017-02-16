package edu.esprit.pi.gui;


import com.jfoenix.controls.JFXButton;
import edu.esprit.pi.iservices.ControlledScreen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class SidePanelContentController implements Initializable,ControlledScreen {

       ScreensController screen;

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void changeColor(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            
            case "Color 2":AjouterAnnoncesController.rootP.setStyle("-fx-background-color:#0000FF");
                break;
            case "Color 3":AjouterAnnoncesController.rootP.setStyle("-fx-background-color:#FF0000");
                break;
        }
    }
    
       @FXML
    void retourOnAction(ActionEvent event) {
      screen.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);

        screen.setScreen(ScreensFramework.screen1ID);
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
    screen=screenPage;
    }
    
}
