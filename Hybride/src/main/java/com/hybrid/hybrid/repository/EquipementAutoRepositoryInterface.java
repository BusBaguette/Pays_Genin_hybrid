package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.EquipementAuto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipementAutoRepositoryInterface extends MongoRepository<EquipementAuto, String> {

    EquipementAuto findEquipementById(ObjectId id);
    List<EquipementAuto> findAll();
}
