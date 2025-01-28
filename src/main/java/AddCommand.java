/**
 * Represents a command that adds a task to the task list.
 */
public abstract class AddCommand extends Command {
    protected String description;

    /**
     * Constructs an AddCommand.
     *
     * @param description Description of the task.
     */
    public AddCommand(String description) {
        this.description = description;
    }
}