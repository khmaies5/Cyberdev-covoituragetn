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
    private Date dateReponse;
    private String reponseSujet;
    private Sujet sujet;
    private Abonnes creator;
private int etatSupp;

    public Reponse(int id, Date dateReponse, String reponseSujet, Sujet sujet, Abonnes creator, int etatSupp) {
        this.id = id;
        this.dateReponse = dateReponse;
        this.reponseSujet = reponseSujet;
        this.sujet = sujet;
        this.creator = creator;
        this.etatSupp = etatSupp;
    }

    public Reponse(int id, Date dateReponse, String reponseSujet, Sujet sujet, Abonnes creator) {
        this.id = id;
        this.dateReponse = dateReponse;
        this.reponseSujet = reponseSujet;
        this.sujet = sujet;
        this.creator = creator;
    }

    public Reponse(int id, String reponseSujet) {
        this.id = id;
        this.reponseSujet = reponseSujet;
    }

    public Reponse(String reponseSujet, Sujet sujet, Abonnes creator) {
        this.reponseSujet = reponseSujet;
        this.sujet = sujet;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getReponseSujet() {
        return reponseSujet;
    }

    public void setReponseSujet(String reponseSujet) {
        this.reponseSujet = reponseSujet;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public Abonnes getCreator() {
        return creator;
    }

    public void setCreator(Abonnes creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Reponse other = (Reponse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", dateReponse=" + dateReponse + ", reponseSujet=" + reponseSujet + ", sujet=" + sujet + ", creator=" + creator + '}';
    }
    

}
