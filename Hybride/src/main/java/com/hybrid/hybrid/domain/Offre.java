package com.hybrid.hybrid.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@CompoundIndexes({@CompoundIndex(name = "nom", def = "{'nom' : 1}")})
@Document(collection = "Offre")
public class Offre {
    @Id
    private ObjectId id;

    @Field(value = "nom")
    private String nom;


    public Offre(String nom) {
        this.nom = nom;
    }

    public Offre() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}