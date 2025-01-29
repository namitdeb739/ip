package c3po.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class IoCommandTest extends CommandTest {
    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpIO() {
        System.setOut(new PrintStream(this.outputStreamCaptor));
    }

    @AfterEach
    public void tearDownIO() {
        System.setOut(this.originalOut);
    }
}
