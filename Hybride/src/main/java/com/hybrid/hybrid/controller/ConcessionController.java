package com.hybrid.hybrid.controller;

import com.hybrid.hybrid.Mapper;
import com.hybrid.hybrid.controller.exceptions.Error;
import com.hybrid.hybrid.controller.verify.Verify;
import com.hybrid.hybrid.domain.Concession;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.ConcessionDTO;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import com.hybrid.hybrid.repository.ConcessionRepositoryInterface;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/concession")
public class ConcessionController {

    @Autowired
    private ConcessionRepositoryInterface repository;

    @Autowired
    private VoitureRepositoryInterface voitureRepository;

    private Mapper mapper;

    public ConcessionController(ConcessionRepositoryInterface repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<ConcessionDTO> getListConcession() {
        return repository.findAll().stream()
                .map(mapper::toConcessionDTO)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public Concession getConcession(@PathVariable ObjectId id){
        if(id == null){
            throw new Error("Id is required !", HttpStatus.BAD_REQUEST);
        }
        return repository.findConcessionById(id);
    }

    @PostMapping("/add_concession")
    public ConcessionDTO insert(@RequestBody ConcessionDTO concessionDTO) {
        Concession concession = mapper.dtoToConcession(concessionDTO);
        if (concession == null) {
            throw new Error("Must be not null", HttpStatus.BAD_REQUEST);
        }
        Verify.verifyConcession(concession, voitureRepository);
        return mapper.toConcessionDTO(repository.save(concession));
    }

    @PutMapping("/{id}")
    public ConcessionDTO putConcession(@PathVariable("id") ObjectId id, @RequestBody ConcessionDTO concessionDTO){
        if (concessionDTO == null){
            throw new Error("concessionDTO is null !", HttpStatus.BAD_REQUEST);
        }
        Concession c = repository.findConcessionById(id);

        ConcessionDTO updateconcessionDTO = mapper.toConcessionDTO(c);

        if(concessionDTO.getNom() != null){
            updateconcessionDTO.setNom(concessionDTO.getNom());
        }

        if(concessionDTO.getVille() != null){
            updateconcessionDTO.setVille(concessionDTO.getVille());
        }

        if(concessionDTO.getVoituresDTO() != null){
            updateconcessionDTO.setVoituresDTO(concessionDTO.getVoituresDTO());
        }

        repository.save(mapper.dtoToConcession(updateconcessionDTO));

        return updateconcessionDTO;
    }

    @DeleteMapping("/{id}")
    public ConcessionDTO deleteConcession(@PathVariable("id") ObjectId id){
        return mapper.toConcessionDTO(repository.deleteConcessionById(id));
    }
}
