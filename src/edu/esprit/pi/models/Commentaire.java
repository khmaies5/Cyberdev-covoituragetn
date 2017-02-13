/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.models;

import java.util.Objects;

/**
 *
 * @author Momo
 */
public class Commentaire {
    private int idCommentaire;
    private String description;
    private Annonce idAnnonce;
    private User idUser;

    public Commentaire(int idCommentaire, String description, Annonce idAnnonce, User idUser) {
        this.idCommentaire = idCommentaire;
        this.description = description;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
    }

    public Commentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    
    public Commentaire(String description, Annonce idAnnonce, User idUser) {
        this.description = description;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idCommentaire;
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.idAnnonce);
        hash = 17 * hash + Objects.hashCode(this.idUser);
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
        final Commentaire other = (Commentaire) obj;
        if (this.idCommentaire != other.idCommentaire) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idAnnonce, other.idAnnonce)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", description=" + description + ", idAnnonce=" + idAnnonce + ", idUser=" + idUser + '}';
    }
    
    
    
    
}
