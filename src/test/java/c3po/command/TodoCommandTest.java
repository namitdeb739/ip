package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import c3po.exception.TaskNotFoundException;
import c3po.task.Task;
import c3po.task.Todo;

/**
 * Tests the TodoCommand class.
 */
public class TodoCommandTest extends InputOutputCommandTest {

    /**
     * Tests the execute method.
     */
    @Test
    public void execute_addsTodoTask() {
        TodoCommand todoCommand = new TodoCommand("read book");
        todoCommand.execute(this.tasks, this.ui, this.storage);

        assertEquals(1, this.tasks.size());
        Task task = null;
        try {
            task = this.tasks.get(0);
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }
        assertTrue(task instanceof Todo);
        assertEquals("[T][ ] read book", task.toString());

        String expectedOutput = "Very well, sir, I am now adding this task:\n"
                + "[T][ ] read book\nNow you have 1 tasks in the list.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the execute method with tags.
     */
    @Test
    public void execute_addsTodoTaskWithTags() {
        TodoCommand todoCommand = new TodoCommand("read book #leisure #reading");
        todoCommand.execute(this.tasks, this.ui, this.storage);

        assertEquals(1, this.tasks.size());
        Task task = null;
        try {
            task = this.tasks.get(0);
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }
        assertTrue(task instanceof Todo);
        assertEquals("[T][ ] read book #leisure #reading", task.toString());

        String expectedOutput = "Very well, sir, I am now adding this task:\n"
                + "[T][ ] read book #leisure #reading\nNow you have 1 tasks in the list.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}

