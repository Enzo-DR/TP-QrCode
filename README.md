# TP – Génération de PDF avec QR code (Java Swing, MVC)

## 1. Objectif du TP

Réaliser une application Java qui permet à l'utilisateur de saisir des informations (texte, lien),
puis de générer un fichier PDF contenant ces informations ainsi qu'un QR code.

Travail demandé :
- Créer l'interface utilisateur avec Java Swing.
- Générer des fichiers PDF avec une bibliothèque Java (iText).
- Générer des QR codes à partir des données saisies.
- Intégrer les QR codes générés dans les fichiers PDF.

## 2. Fonctionnement de l'application

1. L'utilisateur saisit un titre, un texte et un lien.
2. Il clique sur « Créer le PDF avec QR code ».
3. Le QR code contient le lien, ou le texte si aucun lien n'est saisi.
4. Le fichier `resultat.pdf` est créé à la racine du projet.