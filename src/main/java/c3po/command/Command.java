package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    public abstract void execute(TaskList tasks, UserInterface ui,
            Storage storage);

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}