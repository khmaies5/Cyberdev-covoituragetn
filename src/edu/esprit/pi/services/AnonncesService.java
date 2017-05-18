/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.services;
import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IPublicationFavoritesService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    
   public List<Annonce>  GetAnnonceNotFavoris(int idUserConnect)
    {    

        IPublicationFavoritesService PFService = new PublicationFavoritesService();
     List<Annonce> Newpub= new ArrayList<>();
     List<Annonce> pubssfav=PFService.findByIduserconnecte(idUserConnect);
     List<Annonce> listeSource = new ArrayList<Annonce>(pubssfav);
  List<Annonce> annonce = getAll();
 
        if(!pubssfav.isEmpty())
        {   for (Annonce user:annonce)
     {
         for(Annonce usFav:pubssfav)
         {
         
         if((user.getIdAnnonce()!=usFav.getIdAnnonce()) )
       
       { 
         
             Newpub.add(user);

         }
         }
       
     }  
        }
       
        else{ 
              for (Annonce user:annonce)
              { 
           

             Newpub.add(user);
          
        }}
      Newpub.removeAll(listeSource);
  Set set = new HashSet();
            set.addAll(Newpub);
            ArrayList distinctList = new ArrayList(set);
     return distinctList;
 }
    @Override
    public void add(Annonce annonces) {
 String req = "insert into annonce (id_annonce,trip_date,lieu_depart,lieu_arrive,type,nbr_personne,prix,critere,id_user) values (?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, annonces.getIdAnnonce());
           
           // ps.setDate(2, (java.sql.Date) annonces.getTripDate());
            
            
            ps.setTimestamp(2, new java.sql.Timestamp(annonces.getTripDate().getTime()));
            //ps.setTimestamp(3, null);
            
            //ps.setDate(3, (java.sql.Date) annonces.getTripTime());
            ps.setString(3, annonces.getLieuDepart());
            ps.setString(4, annonces.getLieuArriver());
            ps.setString(5, annonces.getTypeAnnonce());
            ps.setInt(6, annonces.getNbrPersonne());
            ps.setFloat(7, annonces.getPrix());
            ps.setString(8, annonces.getCritere());
            

            ps.setInt(9, annonces.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void Update(Annonce annonce) {
     try {
            String req="UPDATE annonce SET trip_date=?,lieu_depart=?,lieu_arrive=?,type=?,nbr_personne=?,prix=?,critere=?,id_user=? WHERE id_annonce=?";
      ps = connection.prepareStatement(req);
      ps.setTimestamp(1,  new java.sql.Timestamp(annonce.getTripDate().getTime()));
      ps.setString(2, annonce.getLieuDepart());
            ps.setString(3, annonce.getLieuArriver());
            ps.setString(4, annonce.getTypeAnnonce());
            ps.setInt(5, annonce.getNbrPersonne());
            ps.setFloat(6, annonce.getPrix());
            ps.setString(7, annonce.getCritere());
            ps.setInt(8, annonce.getCreator().getId());
            ps.setInt(9, annonce.getIdAnnonce());
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ps);
  Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
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
                annonces = new Annonce(resultSet.getInt(1), resultSet.getDate(3),resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10), new User(resultSet.getInt(2))); // new User(resultSet.getInt(3))
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
                      System.err.println("rrrrrraaaaabek"+resultSet.getInt(2));
                Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(3),resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10), new User(resultSet.getInt(2)));
          
              
                annonces.add(annonce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;
    }

    @Override
    public List<Annonce> rechercherannonceselontrajet(String depart, String arrivee, String date) {
  String req = "select * from annonce where lieu_depart LIKE ? or lieu_arrive LIKE ? or date LIKE ? ";
        List<Annonce> annonces = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, depart);
            ps.setString(2, arrivee);
            ps.setString(3,date);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
               Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(3),resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10), new User(resultSet.getInt(2))); // new User(resultSet.getInt(3))
                annonces.add(annonce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;   }

   
public  List<Annonce> rechercheavancee(String depart, String arrivee, String date,int prix,String type,String sex)
{
String req = "select * from annonce where lieu_depart LIKE ? or lieu_arrive LIKE ? or date LIKE ? or prix LIKE ? or type LIKE ?   ";
        List<Annonce> annonces = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, depart);
            ps.setString(2, arrivee);
            ps.setString(3,date);
            ps.setInt(4, prix);
            ps.setString(5, type);
            
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
             Annonce   annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(3),resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10), new User(resultSet.getInt(2))); // new User(resultSet.getInt(3))
                annonces.add(annonce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;  
}
  
  public void Updateplaces(int id,int nbre) {
     try {
            String req="UPDATE annonce SET nbr_personne=? WHERE id_annonce=?";
      ps = connection.prepareStatement(req);
    ps.setInt(1, nbre);
     ps.setInt(2, id);
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ps);
  Logger.getLogger(SujetService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
  
  public List<String> autocompletefield()
  {
  
     String req = "select lieu_depart from annonce";
        List<String> annonces = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
               // Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(2),resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getString(9), new User(resultSet.getInt(10)));
               String s =resultSet.getString(1);
               annonces.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;
  
  }

    public List<Annonce> listPubUser( int idUserFav)
     {
         String req = "select * from annonce  where id_user = ?";
      List<Annonce> annonces = new ArrayList<>();
     
        try {
            ps = connection.prepareStatement(req);
         
            ps.setInt(1, idUserFav);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
           
         Annonce annonce = new Annonce(resultSet.getInt(1), resultSet.getDate(2),resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getFloat(8), resultSet.getString(9), new User(resultSet.getInt(10)));
                annonces.add(annonce);
            }    
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(annonces);
        return annonces;  
         
     }
   

  


   
 
}
