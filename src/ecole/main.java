package ecole;
import ecole.gestion.Groupe;
import ecole.model.*;
import ecole.exception.*;
public class main {
    public static void main ( String [] args) {
        /*
        System.out.println("Bonjour, je m’appelle " + john.prenom + " " + john.nom +
                " et j’ai " + john.age + " ans.");
        System.out.println("Bonjour, je m’appelle " + arya.prenom + " " + arya.nom +
                " et j’ai " + arya.age + " ans.");
        System.out.println("Il y a actuellement " + ecole.model.Personne.nbPersonnes + "Personnes"); */

        /*Voiture maVoiture = new Voiture("off", 0) ;
        maVoiture.demarrer();*/

        // Création de personnes
        Personne p = new Personne("Dupont", "Alice", 20 , Genre.HOMME);
        Personne arya = new Personne ( "Stark", "Arya", 10, Genre.FEMME);
        arya.setPrenom("Sansa");
        System.out.println("ecole.model.Personne : " + p.toString());
        System.out.println("ecole.model.Personne : " + arya.toString());

        // Test sur personne
        System.out.println("p et arya sont elle la même personne ? " + p.equals(arya));

        // Création d'un étudiant
        Etudiant e = new Etudiant("Martin", "Bob", 22, "E12345" ,Genre.HOMME, Filiere.BUT_SCIENCE_DES_DONNEES);
        Etudiant e2 = new Etudiant("Durand", "Charlie", 22, "E54301" ,Genre.FEMME,Filiere.BUT_SCIENCE_DES_DONNEES);
        Etudiant e3 = new Etudiant("Lampion", "Séraphin", 45, "E99999", Genre.HOMME, Filiere.BUT_GEA);
        System.out.println("Étudiant : " + e.toString());

        // Test des getters
        System.out.println("Numéro étudiant : " + e.getNumeroEtudiant());

        // Test des setters
        e.setNumeroEtudiant("E54321");
        System.out.println("Après modification : " + e.toString());

        // Test sur equals etudiants
        Etudiant e4 = new Etudiant("Durand", "Charlie", 22, "E54301" ,Genre.FEMME,Filiere.BUT_SCIENCE_DES_DONNEES);
        System.out.println("e et e2 sont-ils égaux ? " + e.equals(e4));

        // Créer deux professeurs

        Professeur prof1 = new Professeur("Tournesol", "Tryphon", 55, Genre.HOMME, "Physique", 4000.0);
        Professeur prof2 = new Professeur("Castafiore", "Bianca", 40, Genre.FEMME, "Chant lyrique", 10000.0);

        System.out.println("ecole.model.Professeur 1: " + prof1);
        System.out.println("ecole.model.Professeur 2: " + prof2);

        //  Création de deux groupes
        Groupe SD = new Groupe("Projet Science des Données");
        Groupe GEA = new Groupe("Projet GEA");

        // 3. Utiliser les méthodes pour ajouter
        SD.ajouterEtudiant(e);
        SD.ajouterEtudiant(e2);

        GEA.ajouterEtudiant(e3);

        System.out.println("\n--- TEST 'afficherMembres' ---");
        SD.afficherMembres();
        GEA.afficherMembres();

        System.out.println("\n--- TEST 'findEtudiant' et 'existeEtudiant' ---");
        // On cherche un étudiant qui existe
        Etudiant etuRecherche = SD.findEtudiant("E54321"); // Le n° de Bob Martin
        System.out.println("Recherche de E54321: " + etuRecherche.getPrenom()); // Devrait afficher Bob

        // On cherche un étudiant qui n'existe pas
        Etudiant etuRechercheNull = SD.findEtudiant("E00000");
        System.out.println("Recherche de E00000: " + etuRechercheNull); // Devrait afficher null

        // On vérifie l'existence
        System.out.println("E54301 (Charlie) existe dans groupeSD? " + SD.existeEtudiant("E54301")); // true
        System.out.println("E99999 (Séraphin) existe dans groupeSD? " + SD.existeEtudiant("E99999")); // false

        System.out.println("\n--- TEST 'supprimerEtudiant' ---");
        System.out.println("On supprime Charlie (E54301) du groupeSD...");
        Etudiant etuSupprime = SD.supprimerEtudiant("E54301");

        System.out.println("Étudiant supprimé : " + etuSupprime.getPrenom());

        System.out.println("Vérification après suppression :");
        SD.afficherMembres(); // Ne devrait afficher que Bob

        //Création de tableau

        Etudiant[] tableauAssezGrand = new Etudiant[5];
        Etudiant[] resultat = SD.toArray(tableauAssezGrand);
        for (Etudiant etu : resultat) {
            System.out.println(etu);
        }
    }
}


