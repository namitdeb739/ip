package c3po.command;

/**
 * Represents a command that requires an index.
 */
public abstract class IndexedCommand extends Command {
    protected int index;

    /**
     * Constructs an IndexedCommand.
     *
     * @param index Index of the task.
     */
    public IndexedCommand(int index) {
        this.index = index;
    }

}