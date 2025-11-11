package ecole.exception;
/**
 *  * Une exception non-vérifiée (RuntimeException) lancée lorsqu'une tentative
 *  * est faite pour ajouter un étudiant existant dans un groupe.
 *  */
public class Etudiantpresent extends RuntimeException {
    /**
     * Construit une nouvelle exception Etudiantpresent avec un message d'erreur détaillé.
     *
     * @param message contient le message d'erreur qui explique qu'il a déjà cet étudiant inscrit dans le groupe.
     * (Ce message est hérité de RuntimeException).
     */
    public Etudiantpresent(String message) {
        super(message);
    }
}
