package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import c3po.task.Todo;

public class ExitCommandTest extends IoCommandTest {

    @Test
    public void execute_savesTasksAndClosesUI() {
        this.tasks.add(new Todo("read book"));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute(this.tasks, this.ui, this.storage);
        String expectedOutput = "Hooray! I have saved your tasks, sir.";
        assertTrue(this.outputStreamCaptor.toString().contains(expectedOutput));

        try {
            String savedTasks = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));
            StringBuilder tasksStringBuilder = new StringBuilder();
            for (int i = 0; i < this.tasks.size(); i++) {
                tasksStringBuilder.append(this.tasks.get(i).toFileString())
                        .append(System.lineSeparator());
            }
            String expectedTasksString = tasksStringBuilder.toString().trim();
            assertEquals(expectedTasksString, savedTasks.trim());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false, "Failed to read the saved tasks file.");
        }
    }

    @Test
    public void isExit_returnsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }
}
