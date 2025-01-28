/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * 
     * @param tasks   The task list to manage.
     * @param ui      The user interface to manage.
     * @param storage The storage to manage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.list(tasks);
    }
}