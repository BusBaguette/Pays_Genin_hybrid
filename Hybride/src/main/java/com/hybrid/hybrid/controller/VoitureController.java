package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.controller.exceptions.Error;
import com.hybrid.hybrid.controller.verify.Verify;
import com.hybrid.hybrid.domain.Concession;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.ConcessionDTO;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import com.hybrid.hybrid.repository.ConcessionRepositoryInterface;
import com.hybrid.hybrid.repository.EquipementAutoRepositoryInterface;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private EquipementAutoRepositoryInterface repositoryEquipement;

    @Autowired
    private ConcessionRepositoryInterface repositoryConcession;

    private Mapper mapper;


    public VoitureController(VoitureRepositoryInterface repository, Mapper mapper, EquipementAutoRepositoryInterface equipementAutoRepositoryInterface, ConcessionRepositoryInterface concessionRepositoryInterface) {
        this.repository = repository;
        this.mapper = mapper;
        this.repositoryEquipement = equipementAutoRepositoryInterface;
        this.repositoryConcession = concessionRepositoryInterface;
    }

    @GetMapping("")
    public List<VoitureDTO> getListVoitures() {
        return repository.findAll().stream()
                .map(mapper::toVoitureDTO)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public VoitureDTO getVoiture(@PathVariable ObjectId id) {
        return mapper.toVoitureDTO(repository.findVoitureById(id));
    }

    @GetMapping("/countModelByMarque/{marque}")
    public List<Object> getCountModelByMarque(@PathVariable String marque) {
        return repository.countModelByMarque(marque.toUpperCase()).getMappedResults();
    }

    @GetMapping("/groupByModel/{marque}")
    public List<Voiture> getVoituregroupByModel(@PathVariable String marque) {
        return repository.groupByModel(marque.toUpperCase()).getMappedResults();
    }

    @PostMapping("/add_voiture")
    public VoitureDTO insert(@RequestBody VoitureDTO voitureDTO) {
        Voiture voiture = mapper.dtoToVoiture(voitureDTO);
        if (voiture == null) {
            throw new Error("Must be not null", HttpStatus.BAD_REQUEST);
        }
        voiture.setMarque(voiture.getMarque().toUpperCase());
        Verify.verifyVoiture(voiture, repositoryEquipement);
        return mapper.toVoitureDTO(repository.save(voiture));
    }


    @PutMapping("/{id}")
    public VoitureDTO putVoiture(@PathVariable("id") ObjectId id, @RequestBody VoitureDTO voitureDTO){
        if (voitureDTO == null){
            throw new Error("Voiture must be not null !", HttpStatus.BAD_REQUEST);
        }
        Voiture v = repository.findVoitureById(id);
        VoitureDTO updateVoitureDTO = mapper.toVoitureDTO(v);

        if(voitureDTO.getPrix() != 0.0f){
            updateVoitureDTO.setPrix(voitureDTO.getPrix());
        }

        if(voitureDTO.getNbChvThermique() != 0){
            updateVoitureDTO.setNbChvThermique(voitureDTO.getNbChvThermique());
        }

        if(voitureDTO.getNbChvElec() != 0){
            updateVoitureDTO.setNbChvElec(voitureDTO.getNbChvElec());
        }

        if(voitureDTO.getCouple() != 0){
            updateVoitureDTO.setCouple(voitureDTO.getCouple());
        }

        if(voitureDTO.getPuissanceElectrique() != 0){
            updateVoitureDTO.setPuissanceElectrique(voitureDTO.getPuissanceElectrique());
        }

        if(voitureDTO.getAutonomie() != 0){
            updateVoitureDTO.setAutonomie(voitureDTO.getAutonomie());
        }

        if(voitureDTO.getPrix() != 0.0f){
            updateVoitureDTO.setPrix(voitureDTO.getPrix());
        }

        if(voitureDTO.getVitesseMax() != 0){
            updateVoitureDTO.setVitesseMax(voitureDTO.getVitesseMax());
        }

        repository.save(mapper.dtoToVoiture(updateVoitureDTO));

        return updateVoitureDTO;
    }

    @DeleteMapping("/{id}")
    public VoitureDTO deleteVoiture(@PathVariable("id") ObjectId id){
        List<Concession> concessions = repositoryConcession.findAll();
        for (Concession c : concessions) {
            if(c.getVoitures().contains(id)){
                c.getVoitures().remove(id);
                repositoryConcession.save(c);
            }
        }
        return mapper.toVoitureDTO(repository.deleteVoitureById(id));
    }

}
