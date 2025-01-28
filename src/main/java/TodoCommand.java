/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand.
     *
     * @param description Description of the task.
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the command to add a todo task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = new Todo(this.description);
        tasks.add(task);
        ui.add(task, tasks.size());
    }
}