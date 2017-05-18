/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.Annonce;
import java.util.List;

/**
 *
 * @author Nacef Fethi
 */
public interface IAlerteService {
     void  update (Alerte alerte);
    List<Alerte> getAll(Integer idUser);
    List<Alerte> getAll();
      List<Alerte> rechercher(String recherche,Integer idUser);
   List <Annonce>comparerPublicationAlerte(List<Alerte> alerte);
}
