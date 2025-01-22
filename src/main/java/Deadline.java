/**
 * Represents a a Task which has a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline. The string includes
     * the status icon, the description of the deadline and the deadline of the
     * deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
