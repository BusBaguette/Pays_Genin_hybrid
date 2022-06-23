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

    /**
     * Vérifie si tous les champs d'une voiture sont renseignés et l'equipement auto passés en paramètre
     * @param voiture Vérifie que les éléments d'une voiture soient bien renseignés
     * @param equipementAutoRepositoryInterface EquipementAuto qui soit bien dans une voiture
     */
    public static void verifyVoiture(Voiture voiture, EquipementAutoRepositoryInterface equipementAutoRepositoryInterface) {

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


    /**
     * Vérifie que le nom d'un équipement auto soit renseigné
     * @param equipementAuto EquipementAuto a vérifier
     */
    public static void verifyEquipement(EquipementAuto equipementAuto) {
        if (isNullOrEmpty(equipementAuto.getNom())) {
            throw new Error("Le champ nom ne doit pas être null ou vide", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Vérifie que les champs d'une concession sont pas vides et que la voiture passée en paramètre appartient à la concession passée en paramètre
     * @param concession Vérifier les champs de la concession
     * @param voitureRepositoryInterface Vérifie que la voiture appartient bien a la concession passée en paramètre
     */
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

    /**
     * Vérifie que la chaine de caractère soit pas vide ou null
     * @param str Chaine de caractères a vérifier
     * @return Retourne si la chaine est nulle/vide
     */
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * Vérifie que l'entier passé en paramètre soit pas null
     * @param entier Entier a tester
     * @return Renvoie si l'entier est null ou non
     */
    private static boolean isNullInt(int entier) {
        return entier == 0;
    }

    /**
     * Vérifie que le float passé en paramètre soit pas null
     * @param decimal Float a testé
     * @return Renvoie si le float est null ou non
     */
    private static boolean isNullFloat(float decimal){
        return decimal == 0.0f;
    }

}
