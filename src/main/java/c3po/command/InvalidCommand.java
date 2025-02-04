package c3po.command;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * This class is used to represent an invalid command.
 */
public class InvalidCommand extends DesciptionCommand {
    public static final String DEFAULT_INVALID_COMMAND_MESSAGE =
            "I have a bad feeling about this...";

    /**
     * Constructs an invalid command.
     */
    public InvalidCommand() {
        super(DEFAULT_INVALID_COMMAND_MESSAGE);
    }

    /**
     * Constructs an invalid command with the specified description.
     *
     * @param description The description of the invalid command.
     */
    public InvalidCommand(String description) {
        super(description);
    }

    /**
     * Executes the invalid command.
     *
     * @param tasks The task list to manage.
     * @param ui The user interface to manage.
     * @param storage The storage to manage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        this.response = ui.invalidCommand(this.description);
    }

    /**
     * Returns the type of command.
     *
     * @return The type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.INVALID;
    }
}
