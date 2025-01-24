/**
 * Represents an event with a description and a time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event with the specified description and time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event. The string includes the
     * status icon, the description of the event and the time of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + from + " to " + to + ")";
    }

    /**
     * Returns the string representation of the event in the format to be saved
     * in a file.
     *
     * @return The string representation of the event in the format to be saved
     *         in a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + this.from + " | " + this.to;
    }
}