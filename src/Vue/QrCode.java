package Vue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class QrCode extends JFrame {

    private final JTextField champTitre = new JTextField(25);
    private final JTextField champTexte = new JTextField(25);
    private final JTextField champLien = new JTextField(25);
    private final JButton bouton = new JButton("Créer le PDF avec QR code");

    public QrCode() {
        super("PDF + QR code");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 5, 5));
        add(new JLabel("Titre :"));  add(champTitre);
        add(new JLabel("Texte :"));  add(champTexte);
        add(new JLabel("Lien :"));   add(champLien);
        add(new JLabel());           add(bouton);
        pack();
        setLocationRelativeTo(null);
    }

    public String getTitre() { return champTitre.getText(); }
    public String getTexte() { return champTexte.getText(); }
    public String getLien()  { return champLien.getText(); }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void onCreerPdf(ActionListener action) {
        bouton.addActionListener(action);
    }
}