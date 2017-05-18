/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author Nacef Fethi
 */
public interface IEmailService {
  void envoyerMail(User u, List <Annonce> an,Alerte a);  
}
