/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.iservices.ISujetService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Reponse;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.pi.models.User;
import java.util.Date;

/**
 *
 * @author Sarra
 */
public class SujetService  implements ISujetService {
private Connection connection;
    private PreparedStatement ps;
 private PreparedStatement ps2;

    public SujetService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Sujet sujet) {
      String req = "insert into sujet (objet,topic,id_groupe,id_user) values (?,?,?,?)";

        System.out.println(sujet.getGroupe().getId());
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, sujet.getObjet());
            ps.setString(2, sujet.getTopic());
          
            ps.setInt(3, sujet.getGroupe().getId());
             ps.setInt(4,sujet.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@Override
    public void update(Sujet S) {
     try {
            String req="UPDATE sujet SET objet=? , topic=? WHERE id=?";
      ps = connection.prepareStatement(req);
      ps.setString(1, S.getObjet());
     ps.setString(2, S.getTopic());
            ps.setInt(3,S.getId());
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
  Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } }
    @Override
    public void delete(Integer idSujet) {
      String req = "delete from sujet where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idSujet);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sujet findById(Integer idSujet) {
         String req = "select * from sujet where id = ?";
       //   String req2 = "select * from reponse where id_sujet= ?";
     Sujet  sujet = null;
   //     List<Reponse> reponses = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
           
            ps.setInt(1, idSujet);
          
            ResultSet resultSet = ps.executeQuery();
        
            if (resultSet.next()) {
     sujet = new Sujet(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),new Groupe(resultSet.getInt(4)),new Abonnes(resultSet.getInt(5)),resultSet.getDate(6));
            }
     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sujet;
    }

    @Override
    public List<Sujet> getAll() {
       String req = "select * from sujet";
        String req2 = "select * from sujet inner join reponse on sujet.id = ? and reponse.id_sujet=?";
        List<Sujet> sujets = new ArrayList<>();
        List<Reponse> reponses = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                
     Sujet  sujet = new Sujet(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),new Groupe(resultSet.getInt(4)),new Abonnes(resultSet.getInt(5)),resultSet.getDate(6));
                ps2 = connection.prepareStatement(req2);
                ps2.setInt(1, sujet.getId());
                ps2.setInt(2, sujet.getId());
                ResultSet resultSet2 = ps2.executeQuery();
                while (resultSet2.next()) {

        Reponse reponse = new Reponse(resultSet2.getInt(1), resultSet2.getDate(2),new Sujet(resultSet2.getInt(3)), resultSet2.getString(4),new User(resultSet2.getInt(5)));
                    reponses.add(reponse);
                    sujet.setReponses(reponses);
                }
                sujets.add(sujet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sujets;
    }
 @Override
    public List<Sujet> getbyIdGroupe(Integer idGroupe) {
       String req = "select * from sujet where id_groupe=?";
      //  String req2 = "select * from sujet inner join reponse on sujet.id = ? and reponse.id_sujet=?";
        List<Sujet> sujets = new ArrayList<>();
      //  List<Reponse> reponses = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idGroupe);
             
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

     Sujet  sujet = new Sujet(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),new Groupe(resultSet.getInt(4)),new Abonnes(resultSet.getInt(5)),resultSet.getDate(6));
       
                sujets.add(sujet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sujets;
    }

   
    
    
}
