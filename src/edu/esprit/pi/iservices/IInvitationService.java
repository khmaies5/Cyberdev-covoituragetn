/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Invitation;
import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author Sarra
 */

 public interface IInvitationService extends IService<Invitation,Integer>{

   boolean accepter();
  
     boolean addInvitation(Invitation invitation);
       List<Invitation>  getInvitationbyGroupe(int IdGroupe);
   List<Invitation>  getInvitationbyUser(int idUser);
   boolean envoyerInvitation(List<User> userClicked,int idGroupe,int idcreator);
 boolean deletebyGroupe(Integer idGroupe);
}
   

