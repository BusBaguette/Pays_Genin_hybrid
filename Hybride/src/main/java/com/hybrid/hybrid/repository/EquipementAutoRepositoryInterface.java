package com.hybrid.hybrid.repository;

import com.hybrid.hybrid.domain.EquipementAuto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface permettant des requêtes sur les EquipementAuto en BD
 */
public interface EquipementAutoRepositoryInterface extends MongoRepository<EquipementAuto, String> {

    /**
     * Méthode permettant de récupérer un equipement par son ObjectId en BD
     * @param id
     * @return L'équipement auto associer
     */
    EquipementAuto findEquipementById(ObjectId id);

    /**
     * Méthode qui supprime un équipement auto en passant l'id a supprimer
     * @param id L'id de l'équipement auto a supprimé
     * @return Renvoie l'equipement auto qui a été supprimé
     */
    EquipementAuto deleteEquipementAutoById(ObjectId id);

    /**
     * Methode qui récupère tous les équipements auto
     * @return Liste de tous les équipements auto
     */
    List<EquipementAuto> findAll();
}
