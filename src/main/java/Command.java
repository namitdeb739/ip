/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     */
    public abstract void execute(TaskList tasks, UserInterface ui,
            Storage storage);

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}