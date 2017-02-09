/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.tests;


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
String date1 = "23/06/2006";

        //Product product = new Product("TV", new User(1));
       // IService service = new ProductService();
         // service.add(product);
          
        //service.getAll().forEach(System.out::println);
      //  Date date =new Date(22, 01, 1995);
      Date date =new Date();
       //  IDemandeService service =new DemandeService();
       
        Reservation reservation =new Reservation(date1,"reservés",6,"cartecredits",new User(1),3, new Annonce(1));
       IReservationService service = new ReservationService();
         IAnnonceService serv1=new AnonncesService();
     // Reservation res = new Reservation(date, date1, 0, date1, creator)
        Demande demande =new Demande("", new Annonce(1),new User(1));
       //  System.out.println(service.getAll());
         serv1.rechercherannonceselontrajet("fnfgngfn","nfgngfnfgngf",date1);
      // service.add(reservation);
     //service.delete(2);
     
     }
    
}
