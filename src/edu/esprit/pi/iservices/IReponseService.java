/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Reponse;
import java.util.List;

/**
 *
 * @author Sarra
 */
public interface IReponseService extends IService<Reponse,Integer>{
       void update(Reponse R);
    List<Reponse> GetReponseOfSujet(Integer idSujet);
    boolean addReponse(Reponse reponse);
}