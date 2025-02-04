package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.Response;
import c3po.ui.UserInterface;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    protected Response response = Response.EMPTY_RESPONSE;

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the response to be displayed.
     *
     * @return Response to be displayed.
     */
    public Response getResponse() {
        return this.response;
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
     */
    public abstract CommandEnum getCommandType();
}
