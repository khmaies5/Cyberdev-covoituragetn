/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.tests;

import edu.esprit.pi.iservices.IRateService;
import edu.esprit.pi.iservices.IService;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Rate;
import edu.esprit.pi.models.User;
import edu.esprit.pi.services.RateService;

/**
 *
 * @author Momo
 */
public class RateTest {
    public static void main(String[] args){
    
        IService service = new RateService();
        IRateService rateService=new RateService();
        
        
    Rate rate = new Rate(5, new User(1),new Annonce(1));
    //service.add(rate);
    Rate rateModifier = new Rate(1,3, new User(1),new Annonce(1));
    //rateService.Update(rateModifier);
    //service.delete(1);
    //System.out.println(service.findById(2));
    service.getAll().forEach(System.out::println);
        
    }  
}
