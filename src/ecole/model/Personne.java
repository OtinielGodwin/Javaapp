package ecole.model;

import ecole.exception.AgeInvalidException;
import ecole.exception.NomInvalidException;
import java.util.Objects;

/**
 * Représente la classe de base pour tout individu (personne) dans le système.
 * Avec les informations de base comme le nom, le prénom, l'âge et le genre.
 * Elle inclut des règles de validation pour s'assurer de la cohérence des données.
 */
public class Personne {
    /** Le nom de famille de la personne. Utilisable par les classes enfants. */
    protected String nom;
    /** Le prénom de la personne. Utilisable par les classes enfants. */
    protected String prenom;
    /** L'âge de la personne. Utilisable par les classes enfants. */
    protected int age;
    /** Le genre de la personne. Utilisable par les classes enfants. */
    protected Genre genre;
    /** Un compteur statique pour suivre le nombre total d'objets Personne créés. */
    protected static int nbPersonnes;
    /**
     * Constructeur principal pour créer un objet Personne complet.
     * Avec les exceptions créées, on vérifie les validités du nom, du prénom et de l'âge.
     *
     * @param leNom Le nom de la personne. Ne peut pas être nul, vide ou contenir des chiffres.
     * @param lePrenom Le prénom de la personne. Ne peut pas être nul, vide ou contenir des chiffres.
     * @param lAge L'âge de la personne. Ne peut pas être négatif.
     * @param leGenre Le genre de la personne.
     * @throws NomInvalidException Si le nom ou le prénom est nul ou vide.
     * @throws IllegalArgumentException Si le nom ou le prénom contient un chiffre.
     * @throws AgeInvalidException Si l'âge est négatif.
     */
    public Personne(String leNom, String lePrenom, int lAge, Genre leGenre) {
        // Validation des arguments et levée des exceptions
        if (leNom == null || leNom.isEmpty()) {
            throw new NomInvalidException("Le nom ne peut pas être nul ou vide.");
        }
        // Vérifie si le nom contient des chiffres
        for (char c : leNom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffres. Reçu : " + lePrenom);
            }
        }
        if (lePrenom == null || lePrenom.isEmpty()){
            throw new NomInvalidException("Le prenom ne peut pas être nul ou vide.");
        }
        // Vérifie si le prénom contient des chiffres
        for (char c : lePrenom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le prénom ne peut pas contenir de chiffres. Reçu : " + lePrenom);
            }
        }
        if (lAge < 0) {
            throw new AgeInvalidException("L'âge renseigné ne doit pas être négatif. Valeur reçue : " + lAge);
        }

        // Initialisation des attributs
        this.nom = leNom;
        this.prenom = lePrenom;
        this.age = lAge;
        this.genre = leGenre;

        // Incrémentation du compteur statique
        nbPersonnes++;
    }

    /**
     * Constructeur secondaire, par défaut.
     * Utilisé pour créer une Personne avec un prénom donné et des valeurs par défaut
     * pour les autres attributs ("Snow" pour le nom, 0 pour l'âge, AUTRE pour le genre).
     *
     * @param lePrenom Le prénom de la personne.
     */
    Personne(String lePrenom) {
        nom = "Snow";
        prenom = lePrenom;
        age = 0;
        genre = Genre.AUTRE;
        nbPersonnes = nbPersonnes + 1;
    }

    // --- Getters et Setters ---

    /**
     * Récupère le nom de famille de la personne.
     * @return Le nom (String).
     */
    public String getnom() {
        return nom;
    }

    /**
     * Modifie le nom de famille de la personne.
     * @param nouveaunom Le nouveau nom à assigner.
     * @throws IllegalArgumentException Si le nouveau nom est nul, vide ou contient des chiffres.
     */
    public void setnom (String nouveaunom) {
        if (nouveaunom == null || nouveaunom.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être nul ou vide.");
        }
        for (char c : nouveaunom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffres.");
            }
        }
        this.nom = nouveaunom;
    }

    /**
     * Récupère le prénom de la personne.
     * @return Le prénom (String).
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Modifie le prénom de la personne.
     * @param nouveauPrenom Le nouveau prénom à assigner.
     * @throws IllegalArgumentException Si le nouveau prénom est nul, vide ou contient des chiffres.
     */
    public void setPrenom(String nouveauPrenom) {
        if (nouveauPrenom == null || nouveauPrenom.isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être nul ou vide.");
        }
        for (char c : nouveauPrenom.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("Le prénom ne peut pas contenir de chiffres.");
            }
        }
        this.prenom = nouveauPrenom;
    }

    /**
     * Affiche l'âge de la personne dans la console (pour le débogage).
     */
    public void afficheAge() {
        System.out.println(age);
    }

    // --- Méthodes surchargées ---

    /**
     * Renvoie une description textuelle de l'objet Personne.
     * @return Une chaîne de caractères formatée.
     */
    @Override
    public String toString() {
        return "Je me nomme " + prenom + nom + "et j'ai " + age + " ans";
    }

    /**
     * Compare cet objet Personne à un autre objet pour vérifier l'égalité.
     * Deux personnes sont considérées comme égales si elles ont le même nom, prénom et âge.
     *
     * @param o est l'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        // Vérification du type (instanceof)
        if (!(o instanceof Personne personne)) return false;

        // Vérification de l'égalité des attributs
        return age == personne.age && Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom);
    }

    /**
     * Génère un code de hachage (hash code) pour cet objet.
     * Important pour une utilisation dans des collections comme HashMap ou HashSet.
     *
     * @return Un 'int' représentant le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        // Utilise les attributs nom, prénom, âge et genre pour générer le code
        return Objects.hash(nom, prenom, age, genre);
    }
}