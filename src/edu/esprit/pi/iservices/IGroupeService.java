

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
}
