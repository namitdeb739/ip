package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import c3po.exception.TaskNotFoundException;
import c3po.task.Deadline;
import c3po.task.Task;

/**
 * Tests for DeadlineCommand.
 */
public class DeadlineCommandTest extends InputOutputCommandTest {

    private DeadlineCommand deadlineCommand;

    /**
     * Sets up the DeadlineCommand for testing.
     */
    @BeforeEach
    public void setUpDeadlineCommand() {
        this.deadlineCommand =
                new DeadlineCommand("Finish project", LocalDateTime.of(2023, 10, 10, 23, 59));
    }

    /**
     * Tests the execute method of DeadlineCommand if it adds a Deadline task to the task list.
     */
    @Test
    public void execute_addsDeadlineTask() {
        this.deadlineCommand.execute(this.tasks, this.ui, this.storage);
        try {
            Task task = this.tasks.get(0);
            assertTrue(task instanceof Deadline);
            assertEquals("[D][ ] Finish project (by: 10 Oct 2023 23:59)", task.toString());
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }
    }


    /**
     * Tests the execute method of DeadlineCommand if it increases the size of the task list.
     */
    @Test
    public void execute_increasesTaskListSize() {
        int initialSize = this.tasks.size();
        this.deadlineCommand.execute(this.tasks, this.ui, this.storage);
        assertEquals(initialSize + 1, this.tasks.size());
    }
}
