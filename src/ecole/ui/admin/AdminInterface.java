package ecole.ui.admin;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

// On importe nos classes "moteur"
import ecole.gestion.Cours;
import ecole.gestion.Groupe;
import ecole.model.Professeur;

/**
 * C'est la fenêtre principale de l'Admin.
 * Elle contient les onglets pour gérer l'école.
 */
public class AdminInterface extends JFrame {

    private JTabbedPane systemeOnglets;

    // --- Les "Moteurs" ---
    private Groupe groupePrincipal;
    private List<Professeur> listeProfesseurs;

    public AdminInterface(Groupe groupeCible, List<Professeur> listeProfesseurs, List<Cours> listeCours) {
        this.setTitle("Panneau d'Administration");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 1. Initialiser nos "moteurs"
        this.groupePrincipal = new Groupe("BUT SD 1A");
        this.listeProfesseurs = new ArrayList<>();

        // 2. Créer le système d'onglets
        systemeOnglets = new JTabbedPane();

        // 3. Créer les panneaux (nos "interfaces" 1 et 2)
        JPanel ongletEtu = new PanelCreationEtudiant(this.groupePrincipal);
        JPanel ongletProf = new PanelCreationProfesseur(this.listeProfesseurs);

        // 4. Ajouter les onglets à la fenêtre
        systemeOnglets.addTab("👤 Créer Étudiant", ongletEtu);
        systemeOnglets.addTab("👨‍🏫 Créer Professeur", ongletProf);

        // 5. Ajouter le système d'onglets à la fenêtre
        this.add(systemeOnglets, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public AdminInterface(Groupe groupeGlobal, List<Professeur> listeProfesseurs) {
    }
}
