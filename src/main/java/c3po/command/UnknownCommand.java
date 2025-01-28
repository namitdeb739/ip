package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command that is not recognised by the program.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showUnknownCommand();
    }

}