package c3po.exception;

/**
 * Represents an exception that is thrown when an error occurs while loading
 * tasks from the storage file.
 */
public class StorageLoadingException extends Exception {
    /**
     * Constructs a StorageLoadingException with a default message.
     */
    public StorageLoadingException() {
        super("I'm sorry, sir. I'm afraid I can't do that. I seem to have encountered an error while loading the tasks.");
    }
}
