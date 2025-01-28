/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends IndexedCommand {
    /**
     * Constructs a DeleteCommand.
     *
     * @param index Index of the task.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Executes the command to delete a task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        try {
            Task task = tasks.delete(index);
            ui.delete(task, tasks.size());
        } catch (TaskNotFoundException e) {
            ui.showTaskNotFoundError();
        }
    }
}