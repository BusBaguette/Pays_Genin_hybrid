package com.hybrid.hybrid.domain;

import java.util.UUID;

public class Voiture {
    private UUID uuid;
    private String marque;
    private String model;
    private float prix;
    private int nbChvThermique;
    private int nbChvElec;
    private int couple;
    private int puissanceElectrique;
    private int autonomie;
    private int vistessMax;

    public Voiture(UUID uuid, String marque, String model, float prix, int nbChvThermique, int nbChvElec, int couple, int puissanceElectrique, int autonomie, int vistessMax) {
        this.uuid = uuid;
        this.marque = marque;
        this.model = model;
        this.prix = prix;
        this.nbChvThermique = nbChvThermique;
        this.nbChvElec = nbChvElec;
        this.couple = couple;
        this.puissanceElectrique = puissanceElectrique;
        this.autonomie = autonomie;
        this.vistessMax = vistessMax;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbChvThermique() {
        return nbChvThermique;
    }

    public void setNbChvThermique(int nbChvThermique) {
        this.nbChvThermique = nbChvThermique;
    }

    public int getNbChvElec() {
        return nbChvElec;
    }

    public void setNbChvElec(int nbChvElec) {
        this.nbChvElec = nbChvElec;
    }

    public int getCouple() {
        return couple;
    }

    public void setCouple(int couple) {
        this.couple = couple;
    }

    public int getPuissanceElectrique() {
        return puissanceElectrique;
    }

    public void setPuissanceElectrique(int puissanceElectrique) {
        this.puissanceElectrique = puissanceElectrique;
    }

    public int getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(int autonomie) {
        this.autonomie = autonomie;
    }

    public int getVistessMax() {
        return vistessMax;
    }

    public void setVistessMax(int vistessMax) {
        this.vistessMax = vistessMax;
    }
}
