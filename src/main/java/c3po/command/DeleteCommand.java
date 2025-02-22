package c3po.command;

import c3po.exception.TaskNotFoundException;
import c3po.storage.Storage;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

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
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        try {
            Task task = tasks.delete(this.index);
            this.response = ui.delete(task, tasks.size());
        } catch (TaskNotFoundException e) {
            this.response = ui.taskNotFoundError();
        }
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.DELETE;
    }

}
