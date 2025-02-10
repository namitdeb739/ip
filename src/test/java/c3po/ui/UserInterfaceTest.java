package c3po.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the UserInterface class.
 *
 * Github Copilot was used to aid in developing tests using the ByteArrayInputStream and ByteArrayOutputStream classes.
 */
public class UserInterfaceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inputStreamCaptor =
            new ByteArrayInputStream("test input".getBytes());
    private final InputStream originalIn = System.in;
    private UserInterface ui;

    /**
     * Sets up the I/O for testing.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(this.outputStreamCaptor));
        System.setIn(this.inputStreamCaptor);
        this.ui = new UserInterface();
    }

    /**
     * Tears down the I/O after testing.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(this.originalOut);
        System.setIn(this.originalIn);
    }

    /**
     * Tests that the user interface reads the correct input.
     */
    @Test
    public void getInput_readsCorrectInput() {
        String input = this.ui.getInput();
        assertEquals("test input", input);
    }
}
