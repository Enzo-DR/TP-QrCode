package controleur;

import modele.model;
import Vue.QrCode;

public class MainController {

    public MainController(model modele, QrCode vue) {

        vue.onCreerPdf(e -> {

            // Récupération des informations
            modele.titre = vue.getTitre();
            modele.texte = vue.getTexte();
            modele.lien = vue.getLien();

            // Récupération des paramètres supplémentaires
            modele.image = vue.getImage();
            modele.largeur = vue.getLargeur();
            modele.alignement = vue.getAlignement();

            modele.police = vue.getPolice();
            modele.fichierPolice = vue.getPolice();

            // IMPORTANT : récupération de la couleur
            modele.couleur = vue.getCouleur();

            // Récupération du style gras
            modele.gras = vue.isGras();

            try {

                modele.creerPdf("resultat.pdf");

                vue.afficherMessage(
                        "PDF créé : resultat.pdf"
                );

            } catch (Exception ex) {

                vue.afficherMessage(
                        "Erreur : " + ex.getMessage()
                );
            }
        });
    }
}