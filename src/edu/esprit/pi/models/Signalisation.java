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
public class Signalisation {
    private int idSignalisation;
    private int signalisation;
    private User idUser;
    private Annonce idAnnonce;
    private Annonce idUserSignaler;
    private Commentaire idCommentaire;

    public Signalisation(int signalisation, User idUser, Annonce idAnnonce, Annonce idUserSignaler, Commentaire idCommentaire) {
        this.signalisation = signalisation;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
        this.idUserSignaler = idUserSignaler;
        this.idCommentaire = idCommentaire;
    }

    public Signalisation(int idSignalisation, int signalisation, User idUser, Annonce idAnnonce, Annonce idUserSignaler, Commentaire idCommentaire) {
        this.idSignalisation = idSignalisation;
        this.signalisation = signalisation;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
        this.idUserSignaler = idUserSignaler;
        this.idCommentaire = idCommentaire;
    }

    public int getIdSignalisation() {
        return idSignalisation;
    }

    public void setIdSignalisation(int idSignalisation) {
        this.idSignalisation = idSignalisation;
    }

    public int getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(int signalisation) {
        this.signalisation = signalisation;
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

    public Annonce getIdUserSignaler() {
        return idUserSignaler;
    }

    public void setIdUserSignaler(Annonce idUserSignaler) {
        this.idUserSignaler = idUserSignaler;
    }

    public Commentaire getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Commentaire idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idSignalisation;
        hash = 79 * hash + this.signalisation;
        hash = 79 * hash + Objects.hashCode(this.idUser);
        hash = 79 * hash + Objects.hashCode(this.idAnnonce);
        hash = 79 * hash + Objects.hashCode(this.idUserSignaler);
        hash = 79 * hash + Objects.hashCode(this.idCommentaire);
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
        final Signalisation other = (Signalisation) obj;
        if (this.idSignalisation != other.idSignalisation) {
            return false;
        }
        if (this.signalisation != other.signalisation) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.idAnnonce, other.idAnnonce)) {
            return false;
        }
        if (!Objects.equals(this.idUserSignaler, other.idUserSignaler)) {
            return false;
        }
        if (!Objects.equals(this.idCommentaire, other.idCommentaire)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Signalisation{" + "idSignalisation=" + idSignalisation + ", signalisation=" + signalisation + ", idUser=" + idUser + ", idAnnonce=" + idAnnonce + ", idUserSignaler=" + idUserSignaler + ", IdCommentaire=" + idCommentaire + '}';
    }
    
    
    
}
