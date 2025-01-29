package c3po.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Parser class.
 */
public class ParserTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the I/O for testing.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(this.outputStreamCaptor));
    }

    /**
     * Tears down the I/O after testing.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(this.originalOut);
    }

    /**
     * Tests the parse method with an empty input.
     */
    @Test
    public void parse_emptyInput_printsEmptyInputExceptionMessage() {
        Parser.parse("");
        String expectedOutput =
                "Excuse me, sir. Might I inquire what's going on? You seem to be requesting me to do nothing.\n"
                        + "Please enter a command.";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the parse method with an invalid todo command where the description is missing.
     */
    @Test
    public void parse_missingTodoDescription_printsMissingFieldExceptionMessage() {
        Parser.parse("todo ");
        String expectedOutput =
                "My sincerest apologies, but the field 'description' seems to be missing. "
                        + "Let's try that again, shall we?";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }


    /**
     * Tests the parse method with an invalid deadline command where the date/time is missing.
     */
    @Test
    public void parse_missingDeadlineDateTime_printsMissingFieldExceptionMessage() {
        Parser.parse("deadline submit report /by ");
        String expectedOutput =
                "My sincerest apologies, but the field 'date/time' seems to be missing. "
                        + "Let's try that again, shall we?";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the parse method with an invalid event command where the description is missing.
     */
    @Test
    public void parse_missingEventDescription_printsMissingFieldExceptionMessage() {
        Parser.parse("event /from 2023-12-31 10:00 /to 2023-12-31 12:00");
        String expectedOutput =
                "My sincerest apologies, but the field 'description' seems to be missing. "
                        + "Let's try that again, shall we?";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }

    /**
     * Tests the parse method with an invalid event command where the start date/time is missing.
     */
    @Test
    public void parse_missingEventStartDateTime_printsMissingFieldExceptionMessage() {
        Parser.parse("event project meeting /from /to 2023-12-31 12:00");
        String expectedOutput =
                "My sincerest apologies, but the field 'start date/time' seems to be missing. "
                        + "Let's try that again, shall we?";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }


    /**
     * Tests the parse method with an invalid event command where date/time format is invalid.
     */
    @Test
    public void parse_invalidDateTimeFormat_printsDateTimeExceptionMessage() {
        Parser.parse("deadline submit report /by invalid-date-time");
        String expectedOutput =
                "Oh my! It seems that I cannot understand the date/time you provided: invalid-date-time\n"
                        + "Please provide the date/time in the format yyyy-MM-dd HH:mm.";
        assertEquals(expectedOutput, this.outputStreamCaptor.toString().trim());
    }
}
