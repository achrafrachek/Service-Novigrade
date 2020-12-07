package com.example.servicenovigrad;

public class SuccursaleModel {
    private String zone,adresse,horaire,service;

    public SuccursaleModel() {
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public SuccursaleModel(String zone, String adresse, String horaire, String service) {
        this.zone = zone;
        this.adresse = adresse;
        this.horaire = horaire;
        this.service = service;
    }
}
