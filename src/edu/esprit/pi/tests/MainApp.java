/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.tests;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IDemandeService;
import edu.esprit.pi.iservices.IReservationService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.AnonncesService;
import edu.esprit.pi.services.DemandeService;
import edu.esprit.pi.services.ReservationService;
import java.util.Date;
import edu.esprit.pi.technique.DataSource;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author amrouche
 */
public class MainApp {
     public static void main(String[] args) {
        /* User user = new User("masmoudi", "mohamed");
         IUserService userService = new UserService();
         // userService.add(user);
         //userService.delete(3);
         userService.getAll().forEach(System.out::println);*/
String date1 = "24/06/2006";

        //Product product = new Product("TV", new User(1));
       // IService service = new ProductService();
         // service.add(product);
          
        //service.getAll().forEach(System.out::println);
      //  Date date =new Date(22, 01, 1995);
      Date date =new Date();
       //  IDemandeService service =new DemandeService();
       
        Reservation reservation =new Reservation(date1,"reservésdd",7,"cartecredissts",new User(1),8, new Annonce(1));
      
        IReservationService service = new ReservationService();
         IAnnonceService serv1=new AnonncesService();
     // Reservation res = new Reservation(date, date1, 0, date1, creator)
        Demande demande =new Demande("", new Annonce(1),new User(1));
       //  System.out.println(service.getAll());
       List<Annonce> lst =new ArrayList();
      // lst= serv1.rechercherannonceselontrajet("tunis","bizerte",date1);
      //  System.out.println(lst);      
//service.add(reservation);
     //service.delete(2);
    /* List<Reservation> res =new ArrayList();
     ReservationService e =new ReservationService();
     res=e.findallreservationsbyid(1);
         for (Reservation re : res) {
             System.out.println(re);
         }*/
     AnonncesService arbi =new AnonncesService();
     lst=arbi.rechercheavancee("tunis", "bizerte", date1, 500, "voiture", "adzadaz");
         System.out.println(lst);
     }
    
}
