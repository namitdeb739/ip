import java.time.LocalDateTime;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends AddCommand {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventCommand.
     *
     * @param description Description of the task.
     * @param from        Start time of the task.
     * @param to          End time of the task.
     */
    public EventCommand(String description, LocalDateTime from,
            LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = new Event(this.description, this.from, this.to);
        tasks.add(task);
        ui.add(task, tasks.size());
    }

}