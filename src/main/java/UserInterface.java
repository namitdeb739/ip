import java.util.Scanner;

/**
 * Represents the user interface of the chatbot. The user interface can interact
 * with the user by printing messages and reading input.
 */
public class UserInterface {
    private static final String logo = "" + "            .-.\n"
            + "           |o,o|\n" + "        ,| _\\=/_      .-''-.\n"
            + "        ||/_/_\\_\\    /[] _ _\\\n"
            + "        |_/|(_)|\\  _|_o_LII|_\n"
            + "           \\._./// / | ==== | \\\n"
            + "           |\\_/|'` |_| ==== |_|\n"
            + "           |_|_|    ||' ||  ||\n"
            + "           |-|-|    ||LI  o ||\n"
            + "           |_|_|    ||'----'||\n"
            + "          /_/ \\_\\  /__|    |__\\n";
    private Scanner scanner;

    /**
     * Constructs a user interface.
     */
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        System.out.println(
                "Oh my! It seems that my memory banks are corrupted. I'm afraid I cannot recall your prior tasks.");
    }

    /**
     * Opens the user interface.
     * 
     * @param tasks The task list to manage.
     */
    public void open(TaskList tasks) {
        this.printDivider();
        System.out.println(UserInterface.logo);
        System.out.println("Hello, I am C-3PO, human-cyborg relations.");
        System.out.println(
                "I am fluent in over six million forms of communication.");
        System.out.println();

        if (tasks.size() == 0) {
            System.out.println("You have no pending tasks, sir.");
        } else {
            System.out.println("Here are your pending tasks, sir:");
            this.list(tasks);
        }

        System.out.println();
        this.requestInstructions();
        this.printDivider();
    }

    /**
     * Reads the user input.
     * 
     * @return The user input.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the user interface.
     */
    public void close() {
        System.out.println("Shutting up, sir.");
    }

    /**
     * Prints a message.
     * 
     * @param message The message to print.
     */
    public void list(TaskList tasks) {
        System.out.println(tasks);
        this.printDivider();
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task The task to add.
     * @param size The size of the task list.
     */
    public void add(Task task, int size) {
        System.out.println("Very well, sir, I am now adding this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        this.printDivider();
    }

    /**
     * Marks a task as done.
     * 
     * @param task The task to mark.
     */
    public void mark(Task task) {
        System.out.println(
                "Oh my! The odds of successfully completing this task were 3720 to 1.");
        System.out.println(task);
        this.printDivider();
    }

    /**
     * Unmarks a task as done.
     * 
     * @param task The task to unmark.
     */
    public void unmark(Task task) {
        System.out.println("I really don't see how that's going to help.");
        System.out.println(task);
        this.printDivider();
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param task The task to delete.
     * @param size The size of the task list.
     */
    public void delete(Task task, int size) {
        System.out.println(
                "Surrender is a perfectly acceptable alternative in extreme circumstances. I have deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        this.printDivider();
    }

    /**
     * Shows an error message when a task is not found.
     */
    public void showTaskNotFoundError() {
        System.out.println(
                "I'm terribly sorry, sir, but I cannot find this task.");
        this.printDivider();
    }

    /**
     * Shows an error message when a command is not recognised.
     */
    public void showUnknownCommand() {
        System.out.println(
                "I'm terribly sorry, sir, but I do not understand this command.");
        this.printDivider();
    }

    /**
     * Requests for instructions.
     */
    public void requestInstructions() {
        System.out.println("How may I assist you, sir?");
        this.printDivider();
    }

    private void printDivider() {
        System.out.println("____________________");
    }

}