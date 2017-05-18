package edu.esprit.pi.gui;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.esprit.pi.gui;
//
///**
// *
// * @author Sarra
// */
//
//
//
//import java.io.*;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//public class Listener implements Runnable{
//
//    private static final String HASCONNECTED = "has connected";
//
//    private static String picture;
//    private Socket socket;
//    public String hostname;
//    public int port;
//    public static String username;
//    public ChatViewController controller;
//    private static ObjectOutputStream oos;
//    private InputStream is;
//    private ObjectInputStream input;
//    private OutputStream outputStream;
//    Logger logger = Logger.getLogger(Listener.class.getName());
//
//    public Listener(String hostname, int port, String username, String picture, ChatViewController controller) {
//        this.hostname = hostname;
//        this.port = port;
//        Listener.username = username;
//        Listener.picture = picture;
//        this.controller = controller;
//    }
//
//    public void run() {
//        try {
//            //   try {
//            socket = new Socket(hostname, port);
//            //  LoginController.getInstance().showScene();
//            outputStream = socket.getOutputStream();
//            oos = new ObjectOutputStream(outputStream);
//            is = socket.getInputStream();
//            input = new ObjectInputStream(is);
//            //  } catch (IOException e) {
//            //     LoginController.getInstance().showErrorDialog("Could not connect to server");
//            //    logger.error("Could not Connect");
//            //   }
//            logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
//            
//            try {
//                connect();
//                logger.info("Sockets in and out ready!");
//                while (socket.isConnected()) {
//                    String message = null;
//                    message = (String) input.readObject();
//                    
//                    if (message != null) {
//                        logger.debug("Message recieved:" + message.getMsg() + " MessageType:" + message.getType() + "Name:" + message.getName());
//                        switch (message.getType()) {
//                            case USER:
//                                controller.addToChat(message);
//                                break;
//                            
//                            case SERVER:
//                                controller.addAsServer(message);
//                                break;
//                            case CONNECTED:
//                                controller.setUserList(message);
//                                break;
//                            case DISCONNECTED:
//                                controller.setUserList(message);
//                                break;
//                            case STATUS:
//                                controller.setUserList(message);
//                                break;
//                        }
//                    }
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//                controller.logoutScene();
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /* This method is used for sending a normal Message
//     * @param msg - The message which the user generates
//     */
//    public static void send(String msg) throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(MessageType.USER);
//        createMessage.setStatus(Status.AWAY);
//        createMessage.setMsg(msg);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//        oos.flush();
//    }
//
//    /* This method is used for sending a voice Message
// * @param msg - The message which the user generates
// */
//    public static void sendVoiceMessage(byte[] audio) throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(MessageType.VOICE);
//        createMessage.setStatus(Status.AWAY);
//        createMessage.setVoiceMsg(audio);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//        oos.flush();
//    }
//
//    /* This method is used for sending a normal Message
// * @param msg - The message which the user generates
// */
//    public static void sendStatusUpdate(Status status) throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(MessageType.STATUS);
//        createMessage.setStatus(status);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//        oos.flush();
//    }
//
//    /* This method is used to send a connecting message */
//    public static void connect() throws IOException {
//        Message createMessage = new Message();
//        createMessage.setName(username);
//        createMessage.setType(CONNECTED);
//        createMessage.setMsg(HASCONNECTED);
//        createMessage.setPicture(picture);
//        oos.writeObject(createMessage);
//    }
//
//}
