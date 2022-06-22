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
    public VoitureDTO toVoitureDTO(Voiture voiture) {
        List<String> equipementsAutoDTO = voiture.getEquipementsAuto().stream()
                .map(ObjectId::toHexString)
                .collect(toList());
        return new VoitureDTO(voiture.getId().toHexString(), voiture.getMarque(),
                                voiture.getModel(), voiture.getPrix(), voiture.getNbChvThermique(),
                                voiture.getNbChvElec(), voiture.getCouple(), voiture.getPuissanceElectrique(),
                                voiture.getAutonomie(), voiture.getVitesseMax(), equipementsAutoDTO);
    }

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

    public EquipementAutoDTO toEquipementAutoDTO(EquipementAuto equipementAuto) {
        return new EquipementAutoDTO(equipementAuto.getId().toHexString(), equipementAuto.getNom(), equipementAuto.getPrix());
    }

    public EquipementAuto dtoToEquipementAuto(EquipementAutoDTO equipementautoDTO) {
        return new EquipementAuto(equipementautoDTO.getNom(), equipementautoDTO.getPrix());
    }

    public ConcessionDTO toConcessionDTO(Concession concession) {
        List<String> voituresDTO = concession.getVoitures().stream()
                .map(ObjectId::toHexString)
                .collect(toList());
        return new ConcessionDTO(concession.getId().toHexString(), concession.getNom(), concession.getVille(), voituresDTO);
    }

    public Concession dtoToConcession(ConcessionDTO concessionDTO) {
        List<ObjectId> voitures = concessionDTO.getVoituresDTO().stream()
                .map(ObjectId::new)
                .collect(toList());
        return new Concession(concessionDTO.getNom(), concessionDTO.getVille(), voitures);
    }
}
