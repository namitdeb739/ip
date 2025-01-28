/**
 * Represents a task with a description and a status indicating whether it is
 * done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The task is initially
     * not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. "X" if the task is done, otherwise a
     * space.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task. The string includes the
     * status icon and the description of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task in the format to be saved in
     * a file.
     *
     * @return The string representation of the task in the format to be saved in a
     *         file.
     */
    public abstract String toFileString();

}
