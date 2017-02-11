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
public class Groupe {
    private int id;
    private String nom;
     private String description;
     private Date date_creation;
     private List<Sujet> sujets;

    public Groupe(int id, String nom, String description, Date date_creation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }

    public Groupe(int id) {
        this.id = id;
    }

    public Groupe(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Groupe(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Groupe(String nom, String description, Date date_creation) {
        this.nom = nom;
        this.description = description;
        this.date_creation = date_creation;
    }
  

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public List<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_creation=" + date_creation + ", sujets=" + sujets + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Groupe other = (Groupe) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  

    
   

}
