package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import c3po.task.Task;
import c3po.task.Todo;

/**
 * Tests for DeleteCommand.
 */
public class DeleteCommandTest extends IoCommandTest {

    /**
     * Tests the execute method of DeleteCommand if it deletes a task from the task list.
     */
    @Test
    public void execute_validIndex_deletesTask() {
        Task task = new Todo("read book");
        this.tasks.add(task);
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(this.tasks, this.ui, this.storage);
        String expectedOutput =
                "Surrender is a perfectly acceptable alternative in extreme circumstances. I have deleted this task:\n"
                        + task + "\nNow you have 0 tasks in the list.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
        assertEquals(0, this.tasks.size());
    }

    /**
     * Tests the execute method of DeleteCommand with an invalid index.
     */
    @Test
    public void execute_invalidIndex_showsTaskNotFoundError() {
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(this.tasks, this.ui, this.storage);
        String expectedOutput =
                "I'm terribly sorry, sir, but I cannot find this task.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
