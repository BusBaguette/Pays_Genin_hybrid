package com.hybrid.hybrid.domain.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Objet de transfert de données permettant un affichage des entités Voiture de la base de donnée
 */
public class VoitureDTO {
    private String id;
    private String marque;
    private String model;
    private float prix;
    private int nbChvThermique;
    private int nbChvElec;
    private int couple;
    private int puissanceElectrique;
    private int autonomie;
    private int vitesseMax;
    private List<String> equipementsAutoDTOS;

    public VoitureDTO(String id, String marque, String model, float prix, int nbChvThermique, int nbChvElec, int couple, int puissanceElectrique, int autonomie, int vitesseMax, List<String> equipementsAutoDTOS) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.prix = prix;
        this.nbChvThermique = nbChvThermique;
        this.nbChvElec = nbChvElec;
        this.couple = couple;
        this.puissanceElectrique = puissanceElectrique;
        this.autonomie = autonomie;
        this.vitesseMax = vitesseMax;
        this.equipementsAutoDTOS = equipementsAutoDTOS;
    }

    public VoitureDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }

    public List<String> getEquipementsAutoDTOS() {
        return equipementsAutoDTOS;
    }

    public void setEquipementsAutoDTOS(List<String> equipementsAutoDTOS) {
        this.equipementsAutoDTOS = equipementsAutoDTOS;
    }
}
