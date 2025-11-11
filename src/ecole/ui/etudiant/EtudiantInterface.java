package ecole.ui.etudiant;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

// On importe les "moteurs"
import ecole.gestion.Cours;
import ecole.model.Etudiant;
import ecole.model.Note;

/**
 * C'est l'espace personnel de l'étudiant.
 * Il ne s'ouvre qu'après une connexion réussie.
 */
public class EtudiantInterface extends JFrame {

    private Etudiant etudiantConnecte;
    private List<Cours> listeDesCours;
    private JTextArea zoneAffichageNotes;

    /**
     * Constructeur
     * @param etu L'objet Etudiant qui s'est connecté
     * @param cours La liste de tous les cours
     */
    public EtudiantInterface(Etudiant etu, List<Cours> cours) {
        this.etudiantConnecte = etu;
        this.listeDesCours = cours;

        this.setTitle("Espace Étudiant - " + etu.getPrenom() + " " + etu.getnom());
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        zoneAffichageNotes = new JTextArea();
        zoneAffichageNotes.setEditable(false);
        zoneAffichageNotes.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // On charge les notes
        chargerLesNotes();

        this.add(new JScrollPane(zoneAffichageNotes), BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void chargerLesNotes() {
        zoneAffichageNotes.append("Bulletin de notes pour : " +
                etudiantConnecte.getPrenom() + " " + etudiantConnecte.getnom() + "\n");
        zoneAffichageNotes.append("Numéro Étudiant : " + etudiantConnecte.getNumeroEtudiant() + "\n");
        zoneAffichageNotes.append("--------------------------------------------------\n\n");

        boolean aDesNotes = false;
        for (Cours cours : listeDesCours) {

            // C'EST ICI QU'ON UTILISE LA MAP DE "COURS"
            Note note = cours.getNoteParEtudiant(etudiantConnecte);

            if (note != null) {
                aDesNotes = true;
                zoneAffichageNotes.append("Matière : " + cours.getNom() + "\n");
                zoneAffichageNotes.append("   > Note : " + note.getValeur() + " / 20\n\n");
            }
        }
        if (!aDesNotes) {
            zoneAffichageNotes.append("Vous n'avez aucune note pour le moment.");
        }
    }
}
