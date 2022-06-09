package com.hybrid.hybrid;

import com.hybrid.hybrid.domain.Concession;
import com.hybrid.hybrid.domain.EquipementAuto;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.domain.dto.ConcessionDTO;
import com.hybrid.hybrid.domain.dto.EquipementAutoDTO;
import com.hybrid.hybrid.domain.dto.VoitureDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {
    public VoitureDTO toVoitureDto(Voiture voiture) {
        List<EquipementAutoDTO> equipementsAutoDTO = voiture.getEquipementsAuto().stream()
                .map(this::toEquipementAutoDTO)
                .collect(toList());
        return new VoitureDTO(voiture.getId().toHexString(), voiture.getMarque(),
                                voiture.getModel(), voiture.getPrix(), voiture.getNbChvThermique(),
                                voiture.getNbChvElec(), voiture.getCouple(), voiture.getPuissanceElectrique(),
                                voiture.getAutonomie(), voiture.getVitesseMax(), equipementsAutoDTO);
    }

    public EquipementAutoDTO toEquipementAutoDTO(EquipementAuto equipementAuto) {
        return new EquipementAutoDTO(equipementAuto.getId().toHexString(), equipementAuto.getNom(), equipementAuto.getPrix());
    }

    public ConcessionDTO toConcessionDTO(Concession concession) {
        List<VoitureDTO> voituresDTO = concession.getVoitures().stream()
                .map(this::toVoitureDto)
                .collect(toList());
        return new ConcessionDTO(concession.getId().toHexString(), concession.getNom(), concession.getVille(), voituresDTO);
    }
}
