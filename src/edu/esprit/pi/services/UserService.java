/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.User;
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
 * @author khmai
 */
public class UserService implements IUserService {

    private Connection connection;
    private PreparedStatement ps;

    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(User user) {
              String req = "insert into user(nom,prenom,email,password,cin,travail,sexe,date_naissance,telephone,gouvernerat,role,login,actif,photo_profil,username,username_canonical,email_canonical,enabled,roles)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getCin());
            ps.setString(6, user.getTravail());
            ps.setString(7, user.getSexe());
            ps.setDate(8, user.getDate_naissance());
            ps.setString(9, user.getTelephone());
            ps.setString(10, user.getGouvernorat());
            ps.setInt(11, 1);
            ps.setString(12, user.getLogin());
            ps.setInt(13, 1);
            ps.setString(14, user.getPhoto_Profil());
                        ps.setString(15, user.getLogin());
            ps.setString(16, user.getLogin());
            ps.setString(17, user.getEmail());
            ps.setInt(18,1 );
                        ps.setString(19, "");

           

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer idUser) {
        String req = "delete from user where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUser);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public String findEmailByIdUser(Integer idUser) {
        
        String req = "select email from user where id = ?";
       // User user = null;
        String n="";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
             //   user = new User(resultSet.getString(1));
             n=resultSet.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;

    }
    @Override
    public User findById(Integer idUser) {
        String req = "select * from user where id = ?";
        User user = null;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        String req = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));               
                u.setTravail(rs.getString(12));
                 u.setEmail(rs.getString(4));
                  u.setActif(rs.getInt(19));
                 u.setTelephone(rs.getString(15)); 
                 u.setLogin(rs.getString(18));
                 u.setGouvernorat(rs.getString(16));
                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;   

    }

    @Override
    public User authentication(String login, String password) {
        User u=new User();
          String i="fail";
          String k;
        try {
            String query = "SELECT id,nom,prenom,login,password,role FROM user WHERE user.login ='"+login+"' and user.password='"+password+"'";
            
            
            PreparedStatement ps = connection.prepareStatement(query);
            //ps.setString(1, s2+"{"+k+"}");
            //i=s2+"{"+k+"}";
            ResultSet rs=  ps.executeQuery(query);
            
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setRole(rs.getInt(6));
                
                
                       
            }
            return u;
          
            

        } catch (SQLException ex) {
            Logger.getLogger(IUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return u;
    }
    @Override
     public boolean RechLogin(String text) {
         boolean b=false;
        try {
            String query = "SELECT * FROM user WHERE user.login ='"+text+"'" ;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs=  ps.executeQuery(query);

            while (rs.next()) {
                b=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return b;
    }
     
     
      public User Search(int n) {
      
        User s = new User();
        try {
            String query = "SELECT * FROM user WHERE id =" + n;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                s.setId(rs.getInt(1));
                s.setEmail(rs.getString(4));
                s.setNom(rs.getString(13));
                s.setPrenom(rs.getString(14));                
                s.setTelephone(rs.getString(26));
                s.setGouvernorat(rs.getString(17));
                s.setTravail(rs.getString(23));
                s.setLogin(rs.getString(28));
                s.setPhoto_Profil(rs.getString(18));
                s.setDate_naissance(rs.getDate(25));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

        
        
        
    }
         public List<User>  GetUsersNotFavoris(int idUserConnect)
    {    

    UserFavorisService UFService = new UserFavorisService();
     List<User> NewrUser= new ArrayList<>();
     List<User> usersfav=UFService.findByIduserconnecte(idUserConnect);
     List<User> listeSource = new ArrayList<User>(usersfav);
  List<User> users = getAll();

     
 
        if(!usersfav.isEmpty())
        {   for (User user:users)
     {
         for(User usFav:usersfav)
         {
         
         if((user.getId()!=usFav.getId()) &&(user.getId()!=idUserConnect))
       
       { 
         
             NewrUser.add(user);

         }
         }
       
     
     }  
        }
       
        else{ 
              for (User user:users)
              { 
            if(user.getId()!=idUserConnect)
         {  

             NewrUser.add(user);
         }    
        }}
      NewrUser.removeAll(listeSource);

     return NewrUser;
 }
 
   public int nbrepub(int idUser)
   {
   String req = "select count(id_annonce) from annonce where id_user=?";
      int nbr=0;
        try {
            ps = connection.prepareStatement(req);
           ps.setInt(1, idUser);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
             //   
             nbr=resultSet.getInt(1);}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbr;
   
   }

    @Override
    public void Update(User i1) {

        try {
            String query = "UPDATE user SET nom=? ,prenom=?, email=? ,cin=?,travail=? ,sexe=? ,date_naissance=?,gouvernerat=?,telephone=? WHERE user.id='" + i1.getId() + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, i1.getNom());
            ps.setString(2, i1.getPrenom());
            ps.setString(3, i1.getEmail());
         //   ps.setString(4, u.getPassword());
            ps.setString(4, i1.getCin());
            ps.setString(5, i1.getTravail());
            ps.setString(6, i1.getSexe());
            ps.setDate(7, i1.getDate_naissance());
            ps.setString(8, i1.getTelephone());
            ps.setString(9, i1.getGouvernorat());
            //ps.setInt(11, 1);
           // ps.setInt(13, 1);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Desactiver(User u) {

        try {
            String query = "UPDATE user SET actif=?  WHERE user.id='" + u.getId() + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(19, 0);
           
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    }


