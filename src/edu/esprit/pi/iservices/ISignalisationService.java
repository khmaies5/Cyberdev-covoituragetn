/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.iservices;

import edu.esprit.pi.models.Signalisation;

/**
 *
 * @author Momo
 */
public interface ISignalisationService extends IService<Signalisation, Integer> {
    
    public void Update(Signalisation t);
    
}
