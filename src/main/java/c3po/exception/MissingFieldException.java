package c3po.exception;

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
        super(String.format(
                "My sincerest apologies, but the field '%s' seems to be missing. Let's try that again, shall we?",
                field));
    }
}
