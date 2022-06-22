package com.hybrid.hybrid.controller.verify;

import com.hybrid.hybrid.controller.exceptions.Error;
import com.hybrid.hybrid.domain.Concession;
import com.hybrid.hybrid.domain.EquipementAuto;
import com.hybrid.hybrid.domain.Voiture;
import com.hybrid.hybrid.repository.EquipementAutoRepositoryInterface;
import com.hybrid.hybrid.repository.VoitureRepositoryInterface;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

public class Verify {

    public static void verifyVoiture(Voiture voiture, EquipementAutoRepositoryInterface equipementAutoRepositoryInterface) {

        /*
          Verifie si la marque ou le modele de la voiture passés en paramètre ne sont pas null
         */
        if (isNullOrEmpty(voiture.getMarque()) || isNullOrEmpty(voiture.getModel())){
            throw new Error("Un des champs renseignés ne doit pas être null ou vide", HttpStatus.BAD_REQUEST);
        }

        if (isNullInt(voiture.getAutonomie()) || isNullInt(voiture.getCouple()) || isNullInt(voiture.getNbChvElec()) || isNullInt(voiture.getNbChvThermique()) || isNullInt(voiture.getPuissanceElectrique()) || isNullInt(voiture.getVitesseMax())){
                throw new Error("Un des champs renseignés ne doit pas être null", HttpStatus.BAD_REQUEST);
        }

        if (isNullFloat(voiture.getPrix())) {
            throw new Error("Le prix ne doit pas être null", HttpStatus.BAD_REQUEST);
        }


        for (ObjectId id : voiture.getEquipementsAuto()) {
            try {
                equipementAutoRepositoryInterface.findEquipementById(id);
            } catch (Exception e) {
                throw new Error("Equipement non trouvé", HttpStatus.NOT_FOUND, e);
            }
        }
    }


    public static void verifyEquipement(EquipementAuto equipementAuto) {
        if (isNullOrEmpty(equipementAuto.getNom())) {
            throw new Error("Le champ nom ne doit pas être null ou vide", HttpStatus.BAD_REQUEST);
        }
    }

    public static void verifyConcession(Concession concession, VoitureRepositoryInterface voitureRepositoryInterface) {
        if (isNullOrEmpty(concession.getNom()) || isNullOrEmpty(concession.getVille())) {
            throw new Error("Les champs nom et ville ne doivent pas être null ou vide", HttpStatus.BAD_REQUEST);
        }

        for (ObjectId id : concession.getVoitures()) {
            try {
                voitureRepositoryInterface.findVoitureById(id);
            } catch (Exception e) {
                throw new Error("Voiture non trouvé", HttpStatus.NOT_FOUND, e);
            }
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    private static boolean isNullInt(int entier) {
        return entier == 0;
    }

    private static boolean isNullFloat(float decimal){
        return decimal == 0.0f;
    }

}
