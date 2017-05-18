/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.PublicationFavorite;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.esprit.pi.iservices.IPublicationFavoritesService;


/**
 *
 * @author Nacef Fethi
 */
public class PublicationFavoritesService implements IPublicationFavoritesService {

     private Connection connection;
    private PreparedStatement ps;

    public PublicationFavoritesService() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void add(PublicationFavorite pubFav) {
         String req = "insert into publication_enregistree (id_user,id_annonce) values (?,?)";
        try {
            ps = connection.prepareStatement(req);
            
             ps.setInt(1,pubFav.getCreator().getId());
              ps.setInt(2, pubFav.getAcreator().getIdAnnonce());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idPub) {
            String req = "delete from publication_enregistree where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idPub);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<PublicationFavorite> getAll() {
           String req = "select * from publication_enregistree";
        List<PublicationFavorite> pub_f = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PublicationFavorite pub_fav = new PublicationFavorite(resultSet.getInt(1),resultSet.getDate(2), new UserService().findById(resultSet.getInt(3)), new AnonncesService().findById(resultSet.getInt(4)));
                pub_f.add(pub_fav);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pub_f;
    }

    @Override
    public PublicationFavorite findById(Integer iduser) {
String req = "select * from publication_enregistree  where id_user = ?";
        PublicationFavorite pub = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, iduser);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                pub = new PublicationFavorite(resultSet.getInt(1),resultSet.getDate(2),new User(resultSet.getInt(3)),new Annonce(resultSet.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pub;    }

    @Override
    public List<PublicationFavorite> getAll(Integer idUser) {

String req = "select p.id ,p.date_enregistrement , s.id_annonce  from publication_enregistree p inner join annonce s on p.id_annonce=s.id_annonce  and p.id_user = ?";
List<PublicationFavorite> listPubFav = new ArrayList<>();        
PublicationFavorite pub ;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
              while (resultSet.next()) {
pub = new PublicationFavorite(resultSet.getInt(1),resultSet.getDate(2),new AnonncesService().findById(resultSet.getInt(3)));
              
           listPubFav.add(pub);
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPubFav;     
    }

    @Override
    public List<Annonce> findByIduserconnecte(Integer idUserConnect) {
String req = "select * from publication_enregistree  where id_user = ?";
           PublicationFavorite annonceFavorite = null;
        List  <PublicationFavorite>pubs= new ArrayList<>();
         List  <Annonce>pubs2= new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
         
            ps.setInt(1, idUserConnect);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
           
                annonceFavorite = new PublicationFavorite(resultSet.getInt(1),resultSet.getDate(2),new UserService().findById(resultSet.getInt(3)),new AnonncesService().findById(resultSet.getInt(4)));
      pubs.add(annonceFavorite);
            } 
            }    
 
        catch (Exception e) {
        }
        for (PublicationFavorite ps : pubs)
       {
      pubs2.add(new AnonncesService().findById(ps.getAcreator().getIdAnnonce()));
        }
         System.out.println(pubs2);
        return pubs2;  
    }   

    }

   

