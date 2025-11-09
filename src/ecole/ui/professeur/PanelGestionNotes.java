package ecole.ui.professeur;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

// On importe tous les "moteurs"
import ecole.gestion.Cours;
import ecole.model.Etudiant;
import ecole.model.Note;
import ecole.model.Professeur;

/**
 * C'est le panneau (JPanel) pour l'onglet "Gérer les Notes".
 * Il interagit avec la logique Map de Cours et Groupe.
 */
public class PanelGestionNotes extends JPanel implements ActionListener {

    private Professeur profConnecte;
    private List<Cours> listeCours;

    private JComboBox<Cours> dropdownCours;
    private JList<Etudiant> listeEtudiants;
    private DefaultListModel<Etudiant> listModel; // Le "contrôleur" de la JList
    private JTextField champNote;
    private JButton boutonEnregistrerNote;

    public PanelGestionNotes(Professeur prof, List<Cours> cours) {
        this.profConnecte = prof;
        this.listeCours = cours;

        this.setLayout(new BorderLayout(10, 10));

        // --- 1. HAUT (NORTH) : Le choix du cours ---
        List<Cours> coursDuProf = new ArrayList<>();
        for (Cours c : listeCours) {
            if (c.getReferent().equals(profConnecte)) {
                coursDuProf.add(c);
            }
        }
        dropdownCours = new JComboBox<>(coursDuProf.toArray(new Cours[0]));
        dropdownCours.addActionListener(this);
        this.add(dropdownCours, BorderLayout.NORTH);

        // --- 2. CENTRE (CENTER) : La liste des étudiants ---
        listModel = new DefaultListModel<>();
        listeEtudiants = new JList<>(listModel);
        this.add(new JScrollPane(listeEtudiants), BorderLayout.CENTER);

        // --- 3. BAS (SOUTH) : Le champ de note et le bouton ---
        JPanel panelSud = new JPanel(new BorderLayout(10, 0));
        panelSud.add(new JLabel("Note :"), BorderLayout.WEST);
        champNote = new JTextField();
        panelSud.add(champNote, BorderLayout.CENTER);
        boutonEnregistrerNote = new JButton("Enregistrer");
        boutonEnregistrerNote.addActionListener(this);
        panelSud.add(boutonEnregistrerNote, BorderLayout.EAST);
        this.add(panelSud, BorderLayout.SOUTH);

        // On charge les étudiants du premier cours (si un cours existe)
        mettreAJourListeEtudiants();
    }

    /**
     * Méthode d'aide pour remplir la JList avec les étudiants
     * du cours sélectionné.
     */
    private void mettreAJourListeEtudiants() {
        listModel.clear();
        Cours coursSelectionne = (Cours) dropdownCours.getSelectedItem();

        if (coursSelectionne != null) {
            // C'EST ICI QU'ON UTILISE LA MAP DE GROUPE :
            // On récupère la Map<String, Etudiant> et on prend ses .values()
            for (Etudiant etu : coursSelectionne.getGroupe().getMembres().values()) {
                listModel.addElement(etu);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Action 1: L'utilisateur a changé de cours
        if (e.getSource() == dropdownCours) {
            mettreAJourListeEtudiants();
        }

        // Action 2: L'utilisateur a cliqué sur "Enregistrer"
        if (e.getSource() == boutonEnregistrerNote) {
            try {
                Cours coursSelectionne = (Cours) dropdownCours.getSelectedItem();
                Etudiant etuSelectionne = listeEtudiants.getSelectedValue();

                if (coursSelectionne == null || etuSelectionne == null) {
                    throw new Exception("Veuillez sélectionner un cours ET un étudiant.");
                }

                double valeur = Double.parseDouble(champNote.getText());

                // 3. Appeler le constructeur de Note (qui lance ses exceptions)
                Note nouvelleNote = new Note(etuSelectionne, coursSelectionne, valeur);

                // 4. C'EST ICI QU'ON UTILISE LA MAP DE COURS :
                // On appelle la méthode qui fait .put(etudiantId, nouvelleNote)
                coursSelectionne.ajouterNote(nouvelleNote);

                // 5. Succès
                JOptionPane.showMessageDialog(this, "Note enregistrée pour " + etuSelectionne.getPrenom());
                champNote.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : La note doit être un nombre.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        }
    }
}