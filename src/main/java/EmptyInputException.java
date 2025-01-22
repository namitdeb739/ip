/**
 * Exception thrown when the input is empty.
 */
class EmptyInputException extends Exception {
    /**
     * Constructs an EmptyInputException with a default message.
     */
    public EmptyInputException() {
        super("The input cannot be empty.");
    }
}