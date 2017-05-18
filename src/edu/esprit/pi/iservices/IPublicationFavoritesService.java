/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import java.util.List;

/**
 *
 * @author Nacef Fethi
 */
public interface IPublicationFavoritesService  {
       void add(PublicationFavorite pubFav);
     void delete(Integer idPub);
     
      List<PublicationFavorite> getAll();
      PublicationFavorite findById(Integer iduser);
    List<PublicationFavorite> getAll(Integer idUser); 
         List<Annonce> findByIduserconnecte(Integer idUserConnect);
    
    
}
