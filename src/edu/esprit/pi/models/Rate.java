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
public class Rate {
    private int id;
    private int nbrVote;
    private User idUser;
    private Annonce idAnnonce;

    public Rate(int nbrVote, User idUser, Annonce idAnnonce) {
        this.nbrVote = nbrVote;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
    }

    public Rate(int id, int nbrVote, User idUser, Annonce idAnnonce) {
        this.id = id;
        this.nbrVote = nbrVote;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrVote() {
        return nbrVote;
    }

    public void setNbrVote(int nbrVote) {
        this.nbrVote = nbrVote;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Annonce getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Annonce idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.nbrVote;
        hash = 79 * hash + Objects.hashCode(this.idUser);
        hash = 79 * hash + Objects.hashCode(this.idAnnonce);
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
        final Rate other = (Rate) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbrVote != other.nbrVote) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.idAnnonce, other.idAnnonce)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rate{" + "id=" + id + ", nbrVote=" + nbrVote + ", idUser=" + idUser + ", idAnnonce=" + idAnnonce + '}';
    }
    
    
    
}
