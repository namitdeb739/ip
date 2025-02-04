package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param tasks The task list to manage.
     * @param ui The user interface to manage.
     * @param storage The storage to manage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        this.response = ui.list(tasks);
    }

    /**
     * Returns the type of command.
     *
     * @return The type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.LIST;
    }

}
