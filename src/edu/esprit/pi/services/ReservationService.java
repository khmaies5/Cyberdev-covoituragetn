/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IReservationService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.esprit.pi.models.Demande;

/**
 *
 * @author amrouche
 */
public class ReservationService implements IReservationService {

    private Connection connection;
    private PreparedStatement ps;

    public ReservationService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Reservation t) {
        String req = "insert into reservation (date_reservation,etat_reservation,montant,type_payement,id_user,nbplace,id_annonce) values (?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, t.getDate_reservation());

            ps.setString(2, t.getEtat_reservation());
            ps.setDouble(3, t.getMontant());
            ps.setString(4, t.getType_payement());
            ps.setInt(5, t.getCreator().getId());
            ps.setInt(6, t.getNbr_place());
            ps.setInt(7, t.getAnnonce().getIdAnnonce());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer r) {
        String req = "delete from reservation where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation findById(Integer r) {
        String req = "select * from reservation where id = ?";
        Reservation Reservation = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Reservation = new Reservation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), new User(resultSet.getInt(6)), resultSet.getInt(6), new Annonce(resultSet.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Reservation;
    }

    @Override
    public List<Reservation> getAll() {
        String req = "select * from reservation";
        List<Reservation> reservations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Reservation product = new Reservation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), new UserService().findById(resultSet.getInt(6)), resultSet.getInt(7), new AnonncesService().findById(resultSet.getInt(8)));
                //Reservation(resultSet.getInt(1), resultSet.getString(2), new UserService().findById(resultSet.getInt(3)));
                reservations.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void acceptReservation(Reservation reservation, int id) {
        try {
            String req1 = "UPDATE  reservation SET etat_reservation=?   WHERE id =?";

            ps = connection.prepareStatement(req1);
            reservation = new Reservation("accepte");
            ps.setString(1, reservation.getEtat_reservation());//1 est le premier parametre ? 
            ps.setInt(2, id);
            ps.executeUpdate();//insert update delete 
            // executeQuery pour insert

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declineReservation(Reservation reservation, int id) {
        try {
            String req1 = "UPDATE  reservation SET etat_reservation=?   WHERE id =?";

            ps = connection.prepareStatement(req1);
            reservation = new Reservation("refuse");
            ps.setString(1, reservation.getEtat_reservation());//1 est le premier parametre ? 
            ps.setInt(2, id);
            ps.executeUpdate();//insert update delete 
            // executeQuery pour insert

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reservation findreservationbyuser(int r)
    {
         String req = "select * from reservation where id_user = ?";
     Reservation reservation = null ;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
              reservation   = new Reservation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), new User(resultSet.getInt(6)), resultSet.getInt(6), new Annonce(resultSet.getInt(7)));
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation ;
        
    }
      public Reservation findreservationbyannonce(int r)
    {
         String req = "select * from reservation where id_annonce = ?";
     Reservation reservation = null ;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
              reservation   = new Reservation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), new User(resultSet.getInt(6)), resultSet.getInt(6), new Annonce(resultSet.getInt(7)));
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation ;
        
    }
      public Demande findreservationbydemande(int r)
    {
         String req = "select * from demande where id_demande = ?";
      Demande demande =null ;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
 demande = new Demande(resultSet.getInt(9));           
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demande ;
        
    }
      
        public List<Reservation> findallreservationsbyid(int id_user) {
        String req = "select * from reservation where id_user= ?";
        List<Reservation> reservations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,id_user);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Reservation product = new Reservation(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getString(7), new UserService().findById(resultSet.getInt(2)), resultSet.getInt(8), new AnonncesService().findById(resultSet.getInt(3)));
                //Reservation(resultSet.getInt(1), resultSet.getString(2), new UserService().findById(resultSet.getInt(3)));
                reservations.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }
        
}
