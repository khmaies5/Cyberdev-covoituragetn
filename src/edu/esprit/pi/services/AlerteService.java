/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IAlerteService;
import edu.esprit.pi.iservices.IAnnonceService;
import edu.esprit.pi.iservices.IEmailService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;

/**
 *
 * @author Nacef Fethi
 */
public class AlerteService implements IAlerteService {
    
//   @FXML 
// private Button btn_ajouterAlerte  ;
//      @FXML 
// private TextField txt_LDepart  ;
//         @FXML 
// private TextField txt_LArrivee  ;
//            @FXML 
// private DatePicker DP_Date  ;
            
    private Connection connection;
    private PreparedStatement ps;

    public AlerteService() {
        connection = DataSource.getInstance().getConnection();
    }

     
    public void add(Alerte alerte) 
     {
     String req = "insert into alerte (lieudepart,lieuarrivee,date,heure,id_user) values (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            
     
           ps.setString(1, alerte.getLieuDepart());
           ps.setString(2, alerte.getLieuArrivee());
             ps.setDate(3, (Date) alerte.getDate());
             ps.setInt(4, alerte.getHeure());
            ps.setInt(5, alerte.getCreator().getId());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void delete(Integer idAlerte) {
        String req = "delete from alerte where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idAlerte);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Alerte alerte) {
         try {
            String req="UPDATE `alerte` SET `lieudepart`=?,"
                  + "`lieuarrivee`=?,`date`=? ,`heure`=? WHERE id=? and id_user=?";
            ps=connection.prepareStatement(req);
            ps.setString(1,alerte.getLieuDepart());
            ps.setString(2,alerte.getLieuArrivee());
            ps.setDate(3, (Date) alerte.getDate());
            ps.setInt(4,alerte.getHeure());
            ps.setInt(5,alerte.getId());
            ps.setInt(6, alerte.getCreator().getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Alerte> getAll() {
    String req = "select * from alerte ";
    
        List<Alerte> alertes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Alerte alerte = new Alerte(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4), resultSet.getInt(5),new UserService().findById(resultSet.getInt(6)));
                alertes.add(alerte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alertes;
    }
   
    public Alerte findById(Integer idalerte) {
        String req = "select * from alerte where id = ?";
        Alerte alerte = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idalerte);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                alerte = new Alerte(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4),resultSet.getInt(5),new UserService().findById(resultSet.getInt(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alerte;
    }

    @Override
    public List<Alerte> getAll(Integer idUser) {
    String req = "select * from alerte where id_user=? ";
     List<Alerte> alertes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
             ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Alerte alerte = new Alerte(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4), resultSet.getInt(5),new UserService().findById(resultSet.getInt(6)));
                alertes.add(alerte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alertes;

    }

    @Override
    public List<Annonce> comparerPublicationAlerte(List<Alerte> alertes) {
         IAnnonceService annonceService= new AnonncesService();
       List<Annonce> annonceAll=annonceService.getAll();
       int index=0;
       List<Annonce> annonce = new ArrayList<>();
       User user=new User(1);
       for(Alerte al :alertes)
       {
      for (Annonce a : annonceAll) {
           if (a.getLieuDepart().equals(al.getLieuDepart())
                   &&(a.getLieuArriver().equals(al.getLieuArrivee())))
           {
              index=alertes.indexOf(al);
              
                               // System.out.println(index);
                                Alerte al2=alertes.get(index);
                               // System.out.println(al2);

         annonce.add(a);
               
           }    }}
      if(!annonce.isEmpty()) 
              {
                 IEmailService mailService= new MailService();
                 mailService.envoyerMail(user, annonce, alertes.get(index));
            
              }
  
      return annonce;}
//  @Override
//  public void comparerPublicationAlertes() {
//        IAlerteService alerteService= new AlerteService();
//      List<Alerte> alerteAll = new ArrayList<>(); 
//      
// 
//     alerteAll=alerteService.getAll();
//       List<Annonce> pub = new ArrayList<>(); 
//      
//       for (Alerte  a : alerteAll) {
//       pub=comparerPublicationAlerte(a);
//          }
//
//         
//        
//   }

    @Override
    public List<Alerte> rechercher(String recherche, Integer idUser) {

    
String req ="select * from alerte where concat(`lieudepart`,`lieuarrivee`,`date`,`heure`) like '%"+recherche+"%' and id_user=? ";  
  
    List<Alerte> alertes = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Alerte alerte = new Alerte(resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4), resultSet.getInt(5));
                alertes.add(alerte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alertes;
    }





  
}
