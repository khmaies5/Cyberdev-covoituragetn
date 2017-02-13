/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IRateService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Rate;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Momo
 */
public class RateService implements IRateService {
private Connection connection;
    private PreparedStatement ps;

    public RateService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void Update(Rate t) {
String req = "update `rate` set `nbrRate`=?  where idRate=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(2, t.getId());
            ps.setInt(1, t.getNbrVote());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }     }

    @Override
    public void add(Rate t) {
String req = "insert into rate (nbrRate,idUser,idAnnonce) values (?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, t.getNbrVote());
            ps.setInt(2, t.getIdUser().getId());
            ps.setInt(3, t.getIdAnnonce().getIdAnnonce());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idRate) {
String req = "delete from rate where idRate =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idRate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }          }

    @Override
    public Rate findById(Integer idRate) {
String req = "select * from rate where idRate = ?";
        Rate rate = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idRate);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                rate= new Rate( resultSet.getInt(1),resultSet.getInt(2), new User(resultSet.getInt(4)),new Annonce(resultSet.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;    
    }

    @Override
    public List<Rate> getAll() {
String req = "select * from rate";
        List<Rate> listRate = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Rate rate= new Rate( resultSet.getInt(1),resultSet.getInt(2), new User(resultSet.getInt(4)),new Annonce(resultSet.getInt(3)));
                listRate.add(rate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRate;  
    }
    
}
