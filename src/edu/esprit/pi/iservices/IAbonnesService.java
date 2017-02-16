

package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Abonnes;
import edu.esprit.pi.models.Groupe;
import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author Sarra
 */
public interface IAbonnesService extends IService<Abonnes, Integer>{
    
        void update(Groupe s);
       List<Abonnes>  findByIdGroupe(Integer I);
        List<Abonnes>  findByIdUser(Integer I);
void desabonner(Groupe g,User u);
}
