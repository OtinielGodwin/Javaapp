package ecole.model;

import java.util.Objects;

/**
 * Représente un Étudiant.
 * Un Étudiant est une Personne (il hérite de la classe Personne),
 * mais il a des informations en plus : un numéro d'étudiant et une filière.
 */
public class Etudiant extends Personne {

    /** L'identifiant unique de l'étudiant. */
    private String numeroEtudiant;

    /**
     * Construit un nouvel objet Étudiant.
     *
     * @param nom Le nom de famille (passé à Personne).
     * @param prenom Le prénom (passé à Personne).
     * @param age L'âge (passé à Personne).
     * @param numeroEtudiant L'ID unique de cet étudiant.
     * @param legenre Le genre (passé à Personne).
     * @param filiere La filière de l'étudiant.
     */
    public Etudiant(String nom, String prenom, int age, String numeroEtudiant , Genre legenre, Filiere filiere ) {
        // Appel du constructeur de la classe "mère" (Personne)
        // pour initialiser le nom, le prénom, l'âge et le genre.
        super(nom,prenom,age,legenre);
        //Initialiser les attributs qui sont spécifiques à l'Étudiant.
        this.numeroEtudiant =  numeroEtudiant;
    }

    /**
     * Récupère le numéro d'étudiant.
     * @return L'ID (String) de l'étudiant.
     */
    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    /**
     * Modifie le numéro d'étudiant.
     * @param numeroEtudiant Le nouvel ID à assigner.
     */
    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    /**
     * Renvoie une description textuelle de l'objet Étudiant.
     * On "redéfinit" (Override) la méthode de Personne pour y ajouter
     * les informations de l'étudiant.
     *
     * @return Une chaîne de caractères (ex: "Jean Dupont, 20 ans, HOMME - Étudiant n°e12345").
     */
    @Override
    public String toString() {
        // On appelle la méthode toString() de Personne (avec 'super')
        // et on ajoute nos propres informations à la fin.
        return super.toString() + " - Étudiant n°" + numeroEtudiant;
    }

    /**
     * Compare cet Étudiant à un autre objet pour vérifier s'ils sont "égaux".
     * Deux étudiants sont considérés comme égaux si :
     * 1. Leurs informations de Personne (nom, prénom, âge, genre) sont identiques.
     * 2. Leurs numéros d'étudiant sont identiques.
     *
     * @param o L'objet a comparé avec cet étudiant.
     * @return true s'ils sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        // Si 'o' est nul ou n'est pas de la même classe (Etudiant), ils ne sont pas égaux.
        if (o == null || getClass() != o.getClass()) return false;

        // On vérifie d'abord si la partie "Personne" est égale
        // en appelant la méthode equals() de la classe Personne.
        if (!super.equals(o)) return false;

        // Si la partie Personne est égale, on convertit 'o' en Etudiant
        // pour pouvoir comparer le numéro d'étudiant.
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(numeroEtudiant, etudiant.numeroEtudiant);
    }

    /**
     * Génère un "code" (hash code) pour cet objet.
     * Règle Java : Si on redéfinit equals(), on doit aussi redéfinir hashCode().
     * On combine le hash code de la partie Personne avec celui du numéro d'étudiant.
     *
     * @return un 'int' unique qui représente l'objet.
     */
    @Override
    public int hashCode() {
        // On utilise le 'super.hashCode()' (qui combine nom, prénom, âge, genre)
        // et on le combine avec 'numeroEtudiant'.
        return Objects.hash(super.hashCode(), numeroEtudiant);
    }
}