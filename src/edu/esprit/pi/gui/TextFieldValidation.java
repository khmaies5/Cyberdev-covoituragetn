/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.gui;

import com.jfoenix.controls.JFXTextField;

/**
 *
 * @author Nacef Fethi
 */
public class TextFieldValidation {
   public static boolean isTextFieldEmpty(JFXTextField tf)
   {
   boolean b=false;
   if (tf.getText().length()!=0 || !tf.getText().isEmpty())
   b=true;
   return b;
   
   }
}
