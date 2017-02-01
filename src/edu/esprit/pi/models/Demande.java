/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.models;

import java.util.Objects;

/**
 *
 * @author amrouche
 */
public class Demande {
    private int id_demande ;
    private int id_annonce ;
    private int id_user ;
    private String etat ;

    public Demande() {
    }

    public Demande(int id_annonce, int id_user, String etat) {
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.etat = etat;
    }

    public Demande(int id_demande, int id_annonce, int id_user, String etat) {
        this.id_demande = id_demande;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.etat = etat;
    }

    public int getId_demande() {
        return id_demande;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public int getId_user() {
        return id_user;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_demande;
        hash = 23 * hash + this.id_annonce;
        hash = 23 * hash + this.id_user;
        hash = 23 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Demande other = (Demande) obj;
        if (this.id_demande != other.id_demande) {
            return false;
        }
        if (this.id_annonce != other.id_annonce) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", id_annonce=" + id_annonce + ", id_user=" + id_user + ", etat=" + etat + '}';
    }
    
    
}
