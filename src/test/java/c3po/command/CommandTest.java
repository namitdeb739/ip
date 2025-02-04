package c3po.command;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.UserInterface;

/**
 * Abstract class for command tests.
 *
 */
public abstract class CommandTest {
    protected static final String TEST_FILE_PATH = "data/tests/test_tasks.txt";
    protected TaskList tasks;
    protected UserInterface ui;
    protected Storage storage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        this.tasks = new TaskList();
        this.ui = new UserInterface();
        this.storage = new Storage(TEST_FILE_PATH);
    }

    /**
     * Tears down the test environment.
     */
    @AfterEach
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
