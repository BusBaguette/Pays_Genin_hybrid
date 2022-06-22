package com.hybrid.hybrid;

import com.hybrid.hybrid.domain.Concession;
import com.hybrid.hybrid.domain.EquipementAuto;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.ConcessionDTO;
import com.hybrid.hybrid.domain.dto.EquipementAutoDTO;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {

    /**
     * Methode permettent la transformation d'une voiture en voiture DTO
     * @param voiture Voiture à convertir en VoitureDTO
     * @return Une voiture de type VoitureDTO
     */
    public VoitureDTO toVoitureDTO(Voiture voiture) {
        List<String> equipementsAutoDTO = voiture.getEquipementsAuto().stream()
                .map(ObjectId::toHexString)
                .collect(toList());
        return new VoitureDTO(voiture.getId().toHexString(), voiture.getMarque(),
                                voiture.getModel(), voiture.getPrix(), voiture.getNbChvThermique(),
                                voiture.getNbChvElec(), voiture.getCouple(), voiture.getPuissanceElectrique(),
                                voiture.getAutonomie(), voiture.getVitesseMax(), equipementsAutoDTO);
    }

    /**
     * Methode permettant la transformation d'une voitureDTO en voiture
     * @param voitureDTO VoitureDTO à convertir en Voiture
     * @return Une voiture de type Voiture
     */
    public Voiture dtoToVoiture(VoitureDTO voitureDTO) {
        List<ObjectId> equipementsAuto = null;
        if (voitureDTO.getEquipementsAutoDTOS() != null) {
            equipementsAuto = voitureDTO.getEquipementsAutoDTOS().stream()
                    .map(ObjectId::new)
                    .collect(toList());
        }

        return new Voiture(voitureDTO.getMarque(), voitureDTO.getModel(), voitureDTO.getPrix(), voitureDTO.getNbChvThermique(),
                voitureDTO.getNbChvElec(), voitureDTO.getCouple(), voitureDTO.getPuissanceElectrique(),
                voitureDTO.getAutonomie(), voitureDTO.getVitesseMax(), equipementsAuto);
    }

    /**
     * Methode permettant la transformation d'un EquipementAuto en EquipementAutoDTO
     * @param equipementAuto EquipementAuto à convertir en EquipementAutoDTO
     * @return Un equipement de EquipementAutoDTO
     */
    public EquipementAutoDTO toEquipementAutoDTO(EquipementAuto equipementAuto) {
        return new EquipementAutoDTO(equipementAuto.getId().toHexString(), equipementAuto.getNom(), equipementAuto.getPrix());
    }

    /**
     * Methode permettant la tranformation d'un EquipementAutoDTO en Equipement
     * @param equipementautoDTO EquipementAutoDTO a convertir en EquipementAuto
     * @return Un equipement de type Equipement
     */
    public EquipementAuto dtoToEquipementAuto(EquipementAutoDTO equipementautoDTO) {
        return new EquipementAuto(equipementautoDTO.getNom(), equipementautoDTO.getPrix());
    }

    /**
     * Methode permettant la tranformation d'une Concession en ConcessionDTO
     * @param concession Concession à convertir en ConcessionDTO
     * @return Une concession de type ConcessionDTO
     */
    public ConcessionDTO toConcessionDTO(Concession concession) {
        List<String> voituresDTO = concession.getVoitures().stream()
                .map(ObjectId::toHexString)
                .collect(toList());
        return new ConcessionDTO(concession.getId().toHexString(), concession.getNom(), concession.getVille(), voituresDTO);
    }

    /**
     * Methode permettant la tranformation d'une ConcessionDTO en Concession
     * @param concessionDTO ConcessionDTO à convertir en Concession
     * @return Une concession de type Concession
     */
    public Concession dtoToConcession(ConcessionDTO concessionDTO) {
        List<ObjectId> voitures = concessionDTO.getVoituresDTO().stream()
                .map(ObjectId::new)
                .collect(toList());
        return new Concession(concessionDTO.getNom(), concessionDTO.getVille(), voitures);
    }
}
