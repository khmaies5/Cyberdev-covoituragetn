/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.User;
import java.util.List;

/**
 *
 * @author khmai
 */
public interface IUserService extends IService<User, Integer> {

    User authentication(String login, String password);
    public boolean RechLogin(String text);
    
   // public boolean RechLogin(String text);
    User Search(int n);
    void Update(User i1);
    void Desactiver(User u);

}
