/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.services;
import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author khmai
 */
public class AnonncesService implements IAnnonceService{
    private Connection connection;
    private PreparedStatement ps;

    public AnonncesService() {
         connection = DataSource.getInstance().getConnection();
    }

    

    @Override
    public void add(Annonce annonces) {
 String req = "insert into annonce (id_annonce,trip_date,trip_time,lieu_depart,lieu_arrive,type,nbr_personne,prix,critere,id_user) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, annonces.getIdAnnonce());
           
            ps.setDate(2, (java.sql.Date) annonces.getTripDate());
            ps.setDate(3, (java.sql.Date) annonces.getTripTime());
            ps.setString(4, annonces.getLieuDepart());
            ps.setString(5, annonces.getLieuArriver());
            ps.setString(6, annonces.getTypeAnnonce());
            ps.setInt(7, annonces.getNbrPersonne());
            ps.setFloat(8, annonces.getPrix());
            ps.setString(9, annonces.getCritere());
            

            ps.setInt(10, annonces.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idAnnonce) {
        String req = "delete from annonce where id_annonce =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idAnnonce);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Annonce findById(Integer idAnnonce) {
        String req = "select * from annonce where id_annonce = ?";
        Annonce annonces = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idAnnonce);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                annonces = new Annonce(resultSet.getInt(1), resultSet.getDate(2),resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getString(9), new User(resultSet.getInt(10))); // new User(resultSet.getInt(3))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;
    }

    @Override
    public List<Annonce> getAll() {
          String req = "select * from annonce";
        List<Annonce> annonces = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(2),resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getString(9), new User(resultSet.getInt(10)));
                annonces.add(annonce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;
    }

    @Override
    public List<Annonce> rechercherannonceselontrajet(String depart, String arrivee, String date) {
 String req = "select * from annonce where lieu_depart LIKE ? and lieu_arrive LIKE ? and date LIKE ? ";
        List<Annonce> annonces = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, depart);
            ps.setString(2, arrivee);
            ps.setString(3,date);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(2),resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getString(9), new User(resultSet.getInt(10)));
                annonces.add(annonce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;     }

   

  
 

    
}
