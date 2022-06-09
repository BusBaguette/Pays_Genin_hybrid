package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Les Controller vont vous permettre une exposition rapide vos {@link org.springframework.data.mongodb.repository.MongoRepository}
 * et une mise en situation de leurs cas d'usages.
 */
@RestController
@RequestMapping("/voiture")
public class VoitureController {

    @Autowired
    private VoitureRepositoryInterface repository;

    private Mapper mapper;


    public VoitureController(VoitureRepositoryInterface repository, Mapper mapper) { // Il vous faut une implem à vous pour que ça compile
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<VoitureDTO> getListVoitures() {
        return repository.findAll().stream()
                .map(mapper::toVoitureDto)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public Voiture getVoiture(@PathVariable ObjectId id) {
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
