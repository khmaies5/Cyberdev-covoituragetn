/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Groupe;
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

/**
 *
 * @author Sarra
 */
public class GroupeService implements IService<Groupe, Integer> {

    private Connection connection;
    private PreparedStatement ps;
    private PreparedStatement ps2;

    public GroupeService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Groupe groupe) {
        String req = "insert into groupe (nom,description) values (?,?)";

        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, groupe.getNom());
            ps.setString(2, groupe.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idGroupe) {
        String req = "delete from groupe where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idGroupe);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 /*@Override
    public void update(Groupe groupe) {
     try {
            String req="UPDATE `groupe` SET `nom`=?,"
                    + "`description`=? WHERE id=?";
      ps = connection.prepareStatement(req);
      ps.setString(1, groupe.getNom());
            ps.setString(2, groupe.getDescription());
      ps.setInt(3, groupe.getId());
            ps.executeUpdate();
           
        } catch (SQLException ex) {
  Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        } }
    */
    @Override
    public Groupe findById(Integer idGroupe) {
         String req = "select * from groupe where id = ?";
          String req2 = "select * from sujet where id_groupe= ?";
        Groupe groupe = null;
        List<Sujet> sujets = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
               ps2 = connection.prepareStatement(req2);
            ps.setInt(1, idGroupe);
             ps2.setInt(1, idGroupe);
            ResultSet resultSet = ps.executeQuery();
            ResultSet resultSet2 = ps2.executeQuery();
            if (resultSet.next()) {
                groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4));
            }
            while (resultSet2.next()) {
                Sujet sujet = new Sujet(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3));
                sujets.add(sujet);
             
                groupe.setSujets(sujets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return groupe;
    }

    @Override
    public List<Groupe> getAll() {
        String req = "select * from groupe";
        String req2 = "select * from groupe inner join sujet on groupe.id = ? and sujet.id_groupe=?";
        List<Groupe> groupes = new ArrayList<>();
        List<Sujet> sujets = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                
                Groupe groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));
                ps2 = connection.prepareStatement(req2);
                ps2.setInt(1, groupe.getId());
                ps2.setInt(2, groupe.getId());
                ResultSet resultSet2 = ps2.executeQuery();
                while (resultSet2.next()) {

                    Sujet sujet = new Sujet(resultSet2.getInt(5), resultSet2.getString(6), resultSet2.getString(7));
                    sujets.add(sujet);
                    groupe.setSujets(sujets);
                }
                groupes.add(groupe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;
    }

}
