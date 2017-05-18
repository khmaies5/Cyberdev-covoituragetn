/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.models;

import java.util.Date;

/**
 *
 * @author Nacef Fethi
 */
public class PublicationFavorite {
      private int id;
  private Date dateEnregistrement;
     private User creator;
      private Annonce acreator;

    public PublicationFavorite(int id) {
        this.id = id;
    }

    public PublicationFavorite() {
    }

    public PublicationFavorite(int id, Date dateEnregistrement, Annonce acreator) {
        this.id = id;
        this.dateEnregistrement = dateEnregistrement;
        this.acreator = acreator;
    }



    public PublicationFavorite(int id, User creator, Annonce acreator) {
        this.id = id;
        this.creator = creator;
        this.acreator = acreator;
    }

    public PublicationFavorite(Date date_enregistrement, Annonce acreator) {
        this.dateEnregistrement = date_enregistrement;
        this.acreator = acreator;
    }

    public PublicationFavorite(int id, Date date_enregistrement, User creator, Annonce acreator) {
        this.id = id;
        this.dateEnregistrement = date_enregistrement;
        this.creator = creator;
        this.acreator = acreator;
    }

    public PublicationFavorite(Date date_enregistrement, User creator, Annonce acreator) {
        this.dateEnregistrement = date_enregistrement;
        this.creator = creator;
        this.acreator = acreator;
    }
    

    public PublicationFavorite(User creator, Annonce acreator) {
        this.creator = creator;
        this.acreator = acreator;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Annonce getAcreator() {
        return acreator;
    }

    public void setAcreator(Annonce acreator) {
        this.acreator = acreator;
    }

    @Override
    public String toString() {
        return "PublicationFavorite{" + "id=" + id + ", date_enregistrement=" + dateEnregistrement + ", creator=" + creator + ", acreator=" + acreator + '}';
    }


 


    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        final PublicationFavorite other = (PublicationFavorite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
      
    
}
