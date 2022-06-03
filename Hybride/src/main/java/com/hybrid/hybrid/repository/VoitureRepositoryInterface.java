package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.Voiture;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface VoitureRepositoryInterface extends MongoRepository<Voiture, String> {

    Voiture findVoitureById(ObjectId id);
    List<Voiture> findAll();
}
