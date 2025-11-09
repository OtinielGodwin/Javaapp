package ecole.ui.professeur;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

// On importe tous les "moteurs"
import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.Professeur;
import ecole.model.Etudiant;
import ecole.model.Genre;
import ecole.model.Filiere;

/**
 * C'est la fenêtre principale de l'Espace Professeur.
 * Elle contient les onglets pour gérer les cours et les notes.
 */
public class ProfInterface extends JFrame {

    private JTabbedPane systemeOnglets;

    // --- Les "Moteurs" (Données de l'application) ---
    private Professeur profConnecte;
    private List<Groupe> listeDesGroupes;
    private List<Cours> listeDesCours;

    public ProfInterface(Professeur prof, List<Groupe> groupes, List<Cours> cours) {
        this.profConnecte = prof;
        this.listeDesGroupes = groupes;
        this.listeDesCours = cours;

        this.setTitle("Espace Professeur - " + prof.getPrenom() + " " + prof.getnom());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Ouvre au centre

        // 1. Créer le système d'onglets
        systemeOnglets = new JTabbedPane();

        // 2. Créer les panneaux (nos "interfaces" 1 et 2)
        // On "donne" les moteurs aux panneaux dont ils ont besoin
        JPanel onglet1 = new PanelCreationCours(profConnecte, listeDesGroupes, listeDesCours);
        JPanel onglet2 = new PanelGestionNotes(profConnecte, listeDesCours);

        // 3. Ajouter les onglets à la fenêtre
        systemeOnglets.addTab("📚 Créer un Cours", onglet1);
        systemeOnglets.addTab("📝 Gérer les Notes", onglet2);

        // 4. Ajouter le système d'onglets à la fenêtre
        this.add(systemeOnglets, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Main de test pour lancer UNIQUEMENT l'interface Professeur.
     */
    public static void main(String[] args) {

        // --- On crée des données factices pour tester ---

        // 1. Créer un prof
        Professeur profTest = new Professeur("Tournesol", "Tryphon", 60, Genre.HOMME, "Physique", 5000);

        // 2. Créer un groupe et y ajouter des étudiants
        Groupe groupeTest = new Groupe("BUT SD 1A");
        try {
            groupeTest.ajouterEtudiant(new Etudiant("Martin", "Bob", 20, "e123", Genre.HOMME, Filiere.BUT_SCIENCE_DES_DONNEES));
            groupeTest.ajouterEtudiant(new Etudiant("Dupont", "Alice", 21, "e456", Genre.FEMME, Filiere.BUT_SCIENCE_DES_DONNEES));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Créer une liste de groupes
        List<Groupe> tousLesGroupes = new ArrayList<>();
        tousLesGroupes.add(groupeTest);

        // 4. Créer une liste (vide) de cours
        List<Cours> tousLesCours = new ArrayList<>();

        // 5. Lancer l'interface
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // On donne les données factices à l'interface
                new ProfInterface(profTest, tousLesGroupes, tousLesCours);
            }
        });
    }
}