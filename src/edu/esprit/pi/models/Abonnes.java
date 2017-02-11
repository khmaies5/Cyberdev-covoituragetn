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
public class Abonnes {
    int id;
    Date date_abonnement;
    String role_user;
   User user;
   Groupe groupe;
private List<Reponse> reponses;
private List<Sujet> sujets;

    public Abonnes(int id) {
        this.id = id;
    }

    public Abonnes(int id, Date date_abonnement, String role_user, User user, Groupe groupe) {
        this.id = id;
        this.date_abonnement = date_abonnement;
        this.role_user = role_user;
        this.user = user;
        this.groupe = groupe;
    }

    public Abonnes(Date date_abonnement, String role_user, User user, Groupe groupe) {
        this.date_abonnement = date_abonnement;
        this.role_user = role_user;
        this.user = user;
        this.groupe = groupe;
    }

    public Abonnes(String role_user, User user, Groupe groupe) {
        this.role_user = role_user;
        this.user = user;
        this.groupe = groupe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_abonnement() {
        return date_abonnement;
    }

    public void setDate_abonnement(Date date_abonnement) {
        this.date_abonnement = date_abonnement;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
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
        final Abonnes other = (Abonnes) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
