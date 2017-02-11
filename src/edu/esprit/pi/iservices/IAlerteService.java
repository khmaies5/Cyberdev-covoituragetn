/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Alerte;
import java.util.List;

/**
 *
 * @author Nacef Fethi
 */
public interface IAlerteService {
       void add(Alerte alerte);
     void delete(Integer idAlerte);
     
      List<Alerte> getAll();
      Alerte findById(Integer idAlerte);
    void  update (Alerte alerte);
    List<Alerte> getAll(Integer idUser);
}
