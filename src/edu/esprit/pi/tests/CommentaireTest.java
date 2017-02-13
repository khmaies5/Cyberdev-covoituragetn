/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.tests;

import edu.esprit.pi.iservices.ICommentaireService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Commentaire;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.CommentaireService;
import sun.print.resources.serviceui;

/**
 *
 * @author Momo
 */
public class CommentaireTest {
    public static void main(String[] args){
    Commentaire comm = new Commentaire("skcjsmk",new Annonce(1), new User(1));
    Commentaire coModifier = new Commentaire(2,"mondher",new Annonce(1), new User(1));
         
         IService service = new CommentaireService();
         ICommentaireService commentaireService=new CommentaireService();
         
         
         commentaireService.Update(coModifier);
         //service.getAll().forEach(System.out::println);
      // service.add(comm);
       //System.out.println("smlkfdgslmkdfjùsdkjfùmlsdkfmlùskdfùmlsdkfùmlsdkfmlsdkfmlsdksmflkgùmlsfkdml");
        //System.out.println(service.findById(2));
        
       //service.delete(1);
         
     }
    
}
