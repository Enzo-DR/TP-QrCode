import controleur.MainController;
import modele.model;
import Vue.QrCode;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QrCode vue = new QrCode();
            new MainController(new model(), vue);
            vue.setVisible(true);
        });
    }
}