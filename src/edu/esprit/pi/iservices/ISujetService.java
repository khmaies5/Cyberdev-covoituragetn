/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import java.util.List;

/**
 *
 * @author Sarra
 */
public interface ISujetService extends IService<Sujet, Integer>{
    
        void update(Sujet s);
List<Sujet>getbyIdGroupe(Integer idGroupe);
 int LastInseredId();
      
boolean addSujet(Sujet sujet);
   boolean updatePath(String path ,int id );
     List<Sujet> rechercherMessujets(String recherche,Groupe gr); 
}
