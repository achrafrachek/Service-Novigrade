package com.example.servicenovigrad;

public class SuccursaleModel {
    private String zone,adresse,horaire,service,rating;

    public SuccursaleModel(String zone, String adresse, String horaire, String service, String rating) {
        this.zone = zone;
        this.adresse = adresse;
        this.horaire = horaire;
        this.service = service;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

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
