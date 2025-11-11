package ecole.model;

import java.util.Objects;
import ecole.exception.SalaireInvalidException;

/**
 * Représente un Professeur.
 * Un Professeur EST une Personne (il hérite de la classe Personne),
 * mais il a des informations professionnelles en plus : la matière enseignée et son salaire.
 */
public class Professeur extends Personne {

    // --- Attributs ---

    /** La matière enseignée par ce professeur */
    private String matiere;

    /** Le salaire mensuel du professeur (utilisé comme un nombre décimal double). */
    private double salaire;

    /**
     * Constructeur pour créer un objet Professeur complet.
     * Il vérifie que le salaire n'est pas négatif.
     *
     * @param nom Le nom de la personne (passé à Personne).
     * @param prenom Le prénom de la personne (passé à Personne).
     * @param age L'âge de la personne (passé à Personne).
     * @param genre Le genre de la personne (passé à Personne).
     * @param matiere La matière enseignée par le professeur.
     * @param salaire Le salaire du professeur. Ne dois pas être négatif.
     * @throws SalaireInvalidException Si le salaire renseigné est négatif.
     */
    public Professeur(String nom, String prenom, int age, Genre genre,
                      String matiere, double salaire)
    {
        // Appel du constructeur de la classe "mère" (Personne) pour initialiser les attributs de base.
        super(nom, prenom, age, genre);

        // Vérification de la règle métier spécifique au Professeur.
        if (salaire < 0) {
            throw new SalaireInvalidException("Le salaire ne peut pas être négatif. Valeur reçue : " + salaire);
        }

        // Initialisation des attributs spécifiques au Professeur.
        this.matiere = matiere;
        this.salaire = salaire;
    }

    // --- Getters et Setters ---

    /**
     * Récupère la matière enseignée par le professeur.
     * @return La matière (String).
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * Récupère le salaire du professeur.
     * @return Le salaire (double).
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * Modifie la matière enseignée par le professeur.
     * @param matiere La nouvelle matière.
     */
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    /**
     * Modifie le salaire du professeur.
     * @param salaire Le nouveau salaire à assigner.
     * @throws SalaireInvalidException Si le salaire est négatif.
     */
    public void setSalaire(double salaire) {
        // Vérification de la règle métier.
        if (salaire < 0) {
            throw new SalaireInvalidException("Le salaire ne peut pas être négatif. Valeur reçue : " + salaire);
        }
        this.salaire = salaire;
    }

    // --- Méthodes surchargées (Override) ---

    /**
     * Renvoie une description textuelle de l'objet Professeur.
     * On redéfinit la méthode de Personne pour y ajouter la matière enseignée.
     *
     * @return Une chaîne de caractères formatée.
     */
    @Override
    public String toString() {
        // On utilise super.toString() pour récupérer la description de la Personne,
        // puis on ajoute les informations spécifiques au Professeur.
        return super.toString() + " | Enseigne: " + this.matiere;
    }

    /**
     * Compare cet objet Professeur à un autre objet pour vérifier l'égalité.
     * Deux professeurs sont considérés comme égaux si :
     * 1. Leurs informations de Personne sont identiques (nom, prénom, âge, genre).
     * 2. Leurs matières et salaires sont identiques.
     *
     * @param o est l'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // On vérifie d'abord si la partie "Personne" est égale.
        if (!super.equals(o)) return false;

        // Si la partie Personne est égale, on compare les attributs spécifiques.
        Professeur that = (Professeur) o;
        return Double.compare(that.salaire, salaire) == 0 && // Comparaison de doubles
                Objects.equals(matiere, that.matiere); // Comparaison de String
    }

    /**
     * Génère un code de hachage (hash code) pour cet objet.
     * On combine le hash code de la partie Personne avec les attributs spécifiques.
     *
     * @return Un 'int' unique qui représente l'objet.
     */
    @Override
    public int hashCode() {
        // On combine le hash code hérité avec la matière et le salaire.
        return Objects.hash(super.hashCode(), matiere, salaire);
    }
}