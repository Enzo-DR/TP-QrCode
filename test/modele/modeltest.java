package modele;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


class modeltest {

    @TempDir
    Path dossier;

    private File fichierTest() {
        return dossier.resolve("test.pdf").toFile();
    }

    @Test
    void creerPdf_avecLien_creeUnVraiFichierPdf() throws Exception {
        model m = new model();
        m.titre = "Mon titre";
        m.texte = "Un texte";
        m.lien = "https://www.example.com";
        File f = fichierTest();

        m.creerPdf(f.getAbsolutePath());

        assertTrue(f.exists());
        assertTrue(f.length() > 0);
        // Un PDF commence toujours par "%PDF"
        byte[] octets = Files.readAllBytes(f.toPath());
        assertEquals("%PDF", new String(octets, 0, 4));
    }

    @Test
    void creerPdf_avecTexteSeul_fonctionne() throws Exception {
        model m = new model();
        m.texte = "Seulement du texte";
        File f = fichierTest();

        m.creerPdf(f.getAbsolutePath());

        assertTrue(f.exists());
    }

    @Test
    void creerPdf_sansTexteNiLien_leveUneException() {
        model m = new model();
        File f = fichierTest();

        Exception e = assertThrows(Exception.class, () -> m.creerPdf(f.getAbsolutePath()));

        assertEquals("Saisissez un texte ou un lien.", e.getMessage());
        assertFalse(f.exists()); // aucun fichier ne doit être créé
    }

    @Test
    void creerPdf_avecEspacesSeulement_leveUneException() {
        model m = new model();
        m.texte = "   ";
        m.lien = "   ";

        assertThrows(Exception.class, () -> m.creerPdf(fichierTest().getAbsolutePath()));
    }
}