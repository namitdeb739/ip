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
}