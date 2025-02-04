package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    private String[] description;

    /**
     * Constructs a find command with the specified keyword.
     *
     * @param keywords The keyword to search for.
     */
    public FindCommand(String... keywords) {
        this.description = keywords;
    }

    /**
     * Executes the find command.
     *
     * @param tasks The task list to manage.
     * @param ui The user interface to manage.
     * @param storage The storage to manage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        TaskList foundTasks = tasks.find(this.description);
        this.response = ui.find(foundTasks);
    }

    /**
     * Returns the type of command.
     *
     * @return The type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.FIND;
    }
}
