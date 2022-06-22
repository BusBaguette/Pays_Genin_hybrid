package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.Voiture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface VoitureRepositoryInterface extends MongoRepository<Voiture, String> {

    /**
     * Methode permettant la récupération d'une voiture par son ObjectId
     * @param id Id de la voiture à recupérer
     * @return une voiture de type Voiture
     */
    Voiture findVoitureById(ObjectId id);

    /**
     * Methode permettant la suppression d'une voiture par son ObjectId
     * @param id Id de la voiture a supprimé
     * @return La voiture supprimée
     */
    Voiture deleteVoitureById(ObjectId id);

    /**
     * Methode permettant de récupérer toutes les voiture de la BD
     * @return List de voiture de type Voiture
     */
    List<Voiture> findAll();

    /**
     * Methode qui le nombre de model de voiture pour une marque donné
     * @param marque Marque dont on veut savoir le nombre de modèle
     * @return Une liste d'object du résultat de cette requête
     */
    @Aggregation({"{'$match':{'marque': ?0}}", "{$count: 'model'}","{$project : { model : 1}}"})
    AggregationResults<Object> countModelByMarque(String marque);

    /**
     * Compte le nombre de model par marque
     * @return retourne une liste d'objet du résultat de la requête
     */
    @Aggregation({"{$group : { _id: '$marque', count: {$sum: 1}}}"})
    AggregationResults<Object> countNbModelPerMarque();
}
