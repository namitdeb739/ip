package c3po.command;

/**
 * Represents a command that creates a task.
 */
public abstract class CreateTaskCommand extends DesciptionCommand {
    protected String[] tags;

    /**
     * Constructs a CreateTaskCommand.
     *
     * @param tags Tags of the task.
     */
    public CreateTaskCommand(String description, String... tags) {
        super(description);
        this.tags = tags;
    }
}
