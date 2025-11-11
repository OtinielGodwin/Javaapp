package ecole.exception;
/**
 * Une exception non-vérifiée (RuntimeException) lancée lorsqu'une tentative
 * est faite pour créer une personne avec un nom invalide (Mélanger string et int).
 */
public class NomInvalidException extends RuntimeException {
    /**
     * Construit une nouvelle exception NomInvalidException avec un message d'erreur détaillé.
     *
     * @param message contient le message d'erreur qui explique que le nom contient des chiffres alors qu'on attend des strings.
     * (Ce message est hérité de RuntimeException).
     */
    public NomInvalidException(String message) {
        super(message);
    }
}
