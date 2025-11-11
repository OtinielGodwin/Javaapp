package ecole;

import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.*;
import ecole.exception.*;
import java.util.List;
import java.util.ArrayList;

public class main {
    public static void main ( String [] args) {

        // TEST DES ATTRIBUTS ET ACCESSEURS

        // Création de personnes
        Personne p = new Personne("Dupont", "Alice", 20 , Genre.HOMME);
        Personne arya = new Personne ( "Stark", "Arya", 10, Genre.FEMME);
        arya.setPrenom("Sansa");
        System.out.println("\n--- TESTS PERSONNE ---");
        System.out.println("Personne : " + p.toString());
        System.out.println("Personne : " + arya.toString());

        // Test des getters pour le Nom
        System.out.println("Le nom de 'p' est: " + p.getnom());

        // Test des setters pour le Nom
        p.setnom("Smith");
        System.out.println("Nouveau nom de 'p': " + p.getnom());

        // Test de la méthode simple afficheAge()
        System.out.print("Affichage de l'âge de 'p': ");
        p.afficheAge();


        // Test sur equals personne
        System.out.println("p et arya sont elle la même personne ? " + p.equals(arya));

        // Création d'étudiants (les étudiants e, e2, e3 sont les données du système)
        Etudiant e = new Etudiant("Martin", "Bob", 22, "E12345" ,Genre.HOMME, Filiere.BUT_SCIENCE_DES_DONNEES);
        Etudiant e2 = new Etudiant("Durand", "Charlie", 22, "E54301" ,Genre.FEMME,Filiere.BUT_SCIENCE_DES_DONNEES);
        Etudiant e3 = new Etudiant("Lampion", "Séraphin", 45, "E99999", Genre.HOMME, Filiere.BUT_GEA);
        Etudiant e4 = new Etudiant("Durand", "Charlie", 22, "E54301" ,Genre.FEMME,Filiere.BUT_SCIENCE_DES_DONNEES);
        System.out.println("Étudiant 'e' : " + e.toString());

        // Test des setters Etudiant
        e.setNumeroEtudiant("E54321");
        System.out.println("Après modification ID 'e': " + e.toString());

        // Test sur equals etudiants
        System.out.println("e2 et e4 sont-ils égaux ? " + e2.equals(e4)); // Doit être true

        // Créer deux professeurs
        Professeur prof1 = new Professeur("Tournesol", "Tryphon", 55, Genre.HOMME, "Physique", 4000.0);
        Professeur prof2 = new Professeur("Castafiore", "Bianca", 40, Genre.FEMME, "Chant lyrique", 10000.0);
        System.out.println("Professeur 1: " + prof1);


        // --- TEST DE LA CLASSE GROUPE ---
        Groupe SD = new Groupe("Projet Science des Données");
        Groupe GEA = new Groupe("Projet GEA");

        // Utiliser les méthodes pour ajouter
        SD.ajouterEtudiant(e); // Bob (E54321)
        SD.ajouterEtudiant(e2); // Charlie (E54301)
        GEA.ajouterEtudiant(e3); // Séraphin

        System.out.println("\n--- TEST 'afficherMembres' (Initial) ---");
        SD.afficherMembres();

        //TEST DE LA SURCHARGE supprimerEtudiant
        Etudiant aSupprimer = new Etudiant("Quick", "Test", 20, "A99999", Genre.AUTRE, Filiere.BUT_CHIMIE);
        SD.ajouterEtudiant(aSupprimer);
        System.out.println("--- Suppression de Test (A99999) par Objet ---");
        SD.supprimerEtudiant(aSupprimer);
        SD.afficherMembres();

        //  Autres tests de groupe
        System.out.println("\n--- TEST 'findEtudiant' et 'existeEtudiant' ---");
        Etudiant etuRecherche = SD.findEtudiant("E54321");
        System.out.println("Recherche de E54321: " + etuRecherche.getPrenom());

        System.out.println("E54301 (Charlie) existe ? " + SD.existeEtudiant("E54301"));

        System.out.println("\n--- TEST 'toArray()' SANS PARAMÈTRE ---");
        Etudiant[] tableauTous = SD.toArray();
        System.out.println("Taille du tableau toArray(): " + tableauTous.length);

        System.out.println("\n--- TEST 'supprimerEtudiant' PAR ID ---");
        System.out.println("On supprime Charlie (E54301)...");
        SD.supprimerEtudiant("E54301");
        SD.afficherMembres();

        //  TEST DE LA CLASSE COURS ET NOTE

        System.out.println("\n--- TEST COURS ET NOTES ---");

        // Liste globale des cours (pour le moteur)
        List<Cours> tousLesCours = new ArrayList<>();

        // Création du cours (Prof1 enseigne au groupe SD)
        Cours coursJava = new Cours("Java POO", prof1, SD);
        tousLesCours.add(coursJava);
        System.out.println(coursJava.toString());

        // TEST : Modifier le référent (Cours.setReferent())
        coursJava.setReferent(prof2);
        System.out.println("Prof référent changé: " + coursJava.getReferent().getPrenom());

        // Création et ajout d'une note
        Note noteBob = new Note(e, coursJava, 15.5);

        // TEST : Ajout de la note au cours
        coursJava.ajouterNote(noteBob);
        System.out.println("Note créée: " + noteBob.toString());

        // TEST : getNoteParEtudiant (trouver la note dans la Map du cours)
        Note noteTrouvee = coursJava.getNoteParEtudiant(e);
        System.out.println("Note de Bob (trouvée via Map): " + noteTrouvee.getValeur());

        // TEST : setValeur (Note.setValeur())
        noteTrouvee.setValeur(18.0);
        System.out.println("Note de Bob mise à jour: " + noteTrouvee.getValeur());

        // TEST DE LA GESTION DES EXCEPTIONS

        System.out.println("\n--- TESTS EXCEPTIONS (ATTENDU : ERREURS EN CONSOLE) ---");

        // 1. Tester AgeInvalidException
        try {
            Personne pErreurAge = new Personne("Erreur", "Age", -5, Genre.AUTRE);
        } catch (AgeInvalidException ex) {
            System.err.println("Exception attrapée : " + ex.getMessage());
        }

        // 2. Tester Nom/Prenom invalide (chiffre)
        try {
            Personne pErreurNom = new Personne("Nom123", "Valide", 25, Genre.HOMME);
        } catch (IllegalArgumentException ex) {
            System.err.println("Exception attrapée : " + ex.getMessage());
        }

        // 3. Tester SalaireInvalidException
        try {
            Professeur profErreurSalaire = new Professeur("Test", "Salaire", 40, Genre.AUTRE, "Info", -100.0);
        } catch (SalaireInvalidException ex) {
            System.err.println("Exception attrapée : " + ex.getMessage());
        }

        // 4. Tester EtudiantDejaPresentException
        try {
            SD.ajouterEtudiant(e); // Bob (E54321) est déjà là
        } catch (Etudiantpresent ex) {
            System.err.println("Exception attrapée : " + ex.getMessage());
        }

    }
}