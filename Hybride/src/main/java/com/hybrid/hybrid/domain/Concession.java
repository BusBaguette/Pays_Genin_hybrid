package com.hybrid.hybrid.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@CompoundIndexes({@CompoundIndex(name = "nom_ville", def = "{'nom' : 1, 'ville' : 1}")})
@Document(collection = "Concession")
public class Concession {
    @Id
    private ObjectId id;
    @Field(value = "nom")
    private String nom;
    @Field(value = "ville")
    private String ville;
    @Field(value = "voitures")
    private List<Voiture> voitures;

    public Concession(ObjectId id, String nom, String ville, List<Voiture> voitures) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.voitures = voitures;
    }

    public Concession() {
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }
}
