package ecole.exception;
/**
 * Une exception non-vérifiée (RuntimeException) lancée lorsqu'une tentative
 * est faite pour créer une personne avec un âge invalide (Ex: négatif).
 */
public class AgeInvalidException extends RuntimeException {
    /**
     * Construit une nouvelle exception AgeInvalidException avec un message d'erreur détaillé.
     *
     * @param message contient le message d'erreur qui explique pourquoi l'âge est invalide.
     * (Ce message est hérité de RuntimeException).
     */
    public AgeInvalidException(String message) {
        super(message);
    }
}
