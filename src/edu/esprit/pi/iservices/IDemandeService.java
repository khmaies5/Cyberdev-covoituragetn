/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author amrouche
 */
public interface IDemandeService extends IService<Demande, Integer> {
    
    List<Demande> afficher(User user);
    Demande getDemandeByUser(Demande demande , User user);
    
    void acceptReservation(Demande demande,int id );
    void declineReservation(Demande demande,int id );
    
    
}
