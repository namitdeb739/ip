package c3po.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import c3po.command.Command;
import c3po.command.CommandEnum;
import c3po.command.DeadlineCommand;
import c3po.command.DeleteCommand;
import c3po.command.EventCommand;
import c3po.command.ExitCommand;
import c3po.command.FindCommand;
import c3po.command.InvalidCommand;
import c3po.command.ListCommand;
import c3po.command.MarkCommand;
import c3po.command.TodoCommand;
import c3po.command.UnknownCommand;
import c3po.command.UnmarkCommand;
import c3po.exception.DateTimeException;
import c3po.exception.EmptyInputException;
import c3po.exception.MissingFieldException;

/**
 * Parser is a class that parses the user input and returns the corresponding command.
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

            String details = input.substring(input.indexOf(" ") + 1).strip();

            switch (command) {
            case LIST:
                return Parser.parseList();
            case TODO:
                return Parser.parseTodo(details);
            case DEADLINE:
                return Parser.parseDeadline(details);
            case EVENT:
                return Parser.parseEvent(details);
            case MARK:
                return Parser.parseMark(details);
            case UNMARK:
                return Parser.parseUnmark(details);
            case DELETE:
                return Parser.parseDelete(details);
            case FIND:
                return Parser.parseFind(details);
            case BYE:
                return Parser.parseBye();
            case UNKNOWN:
                return Parser.parseUnknown();
            default:
                return new InvalidCommand();
            }
        } catch (EmptyInputException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand(e.getMessage());
        } catch (MissingFieldException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand(e.getMessage());
        }
    }

    private static Command parseFind(String details) {
        String[] keywords = details.split(" ");
        return new FindCommand(keywords);
    }

    private static Command parseUnknown() {
        return new UnknownCommand();
    }

    private static Command parseBye() {
        return new ExitCommand();
    }

    private static Command parseDelete(String details) {
        int deleteTaskNumber = Integer.parseInt(details);
        return new DeleteCommand(deleteTaskNumber - 1);
    }

    private static Command parseUnmark(String details) {
        int unmarkTaskNumber = Integer.parseInt(details);
        return new UnmarkCommand(unmarkTaskNumber - 1);
    }

    private static Command parseMark(String details) {
        int markTaskNumber = Integer.parseInt(details);
        return new MarkCommand(markTaskNumber - 1);
    }

    private static Command parseEvent(String details)
            throws MissingFieldException, DateTimeException {
        String eventRegex = ".+ /from .+ /to .+";
        if (!details.matches(eventRegex)) {
            if (details.indexOf("/from ") == 0) {
                throw new MissingFieldException("description");
            }
            if (!details.contains("/from ")) {
                throw new MissingFieldException("start date/time");
            } else {
                int fromDetailsIndex = details.indexOf(" /from") + 6;
                if (fromDetailsIndex == details.length()) {
                    throw new MissingFieldException("start date/time");
                }
                String fromDetails = details.substring(fromDetailsIndex);
                if (fromDetails.strip().indexOf("/to") == 0) {
                    throw new MissingFieldException("start date/time");
                }

            }
            if (!details.contains("/to")) {
                throw new MissingFieldException("end date/time");
            } else {
                int toDetailsIndex = details.indexOf("/to") + 4;
                if (toDetailsIndex == details.length()) {
                    throw new MissingFieldException("end date/time");
                }
                String toDetails = details.substring(toDetailsIndex);
                if (toDetails.strip().indexOf(" ") == 0) {
                    throw new MissingFieldException("end date/time");
                }
            }
        }

        String[] eventFields = details.split(" /from | /to ");
        String eventDetails = eventFields[0];

        String[] tags = details.split(" ");
        tags = Arrays.stream(tags).filter(word -> word.startsWith("#"))
                .map(word -> word.substring(1)).toArray(String[]::new);

        try {
            LocalDateTime from = LocalDateTime.parse(eventFields[1],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime to = LocalDateTime.parse(eventFields[2],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (from.isAfter(to)) {
                throw new IllegalArgumentException(
                        "The start date/time cannot be after the end date/time.");
            }
            return new EventCommand(eventDetails, from, to, tags);
        } catch (DateTimeParseException e) {
            throw new DateTimeException(eventFields[1] + " or " + eventFields[2]);
        }
    }

    private static Command parseDeadline(String details)
            throws MissingFieldException, DateTimeException {
        String deadlineRegex = ".+ /by .+";
        if (!details.matches(deadlineRegex)) {
            if (details.indexOf("/by") == 0) {
                throw new MissingFieldException("description");
            }
            if (!details.contains("/by")) {
                throw new MissingFieldException("date/time");
            } else {
                int byDetailsIndex = details.indexOf("/by") + 4;
                if (byDetailsIndex >= details.length()) {
                    throw new MissingFieldException("date/time");
                }
                String byDetails = details.substring(byDetailsIndex);
                if (byDetails.strip().indexOf(" ") == 0) {
                    throw new MissingFieldException("date/time");
                }
            }
        }

        String[] deadlineFields = details.split(" /by ");

        String deadlineDetails = deadlineFields[0];
        String dateTimeString = deadlineFields[1];

        String[] tags = details.split(" ");
        tags = Arrays.stream(tags).filter(word -> word.startsWith("#"))
                .map(word -> word.substring(1)).toArray(String[]::new);

        try {
            LocalDateTime by = LocalDateTime.parse(dateTimeString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new DeadlineCommand(deadlineDetails, by, tags);
        } catch (DateTimeParseException e) {
            throw new DateTimeException(dateTimeString);
        }
    }

    private static Command parseList() {
        return new ListCommand();
    }

    private static Command parseTodo(String details) throws MissingFieldException {
        String todoDetails = details.split("#")[0].strip();

        String[] tags = details.split(" ");
        tags = Arrays.stream(tags)
                 .filter(word -> word.startsWith("#"))
                 .map(word -> word.substring(1))
                 .toArray(String[]::new);

        if (todoDetails.isEmpty() || todoDetails.isBlank()) {
            throw new MissingFieldException("description");
        }
        return new TodoCommand(todoDetails, tags);
    }
}
