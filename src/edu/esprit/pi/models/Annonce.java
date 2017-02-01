/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.pi.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author khmai
 */
public class Annonce {
    
    private int id_annonce;
    private String trip_date;
   
    private String lieu_depart;
    private String lieu_arrive;
    private String type_annonce;
    private int nbr_personne;
    private float prix;
    private String critere;
    private User creator;

    public Annonce(int id_annonce, String trip_date, String lieu_depart, String lieu_arrive, String type_annonce, int nbr_personne, float prix, String critere, User creator) {
        this.id_annonce = id_annonce;
        this.trip_date = trip_date;
        this.lieu_depart = lieu_depart;
        this.lieu_arrive = lieu_arrive;
        this.type_annonce = type_annonce;
        this.nbr_personne = nbr_personne;
        this.prix = prix;
        this.critere = critere;
        this.creator = creator;
    }

    public Annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", trip_date=" + trip_date + ", lieu_depart=" + lieu_depart + ", lieu_arrive=" + lieu_arrive + ", type_annonce=" + type_annonce + ", nbr_personne=" + nbr_personne + ", prix=" + prix + ", critere=" + critere + ", creator=" + creator + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Annonce other = (Annonce) obj;
        if (this.id_annonce != other.id_annonce) {
            return false;
        }
        return true;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    

    public Annonce() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_annonce;
        return hash;
    }

  

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

   

    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

  

    public String getLieu_depart() {
        return lieu_depart;
    }

    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

    public String getLieu_arrive() {
        return lieu_arrive;
    }

    public void setLieu_arrive(String lieu_arrive) {
        this.lieu_arrive = lieu_arrive;
    }

    public String getType_annonce() {
        return type_annonce;
    }

    public void setType_annonce(String type_annonce) {
        this.type_annonce = type_annonce;
    }

    public int getNbr_personne() {
        return nbr_personne;
    }

    public void setNbr_personne(int nbr_personne) {
        this.nbr_personne = nbr_personne;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCritere() {
        return critere;
    }

    public void setCritere(String critere) {
        this.critere = critere;
    }

    

    
    
    
}
