package gr.perisnik.cj.schoolapp.service.exceptions;

/**
 * Γενική εξαίρεση για σφάλματα στο Service Layer.
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    // Κατασκευαστής με μήνυμα
    public ServiceException(String message) {
        super(message);
    }

    // Κατασκευαστής με μήνυμα και αιτία (ρίχνει άλλη εξαίρεση)
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}