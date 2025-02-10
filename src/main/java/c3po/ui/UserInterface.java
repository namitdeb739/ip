package c3po.ui;

import java.util.Scanner;

import c3po.task.Task;
import c3po.task.TaskList;

/**
 * Represents the user interface of the chatbot. The user interface can interact with the user by
 * printing messages and reading input.
 */
public class UserInterface {
    private static final String LOGO =
            "" + "            .-.\n" + "           |o,o|\n" + "        ,| _\\=/_      .-''-.\n"
                    + "        ||/_/_\\_\\    /[] _ _\\\n" + "        |_/|(_)|\\  _|_o_LII|_\n"
                    + "           \\._./// / | ==== | \\\n" + "           |\\_/|'` |_| ==== |_|\n"
                    + "           |_|_|    ||' ||  ||\n" + "           |-|-|    ||LI  o ||\n"
                    + "           |_|_|    ||'----'||\n" + "          /_/ \\_\\  /__|    |__\\n";
    private static final String INSTRSUCTION_REQUEST = "How may I assist you, sir?";
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
    public Response showLoadingError() {
        String message =
                "Oh my! It seems that my memory banks are corrupted. I'm afraid I cannot recall your prior tasks.";

        Response response = new Response(message);
        response.printMessage();

        return response;
    }

    /**
     * Opens the user interface.
     *
     * @param tasks The task list to manage.
     */
    public Response open(TaskList tasks) {
        this.printDivider();

        String message = "";
        message += UserInterface.LOGO + "\n";
        message += "Hello, I am C-3PO, human-cyborg relations.\n";
        message += "I am fluent in over six million forms of communication.\n\n";
        message += tasks.size() == 0 ? "You have no pending tasks, sir."
                : String.format("Here are your pending tasks, sir:\n %s", tasks);
        message += String.format("\n\n%s", UserInterface.INSTRSUCTION_REQUEST);


        Response response = new Response(message);
        response.printMessage();

        return response;
    }

    /**
     * Opens the user interface in GUI mode.
     *
     * @param tasks The task list to manage.
     */
    public Response openGui(TaskList tasks) {
        this.printDivider();

        String message = "";
        message += "Hello, I am C-3PO, human-cyborg relations.\n";
        message += "I am fluent in over six million forms of communication.\n\n";
        message += tasks.size() == 0 ? "You have no pending tasks, sir."
                : String.format("Here are your pending tasks, sir:\n %s", tasks);
        message += String.format("\n\n%s", UserInterface.INSTRSUCTION_REQUEST);


        Response response = new Response(message);
        response.printMessage();

        return response;
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
    public Response close(String savedTasks) {
        String message = savedTasks + "\n";
        message += "Shutting up, sir.";

        Response response = new Response(message);
        response.printMessage();

        return response;
    }

    /**
     * Lists the tasks in the task list.
     *
     * @param tasks The task list to list.
     */
    public Response list(TaskList tasks) {
        String message = tasks.size() == 0 ? "You have no pending tasks, sir."
                : String.format("Here are your pending tasks, sir:\n %s", tasks);

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @param size The size of the task list.
     */
    public Response add(Task task, int size) {
        String message = "";
        message += "Very well, sir, I am now adding this task:\n";
        message += task + "\n";
        message += String.format("Now you have %d tasks in the list.", size);

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to mark.
     */
    public Response mark(Task task) {
        String message = "";
        message += "Oh my! The odds of successfully completing this task were 3720 to 1.\n";
        message += task;

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Unmarks a task as done.
     *
     * @param task The task to unmark.
     */
    public Response unmark(Task task) {
        String message = "";
        message += "I really don't see how that's going to help.\n";
        message += task;

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param task The task to delete.
     * @param size The size of the task list.
     */
    public Response delete(Task task, int size) {
        String message = "";
        message +=
                "Surrender is a perfectly acceptable alternative in extreme circumstances. I have deleted this task:\n";
        message += task + "\n";
        message += String.format("Now you have %d tasks in the list.", size);

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Shows an error message when a task is not found.
     */
    public Response taskNotFoundError() {
        String message = "I'm terribly sorry, sir, but I cannot find this task.";

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Shows an error message when a command is not recognised.
     */
    public Response unknownCommand() {
        String message = "I'm terribly sorry, sir, but I do not understand this command.";

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Finds tasks in the task list.
     *
     * @param foundTasks The tasks found.
     */
    public Response find(TaskList foundTasks) {
        String message = "";
        if (foundTasks.size() == 0) {
            message += "Perhaps I can find some clue in these volumes that will save us all! "
                    + "Now let me see, this looks like... yes... two cups... "
                    + "balka greens... add a pinch of—oh, blast it all! This is a cookbook!";
        } else {
            message += "I've found it! :\n";
            message += foundTasks;
        }

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    /**
     * Requests for instructions.
     */
    public Response requestInstructions() {
        String message = UserInterface.INSTRSUCTION_REQUEST;

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

    private void printDivider() {
        System.out.println("____________________");
    }

    /**
     * Generates a response for an invalid command.
     *
     * @param description The description of the invalid command.
     */
    public Response invalidCommand(String description) {
        String message = description + "\n";
        message += UserInterface.INSTRSUCTION_REQUEST;

        Response response = new Response(message);
        response.printMessage();
        this.printDivider();

        return response;
    }

}
