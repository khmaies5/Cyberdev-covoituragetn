/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 * @author Sarra
 */
public class Server extends ConnexionInternet{
private int port;
    public Server(int port ,Consumer<Serializable> onReceiveCallBack) {
        super(onReceiveCallBack);
        this.port=port;
    }

    public int getPort() {
        return port;
    }

    @Override
    protected boolean isServer() {
return true;
    }

    @Override
    protected String getIP() {
return null;
    }


    
}
