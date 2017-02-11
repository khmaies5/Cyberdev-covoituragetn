/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sarra
 */
public class Sujet {
    private int id;
    private String objet;
    private String topic;
    private Groupe groupe;
     private Abonnes creator;
     private List<Reponse> reponses;
     private Date date_publication;

    public Sujet(int id) {
        this.id = id;
    }

     
    public Sujet(String objet, String topic, Groupe groupe) {
        this.objet = objet;
        this.topic = topic;
        this.groupe = groupe;
    }

    public Sujet(int id, String objet, String topic, Groupe groupe) {
        this.id = id;
        this.objet = objet;
        this.topic = topic;
        this.groupe = groupe;
    }

    public Sujet(int id, String objet, String topic) {
        this.id = id;
        this.objet = objet;
        this.topic = topic;
    }
 



    public Abonnes getCreator() {
        return creator;
    }

    public Sujet(int id, String objet, String topic, Groupe groupe, Abonnes creator, Date date_publication) {
        this.id = id;
        this.objet = objet;
        this.topic = topic;
        this.groupe = groupe;
        this.creator = creator;
        this.date_publication = date_publication;
    }

    public void setCreator(Abonnes creator) {
        this.creator = creator;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

   

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", objet=" + objet + ", topic=" + topic + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sujet other = (Sujet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}
