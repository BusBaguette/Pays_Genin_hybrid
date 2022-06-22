package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.Concession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConcessionRepositoryInterface extends MongoRepository<Concession, String> {

    Concession findConcessionById(ObjectId id);

    Concession deleteConcessionById(ObjectId id);

    List<Concession> findAll();
}
