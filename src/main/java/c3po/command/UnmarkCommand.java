package c3po.command;

import c3po.exception.TaskNotFoundException;
import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.task.Task;
import c3po.ui.UserInterface;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends IndexedCommand {
    /**
     * Constructs an unmark command with the specified index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Executes the command to unmark a task.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        try {
            Task task = tasks.unmark(index);
            ui.unmark(task);
        } catch (TaskNotFoundException e) {
            ui.showTaskNotFoundError();
        }
    }

}