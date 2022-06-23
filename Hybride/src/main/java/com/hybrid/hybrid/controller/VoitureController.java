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
 * Le Controller Voiture va nous permettre une exposition rapide de nos Endpoints qui concerne les voitures
 */
@RestController
@RequestMapping("/voiture")
public class VoitureController {

    private final VoitureRepositoryInterface repository;

    private final EquipementAutoRepositoryInterface repositoryEquipement;

    private final ConcessionRepositoryInterface repositoryConcession;

    private Mapper mapper;


    public VoitureController(VoitureRepositoryInterface repository, Mapper mapper, EquipementAutoRepositoryInterface equipementAutoRepositoryInterface, ConcessionRepositoryInterface concessionRepositoryInterface) {
        this.repository = repository;
        this.mapper = mapper;
        this.repositoryEquipement = equipementAutoRepositoryInterface;
        this.repositoryConcession = concessionRepositoryInterface;
    }

    /**
     * Méthode GET qui renvoie la liste de toutes les voitures
     * @return Liste de toutes les voitures
     */
    @GetMapping("")
    public List<VoitureDTO> getListVoitures() {
        return repository.findAll().stream()
                .map(mapper::toVoitureDTO)
                .collect(toList());
    }

    /**
     * Méthode GET qui renvoie la voiture dont l'ID est passé en paramètre
     * @param id Id de la voiture a récupérer
     * @return La voiture dont son Id a été passé en paramètre
     */
    @GetMapping("/{id}")
    public VoitureDTO getVoiture(@PathVariable ObjectId id) {
        return mapper.toVoitureDTO(repository.findVoitureById(id));
    }

    /**
     * Méthode GET qui renvoie le nombre de modèle de la marque passée en paramètre (Pipeline d'agrégation)
     * @param marque Marque dont on veut connaitre le nombre de modèle
     * @return Nombre de modèle de la marque passé en paramètre
     */
    @GetMapping("/countModelByMarque/{marque}")
    public List<Object> getCountModelByMarque(@PathVariable String marque) {
        return repository.countModelByMarque(marque.toUpperCase()).getMappedResults();
    }

    /**
     * Méthode GET qui renvoie le nombre de modèle de toutes les marques (Pipeline d'agrégation)
     * @return Nombre de modèle par marque
     */
    @GetMapping("/nbModelPerMarque")
    public List<Object> getNbModelPerMarque() {
        return repository.countNbModelPerMarque().getMappedResults();
    }

    /**
     * Méthode POST qui permet d'ajouter une voitureDTO
     * @param voitureDTO VoitureDTO a ajouté
     * @return VoitureDTO qui a été ajoutée
     */
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

    /**
     * Méthode PUT qui permet de modifier une voiture
     * @param id Id de la voiture a modifier
     * @param voitureDTO VoitureDTO avec les nouvelles informations
     * @return VoitureDTO modifiée
     */
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

    /**
     * Méthode DELETE qui permet de supprimer une Voiture en passant son id et la supprimer des concessions
     * @param id ID de la voiture a supprimer
     * @return Renvoie la VoitureDTO supprimée
     */
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
