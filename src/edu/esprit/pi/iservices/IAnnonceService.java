/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import java.util.Date;
import java.util.List;

/**
 *
 * @author amrouche
 */
public interface IAnnonceService extends IService<Annonce, Integer> {
    List<Annonce> rechercherannonceselontrajet(String depart,String arrivee,String date);
    
}
