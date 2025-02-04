package c3po.command;

/**
 * Represents a command that adds a task to the task list.
 */
public abstract class DesciptionCommand extends Command {
    protected String description;

    /**
     * Constructs an AddCommand.
     *
     * @param description Description of the task.
     */
    public DesciptionCommand(String description) {
        this.description = description;
    }
}
