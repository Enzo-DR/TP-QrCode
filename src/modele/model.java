
package modele;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class model {


    public static final List<String> ALIGNEMENTS =
            Arrays.asList("Gauche", "Centre", "Droite");

    public static final List<String> POLICES =
            Arrays.asList("Helvetica", "Times-Roman", "Courier");

    public static final List<String> COULEURS =
            Arrays.asList("Noir", "Rouge", "Bleu");


    public String titre = "";
    public String texte = "";
    public String lien = "";

    public String image = "";
    public int largeur = 200;
    public String alignement = "Centre";
    public String police = "Helvetica";
    public String fichierPolice = "";
    public String couleur = "Noir";
    public boolean gras = true;


    public void creerPdf(String fichier) throws Exception {


        String contenu = lien.isBlank() ? texte : lien;

        if (contenu.isBlank()) {
            throw new Exception("Saisissez un texte ou un lien.");
        }

        // Création du QR code
        var matrice = new QRCodeWriter().encode(
                contenu,
                BarcodeFormat.QR_CODE,
                300,
                300
        );


        var baos = new ByteArrayOutputStream();

        BufferedImage qrImage =
                MatrixToImageWriter.toBufferedImage(matrice);

        ImageIO.write(qrImage, "png", baos);

        // Création du PDF
        try (var pdf = new PdfDocument(new PdfWriter(fichier));
             var doc = new Document(pdf)) {



            Paragraph paragrapheTitre =
                    new Paragraph(titre)
                            .setFontSize(20);

            if (gras) {
                paragrapheTitre.setBold();
            }

            paragrapheTitre.setTextAlignment(getTextAlignment());

            doc.add(paragrapheTitre);



            if (!texte.isBlank()) {

                Paragraph paragrapheTexte =
                        new Paragraph(texte)
                                .setTextAlignment(getTextAlignment());

                doc.add(paragrapheTexte);
            }



            if (!lien.isBlank()) {

                Paragraph paragrapheLien =
                        new Paragraph(lien)
                                .setTextAlignment(getTextAlignment());

                doc.add(paragrapheLien);
            }



            if (!image.isBlank()) {

                try {
                    Image imagePdf =
                            new Image(ImageDataFactory.create(image));

                    imagePdf.scaleToFit(largeur, largeur);

                    doc.add(imagePdf);

                } catch (Exception e) {
                    throw new Exception(
                            "Impossible de charger l'image : " + image
                    );
                }
            }


            Image qr =
                    new Image(
                            ImageDataFactory.create(
                                    baos.toByteArray()
                            )
                    );

            qr.scaleToFit(200, 200);

            doc.add(qr);
        }
    }


    private TextAlignment getTextAlignment() {

        switch (alignement) {

            case "Gauche":
                return TextAlignment.LEFT;

            case "Droite":
                return TextAlignment.RIGHT;

            case "Centre":
            default:
                return TextAlignment.CENTER;
        }
    }
}

