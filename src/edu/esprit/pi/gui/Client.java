/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import edu.esprit.pi.gui.ConnexionInternet;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 * @author Sarra
 */
public class Client  extends ConnexionInternet {
private int port;
private String ip;
    public Client(String ip,int port,Consumer<Serializable> onReceiveCallBack) {
        super(onReceiveCallBack);
        this.port=port;
        this.ip=ip;
    }

    @Override
    protected boolean isServer() {
return false;    }

    @Override
    protected String getIP() {
return ip;    }

    @Override
    protected int getPort() {
return port;    }
    
}
