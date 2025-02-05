package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import c3po.exception.TaskNotFoundException;
import c3po.task.TaskList;
import c3po.task.Todo;

/**
 * Tests the FindCommand class.
 */
public class FindCommandTest extends InputOutputCommandTest {

    /**
     * Tests the execute method of FindCommand if it finds tasks with the specified keyword.
     */
    @Test
    public void execute_findsTasksWithKeyword() {
        this.tasks.add(new Todo("read book"));
        this.tasks.add(new Todo("write report"));
        this.tasks.add(new Todo("read newspaper"));

        FindCommand findCommand = new FindCommand("read");
        findCommand.execute(this.tasks, this.ui, this.storage);

        TaskList foundTasks = new TaskList();
        try {
            foundTasks.add(this.tasks.get(0));
            foundTasks.add(this.tasks.get(2));
        } catch (TaskNotFoundException e) {
            fail("Task not found: " + e.getMessage());
        }

        String expectedOutput = "I've found it! :\n" + "1. [T][ ] read book\n"
                + "2. [T][ ] read newspaper\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the execute method of FindCommand if no tasks are found with the specified keyword.
     */
    @Test
    public void execute_noTasksFoundWithKeyword() {
        this.tasks.add(new Todo("read book"));
        this.tasks.add(new Todo("write report"));

        FindCommand findCommand = new FindCommand("exercise");
        findCommand.execute(this.tasks, this.ui, this.storage);

        String expectedOutput =
                "Perhaps I can find some clue in these volumes that will save us all! "
                        + "Now let me see, this looks like... yes... two cups... "
                        + "balka greens... add a pinch of—oh, blast it all! "
                        + "This is a cookbook!\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
