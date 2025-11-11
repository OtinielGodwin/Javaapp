package ecole.model;

import ecole.gestion.Cours;

/**
 * Représente des Notes académiques.
 * Cet objet sert de lien pour associer une valeur (la note) 
 * à un Étudiant spécifique et à un Cours particulier.
 */
public class Note {

    /** L'étudiant qui a reçu la note. */
    private final Etudiant etudiant;

    /** Le cours pour lesquelles la note a été attribuée. */
    private final Cours cours;

    /** La valeur numérique de la note (par exemple, 15.5). */
    private double valeur;

    /**
     * Construit un nouvel objet Note.
     * @param etudiant L'objet Etudiant concerné.
     * @param cours L'objet Cours concerné.
     * @param valeur La note attribuée (doit être entre 0 et 20).
     */
    public Note(Etudiant etudiant, Cours cours, double valeur) {
        // Validation des arguments
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être nul.");
        }
        if (cours == null) {
            throw new IllegalArgumentException("Le cours ne peut pas être nul.");
        }
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20.");
        }

        this.etudiant = etudiant;
        this.cours = cours;
        this.valeur = valeur;
    }

    /**
     * Récupère l'objet Etudiant associé à cette note.
     * @return L'objet Etudiant.
     */
    public Etudiant getEtudiant() {
        return etudiant;
    }

    /**
     * Récupère l'objet Cours associé à cette note.
     * @return L'objet Cours.
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * Récupère la valeur numérique de la note.
     * @return La note (double).
     */
    public double getValeur() {
        return valeur;
    }

    /**
     * Modifie la valeur de la note.
     * @param valeur La nouvelle note.
     */
    public void setValeur(double valeur) {
        // On vérifie la règle 0-20 à nouveau.
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20.");
        }
        this.valeur = valeur;
    }

    /**
     * Renvoie une description textuelle de l'objet Note.
     * @return Une chaîne de caractères formatée.
     */
    @Override
    public String toString() {
        return "Note: " + etudiant.getPrenom() + " " + etudiant.getnom() +
                " | Cours: " + cours.getNom() +
                " | Note: " + valeur + "/20";
    }
}