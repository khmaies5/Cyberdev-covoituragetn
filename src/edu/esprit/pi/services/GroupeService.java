




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IAbonnesService;
import edu.esprit.pi.iservices.IGroupeService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.iservices.ISujetService;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sarra
 */
public class GroupeService implements IGroupeService {

    private Connection connection;
    private PreparedStatement ps;
    private PreparedStatement ps2;
    IAbonnesService abonnesService = new AbonnesService();
    IUserService userService = new UserService();

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

  
    public boolean addGroupe(Groupe groupe) {
        String req = "insert into groupe (nom,description) values (?,?)";
        int i = -1;

        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, groupe.getNom());
            ps.setString(2, groupe.getDescription());
            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1) {
            return true;
        } else {
            return false;
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
                groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), new SujetService().getbyIdGroupe(resultSet.getInt(1)), resultSet.getString(5));
            }
//            while (resultSet2.next()) {
//                Sujet sujet = new Sujet(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3));
//                sujets.add(sujet);
//             
//                groupe.setSujets(sujets);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupe;
    }

    @Override
    public List<Groupe> getAll() {
        String req = "select * from groupe";

        List<Groupe> groupes = new ArrayList<>();
        List<Sujet> sujets = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Groupe groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), new AbonnesService().findByIdGroupe(resultSet.getInt(1)), new SujetService().getbyIdGroupe(resultSet.getInt(1)), resultSet.getString(5));

                groupes.add(groupe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;
    }

    public List<Groupe> getGroupWithSujet() {
        String req = "select * from groupe";
        // String req2 = "select * from groupe inner join sujet on groupe.id = ? and sujet.id_groupe=?";

        List<Groupe> groupes = new ArrayList<>();
        //  List<Sujet> sujets = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Groupe groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), new AbonnesService().findByIdGroupe(resultSet.getInt(1)), new SujetService().getbyIdGroupe(resultSet.getInt(1)), resultSet.getString(5));

//                ps2 = connection.prepareStatement(req2);
//                ps2.setInt(1, groupe.getId());
//                ps2.setInt(2, groupe.getId());
//                ResultSet resultSet2 = ps2.executeQuery();
//                while (resultSet2.next()) {
//
//                    Sujet sujet = new Sujet(resultSet2.getInt(5), resultSet2.getString(6), resultSet2.getString(7));
//                    sujets.add(sujet);
//                    groupe.setSujets(sujets);
                //  }
                groupes.add(groupe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;
    }

   

    public boolean updatePath(String path, int id) {
        int i = -1;
        try {
            String req = "UPDATE groupe SET pathFichier=?  WHERE id=?";
            ps = connection.prepareStatement(req);
            ps.setString(1, path);
            ps.setInt(2, id);

            i = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(Groupe s) {

        try {
            String req = "UPDATE `groupe` SET `nom`=?,"
                    + "`description`=? WHERE id=?";

            ps = connection.prepareStatement(req);
            ps.setString(1, s.getNom());
            ps.setString(2, s.getDescription());
            ps.setInt(3, s.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public boolean Groupeupdate(Groupe g) {
        int i = 0;
        try {
            String req = "UPDATE groupe SET nom=?,description=?,pathFichier=? WHERE id=?";
            ps = connection.prepareStatement(req);
            ps.setString(1, g.getNom());
            ps.setString(2, g.getDescription());

            ps.setString(3, g.getPathImage());
            ps.setInt(4, g.getId());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Groupe> getGroupWhenUserIsAdmin(User u) {
        String req = "select * from abonnees where id_user=? and role_user=?";

        List<Groupe> groupes = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.setString(2, "admin");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Abonnes ab = new Abonnes(new Groupe(resultSet.getInt(5)));

                String req2 = "select * from groupe where id=?";
                ps2 = connection.prepareStatement(req2);
                ps2.setInt(1, ab.getGroupe().getId());

                ResultSet resultSet2 = ps2.executeQuery();

                while (resultSet2.next()) {

                    Groupe groupe = new Groupe(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet.getString(5));

                    groupes.add(groupe);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;
    }

    @Override
    public int LastInseredId() {
        Integer id = 0;
        try {
            String req = "select * from groupe";
            ps2 = connection.prepareStatement(req);

            ResultSet resultSet = ps2.executeQuery();
            if (resultSet.last()) {
                id = resultSet.getInt(1);
            }

//String iD=resultSet.getString("last_id");
//id= Integer.parseInt(iD);
            //System.out.println(id);
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public List<Groupe> getGroupbyUser(User u) {
        //String req = "select * from abonnees where id_user=? and etat_abonnement=? ";

        List<Abonnes> abonnes = new ArrayList<>();
        List<Groupe> groupes = new ArrayList<>();

        try {

            abonnes = new AbonnesService().findByIdUser(u.getId());
            System.out.println(abonnes);
            for (Abonnes ab : abonnes) {
                String req2 = "select * from groupe where id=?";
                ps2 = connection.prepareStatement(req2);
                ps2.setInt(1, ab.getGroupe().getId());

                ResultSet resultSet2 = ps2.executeQuery();

                while (resultSet2.next()) {

                    Groupe groupe = new Groupe(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet2.getDate(4), resultSet2.getString(5));

                    //      System.out.println(groupe);
                    groupes.add(groupe);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;

    }

   
    public List<Groupe> rechercherMesGroupes(String recherche, int idUser) {
        List<Groupe> listeGroupes = new ArrayList<>();
        List<Groupe> listeGroupes2 = new ArrayList<>();
        listeGroupes = getGroupbyUser(new User(idUser));
        //  System.out.println("1111111111111111");
        //      System.out.println(listeGroupes);
        //List<Abonnes> mesAbonnements= abonnesService.findByIdUser(id_user);
        //String idcreator=userService.findById(id_user).get
        User userCreator = null;
        for (Groupe gr : listeGroupes) {
            List<Abonnes> ab = abonnesService.findByIdGroupe(gr.getId());
            //System.out.println("ab");
            //System.out.println(ab);
            for (Abonnes a : ab) { // System.out.println(a.getRoleUser());
                if ("admin".equals(a.getRoleUser())) {// System.out.println("  l;kjhngfvd"); 
                    userCreator = userService.findById(a.getUser().getId());
                }
            }
            // System.out.println(userCreator);
            String req = "select * from groupe g inner join  user u where g.nom like '%" + recherche + "%' or g.description like '%" + recherche + "%' or g.datecreaction like '%" + recherche + "%' or u.nom like '%" + recherche + "%' or u.prenom like '%" + recherche + "%' and u.id=? and g.id=?";

            // List<Alerte> alertes = new ArrayList<>();
            try {
                ps = connection.prepareStatement(req);
                ps.setInt(1, userCreator.getId());
                ps.setInt(2, gr.getId());
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Groupe groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5));
                    listeGroupes2.add(groupe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listeGroupes2;
    }

  

    public List<Groupe> rechercherTousLesGroupes(String recherche, int id_user) {
        List<Groupe> listeGroupes = new ArrayList<>();
        List<Groupe> listeGroupes2 = new ArrayList<>();
        listeGroupes = getAll();
        //  System.out.println("1111111111111111");
        //      System.out.println(listeGroupes);
        //List<Abonnes> mesAbonnements= abonnesService.findByIdUser(id_user);
        //String idcreator=userService.findById(id_user).get
        User userCreator = null;
        for (Groupe gr : listeGroupes) {
            List<Abonnes> ab = abonnesService.findByIdGroupe(gr.getId());

            for (Abonnes a : ab) {
                if ("admin".equals(a.getRoleUser())) {
                    userCreator = userService.findById(a.getUser().getId());
                }
            }

            String req = "select * from groupe g inner join  user u where g.nom like '%" + recherche + "%' or g.description like '%" + recherche + "%' or g.datecreaction like '%" + recherche + "%' or u.nom like '%" + recherche + "%' or u.prenom like '%" + recherche + "%' and u.id=? and g.id=?";

            try {
                ps = connection.prepareStatement(req);
                ps.setInt(1, userCreator.getId());
                ps.setInt(2, gr.getId());
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Groupe groupe = new Groupe(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5));
                    listeGroupes2.add(groupe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listeGroupes2;
    }

}
