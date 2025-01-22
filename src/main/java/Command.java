/**
 * Represents the command that the user inputs.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    UNKNOWN;

    /**
     * Returns the Command enum value corresponding to the specified string.
     *
     * @param command The string representation of the command.
     * @return The Command enum value corresponding to the specified string.
     */
    public static Command fromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}