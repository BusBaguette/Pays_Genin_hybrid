package com.hybrid.hybrid.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

/**
 * Entité Voiture sauvegardé en base de donnée
 */
@CompoundIndexes({@CompoundIndex(name = "marque_modele", def = "{'marque' : 1, 'model' : 1}")})
@Document(collection = "Voiture")
public class Voiture {
    @Id
    private ObjectId id;

    @Field(value = "marque")
    private String marque;
    @Field(value = "model")
    private String model;
    @Field(value = "prix")
    private float prix;
    @Field(value = "chevauxThermique")
    private int nbChvThermique;
    @Field(value = "chevauxElectrique")
    private int nbChvElec;
    @Field(value = "couple")
    private int couple;
    @Field(value = "puissanceElectrique")
    private int puissanceElectrique;
    @Field(value = "autonomie")
    private int autonomie;
    @Field(value = "vitesseMax")
    private int vitesseMax;
    @Field(value = "equipementsAuto")
    private List<ObjectId> equipementsAuto;

    public Voiture(String marque, String model, float prix, int nbChvThermique, int nbChvElec, int couple, int puissanceElectrique, int autonomie, int vitesseMax, List<ObjectId> equipementsAuto) {
        this.marque = marque;
        this.model = model;
        this.prix = prix;
        this.nbChvThermique = nbChvThermique;
        this.nbChvElec = nbChvElec;
        this.couple = couple;
        this.puissanceElectrique = puissanceElectrique;
        this.autonomie = autonomie;
        this.vitesseMax = vitesseMax;
        this.equipementsAuto = equipementsAuto;
    }

    public Voiture() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public List<ObjectId> getEquipementsAuto() {
        return equipementsAuto;
    }

    public void setEquipementsAuto(List<ObjectId> equipementsAuto) {
        this.equipementsAuto = equipementsAuto;
    }
}
