package c3po.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a description and a time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description and time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, String... tags) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event. The string includes the status icon, the
     * description of the event and the time of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (on: %s to %s)%s", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.hasTags() ? String.format(" %s", this.tagString()) : "");
    }

    /**
     * Returns the string representation of the event in the format to be saved in a file.
     *
     * @return The string representation of the event in the format to be saved in a file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s%s", this.isDone ? 1 : 0, this.description,
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.hasTags() ? String.format(" | %s", this.tagString()) : "");
    }
}
