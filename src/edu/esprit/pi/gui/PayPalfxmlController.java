package edu.esprit.pi.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class PayPalfxmlController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private WebView webview;
        private static final String[] locs = {
    "https://www.youtube.com/results?search_query=jeux+video",
                "https://www.paypal.com/fr/home"
    };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int lyricIndex=1;
        // TODO
        
        webview.getEngine().load(locs[lyricIndex]);
    }    
    
}
