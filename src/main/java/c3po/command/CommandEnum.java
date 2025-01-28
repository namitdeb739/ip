package c3po.command;

/**
 * Represents the command that the user inputs.
 */
public enum CommandEnum {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

    /**
     * Returns the Command enum value corresponding to the specified string.
     *
     * @param command The string representation of the command.
     * @return The Command enum value corresponding to the specified string.
     */
    public static CommandEnum fromString(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    /**
     * Returns whether the command requires a description.
     *
     * @return Whether the command requires a description.
     */
    public boolean requiresDescription() {
        return this == TODO || this == DEADLINE || this == EVENT || this == MARK
                || this == DELETE;
    }
}
