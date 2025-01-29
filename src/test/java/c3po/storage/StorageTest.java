package c3po.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import c3po.exception.StorageLoadingException;
import c3po.task.Deadline;
import c3po.task.Event;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.task.Todo;

/**
 * Tests the Storage class.
 */
public class StorageTest {

    protected static final String TEST_FILE_PATH = "data/tests/test_tasks.txt";
    private Storage storage;

    /**
     * Sets up the storage for testing.
     */
    @BeforeEach
    public void setUp() {
        this.storage = new Storage(TEST_FILE_PATH);
    }

    /**
     * Tests the loadTasks method with a valid file.
     */
    @Test
    public void loadTasks_validFile_loadsTasks() {
        try {
            this.createTestFile("T | 1 | read book\nD | 0 | submit report | 2023-12-31T23:59\n"
                    + "E | 1 | project meeting | 2023-12-31T10:00 | 2023-12-31T12:00");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException should not be thrown.");
        }
        try {
            ArrayList<Task> tasks = this.storage.loadTasks();
            assertEquals(3, tasks.size());
            assertEquals("[T][X] read book", tasks.get(0).toString());
            assertEquals("[D][ ] submit report (by: 31 Dec 2023 23:59)", tasks.get(1).toString());
            assertEquals("[E][X] project meeting (on: 31 Dec 2023 10:00 to 31 Dec 2023 12:00)",
                    tasks.get(2).toString());
        } catch (StorageLoadingException e) {
            e.printStackTrace();
            fail("StorageLoadingException should not be thrown.");
        }

    }

    /**
     * Tests the loadTasks method with no preexisting file.
     */
    @Test
    public void loadTasks_nonExistentFile_createsNewFile() {
        try {
            this.storage.loadTasks();
            File file = new File(TEST_FILE_PATH);
            assertTrue(file.exists());
        } catch (StorageLoadingException e) {
            fail("StorageLoadingException should not be thrown.");
        }
    }

    /**
     * Tests the saveTasks method with valid tasks.
     */
    @Test
    public void saveTasks_validTasks_savesTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("submit report", LocalDateTime.parse("2023-12-31T23:59")));
        tasks.add(new Event("project meeting", LocalDateTime.parse("2023-12-31T10:00"),
                LocalDateTime.parse("2023-12-31T12:00")));
        this.storage.saveTasks(tasks);

        File file = new File(TEST_FILE_PATH);
        StringBuilder fileContent = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException should not be thrown.");
        }
        String expectedContent =
                "T | 0 | read book\n" + "D | 0 | submit report | 2023-12-31T23:59\n"
                        + "E | 0 | project meeting | 2023-12-31T10:00 | 2023-12-31T12:00\n";
        assertEquals(expectedContent, fileContent.toString());
    }

    private void createTestFile(String content) throws IOException {
        if (!new File(TEST_FILE_PATH).getParentFile().exists()) {
            new File(TEST_FILE_PATH).getParentFile().mkdirs();
        }
        if (!new File(TEST_FILE_PATH).exists()) {
            new File(TEST_FILE_PATH).createNewFile();
        }

        try (FileWriter writer = new FileWriter(TEST_FILE_PATH)) {
            writer.write(content);
        }
    }
}
