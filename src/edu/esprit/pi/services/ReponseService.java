/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sarra
 */
public class ReponseService  implements IService<Reponse, Integer>{
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
            ps.setString(2, R.getReponse_sujet());
            ps.setInt(3, R.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
 /*@Override
    public void update(Reponse R) {
     try {
            String req="UPDATE `reponse` SET `reponse_sujet`=?
                  WHERE id=?";
      ps = connection.prepareStatement(req);
      ps.setString(1, R.getReponse_sujet());
            ps.setInt(2, R.getId());
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
  Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        } }*/
    
    @Override
    public void delete(Integer idReponse) {
        String req = "delete from reponse where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idReponse);
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
                reponse = new Reponse(resultSet.getInt(1), resultSet.getDate(2),new Sujet(resultSet.getInt(3)),resultSet.getString(4),new Abonnes(resultSet.getInt(5)));
                
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
                
                Reponse  reponse = new Reponse(resultSet.getInt(1), resultSet.getDate(2),new Sujet(resultSet.getInt(3)),resultSet.getString(4),new Abonnes(resultSet.getInt(5)));

                reponses.add(reponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return reponses;
    }
}
