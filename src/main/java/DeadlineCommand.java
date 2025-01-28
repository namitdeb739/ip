import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends AddCommand {
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description Description of the task.
     * @param by          Deadline of the task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = new Deadline(this.description, this.by);
        tasks.add(task);
        ui.add(task, tasks.size());
    }

}