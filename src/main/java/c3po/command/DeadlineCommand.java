package c3po.command;

import java.time.LocalDateTime;

import c3po.storage.Storage;
import c3po.task.Deadline;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends CreateTaskCommand {
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @param tags Tags of the task.
     */
    public DeadlineCommand(String description, LocalDateTime by, String... tags) {
        super(description, tags);
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        Task task = new Deadline(this.description, this.by, this.tags);
        tasks.add(task);
        this.response = ui.add(task, tasks.size());
    }

    /**
     * Returns the type of command.
     *
     * @return Type of command.
     */
    @Override
    public CommandEnum getCommandType() {
        return CommandEnum.DEADLINE;
    }
}
