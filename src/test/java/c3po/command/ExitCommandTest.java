package c3po.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import c3po.task.Todo;

/**
 * Tests for ExitCommand.
 */
public class ExitCommandTest extends IoCommandTest {

    /**
     * Tests the execute method of ExitCommand if it saves the tasks and closes the UI.
     */
    @Test
    public void execute_closesUI() {
        this.tasks.add(new Todo("read book"));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute(this.tasks, this.ui, this.storage);
        String expectedOutput = "Hooray! I have saved your tasks, sir.";
        assertTrue(this.outputStreamCaptor.toString().contains(expectedOutput));
    }

    /**
     * Tests the isExit method of ExitCommand.
     */
    @Test
    public void isExit_returnsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
