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
 * @author amrouche
 */
public class Reservation {
    private int id ;
    private Date date_reservation ;
    private String etat_reservation ;
    private double montant ;
    private String Type_payement;
    private int id_user ;

    public Reservation() {
    }

    public Reservation(Date date_reservation, String etat_reservation, double montant, String Type_payement, int id_user) {
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.Type_payement = Type_payement;
        this.id_user = id_user;
    }

    public Reservation(int id, Date date_reservation, String etat_reservation, double montant, String Type_payement, int id_user) {
        this.id = id;
        this.date_reservation = date_reservation;
        this.etat_reservation = etat_reservation;
        this.montant = montant;
        this.Type_payement = Type_payement;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public String getEtat_reservation() {
        return etat_reservation;
    }

    public double getMontant() {
        return montant;
    }

    public String getType_payement() {
        return Type_payement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public void setEtat_reservation(String etat_reservation) {
        this.etat_reservation = etat_reservation;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setType_payement(String Type_payement) {
        this.Type_payement = Type_payement;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.date_reservation);
        hash = 79 * hash + Objects.hashCode(this.etat_reservation);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.montant) ^ (Double.doubleToLongBits(this.montant) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.Type_payement);
        hash = 79 * hash + this.id_user;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.montant) != Double.doubleToLongBits(other.montant)) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.etat_reservation, other.etat_reservation)) {
            return false;
        }
        if (!Objects.equals(this.Type_payement, other.Type_payement)) {
            return false;
        }
        if (!Objects.equals(this.date_reservation, other.date_reservation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_reservation=" + date_reservation + ", etat_reservation=" + etat_reservation + ", montant=" + montant + ", Type_payement=" + Type_payement + ", id_user=" + id_user + '}';
    }
    
    
}