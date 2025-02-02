package c3po.command;

import c3po.storage.Storage;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.task.Todo;
import c3po.ui.UserInterface;

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
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = new Todo(this.description);
        tasks.add(task);
        ui.add(task, tasks.size());
    }

    /**
     * Returns the string representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String getString() {
        return "todo";
    }
}
