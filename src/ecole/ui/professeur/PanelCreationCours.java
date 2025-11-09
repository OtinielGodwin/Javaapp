package ecole.ui.professeur;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// On importe les "moteurs"
import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.Professeur;

/**
 * C'est le panneau (JPanel) pour l'onglet "Créer un Cours".
 */
public class PanelCreationCours extends JPanel implements ActionListener {

    private Professeur profConnecte;
    private List<Groupe> listeGroupes;
    private List<Cours> listeCours;

    private JTextField champNomCours;
    private JComboBox<Groupe> dropdownGroupes;
    private JButton boutonCreerCours;

    public PanelCreationCours(Professeur prof, List<Groupe> groupes, List<Cours> cours) {
        this.profConnecte = prof;
        this.listeGroupes = groupes;
        this.listeCours = cours;

        this.setLayout(new GridLayout(4, 2, 10, 10));

        this.add(new JLabel("Nom du Cours :"));
        champNomCours = new JTextField();
        this.add(champNomCours);

        this.add(new JLabel("Professeur Référent :"));
        this.add(new JLabel(prof.getPrenom() + " " + prof.getnom()));

        this.add(new JLabel("Attribuer au Groupe :"));
        dropdownGroupes = new JComboBox<>(groupes.toArray(new Groupe[0]));
        this.add(dropdownGroupes);

        boutonCreerCours = new JButton("Créer le Cours");
        boutonCreerCours.addActionListener(this);
        this.add(boutonCreerCours);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonCreerCours) {
            try {
                String nomCours = champNomCours.getText();
                Groupe groupeSelectionne = (Groupe) dropdownGroupes.getSelectedItem();

                // On appelle le constructeur de Cours (qui a ses propres exceptions)
                Cours nouveauCours = new Cours(nomCours, this.profConnecte, groupeSelectionne);

                // On ajoute le nouveau cours à la liste "moteur"
                this.listeCours.add(nouveauCours);

                JOptionPane.showMessageDialog(this, "Cours '" + nomCours + "' créé !");
                champNomCours.setText("");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : Veuillez sélectionner un groupe.");
            }
        }
    }
}