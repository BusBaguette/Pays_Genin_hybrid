package com.hybrid.hybrid.domain.dto;

import com.hybrid.hybrid.domain.Voiture;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Objet de transfert de données permettant un affichage des entités Concession de la base de donnée
 */
public class ConcessionDTO {

    private String id;

    private String nom;

    private String ville;

    private List<String> voituresDTO;

    public ConcessionDTO(String id, String nom, String ville, List<String> voituresDTO) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.voituresDTO = voituresDTO;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<String> getVoituresDTO() {
        return voituresDTO;
    }

    public void setVoituresDTO(List<String> voituresDTO) {
        this.voituresDTO = voituresDTO;
    }
}
