package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * This class is used to represent an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command.
     *
     * @param tasks   The task list to manage.
     * @param ui      The user interface to manage.
     * @param storage The storage to manage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.requestInstructions();
    }

}
