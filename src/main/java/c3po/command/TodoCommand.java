package c3po.command;

import c3po.storage.Storage;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.task.Todo;
import c3po.ui.UserInterface;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends CreateTaskCommand {
    /**
     * Constructs a TodoCommand.
     *
     * @param description Description of the task.
     * @param tags Tags of the task.
     */
    public TodoCommand(String description, String... tags) {
        super(description, tags);
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
        Task task = new Todo(this.description, this.tags);
        tasks.add(task);
        this.response = ui.add(task, tasks.size());
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.TODO;
    }
}
