/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.models;

/**
 *
 * @author amrouche
 */
public class ContoleSaisie {
     public static boolean isString(String text) {

        if (text.matches("^[a-zA-Z]+$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNbPlace(String text) {

        try {
            Integer.parseInt(text);
            if (Integer.parseInt(text) >= 1 && Integer.parseInt(text) <= 1000) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    
}
