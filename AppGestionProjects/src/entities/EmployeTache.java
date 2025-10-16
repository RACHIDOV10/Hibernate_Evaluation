/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Admin
 */
@Entity
public class EmployeTache {
    @EmbeddedId
    private EmployeTachepK pK;
    
    
    @ManyToOne
    @JoinColumn(name="tache", insertable = false, updatable = false)
    private Tache tache;
    @ManyToOne
    @JoinColumn(name="employe", insertable = false, updatable = false)
    private Employe employe;
    
    private  Date datDebutReelle;
    
    private  Date datFinReelle;

    public EmployeTache() {
    }

    public EmployeTache(Employe employe, Tache tache, Date datDebutReelle, Date datFinReelle) {
        this.pK = new EmployeTachepK(tache.getId(),employe.getId());
        this.employe = employe;
        this.tache = tache;
        this.datDebutReelle = datDebutReelle;
        this.datFinReelle = datFinReelle;
    }
    
    
    


    public EmployeTachepK getpK() {
        return pK;
    }

    public void setpK(EmployeTachepK pK) {
        this.pK = pK;
    }

    public Date getDatDebutReelle() {
        return datDebutReelle;
    }

    public void setDatDebutReelle(Date datDebutReelle) {
        this.datDebutReelle = datDebutReelle;
    }

    public Date getDatFinReelle() {
        return datFinReelle;
    }

    public void setDatFinReelle(Date datFinReelle) {
        this.datFinReelle = datFinReelle;
    }

    @Override
    public String toString() {
        return "EmployeTache{" + "pK=" + pK + ", tache=" + tache + ", employe=" + employe + '}';
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    
}
