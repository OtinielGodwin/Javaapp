package ecole.ui.professeur;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Imports des classes métier
import ecole.gestion.Cours;
import ecole.gestion.Groupe;
import ecole.model.Professeur;
import ecole.ui.professeur.ProfInterface;

/**
 * Fenêtre de connexion pour le Professeur.
 * Elle cherche le professeur dans la liste globale par son nom/prénom.
 */
public class LoginProf extends JFrame implements ActionListener {

    private List<Professeur> listeProfesseurs;
    private List<Groupe> listeGroupes;
    private List<Cours> listeCours;

    // Composants UI
    private JTextField champPrenom;
    private JTextField champNom;
    private JButton boutonConnexion;

    public LoginProf(List<Professeur> profs, List<Groupe> groupes, List<Cours> cours) {
        this.listeProfesseurs = profs;
        this.listeGroupes = groupes;
        this.listeCours = cours;

        this.setTitle("Connexion Professeur");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme sans arrêter l'app
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 2, 10, 10));

        this.add(new JLabel("Prénom :"));
        champPrenom = new JTextField(10);
        this.add(champPrenom);

        this.add(new JLabel("Nom :"));
        champNom = new JTextField(10);
        this.add(champNom);

        this.add(new JPanel());
        boutonConnexion = new JButton("Se Connecter");
        boutonConnexion.addActionListener(this);
        this.add(boutonConnexion);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonConnexion) {
            String prenom = champPrenom.getText().trim();
            String nom = champNom.getText().trim();

            try {
                if (prenom.isEmpty() || nom.isEmpty()) {
                    throw new Exception("Veuillez entrer le nom et le prénom.");
                }

                // 1. Chercher le professeur dans la liste
                Professeur profTrouve = null;
                for (Professeur p : listeProfesseurs) {
                    // On compare le nom ET le prénom pour l'authentification (insensible à la casse)
                    if (p.getPrenom().trim().equalsIgnoreCase(prenom) &&
                            p.getnom().trim().equalsIgnoreCase(nom)) {

                        profTrouve = p;
                        break;
                    }
                }

                if (profTrouve != null) {
                    // 2. SUCCÈS : Lancer l'interface Professeur
                    JOptionPane.showMessageDialog(this, "Connexion Prof réussie !");

                    // On lance l'interface ProfInterface avec l'objet Professeur trouvé
                    new ProfInterface(profTrouve, listeGroupes, listeCours);
                    this.dispose();
                } else {
                    // 3. ÉCHEC
                    throw new Exception("Nom ou Prénom du professeur incorrect.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Échec de Connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}