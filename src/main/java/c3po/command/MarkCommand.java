package c3po.command;

import c3po.exception.TaskNotFoundException;
import c3po.storage.Storage;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends IndexedCommand {
    /**
     * Constructs a MarkCommand.
     *
     * @param index Index of the task.
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        try {
            Task task = tasks.mark(this.index);
            this.response = ui.mark(task);
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
        return CommandEnum.MARK;
    }
}
