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
 * @author Nacef Fethi
 */
public class Alerte {
     private int id;
    
    private String lieuDepart;
    private String lieuArrivee;
    private Date date;
    private int heure;
     private User creator;

    public Alerte() {
    }

    public Alerte(int id) {
        this.id = id;
    }

    public Alerte(String lieuDepart, String lieuArrivee, Date date) {
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
    }

    public Alerte(String lieuDepart, String lieuArrivee, Date date, int heure, User creator) {
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
        this.heure = heure;
        this.creator = creator;
    }

    public Alerte(String lieuDepart, String lieuArrivee, Date date, int heure) {
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
        this.heure = heure;
    }



    public Alerte(int id, String lieuDepart, String lieuArrivee, Date date, int heure, User creator) {
        this.id = id;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
        this.heure = heure;
        this.creator = creator;
    }

    public Alerte(int id, String lieuDepart, String lieuArrivee, Date date, int heure) {
        this.id = id;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.date = date;
        this.heure = heure;
    }
    



    public User getCreator() {
        return creator;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Alerte other = (Alerte) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Alerte{" + "id=" + id + ", lieuDepart=" + lieuDepart + ", lieuArrivee=" + lieuArrivee + ", date=" + date + ", heure=" + heure + ", creator=" + creator + '}';
    }

 
    

    
    
      
  
}

