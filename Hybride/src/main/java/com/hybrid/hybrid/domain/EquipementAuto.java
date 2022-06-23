package com.hybrid.hybrid.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entité EquipementAuto sauvegardé en base de donnée
 */
@CompoundIndexes({@CompoundIndex(name = "nom", def = "{'nom' : 1}")})
@Document(collection = "EquipementAuto")
public class EquipementAuto {
    @Id
    private ObjectId id;

    @Field(value = "nom")
    private String nom;
    @Field(value = "prix")
    private Float prix;

    public EquipementAuto(String nom, Float prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public EquipementAuto() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}