/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IReponseService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sarra
 */
public class ReponseService  implements IReponseService{
     private Connection connection;
    private PreparedStatement ps;
      public ReponseService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Reponse R) {
   String req = "insert into reponse (id_sujet,reponse_sujet,id_userAb) values (?,?,?)";

        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, R.getSujet().getId());
            ps.setString(2, R.getReponseSujet());
            ps.setInt(3, R.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
      @Override
    public void update(Reponse R) {
     try {
            String req="UPDATE `reponse` SET `reponse_sujet`=?  WHERE id=?";
      ps = connection.prepareStatement(req);
      ps.setString(1, R.getReponseSujet());
            ps.setInt(2, R.getId());
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
  Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        } }
    
    @Override
    public void delete(Integer idReponse) {
        String req = "UPDATE `reponse` SET `etat_reponse`=? where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(2, idReponse);
              ps.setInt(1, 0);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reponse findById(Integer idReponse) {
            String req = "select * from groupe where id = ?";
        
        Reponse reponse = null;
      
        try {
            ps = connection.prepareStatement(req);
         
            ps.setInt(1, idReponse);
          
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
     
 
             reponse = new Reponse(resultSet.getInt(1), resultSet.getDate(3),resultSet.getString(4),new Sujet(resultSet.getInt(2)),new Abonnes(resultSet.getInt(6)),resultSet.getInt(5));
                
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return reponse;
    }

    @Override
    public List<Reponse> getAll() {
        String req = "select * from reponse";
        List<Reponse> reponses = new ArrayList<>();
       
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                
              Reponse  reponse = new Reponse(resultSet.getInt(1), resultSet.getDate(3),resultSet.getString(4),new Sujet(resultSet.getInt(2)),new Abonnes(resultSet.getInt(6)),resultSet.getInt(5));

                reponses.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reponses;
    }

    @Override
    public List<Reponse> GetReponseOfSujet(Integer idSujet) {
 String req = "select * from reponse where id_sujet=? and etat_reponse=?";
        List<Reponse> reponses = new ArrayList<>();
       
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idSujet);
            ps.setInt(2, 1);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                
              Reponse  reponse = new Reponse(resultSet.getInt(1), resultSet.getDate(3),resultSet.getString(4),new Sujet(resultSet.getInt(2)),new Abonnes(resultSet.getInt(6)),resultSet.getInt(5));

                reponses.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reponses;
    }

    @Override
    public boolean addReponse(Reponse reponse) {
 String req = "insert into reponse (id_sujet,reponse_sujet,id_userAb) values (?,?,?)";
int i=0;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, reponse.getSujet().getId());
            ps.setString(2, reponse.getReponseSujet());
            ps.setInt(3, reponse.getCreator().getId());
           i= ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }     
        
         if(i==1) return true; else return false;
}}
