package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.Concession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConcessionRepositoryInterface extends MongoRepository<Concession, String> {

    /**
     * Méthode permettant de récupérer une concession par son ObjectId en BD
     * @param id Id de la concession a récupérer
     * @return La concession récupérée
     */
    Concession findConcessionById(ObjectId id);

    /**
     * Méthode qui supprime une concession en passant l'id a supprimer
     * @param id L'id de la concession a supprimé
     * @return Renvoie la concession qui a été supprimé
     */
    Concession deleteConcessionById(ObjectId id);

    /**
     * Methode qui récupère toutes les concessions
     * @return Liste de tous les équipements auto
     */
    List<Concession> findAll();
}
