/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IDemandeService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Demande;
import edu.esprit.pi.models.Reservation;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amrouche
 */
public class DemandeService implements IDemandeService {
private Connection connection;
    private PreparedStatement ps;

    public DemandeService() {
        connection = DataSource.getInstance().getConnection();
    }
   

   

    @Override
    public void add(Demande t) {
 String req = "insert into demande (id_annonce,id_user,etat_approbation,nbr_places,id_conducteur) values (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,t.getAnnonce().getIdAnnonce());
           ps.setInt(2, t.getUser().getId());
            ps.setString(3, t.getEtat());
            ps.setInt(4, t.getNbrPlaces());
            ps.setInt(5, t.getConducteur().getId());
            
           
            
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer r) {
 String req = "delete from demande where id_demande =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    @Override
    public Demande findById(Integer r) {
String req = "select * from demande where id_demande = ?";
        Demande demande = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                demande = new Demande(resultSet.getInt(1),resultSet.getString(5),new Annonce(resultSet.getInt(4)),new User(resultSet.getInt(2)),resultSet.getInt(6),new User(resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demande;    
    }

    @Override
    public List<Demande> getAll() {

String req = "select * from demande";
        List<Demande> demandes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
               Demande product = new Demande(resultSet.getInt(1), resultSet.getString(5),new AnonncesService().findById(resultSet.getInt(4)),new UserService().findById(resultSet.getInt(2)),resultSet.getInt(6),new UserService().findById(resultSet.getInt(3)) );
        //Reservation(resultSet.getInt(1), resultSet.getString(2), new UserService().findById(resultSet.getInt(3)));
                demandes.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    return demandes ;    }

    @Override
    public void acceptReservation(Demande demande, int id) {
try {
            String req1 ="UPDATE  demande SET etat_approbation=?   WHERE id_demande =?";
            
            ps = connection.prepareStatement(req1);
              demande =new Demande("accepte");
            ps.setString(1,"accepte");//1 est le premier parametre ? 
            ps.setInt(2,id);
            ps.executeUpdate() ;//insert update delete 
            // executeQuery pour insert

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declineReservation(Demande demande, int id) {
        try {
            String req1 ="UPDATE  demande SET etat_approbation=?   WHERE id_demande =?";
            
            ps = connection.prepareStatement(req1);
              demande =new Demande("refuse");
            ps.setString(1,"refuse");//1 est le premier parametre ? 
            ps.setInt(2,id);
            ps.executeUpdate() ;//insert update delete 
            // executeQuery pour insert

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @Override
    public Demande getDemandeByUser(Demande demande, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Demande> afficher(User user) {
String req = "select * from demande WHERE id_conducteur =?";
        List<Demande> demandes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, user.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
             //  Demande product = new Demande(resultSet.getInt(1), resultSet.getString(4),new AnonncesService().findById(resultSet.getInt(2)),new UserService().findById(resultSet.getInt(3)) );
        //Reservation(resultSet.getInt(1), resultSet.getString(2), new UserService().findById(resultSet.getInt(3)));
               Demande product = new Demande(resultSet.getInt(1), resultSet.getString(5),new AnonncesService().findById(resultSet.getInt(4)),new UserService().findById(resultSet.getInt(2)),resultSet.getInt(6),new UserService().findById(resultSet.getInt(3)) );
        
        demandes.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    return demandes ;     }
    
    
 public Demande finddemandebyuser(int r)
    {
         String req = "select * from demande where id_user = ?";
      Demande demande =null ;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
 demande = new Demande(resultSet.getInt(6));           
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demande ;
        
    }
    
    
}
