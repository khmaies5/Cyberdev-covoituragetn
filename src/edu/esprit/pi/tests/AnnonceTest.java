/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.tests;



import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.technique.DataSource;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 *
 * @author khmai
 */
public class AnnonceTest {
    
    
    
    
     public static void main(String[] args) throws ParseException{
      //  Calendar calendar = Calendar.getInstance();
      Date startDate = new Date();

        
        System.out.println(startDate.getTime());
  
         Annonce ann = new Annonce(5,new java.sql.Date(startDate.getTime()),null, "test", "test","test", 4, 200, "test", new User(4));
         
         IService service = new AnonncesService();
        service.add(ann);
       // System.out.println(service.getAll());
        
       // System.out.println(service.findById(2));
       //  service.delete(2);
         
         
     }
}
