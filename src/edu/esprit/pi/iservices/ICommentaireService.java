/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;
import edu.esprit.pi.models.Commentaire;

/**
 *
 * @author Momo
 */
public interface ICommentaireService extends IService<Commentaire, Integer> {
    
    public void Update(Commentaire t);

}
