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
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author khmai
 */
public class AnnonceTest {
    
    
    
    
     public static void main(String[] args){
         String s;
  Format formatter;

         Date date = new Date();
           formatter = new SimpleDateFormat("MM/dd/yy");
  s = formatter.format(date);
  
        // Annonce ann = new Annonce(2, "test", s, "test", "test", 4, 200, "test", new User(4));
         
         IService service = new AnonncesService();
       // service.add(ann);
       // System.out.println(service.getAll());
        
       // System.out.println(service.findById(2));
         service.delete(2);
         
         
     }
}
