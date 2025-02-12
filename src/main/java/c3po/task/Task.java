package c3po.task;

import java.util.ArrayList;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description, String... tags) {
        this.description = description;
        this.isDone = false;
        for (String tag : tags) {
            this.tags.add(tag);
        }
    }

    /**
     * Returns the status icon of the task. "X" if the task is done, otherwise a space.
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
     * Returns the string representation of the task. The string includes the status icon and the
     * description of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns the string representation of the task in the format to be saved in a file.
     *
     * @return The string representation of the task in the format to be saved in a file.
     */
    public abstract String toFileString();

    /**
     * Returns true if the description of the task contains the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the description of the task contains the specified keyword.
     */
    public boolean descriptionContainsAll(String... keyword) {
        for (String k : keyword) {
            if (!this.description.toLowerCase().contains(k.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the task has all the specified tags.
     *
     * @param tags The tags to search for.
     * @return True if the task has all the specified tags.
     */
    public boolean containsTags(String... tags) {
        for (String tag : tags) {
            if (!this.tags.contains(tag)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the tags of the task.
     *
     * @return The tags of the task.
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Returns the string representation of the tags of the task.
     *
     * @return The string representation of the tags of the task.
     */
    public String tagString() {
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append("#").append(tag).append(" ");
        }
        return sb.toString();
    }

    /**
     * Returns true if the task has tags.
     *
     * @return True if the task has tags.
     */
    public boolean hasTags() {
        return !tags.isEmpty();
    }

}
