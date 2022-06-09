package com.hybrid.hybrid.domain.dto;

public class EquipementAutoDTO {

    private String id;
    private String nom;
    private Float prix;

    public EquipementAutoDTO(String id, String nom, Float prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
