package ecole.ui.etudiant;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


// On importe tous les "moteurs"
import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.Etudiant;


/**
 * C'est la fenêtre de connexion pour l'étudiant.
 * Elle vérifie l'ID et le Nom avant d'ouvrir l'espace personnel.
 */

public class LoginEtudiant extends JFrame implements ActionListener {

    // --- Les "Moteurs" (Données de l'application) ---
    private Groupe groupeCible;
    private List<Cours> listeDesCours;

    // --- Composants UI ---
    private JTextField champNumEtu;
    private JTextField champNom;
    private JButton boutonConnexion;

    /**
     * Constructeur de la fenêtre de Login.
     */
    public LoginEtudiant(Groupe groupe, List<Cours> cours) {
        this.groupeCible = groupe;
        this.listeDesCours = cours;

        this.setTitle("Connexion Étudiant");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 2, 10, 10));

        this.add(new JLabel("Numéro Étudiant (ID) :"));
        champNumEtu = new JTextField();
        this.add(champNumEtu);

        this.add(new JLabel("Votre Nom :"));
        champNom = new JTextField();
        this.add(champNom);

        this.add(new JPanel()); // Case vide
        boutonConnexion = new JButton("Connexion");
        boutonConnexion.addActionListener(this);
        this.add(boutonConnexion);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonConnexion) {
            try {
                String numEtu = champNumEtu.getText();
                String nom = champNom.getText();

                if (numEtu.isEmpty() || nom.isEmpty()) {
                    throw new Exception("Veuillez remplir les deux champs.");
                }

                Etudiant etudiantTrouve = groupeCible.findEtudiant(numEtu);

                if (etudiantTrouve != null && etudiantTrouve.getnom().equalsIgnoreCase(nom)) {

                    JOptionPane.showMessageDialog(this, "Connexion réussie ! Bienvenue " + etudiantTrouve.getPrenom());

                    new EtudiantInterface(etudiantTrouve, this.listeDesCours);

                    this.dispose();

                } else {
                    throw new Exception("Numéro d'étudiant ou nom incorrect.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        }
    }
}

