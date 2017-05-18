/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.models;

import java.util.List;

/**
 *
 * @author Nacef Fethi
 */
public class Mail {
    String contenu;
    List <Annonce> annonce;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public List<Annonce> getAnnonce() {
        return annonce;
    }

    public void setAnnonce(List<Annonce> annonce) {
        this.annonce = annonce;
    }

    public Mail(String contenu, List<Annonce> annonce) {
        this.contenu = contenu;
        this.annonce = annonce;
    }

    @Override
    public String toString() {
        return  contenu + "Annonce:" + annonce ;
    }
    
    
}
