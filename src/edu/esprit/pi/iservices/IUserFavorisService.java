/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.UserFavoris;
import java.util.List;
import edu.esprit.pi.models.User;

/**
 *
 * @author Nacef Fethi
 */
public interface IUserFavorisService {
     void add(UserFavoris userFavoris);
     void delete(Integer idUserFavoris);
     
      List<UserFavoris> getAll();
   
   List<UserFavoris>findByIduserconnecter(Integer i);
    List<User> findByIduserconnecte(Integer idUserConnect);
}
