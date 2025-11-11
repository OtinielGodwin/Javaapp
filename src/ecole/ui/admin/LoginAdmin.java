package ecole.ui.admin;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// On importe le modèle Admin et les moteurs
import ecole.model.Administrateur;
import ecole.gestion.Groupe;
import ecole.gestion.Cours;
import ecole.model.Professeur;

/**
 * Fenêtre de connexion pour l'Administrateur.
 */
public class LoginAdmin extends JFrame implements ActionListener {

    private Groupe groupeCible;
    private List<Professeur> listeProfesseurs;
    private List<Cours> listeCours;

    private JTextField champLogin;
    private JPasswordField champPwd; // Utilisation de JPasswordField pour cacher le mot de passe
    private JButton boutonConnexion;

    public LoginAdmin(Groupe groupe, List<Professeur> profs, List<Cours> cours) {
        this.groupeCible = groupe;
        this.listeProfesseurs = profs;
        this.listeCours = cours;

        this.setTitle("Connexion Administrateur");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(3, 2, 10, 10));

        this.add(new JLabel("Identifiant :"));
        champLogin = new JTextField(10);
        this.add(champLogin);

        this.add(new JLabel("Mot de passe :"));
        champPwd = new JPasswordField(10);
        this.add(champPwd);

        this.add(new JPanel()); // Case vide
        boutonConnexion = new JButton("Se Connecter");
        boutonConnexion.addActionListener(this);
        this.add(boutonConnexion);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonConnexion) {
            String login = champLogin.getText();
            // Récupère le mot de passe sous forme de String
            String motDePasse = new String(champPwd.getPassword());

            if (Administrateur.verifierConnexion(login, motDePasse)) {
                // Succès : Lance l'interface Admin
                JOptionPane.showMessageDialog(this, "Connexion Admin réussie !");
                new AdminInterface(groupeCible, listeProfesseurs, listeCours);
                this.dispose();
            } else {
                // Échec
                JOptionPane.showMessageDialog(this, "Identifiants administrateur incorrects.", "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}