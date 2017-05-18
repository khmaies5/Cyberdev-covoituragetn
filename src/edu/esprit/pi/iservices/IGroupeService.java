

package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author Sarra
 */
public interface IGroupeService extends IService<Groupe,Integer>{

   int LastInseredId();
        void update(Groupe s);
       List<Groupe>  getGroupWithSujet();
   List<Groupe>  getGroupWhenUserIsAdmin(User u);
      List<Groupe>  getGroupbyUser(User u);
         boolean addGroupe(Groupe groupe);
  
  List<Groupe> rechercherMesGroupes(String recherche, int id_user) ;

    List<Groupe>  rechercherTousLesGroupes  (String recherche, int id_user);
   boolean updatePath(String path ,int id );
  boolean Groupeupdate(Groupe g);
}
