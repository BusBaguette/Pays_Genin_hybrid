package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.Voiture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface VoitureRepositoryInterface extends MongoRepository<Voiture, String> {

    Voiture findVoitureById(ObjectId id);

    Voiture deleteVoitureById(ObjectId id);
    List<Voiture> findAll();

    @Aggregation({"{'$match':{'marque': ?0}}", "{$count: 'model'}","{$project : { model : 1}}"})
    AggregationResults<Object> countModelByMarque(String marque);

    /*
        Pipeline d'agrégation qui groupe les Voitures par modèle
     */
    @Aggregation({"{$match : {'marque': ?0}}", "{$group : {'model' : 'model'}}"})
    AggregationResults<Voiture> groupByModel(String marque);
}
