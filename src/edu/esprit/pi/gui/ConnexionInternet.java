/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sarra
 */
public abstract class ConnexionInternet {

    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> OnReceiveCalBack;
    
    public ConnexionInternet(Consumer<Serializable> OnReceiveCalBack) {
        this.OnReceiveCalBack = OnReceiveCalBack;
        connThread.setDaemon(true);
    }
    
    public void startConnection() throws Exception {
        connThread.start();
    }

    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
        
    }

    public void CloseConnection() throws Exception {
        connThread.socket.close();
    }

    protected abstract boolean isServer();

    protected abstract String getIP();

    protected abstract int getPort();

    private class ConnectionThread extends Thread {
        
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                    Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                this.socket = socket;
                socket.setTcpNoDelay(true);
                this.out = out;
                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    OnReceiveCalBack.accept(data);
                }
                
            } catch (Exception ex) {
                OnReceiveCalBack.accept("connection closed");
            }
        }
    }
}
