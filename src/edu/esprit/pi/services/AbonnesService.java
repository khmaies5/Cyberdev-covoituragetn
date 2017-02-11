/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sarra
 */
public class AbonnesService implements IService<Abonnes, Integer> {

    private Connection connection;
    private PreparedStatement ps;

    public AbonnesService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Abonnes A) {
        String req = "insert into abonnees (id_groupe,id_user,role_user) values (?,?,?)";

        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, A.getGroupe().getId());
            ps.setInt(2, A.getUser().getId());
            ps.setString(3, A.getRole_user());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Abonnes> getAll() {
        String req = "select * from abonnees";

        List<Abonnes> abonnees = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Abonnes abonnes = new Abonnes(resultSet.getInt(1), resultSet.getDate(2), resultSet.getString(3), new User(resultSet.getInt(4)),  new Groupe(resultSet.getInt(5)));

                abonnees.add(abonnes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return abonnees;
    }

    @Override
    public void delete(Integer idAbonnee) {
        String req = "delete from abonnees where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idAbonnee);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Abonnes findById(Integer idAbonnee) {
        String req = "select * from abonnees where id = ?";

        Abonnes abonne = null;

        try {
            ps = connection.prepareStatement(req);

            ps.setInt(1, idAbonnee);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                abonne = new Abonnes(resultSet.getInt(1), resultSet.getDate(2), resultSet.getString(3), new User(resultSet.getInt(4)), new Groupe(resultSet.getInt(5)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return abonne;
    }

  /* @Override
    public void update(Abonnes A) {
    }*/

}
