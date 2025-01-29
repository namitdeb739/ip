package c3po.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Abstract class for command tests that involve I/O.
 */
public abstract class IoCommandTest extends CommandTest {
    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the I/O for testing.
     */
    @BeforeEach
    public void setUpIO() {
        System.setOut(new PrintStream(this.outputStreamCaptor));
    }

    /**
     * Tears down the I/O after testing.
     */
    @AfterEach
    public void tearDownIO() {
        System.setOut(this.originalOut);
    }
}
