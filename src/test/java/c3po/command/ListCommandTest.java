package c3po.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import c3po.task.Todo;

public class ListCommandTest extends IoCommandTest {

    @Test
    public void execute_listsAllTasks() {
        this.tasks.add(new Todo("read book"));
        this.tasks.add(new Todo("write report"));

        ListCommand listCommand = new ListCommand();
        listCommand.execute(this.tasks, this.ui, this.storage);

        String expectedOutput =
                "1. [T][ ] read book\n" + "2. [T][ ] write report\n" + "____________________";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
