/**
 * Represents an exception that is thrown when a task is not found.
 */
public class TaskNotFoundException extends Exception {
    /**
     * Constructs a TaskNotFoundException with the specified task number.
     *
     * @param taskNumber The task number that is missing.
     */
    public TaskNotFoundException(int taskNumber) {
        super("Task " + taskNumber + " not found.");
    }
}