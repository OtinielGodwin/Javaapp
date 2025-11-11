package ecole;

import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.*;
import ecole.ui.admin.AdminInterface;
import ecole.ui.professeur.ProfInterface;
import ecole.ui.etudiant.LoginEtudiant;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe principale pour le lancement de l'application.
 * Elle gère l'initialisation de toutes les données et le routage des interfaces.
 */
public class AppManager extends JFrame implements ActionListener {

    // --- DONNÉES GLOBALES (Le "Moteur" Central) ---
    // Ces listes sont créées UNE SEULE FOIS et passées à toutes les interfaces.
    private Groupe groupeGlobal;
    private List<Professeur> listeProfesseurs;
    private List<Cours> listeCours;

    // Pour le test des notes (une note doit exister pour que l'étudiant la voie)
    private Etudiant etuTest;

    // --- Composants UI ---
    private JButton btnAdmin, btnProf, btnEtu;
    private List<Groupe> Global;

    public AppManager() {
        this.setTitle("Sélection du Portail");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 1, 10, 10));

        // Initialisation des données
        initialiserDonneesTest();

        // Création des boutons de routage
        btnAdmin = new JButton("Connexion Administrateur");
        btnProf = new JButton("Connexion Professeur");
        btnEtu = new JButton("Connexion Étudiant");

        btnAdmin.addActionListener(this);
        btnProf.addActionListener(this);
        btnEtu.addActionListener(this);

        this.add(btnAdmin);
        this.add(btnProf);
        this.add(btnEtu);

        this.setVisible(true);
    }

    /**
     * Crée et charge toutes les données de base pour le test.
     */
    private void initialiserDonneesTest() {
        this.groupeGlobal = new Groupe("BUT SD 1A");
        this.listeProfesseurs = new ArrayList<>();
        this.listeCours = new ArrayList<>();

        // Données Étudiant de test (Bob Martin)
        this.etuTest = new Etudiant("Martin", "Bob", 20, "e123", Genre.HOMME, Filiere.BUT_SCIENCE_DES_DONNEES);
        try {
            groupeGlobal.ajouterEtudiant(etuTest);
        } catch (Exception e) {}

        // Données Professeur de test (Tryphon Tournesol)
        Professeur profTest = new Professeur("Tournesol", "Tryphon", 60, Genre.HOMME, "Physique", 5000);
        listeProfesseurs.add(profTest);

        // Données Cours et Notes de test
        Cours coursJava = new Cours("Java POO", profTest, groupeGlobal);
        listeCours.add(coursJava);
        try {
            // Bob a une note pour ce cours, pour le test étudiant
            Note noteBobJava = new Note(etuTest, coursJava, 15.5);
            coursJava.ajouterNote(noteBobJava);
        } catch (Exception e) {}
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdmin) {
            // Lance la fenêtre de login Admin
            new ecole.ui.admin.LoginAdmin(groupeGlobal, listeProfesseurs, listeCours);

        } else if (e.getSource() == btnProf) {
            // CORRIGÉ : On lance la fenêtre de login Professeur
            // On donne à cette fenêtre la liste des profs pour l'authentification, les groupes et les cours
            new ecole.ui.professeur.LoginProf(listeProfesseurs, Collections.singletonList(groupeGlobal), listeCours);

        } else if (e.getSource() == btnEtu) {
            // Lance la fenêtre de Login Étudiant
            new ecole.ui.etudiant.LoginEtudiant(groupeGlobal, listeCours);
        }
    }
}