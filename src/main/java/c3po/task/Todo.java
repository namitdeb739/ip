package c3po.task;

/**
 * Represents a task that has no date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the specified description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo. The string includes the
     * status icon and the description of the todo.
     *
     * @return The string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo in the format to be saved
     * in a file.
     *
     * @return The string representation of the todo in the format to be saved
     *         in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
