
package ecole.ui.admin;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// On importe tout ce dont on a besoin
import ecole.gestion.Groupe;
import ecole.model.Etudiant;
import ecole.model.Genre;
import ecole.model.Filiere;
import ecole.exception.AgeInvalidException;
import ecole.exception.Etudiantpresent;

/**
 * C'est le panneau (JPanel) pour l'onglet "Créer Étudiant".
 */
public class PanelCreationEtudiant extends JPanel implements ActionListener {

    private Groupe groupeCible;
    private JTextField champNom, champPrenom, champAge, champNumEtu;
    private JButton boutonAjouterEtu;

    public PanelCreationEtudiant(Groupe groupe) {
        this.groupeCible = groupe;
        this.setLayout(new GridLayout(5, 2, 10, 10));

        this.add(new JLabel("Nom :"));
        champNom = new JTextField();
        this.add(champNom);

        this.add(new JLabel("Prénom :"));
        champPrenom = new JTextField();
        this.add(champPrenom);

        this.add(new JLabel("Age :"));
        champAge = new JTextField();
        this.add(champAge);

        this.add(new JLabel("N° Étudiant :"));
        champNumEtu = new JTextField();
        this.add(champNumEtu);

        boutonAjouterEtu = new JButton("Ajouter Étudiant");
        this.add(boutonAjouterEtu);
        boutonAjouterEtu.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonAjouterEtu) {
            try {
                String nom = champNom.getText();
                String prenom = champPrenom.getText();
                int age = Integer.parseInt(champAge.getText());
                String numEtu = champNumEtu.getText();

                // On utilise les classes importées de "ecole.model"
                Etudiant etu = new Etudiant(nom, prenom, age, numEtu,
                        Genre.AUTRE, Filiere.BUT_SCIENCE_DES_DONNEES);

                this.groupeCible.ajouterEtudiant(etu);
                JOptionPane.showMessageDialog(this, "Étudiant " + prenom + " ajouté !");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : L'âge doit être un nombre.");
            } catch (IllegalArgumentException | AgeInvalidException | Etudiantpresent ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        }
    }
}