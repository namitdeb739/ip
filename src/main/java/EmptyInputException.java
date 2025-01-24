/**
 * Exception thrown when the input is empty.
 */
class EmptyInputException extends Exception {
    /**
     * Constructs an EmptyInputException with a default message.
     */
    public EmptyInputException() {
        super("Excuse me, sir. Might I inquire what's going on? You seem to be requesting me to do nothing.");
    }
}