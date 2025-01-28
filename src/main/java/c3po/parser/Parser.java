package c3po.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import c3po.command.Command;
import c3po.command.CommandEnum;
import c3po.command.DeadlineCommand;
import c3po.command.DeleteCommand;
import c3po.command.EventCommand;
import c3po.command.ExitCommand;
import c3po.command.InvalidCommand;
import c3po.command.ListCommand;
import c3po.command.MarkCommand;
import c3po.command.TodoCommand;
import c3po.command.UnknownCommand;
import c3po.command.UnmarkCommand;
import c3po.exception.MissingFieldException;
import c3po.exception.DateTimeException;
import c3po.exception.EmptyInputException;

/**
 * Parser is a class that parses the user input and returns the corresponding
 * command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        try {
            if (input.trim().isEmpty()) {
                throw new EmptyInputException();
            }

            CommandEnum command = CommandEnum.fromString(input.split(" ")[0]);
            if (command.requiresDescription() && input.indexOf(" ") == -1) {
                throw new MissingFieldException("description");
            }

            String details = input.substring(input.indexOf(" ") + 1);

            switch (command) {
            case LIST:
                return new ListCommand();
            case TODO:
                String todoDetails = details;
                if (todoDetails.isEmpty() || todoDetails.isBlank()) {
                    throw new MissingFieldException("description");
                }
                return new TodoCommand(todoDetails);
            case DEADLINE:
                String[] deadlineFields = details.split(" /by ");
                if (deadlineFields[0].isEmpty()
                        || deadlineFields[0].isBlank()) {
                    throw new MissingFieldException("description");
                }
                if (deadlineFields.length < 2 || deadlineFields[1].isEmpty()
                        || deadlineFields[1].isBlank()) {
                    throw new MissingFieldException("date/time");
                }
                String deadlineDetails = deadlineFields[0];
                String dateTimeString = deadlineFields[1];
                try {
                    LocalDateTime by = LocalDateTime.parse(dateTimeString,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new DeadlineCommand(deadlineDetails, by);
                } catch (DateTimeParseException e) {
                    throw new DateTimeException(dateTimeString);
                }
            case EVENT:
                String[] eventFields = details.split(" /from | /to ");
                if (eventFields[0].isEmpty() || eventFields[0].isBlank()) {
                    throw new MissingFieldException("description");
                }
                if (eventFields[1].isEmpty() || eventFields[1].isBlank()) {
                    throw new MissingFieldException("start date/time");
                }
                if (eventFields[2].isEmpty() || eventFields[2].isBlank()) {
                    throw new MissingFieldException("end date/time");
                }
                String eventDetails = eventFields[0];
                try {
                    LocalDateTime from = LocalDateTime.parse(eventFields[1],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime to = LocalDateTime.parse(eventFields[2],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    if (from.isAfter(to)) {
                        throw new IllegalArgumentException(
                                "The start date/time cannot be after the end date/time.");
                    }
                    return new EventCommand(eventDetails, from, to);
                } catch (DateTimeParseException e) {
                    throw new DateTimeException(
                            eventFields[1] + " or " + eventFields[2]);
                }
            case MARK:
                int markTaskNumber = Integer.parseInt(details);
                return new MarkCommand(markTaskNumber - 1);
            case UNMARK:
                int unmarkTaskNumber = Integer.parseInt(details);
                return new UnmarkCommand(unmarkTaskNumber - 1);
            case DELETE:
                int deleteTaskNumber = Integer.parseInt(details);
                return new DeleteCommand(deleteTaskNumber - 1);
            case BYE:
                return new ExitCommand();
            case UNKNOWN:
                return new UnknownCommand();
            }
        } catch (EmptyInputException e) {
            System.out.println(e.getMessage());
            System.out.println("Please enter a command.");
        } catch (MissingFieldException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        return new InvalidCommand();
    }
}