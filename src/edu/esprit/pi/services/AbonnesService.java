/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Sujet;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sarra
 */
public class AbonnesService implements IAbonnesService {

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
            ps.setString(3, A.getRoleUser());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
    public boolean addAbonnes(Abonnes A) {
        String req = "insert into abonnees (id_groupe,id_user,role_user) values (?,?,?)";
int i=-1;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, A.getGroupe().getId());
            ps.setInt(2, A.getUser().getId());
            ps.setString(3, A.getRoleUser());
           i= ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
         if(i==1) return true; else return false;
    }

        
           public boolean ReAbonnes(Abonnes A) {
   String req="UPDATE `abonnees` SET `etat_abonnement`=? WHERE id=? ";
int i=-1;
        try {
            ps = connection.prepareStatement(req);
               ps.setInt(1,1);
            ps.setInt(2, A.getId());
         
           i= ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
         if(i==1) return true; else return false;
    }
    @Override
    public List<Abonnes> getAll() {
        String req = "select * from abonnees";

        List<Abonnes> abonnees = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

Abonnes  abonnes = new Abonnes(resultSet.getInt(1), resultSet.getDate(4), resultSet.getString(5), new User(resultSet.getInt(3)), new Groupe(resultSet.getInt(2)));

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
  abonne = new Abonnes(resultSet.getInt(1), resultSet.getDate(4), resultSet.getString(5), new User(resultSet.getInt(3)), new Groupe(resultSet.getInt(2)));
                System.out.println("fefefefe  "+abonne);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return abonne;
    }



    @Override
    public void update(Groupe s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Abonnes> findByIdGroupe(Integer IGroupe) {
String req = "select * from abonnees where id_groupe = ? and etat_abonnement=?";

        Abonnes abonne = null;
  List<Abonnes> abonnees = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);

            ps.setInt(1, IGroupe);
   ps.setInt(2, 1);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                abonne = new Abonnes(resultSet.getInt(1), resultSet.getDate(4), resultSet.getString(5), new User(resultSet.getInt(3)));
            abonnees.add(abonne);
               
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
   
        return abonnees;
    }
      
    public Abonnes findByIdGroupeAndIdUser(int iGroupe, int idUser) {
    
String req = "select * from abonnees where id_groupe =? and id_user=?";

        Abonnes abonne = null;
 
        try {
            ps = connection.prepareStatement(req);

            ps.setInt(1, iGroupe);

    ps.setInt(2, idUser);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
          // abonne=new Abonnes
  abonne = new Abonnes(resultSet.getInt(1), resultSet.getDate(4), resultSet.getString(5), new User(resultSet.getInt(3)), new Groupe(resultSet.getInt(2)));
     
      
    }
    
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return abonne;
    }

    @Override
    public void desabonner(Groupe g, User u) {
  try {
            String req="UPDATE `abonnees` SET `etat_abonnement`=? WHERE id_user=? and id_groupe=?";
         
             ps = connection.prepareStatement(req);
               ps.setInt(1,0);
            ps.setInt(2,u.getId());
            ps.setInt(3,g.getId());
          
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Abonnes> findByIdUser(Integer idUser) {
 String req = "select * from abonnees where id_user=? and etat_abonnement=? ";

        Abonnes abonne = null;
  List<Abonnes> abonnees = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);

               ps.setInt(1, idUser);
             ps.setInt(2,1);  

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                
  abonne = new Abonnes(resultSet.getInt(1), resultSet.getDate(4), resultSet.getString(5), new User(resultSet.getInt(3)), new Groupe(resultSet.getInt(2)));
            abonnees.add(abonne);
            
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return abonnees;    }
   public List<User>  GetUsersNonAbonnesAuGroupe(int idGroupe)
    {    
IUserService userService = new UserService(); 
     IGroupeService groupeService = new GroupeService();
    List<User> listeSource= new ArrayList<>();
  List<User> users = userService.getAll();
  List<User> inviterMembre= new ArrayList<>();
     List<Abonnes> abonnements=findByIdGroupe(idGroupe);
    
   for(Abonnes abonnes:abonnements)
   {
   listeSource.add(abonnes.getUser());
   }
     for (User user:users)
     {
         for(Abonnes abonnes:abonnements)
         {
         
         if(user.getId()!=abonnes.getUser().getId())
             inviterMembre.add(user);
             
         }
     }
     
     inviterMembre.removeAll(listeSource);
        Set set = new HashSet();
            set.addAll(inviterMembre);
            ArrayList distinctList = new ArrayList(set);
     return distinctList;
 }

  
      
}