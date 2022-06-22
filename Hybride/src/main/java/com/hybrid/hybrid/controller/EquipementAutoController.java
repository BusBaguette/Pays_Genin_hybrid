package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.controller.exceptions.Error;
import com.hybrid.hybrid.controller.verify.Verify;
import com.hybrid.hybrid.domain.EquipementAuto;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.EquipementAutoDTO;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import com.hybrid.hybrid.repository.EquipementAutoRepositoryInterface;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/equipement")
public class EquipementAutoController {

    @Autowired
    private EquipementAutoRepositoryInterface repository;

    @Autowired
    private VoitureRepositoryInterface repositoryvoiture;

    private Mapper mapper;

    public EquipementAutoController(EquipementAutoRepositoryInterface repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<EquipementAutoDTO> getListEquipements() {
        return repository.findAll().stream()
                .map(mapper::toEquipementAutoDTO)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public EquipementAuto getEquipementAuto(@PathVariable ObjectId id) {
        return repository.findEquipementById(id);
    }

    @PostMapping("/add_equipement")
    public EquipementAutoDTO insert(@RequestBody EquipementAutoDTO equipementAutoDTO) {
        EquipementAuto equipementAuto = mapper.dtoToEquipementAuto(equipementAutoDTO);
        if (equipementAuto == null) {
            throw new Error("Must be not null", HttpStatus.BAD_REQUEST);
        }
        Verify.verifyEquipement(equipementAuto);
        return mapper.toEquipementAutoDTO(repository.save(equipementAuto));
    }

    @PutMapping("/{id}")
    public EquipementAutoDTO putEquipement(@PathVariable ObjectId id, @RequestBody EquipementAutoDTO equipementautoDTO) {
        if(equipementautoDTO == null){
            throw new Error("Equipement Auto must be not null", HttpStatus.BAD_REQUEST);
        }

        EquipementAuto equipementauto = getEquipementAuto(id);

        EquipementAutoDTO updateEquipementAuto = mapper.toEquipementAutoDTO(equipementauto);

        if(equipementautoDTO.getNom() != null){
            updateEquipementAuto.setNom(equipementautoDTO.getNom());
        }

        if(equipementautoDTO.getPrix() != null){
            updateEquipementAuto.setPrix(equipementautoDTO.getPrix());
        }

        repository.save(mapper.dtoToEquipementAuto(updateEquipementAuto));

        return updateEquipementAuto;
    }

    @DeleteMapping("/{id}")
    public EquipementAutoDTO deleteEquipement(@PathVariable("id") ObjectId id){
        List<Voiture> voitures = repositoryvoiture.findAll();
        for (Voiture v : voitures) {
            if(v.getEquipementsAuto().contains(id)){
                v.getEquipementsAuto().remove(id);
                repositoryvoiture.save(v);
            }
        }
        return mapper.toEquipementAutoDTO(repository.deleteEquipementAutoById(id));
    }

}
