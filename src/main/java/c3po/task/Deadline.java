package c3po.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a a Task which has a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, LocalDateTime by, String... tags) {
        super(description, tags);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline. The string includes the status icon, the
     * description of the deadline and the deadline of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)%s", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.hasTags() ? String.format(" %s", this.tagString()) : "");
    }

    /**
     * Returns the string representation of the deadline in the format to be saved in a file.
     *
     * @return The string representation of the deadline in the format to be saved in a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s%s", this.isDone ? 1 : 0, this.description,
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.hasTags() ? String.format(" | %s", this.tagString()) : "");
    }
}
