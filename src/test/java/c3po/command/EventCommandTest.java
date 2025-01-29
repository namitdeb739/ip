package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import c3po.exception.TaskNotFoundException;
import c3po.task.Event;

/**
 * Tests for EventCommand.
 */
public class EventCommandTest extends IoCommandTest {

    /**
     * Tests the execute method of EventCommand if it adds an event task to the task list.
     */
    @Test
    public void execute_addsEventTask() {
        LocalDateTime from = LocalDateTime.parse("2023-12-31T10:00");
        LocalDateTime to = LocalDateTime.parse("2023-12-31T12:00");
        EventCommand eventCommand = new EventCommand("project meeting", from, to);
        eventCommand.execute(this.tasks, this.ui, this.storage);
        Event expectedEvent = new Event("project meeting", from, to);
        String expectedOutput = "Very well, sir, I am now adding this task:\n" + expectedEvent
                + "\nNow you have 1 tasks in the list.\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
        assertEquals(1, this.tasks.size());
        try {
            assertEquals(expectedEvent.toString(), this.tasks.get(0).toString());
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }
    }
}
