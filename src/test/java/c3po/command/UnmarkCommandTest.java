package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import c3po.task.Task;
import c3po.task.Todo;
import c3po.exception.TaskNotFoundException;

public class UnmarkCommandTest extends IOCommandTest {

    @Test
    public void execute_validIndex_unmarksTaskAsNotDone() {
        Task task = new Todo("read book");
        task.mark();
        this.tasks.add(task);

        UnmarkCommand unmarkCommand = new UnmarkCommand(0);
        unmarkCommand.execute(this.tasks, this.ui, this.storage);

        try {
            Task unmarkedTask = this.tasks.get(0);
            assertEquals("[T][ ] read book", unmarkedTask.toString());
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }

        String expectedOutput = "I really don't see how that's going to help.\n"
                + "[T][ ] read book\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_invalidIndex_showsTaskNotFoundError() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(0);
        unmarkCommand.execute(this.tasks, this.ui, this.storage);

        String expectedOutput =
                "I'm terribly sorry, sir, but I cannot find this task.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
