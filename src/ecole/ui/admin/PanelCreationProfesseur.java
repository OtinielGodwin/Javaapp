// Corrigé : le package est maintenant "ecole.ui.admin"
package ecole.ui.admin;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Corrigé : tous les imports pointent vers "ecole.model" (singulier)
import ecole.model.Professeur;
import ecole.model.Genre;
import ecole.exception.AgeInvalidException;
import ecole.exception.SalaireInvalidException;

/**
 * C'est le panneau (JPanel) pour l'onglet "Créer Professeur".
 */
public class PanelCreationProfesseur extends JPanel implements ActionListener {

    private List<Professeur> listeProfesseurs;
    private JTextField champNom, champPrenom, champAge, champMatiere, champSalaire;
    private JButton boutonAjouterProf;

    public PanelCreationProfesseur(List<Professeur> liste) {
        this.listeProfesseurs = liste;
        this.setLayout(new GridLayout(6, 2, 10, 10));

        this.add(new JLabel("Nom :"));
        champNom = new JTextField();
        this.add(champNom);

        this.add(new JLabel("Prénom :"));
        champPrenom = new JTextField();
        this.add(champPrenom);

        this.add(new JLabel("Age :"));
        champAge = new JTextField();
        this.add(champAge);

        this.add(new JLabel("Matière :"));
        champMatiere = new JTextField();
        this.add(champMatiere);

        this.add(new JLabel("Salaire :"));
        champSalaire = new JTextField();
        this.add(champSalaire);

        boutonAjouterProf = new JButton("Ajouter Professeur");
        this.add(boutonAjouterProf);
        boutonAjouterProf.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAjouterProf) {
            try {
                String nom = champNom.getText();
                String prenom = champPrenom.getText();
                int age = Integer.parseInt(champAge.getText());
                String matiere = champMatiere.getText();
                double salaire = Double.parseDouble(champSalaire.getText());

                // On utilise les classes importées de "ecole.model"
                Professeur prof = new Professeur(nom, prenom, age, Genre.AUTRE, matiere, salaire);

                this.listeProfesseurs.add(prof);
                JOptionPane.showMessageDialog(this, "Professeur " + prenom + " ajouté !");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : L'âge et le salaire doivent être des nombres.");
            } catch (IllegalArgumentException | AgeInvalidException | SalaireInvalidException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        }
    }
}