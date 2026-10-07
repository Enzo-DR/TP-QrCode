package Vue;
import modele.model;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class QrCode extends JFrame {

    private final JTextField champTitre = new JTextField(25);
    private final JTextField champTexte = new JTextField(25);
    private final JTextField champLien = new JTextField(25);
    private final JButton bouton = new JButton("Créer le PDF avec QR code");

    private final JTextField champImage = new JTextField(25);
    private final JSpinner champLargeur =
            new JSpinner(new SpinnerNumberModel(200, 20, 500, 10));

    private final JComboBox<String> comboAlignement =
            new JComboBox<>(model.ALIGNEMENTS.toArray(new String[0]));

    private final JComboBox<String> comboPolice =
            new JComboBox<>(model.POLICES.toArray(new String[0]));

    private final JTextField champFichierPolice = new JTextField(25);

    private final JComboBox<String> comboCouleur =
            new JComboBox<>(model.COULEURS.toArray(new String[0]));

    private final JCheckBox caseGras =
            new JCheckBox("Titre en gras", true);


    private final JTextField champSauvegarde = new JTextField(25);

    private final JButton boutonSauverProjet =
            new JButton("Sauver le projet");

    private final JButton boutonOuvrirProjet =
            new JButton("Ouvrir le projet");

    private final JButton boutonSauverProfil =
            new JButton("Sauver le profil");

    private final JButton boutonChargerProfil =
            new JButton("Charger le profil");

    public QrCode() {

        super("PDF + QR code");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(0, 2, 5, 5));

        add(new JLabel("Titre :"));
        add(champTitre);

        add(new JLabel("Texte :"));
        add(champTexte);

        add(new JLabel("Lien :"));
        add(champLien);

        add(new JLabel("Image (chemin du fichier) :"));
        add(champImage);

        add(new JLabel("Largeur de l'image (pt) :"));
        add(champLargeur);

        add(new JLabel("Alignement de l'image :"));
        add(comboAlignement);

        add(new JLabel("Police :"));
        add(comboPolice);

        add(new JLabel("Police .ttf (facultatif) :"));
        add(champFichierPolice);

        add(new JLabel("Couleur :"));
        add(comboCouleur);

        add(new JLabel("Style :"));
        add(caseGras);

        add(new JLabel("Nom de la sauvegarde :"));
        add(champSauvegarde);

        add(boutonSauverProjet);
        add(boutonOuvrirProjet);

        add(boutonSauverProfil);
        add(boutonChargerProfil);

        add(new JLabel());
        add(bouton);

        pack();

        setLocationRelativeTo(null);
    }

    public String getTitre() {
        return champTitre.getText();
    }

    public String getTexte() {
        return champTexte.getText();
    }

    public String getLien() {
        return champLien.getText();
    }

    public String getImage() {
        return champImage.getText();
    }

    public int getLargeur() {
        return (Integer) champLargeur.getValue();
    }

    public String getPolice() {
        return champFichierPolice.getText();
    }

    public String getCouleur() {
        return (String) comboCouleur.getSelectedItem();
    }

    public String getAlignement() {
        return (String) comboAlignement.getSelectedItem();
    }

    public String getNomSauvegarde() {
        return champSauvegarde.getText();
    }

    public boolean isGras() {
        return caseGras.isSelected();
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void onCreerPdf(ActionListener action) {
        bouton.addActionListener(action);
    }

    public void onSauverProjet(ActionListener action) {
        boutonSauverProjet.addActionListener(action);
    }

    public void onOuvrirProjet(ActionListener action) {
        boutonOuvrirProjet.addActionListener(action);
    }

    public void onSauverProfil(ActionListener action) {
        boutonSauverProfil.addActionListener(action);
    }

    public void onChargerProfil(ActionListener action) {
        boutonChargerProfil.addActionListener(action);
    }
}

