/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.models;

import java.util.Date;

/**
 *
 * @author Sarra
 */
public class Reponse {
    private int id;
    private Date date_reponse;
    private String reponse_sujet;
    private Sujet sujet;
    private User creator;

    public Reponse(int id, Date date_reponse, Sujet sujet, String reponse_sujet, User creator) {
        this.id = id;
        this.date_reponse = date_reponse;
        this.reponse_sujet = reponse_sujet;
        this.sujet = sujet;
        this.creator = creator;
    }
 

    public Reponse(Date date_reponse, String reponse_sujet, Sujet sujet, User creator) {
        this.date_reponse = date_reponse;
        this.reponse_sujet = reponse_sujet;
        this.sujet = sujet;
        this.creator = creator;
    }

    public Reponse(String reponse_sujet, Sujet sujet, User Abonnes) {
        this.reponse_sujet = reponse_sujet;
        this.sujet = sujet;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public String getReponse_sujet() {
        return reponse_sujet;
    }

    public void setReponse_sujet(String reponse_sujet) {
        this.reponse_sujet = reponse_sujet;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", date_reponse=" + date_reponse + ", reponse_sujet=" + reponse_sujet + ", sujet=" + sujet + ", creator=" + creator + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Reponse other = (Reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
