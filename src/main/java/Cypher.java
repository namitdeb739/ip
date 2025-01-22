import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class of the Cypher chatbot. The chatbot can manage
 * tasks, including todos, deadlines, and events.
 */
public class Cypher {
    private static final String logo = ""
            + " ██████╗██╗   ██╗██████╗ ██╗  ██╗███████╗██████╗\n"
            + "██╔════╝╚██╗ ██╔╝██╔══██╗██║  ██║██╔════╝██╔══██╗\n"
            + "██║      ╚████╔╝ ██████╔╝███████║█████╗  ██████╔╝\n"
            + "██║       ╚██╔╝  ██╔═══╝ ██╔══██║██╔══╝  ██╔══██╗\n"
            + "╚██████╗   ██║   ██║     ██║  ██║███████╗██║  ██║\n"
            + "╚═════╝   ╚═╝   ╚═╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Represents the main method of the chatbot. The chatbot will greet the
     * user and prompt for commands. The chatbot will continue to prompt for
     * commands until the user types "bye".
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        printDivider();
        greet();
        printDivider();

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            try {
                if (input.trim().isEmpty()) {
                    throw new EmptyInputException();
                }
                Command command = Command.fromString(input.split(" ")[0]);

                switch (command) {
                case BYE:
                    handleBye();
                    break;
                case LIST:
                    handleList();
                    break;
                case MARK:
                    handleMark(input);
                    break;
                case UNMARK:
                    handleUnmark(input);
                    break;
                case TODO:
                    handleTodo(input);
                    break;
                case DEADLINE:
                    handleDeadline(input);
                    break;
                case EVENT:
                    handleEvent(input);
                    break;
                case DELETE:
                    handleDelete(input);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
                }

                printDivider();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a command.");
            } catch (MissingFieldException e) {
                System.out.println(e.getMessage());
                System.out.println(
                        "Please enter your command with all required fields.");
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println(
                        "Total number of tasks: " + Cypher.tasks.size());
            }

        } while (!input.equals("bye"));

        scanner.close();
    }

    private static void greet() {
        System.out.println("Hello, I'm \n" + Cypher.logo);
        System.out.println("What can I do for you?");
    }

    private static void printDivider() {
        System.out.println("____________________");
    }

    private static void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        for (int i = 0; i < Cypher.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + Cypher.tasks.get(i));
        }
    }

    private static void handleMark(String input) throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= Cypher.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }

        Cypher.tasks.get(taskIndex).markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Cypher.tasks.get(taskIndex));
    }

    private static void handleUnmark(String input)
            throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= Cypher.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }
        Cypher.tasks.get(taskIndex).unmarkAsDone();

        System.out.println("Nice! I've unmarked this task as done:");
        System.out.println(Cypher.tasks.get(taskIndex));
    }

    private static void handleTodo(String input) throws MissingFieldException {
        String taskDescription = input;

        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new MissingFieldException("description");
        }

        Cypher.tasks.add(new Todo(taskDescription));

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks.get(Cypher.tasks.size() - 1));
        System.out.println(
                "Now you have " + Cypher.tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input)
            throws MissingFieldException {
        String byCommand = "/by";

        String taskDescription;
        String by;

        try {
            taskDescription = input.substring(0, input.indexOf(byCommand))
                    .trim();
            by = input.substring(
                    input.indexOf(byCommand) + byCommand.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingFieldException("by");
        }

        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new MissingFieldException("description");
        }

        Cypher.tasks.add(new Deadline(taskDescription, by));

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks.get(Cypher.tasks.size() - 1));
        System.out.println(
                "Now you have " + Cypher.tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input) throws MissingFieldException {
        String fromCommand = "/from";
        String toCommand = "/to";

        String taskDescription;
        String from;
        String to;

        try {
            taskDescription = input.substring(0, input.indexOf(fromCommand))
                    .trim();
            from = input.substring(
                    input.indexOf(fromCommand) + fromCommand.length() + 1,
                    input.indexOf(toCommand)).trim();
            to = input
                    .substring(
                            input.indexOf(toCommand) + toCommand.length() + 1)
                    .trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingFieldException("from or to");
        }

        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new MissingFieldException("description");
        }

        Cypher.tasks.add(new Event(taskDescription, from, to));

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks.get(Cypher.tasks.size() - 1));
        System.out.println(
                "Now you have " + Cypher.tasks.size() + " tasks in the list.");
    }

    private static void handleDelete(String input)
            throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= Cypher.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }

        Task task = Cypher.tasks.remove(taskIndex);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(
                "Now you have " + Cypher.tasks.size() + " tasks in the list.");
    }
}
