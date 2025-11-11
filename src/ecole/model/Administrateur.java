package ecole.model;

/**
 * Représente l'utilisateur ayant les droits d'administration.
 * Sert uniquement pour l'authentification et le lancement de l'AdminInterface.
 */
public class Administrateur {

    // Identifiants statiques et fixes (simples pour ce projet)
    private static final String LOGIN_ADMIN = "admin";
    private static final String PWD_ADMIN = "1234";

    /**
     * Vérifie les identifiants de connexion.
     * @param login Le nom d'utilisateur entré.
     * @param motDePasse Le mot de passe entré.
     * @return true si les identifiants correspondent aux valeurs statiques.
     */
    public static boolean verifierConnexion(String login, String motDePasse) {
        // Utilisation de .equals() pour comparer les chaînes
        return LOGIN_ADMIN.equals(login) && PWD_ADMIN.equals(motDePasse);
    }
}