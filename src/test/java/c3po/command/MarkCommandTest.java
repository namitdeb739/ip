package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import c3po.exception.TaskNotFoundException;
import c3po.task.Task;
import c3po.task.Todo;

public class MarkCommandTest extends IOCommandTest {

    @Test
    public void execute_validIndex_marksTaskAsDone() {
        Task task = new Todo("read book");
        this.tasks.add(task);

        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(this.tasks, this.ui, this.storage);

        try {
            assertEquals("[T][X] read book", this.tasks.get(0).toString());
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }

        String expectedOutput =
                "Oh my! The odds of successfully completing this task were 3720 to 1.\n" + task
                        + "\n____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_invalidIndex_showsTaskNotFoundError() {
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(this.tasks, this.ui, this.storage);

        String expectedOutput =
                "I'm terribly sorry, sir, but I cannot find this task.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
