/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;

/**
 *
 * @author amrouche
 */
public interface IReservationService extends IService<Reservation, Integer> {
    void acceptReservation(Reservation reservation,int id );
    void declineReservation(Reservation reservation,int id );
    
}
