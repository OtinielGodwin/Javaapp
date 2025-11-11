package ecole.exception;
/**
 * Une exception non-vérifiée (RuntimeException) lancée lorsqu'une tentative
 * est faite pour créer un professeur avec un salaire invalide (négatif).
 */
public class SalaireInvalidException extends RuntimeException {

    /**
     * Construit une nouvelle exception SalaireInvalidException avec un message d'erreur détaillé.
     *
     * @param message contient le message d'erreur qui explique pourquoi l'âge est invalide.
     * (Ce message est hérité de RuntimeException).
     */
    public SalaireInvalidException(String message) {
        super(message);
    }
}
