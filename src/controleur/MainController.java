package controleur;
import modele.model;
import Vue.QrCode;

public class MainController {

    public MainController(model modele, QrCode vue) {
        vue.onCreerPdf(e -> {
            modele.titre = vue.getTitre();
            modele.texte = vue.getTexte();
            modele.lien = vue.getLien();
            try {
                modele.creerPdf("resultat.pdf");
                vue.afficherMessage("PDF créé : resultat.pdf");
            } catch (Exception ex) {
                vue.afficherMessage("Erreur : " + ex.getMessage());
            }
        });
    }
}
