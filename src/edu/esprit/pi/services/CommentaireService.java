/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

//package edu.esprit.pi.services;
import edu.esprit.pi.iservices.ICommentaireService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Commentaire;
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
public class CommentaireService implements ICommentaireService {
private Connection connection;
    private PreparedStatement ps;

    public CommentaireService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Commentaire t) {
        String req = "insert into commentaire (description,idAnnonce,idUser) values (?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, t.getDescription());
            ps.setInt(2, t.getIdAnnonce().getIdAnnonce());
            ps.setInt(3, t.getIdUser().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Commentaire t) {
String req = "update `commentaire` set `description`=?  where idCommentaire=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(2, t.getIdCommentaire());
            ps.setString(1, t.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @Override
    public void delete(Integer idCommentaire) {
String req = "delete from commentaire where idCommentaire =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idCommentaire);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }

    @Override
    public Commentaire findById(Integer idCommentaire) {
String req = "select * from commentaire where idCommentaire = ?";
        Commentaire commentaire = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idCommentaire);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                commentaire= new Commentaire( resultSet.getInt(1),resultSet.getString(2),new Annonce(resultSet.getInt(3)), new User(resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentaire;    
    }

    @Override
    public List<Commentaire> getAll() {
String req = "select * from commentaire";
        List<Commentaire> listCommentaire = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Commentaire commentaire= new Commentaire( resultSet.getInt(1),resultSet.getString(2),new Annonce(resultSet.getInt(3)), new User(resultSet.getInt(4)));
                listCommentaire.add(commentaire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCommentaire;   
    }
    
   
    
}
