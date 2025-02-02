package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a find command with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
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
        TaskList foundTasks = tasks.find(this.keyword);
        ui.find(foundTasks);
    }

    /**
     * Returns the string representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String getString() {
        return "find";
    }
}
