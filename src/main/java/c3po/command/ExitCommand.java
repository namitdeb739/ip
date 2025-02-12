package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the chatbot.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        String savedTasks = storage.saveTasks(tasks);
        this.response = ui.close(savedTasks);
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.BYE;
    }

}
