package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Les Controller vont vous permettre une exposition rapide vos {@link org.springframework.data.mongodb.repository.MongoRepository}
 * et une mise en situation de leurs cas d'usages.
 */
@RestController
@RequestMapping("/voiture")
public class VoitureController {

    @Autowired
    private VoitureRepositoryInterface repository;


    /**
     * Spring fonctionne avec de l'injection de dépendances, pas d'annotation à rajouter dans les controller,
     * pas de new, il va s'en charger pour vous grâce à l'annotation présente sur cette classe.
     */
    public VoitureController(VoitureRepositoryInterface repository) { // Il vous faut une implem à vous pour que ça compile
        this.repository = repository;
    }

    @GetMapping("")
    public List<Voiture> getListVoitures()
    {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Voiture getVoiture(@RequestParam ObjectId id) {
        return repository.findVoitureById(id);
    }

    @PostMapping("/add_entity")
    public Object insert(@RequestBody Voiture voiture) {
        if (voiture == null) {
            throw new CustomException("Must be not null");
        }
        return repository.save(voiture);
    }

}
