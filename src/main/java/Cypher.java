import java.util.Scanner;

public class Cypher {
    private static final String logo = ""
            + " ██████╗██╗   ██╗██████╗ ██╗  ██╗███████╗██████╗\n"
            + "██╔════╝╚██╗ ██╔╝██╔══██╗██║  ██║██╔════╝██╔══██╗\n"
            + "██║      ╚████╔╝ ██████╔╝███████║█████╗  ██████╔╝\n"
            + "██║       ╚██╔╝  ██╔═══╝ ██╔══██║██╔══╝  ██╔══██╗\n"
            + "╚██████╗   ██║   ██║     ██║  ██║███████╗██║  ██║\n"
            + "╚═════╝   ╚═╝   ╚═╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
                String command = input.split(" ")[0];

                switch (command) {
                case "bye":
                    handleBye();
                    break;
                case "list":
                    handleList();
                    break;
                case "mark":
                    handleMark(input);
                    break;
                case "unmark":
                    handleUnmark(input);
                    break;
                case "todo":
                    handleTodo(input);
                    break;
                case "deadline":
                    handleDeadline(input);
                    break;
                case "event":
                    handleEvent(input);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
                }

                printDivider();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid command.");
            } catch (MissingFieldException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid command.");
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a valid command.");
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
        for (int i = 0; i < Cypher.taskCount; i++) {
            System.out.println((i + 1) + ". " + Cypher.tasks[i]);
        }
    }

    private static void handleMark(String input) throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= Cypher.taskCount || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }

        Cypher.tasks[taskIndex].markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Cypher.tasks[taskIndex]);
    }

    private static void handleUnmark(String input)
            throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= Cypher.taskCount || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }
        Cypher.tasks[taskIndex].unmarkAsDone();

        System.out.println("Nice! I've unmarked this task as done:");
        System.out.println(Cypher.tasks[taskIndex]);
    }

    private static void handleTodo(String input) throws MissingFieldException {
        String taskDescription = input;

        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new MissingFieldException("description");
        }

        Cypher.tasks[Cypher.taskCount++] = new Todo(taskDescription);

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks[Cypher.taskCount - 1]);
        System.out.println(
                "Now you have " + Cypher.taskCount + " tasks in the list.");
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

        Cypher.tasks[Cypher.taskCount++] = new Deadline(taskDescription, by);

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks[Cypher.taskCount - 1]);
        System.out.println(
                "Now you have " + Cypher.taskCount + " tasks in the list.");
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

        Cypher.tasks[Cypher.taskCount++] = new Event(taskDescription, from, to);

        System.out.println("Got it. I've added this task:");
        System.out.println(Cypher.tasks[Cypher.taskCount - 1]);
        System.out.println(
                "Now you have " + Cypher.taskCount + " tasks in the list.");
    }
}
