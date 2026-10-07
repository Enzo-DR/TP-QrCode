package modele;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class model {
    public String titre = "";
    public String texte = "";
    public String lien = "";

    public void creerPdf(String fichier) throws Exception {
        String contenu = lien.isBlank() ? texte : lien;
        if (contenu.isBlank()) throw new Exception("Saisissez un texte ou un lien.");

        var matrice = new QRCodeWriter().encode(contenu, BarcodeFormat.QR_CODE, 300, 300);
        var baos = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter.toBufferedImage(matrice), "png", baos);

        try (var pdf = new PdfDocument(new PdfWriter(fichier));
             var doc = new Document(pdf)) {
            doc.add(new Paragraph(titre).setFontSize(20).setBold());
            doc.add(new Paragraph(texte));
            doc.add(new Paragraph(lien));
            doc.add(new Image(ImageDataFactory.create(baos.toByteArray())).scaleToFit(200, 200));
        }
    }
}
