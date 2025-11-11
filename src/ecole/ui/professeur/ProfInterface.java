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

    // Les Moteurs
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
        this.setLocationRelativeTo(null);

        // 1. Créer le système d'onglets
        systemeOnglets = new JTabbedPane();

        // 2. Créer les panneaux (nos "interfaces" 1 et 2)
        JPanel onglet1 = new PanelCreationCours(profConnecte, listeDesGroupes, listeDesCours);
        JPanel onglet2 = new PanelGestionNotes(profConnecte, listeDesCours);

        // 3. Ajouter les onglets à la fenêtre
        systemeOnglets.addTab("📚 Créer un Cours", onglet1);
        systemeOnglets.addTab("📝 Gérer les Notes", onglet2);

        // 4. Ajouter le système d'onglets à la fenêtre
        this.add(systemeOnglets, BorderLayout.CENTER);
        this.setVisible(true);
    }
}