package edu.esprit.pi.gui;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.esprit.pi.gui;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
///**
// * FXML Controller class
// *
// * @author Sarra
// */
//public class ChatViewController extends Application implements Initializable {
//
//    @FXML
//    private TextArea TextAreaMessages;
//    @FXML
//    private Button send;
//    @FXML
//    private TextField message;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//
//    @FXML
//    private void send(ActionEvent event) {
//        String msg = message.getText();
//        if (!message.getText().isEmpty()) {
//            Listener.send(msg);
//            message.clear();
//        }
//    }
//    
//    @FXML
//    public void closeApplication() {
//        Platform.exit();
//        System.exit(0);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
