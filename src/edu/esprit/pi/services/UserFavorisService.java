/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.User;
import edu.esprit.pi.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import edu.esprit.pi.models.UserFavoris;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.esprit.pi.iservices.IUserFavorisService;
/**
 *
 * @author Nacef Fethi
 */
public class UserFavorisService implements IUserFavorisService  {
   private Connection connection;
    private PreparedStatement ps;
  
    public UserFavorisService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(UserFavoris userFavoris) {
 String req = "insert into user_favoris (iduser_recommendes,id_userconnect) values (?,?)";
        try {
            ps = connection.prepareStatement(req);
            
            
              ps.setInt(1, userFavoris.getUserRecommendes().getId());
               ps.setInt(2,userFavoris.getUserConnect().getId());
          //    ps.setInt(3,userFavoris.getNbrRecommendation());
            
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idUserFavoris) {
       String req = "delete from user_favoris where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idUserFavoris);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<UserFavoris> getAll() {
    
           String req = "select * from user_favoris";
        List<UserFavoris> listpub_f = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
 UserFavoris userFavoris = new UserFavoris(resultSet.getInt(1),resultSet.getDate(2),new UserService().findById(resultSet.getInt(3)),new UserService().findById(resultSet.getInt(4)));
                listpub_f.add(userFavoris);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpub_f;
    }

 //   @Override
 //   public  UserFavoris  findById(Integer iduser_connect) {
//     String req = "select * from user_favoris  where id_userconnect = ?";
  // UserFavoris user_favoris = null;
//  List  <UserRecommendes>users= new ArrayList<>();
//     
//        try {
//            ps = connection.prepareStatement(req);
//         
//            ps.setInt(1, iduser_connect);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//           
//                user_favoris = new UserFavoris(resultSet.getInt(1),new User(resultSet.getInt(2)),new User(resultSet.getInt(3)),resultSet.getInt(4));
//      users.add(user_favoris);
//            }    
// 
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
//return user_favoris;    
//}

    @Override
    public List<UserFavoris> findByIduserconnecter(Integer idUserConnect) {
     String req = "select * from user_favoris  where id_userconnect = ?";
        UserFavoris user_favoris = null;
        List  <UserFavoris>users= new ArrayList<>();
     
        try {
            ps = connection.prepareStatement(req);
         
            ps.setInt(1, idUserConnect);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
           
               UserFavoris userFavoris = new UserFavoris(resultSet.getInt(1),resultSet.getDate(2),new UserService().findById(resultSet.getInt(3)),new UserService().findById(resultSet.getInt(4)));
               // user_favoris=new UserFavoris(resultSet.getInt(1), resultSet.getDate(2), new User(resultSet.getInt(3)), userConnect);
      users.add(userFavoris);
            }    
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;      }

    @Override
    public List<User> findByIduserconnecte(Integer idUserConnect) {
 String req = "select * from user_favoris  where id_userconnect = ?";
           UserFavoris userFavoris = null;
        List  <UserFavoris>users= new ArrayList<>();
         List  <User>users2= new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
         
            ps.setInt(1, idUserConnect);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
           
                userFavoris = new UserFavoris(resultSet.getInt(1),resultSet.getDate(2),new UserService().findById(resultSet.getInt(3)),new UserService().findById(resultSet.getInt(4)));
      users.add(userFavoris);
            } 
         
      
            }    
 
        catch (Exception e) {
        }
        for (UserFavoris us : users)
        {
        users2.add(new UserService().findById(us.getUserRecommendes().getId()));
        }
        return users2;    }
    }

    
    

