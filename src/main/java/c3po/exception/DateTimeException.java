package c3po.exception;

import java.time.LocalDateTime;

/**
 * LocalDateTimeException is a custom exception class that extends the Exception
 * class. It is thrown when there is an error parsing a LocalDateTime object.
 */
public class DateTimeException extends Exception {
    /**
     * Constructs a LocalDateTimeException.
     * 
     * @param dateTime The LocalDateTime object that could not be parsed.
     */
    public DateTimeException(LocalDateTime dateTime) {
        super("Oh my! It seems that I cannot understand the date/time you provided: "
                + dateTime + "\n"
                + "Please provide the date/time in the format yyyy-MM-dd HH:mm.");
    }

    /**
     * Constructs a LocalDateTimeException.
     * 
     * @param dateTime The date/time string that could not be parsed.
     */
    public DateTimeException(String dateTime) {
        super("Oh my! It seems that I cannot understand the date/time you provided: "
                + dateTime + "\n"
                + "Please provide the date/time in the format yyyy-MM-dd HH:mm.");
    }

}
