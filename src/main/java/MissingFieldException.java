/**
 * Exception thrown when a field is missing.
 */
public class MissingFieldException extends Exception {
    /**
     * Constructs a MissingFieldException with the specified field.
     *
     * @param field The field that is missing.
     */
    public MissingFieldException(String field) {
        super("The field " + field + " is missing.");
    }
}