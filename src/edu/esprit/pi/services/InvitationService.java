/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IInvitationService;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.Invitation;
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
public class InvitationService implements IInvitationService {

    private Connection connection;
    private PreparedStatement ps;
    private PreparedStatement ps2;

    public InvitationService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public boolean accepter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> getInvitationbyGroupe(int IdGroupe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> getInvitationbyUser(int idUser) {
        String req = "select * from invitation where id_user_invite= ?";

        List<Invitation> invitations = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);

            ps.setInt(1, idUser);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Invitation invitation = new Invitation(resultSet.getInt(1), new User(resultSet.getInt(2)), new Groupe(resultSet.getInt(3)), new User(resultSet.getInt(4)), resultSet.getDate(5));
                invitations.add(invitation);
            }
//           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invitations;
    }

    @Override
    public void add(Invitation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer idInvitation) {
        String req = "delete from invitation where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, idInvitation);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletebyGroupe(Integer idGroupe) {
        String req = "delete from invitation where id_groupe =?";
        int i = 0;
        try {

            ps = connection.prepareStatement(req);
            ps.setInt(1, idGroupe);
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
    public Invitation findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addInvitation(Invitation invitation) {
        String req = "insert into invitation (id_user_invite,id_groupe,id_user_creator) values (?,?,?)";
        int i = -1;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, invitation.getUser().getId());
            ps.setInt(2, invitation.getGroupe().getId());
            ps.setInt(3, invitation.getCreator().getId());
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
    public boolean envoyerInvitation(List<User> usersClicked, int idGroupe, int idcreator) {
        List<Boolean> nbEnvoye = new ArrayList<>();
        int i = 0;

        for (User u : usersClicked) {
            Invitation invitation = new Invitation(new User(u.getId()), new Groupe(idGroupe), new User(idcreator));

            boolean etat = addInvitation(invitation);

            if (etat == true) {
                i++;
                nbEnvoye.add(etat);

            }
        }

        if (i == nbEnvoye.size()) {
            return true;
        } else {
            return false;
        }
    }

}
