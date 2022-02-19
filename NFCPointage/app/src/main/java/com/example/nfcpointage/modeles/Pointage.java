package com.example.nfcpointage.modeles;

import java.util.Date;

public class Pointage {
    private String _id;
    private String idemploye;
    private String employe;
    private Date datearrivee;
    private Date datedepart;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(String idemploye) {
        this.idemploye = idemploye;
    }

    public String getEmploye() {
        return employe;
    }

    public void setEmploye(String employe) {
        this.employe = employe;
    }

    public Date getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(Date datearrivee) {
        this.datearrivee = datearrivee;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }
}
