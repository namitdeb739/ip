import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class of the Cypher chatbot. The chatbot can manage
 * tasks, including todos, deadlines, and events.
 */
public class C3PO {
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

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Represents the main method of the chatbot. The chatbot will greet the
     * user and prompt for commands. The chatbot will continue to prompt for
     * commands until the user types "bye".
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        C3PO.printDivider();
        C3PO.greet();
        C3PO.loadTasks();
        C3PO.requestInstructions();
        C3PO.printDivider();

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            try {
                if (input.trim().isEmpty()) {
                    throw new EmptyInputException();
                }
                Command command = Command.fromString(input.split(" ")[0]);
                String details = input.substring(input.indexOf(" ") + 1);

                switch (command) {

                case LIST:
                    C3PO.list();
                    break;
                case TODO:
                    C3PO.todo(details);
                    break;
                case DEADLINE:
                    C3PO.deadline(details);
                    break;
                case EVENT:
                    C3PO.event(details);
                    break;
                case MARK:
                    C3PO.mark(details);
                    break;
                case UNMARK:
                    C3PO.unmark(details);
                    break;
                case DELETE:
                    C3PO.delete(details);
                    break;
                case BYE:
                    break;
                default:
                    System.out.println(
                            "My programming forbids me from translating this.");
                    break;
                }

                C3PO.printDivider();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a command.");
            } catch (MissingFieldException e) {
                System.out.println(e.getMessage());
                System.out.println(
                        "Please enter your command with all required fields.");
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
                System.out
                        .println("Total number of tasks: " + C3PO.tasks.size());
            }

        } while (!input.equals("bye"));

        scanner.close();

        C3PO.saveTasks();
        C3PO.bye();
    }

    private static void greet() {
        System.out.println(C3PO.logo);
        System.out.println("Hello, I am C-3PO, human-cyborg relations.");
        System.out.println(
                "I am fluent in over six million forms of communication.");
        System.out.println("");
    }

    private static void requestInstructions() {
        System.out.println("How may I assist you today?");

    }

    private static void printDivider() {
        System.out.println("____________________");
    }

    private static void bye() {
        System.out.println("Shutting up, sir.");
    }

    private static void list() {
        for (int i = 0; i < C3PO.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + C3PO.tasks.get(i));
        }
        System.out.println("");
    }

    private static void mark(String input) throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= C3PO.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }

        C3PO.tasks.get(taskIndex).markAsDone();

        System.out.println(
                "Oh my! The odds of successfully completing this task were 3720 to 1.");
        System.out.println(C3PO.tasks.get(taskIndex));
    }

    private static void unmark(String input) throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= C3PO.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }
        C3PO.tasks.get(taskIndex).unmarkAsDone();

        System.out.println("I really don't see how that's going to help.");
        System.out.println(C3PO.tasks.get(taskIndex));
    }

    private static void todo(String input) throws MissingFieldException {
        String taskDescription = input;

        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new MissingFieldException("description");
        }

        C3PO.tasks.add(new Todo(taskDescription));

        System.out.println("Very well, sir, I am now adding this task:");
        System.out.println(C3PO.tasks.get(C3PO.tasks.size() - 1));
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void deadline(String input) throws MissingFieldException {
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

        C3PO.tasks.add(new Deadline(taskDescription, by));

        System.out.println("Very well, sir, I am now adding this task:");
        System.out.println(C3PO.tasks.get(C3PO.tasks.size() - 1));
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void event(String input) throws MissingFieldException {
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

        C3PO.tasks.add(new Event(taskDescription, from, to));

        System.out.println("Very well, sir, I am now adding this task:");
        System.out.println(C3PO.tasks.get(C3PO.tasks.size() - 1));
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void delete(String input) throws TaskNotFoundException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        if (taskIndex >= C3PO.tasks.size() || taskIndex < 0) {
            throw new TaskNotFoundException(taskIndex + 1);
        }

        Task task = C3PO.tasks.remove(taskIndex);

        System.out.println(
                "Surrender is a perfectly acceptable alternative in extreme circumstances. I have deleted this task:");
        System.out.println(task);
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void loadTasks() {
        try {
            File file = new File("./tasks.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] taskComponents = task.split("\\|");

                String taskType = taskComponents[0].strip();
                boolean isDone = taskComponents[1].equals("1");
                String taskDescription = taskComponents[2].strip();

                // Uninitialised but safe as either it will be
                // initialised in the switch statement or an
                // exception will be thrown
                Task newTask;
                switch (taskType) {
                case "T":
                    newTask = new Todo(taskDescription);
                    break;
                case "D":
                    if (taskComponents.length < 4) {
                        throw new IllegalArgumentException();
                    }
                    newTask = new Deadline(taskDescription, taskComponents[3]);
                    break;
                case "E":
                    if (taskComponents.length < 5) {
                        throw new IllegalArgumentException();
                    }
                    newTask = new Event(taskDescription, taskComponents[3],
                            taskComponents[4]);
                    break;
                default:
                    throw new IllegalArgumentException();
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                C3PO.tasks.add(newTask);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have no pending tasks, sir.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Oh my! It seems that my memory banks are corrupted. I'm afraid I cannot recall your prior tasks.");
        }

        if (C3PO.tasks.size() > 0) {
            System.out.println("Here are your pending tasks, sir:");
            C3PO.list();
        }

        System.out.println("");
    }

    private static void saveTasks() {
        try {
            File file = new File("./tasks.txt");
            file.createNewFile();

            java.io.FileWriter writer = new java.io.FileWriter(file);

            for (Task task : C3PO.tasks) {
                writer.write(task.toFileString() + "\n");
            }

            writer.close();
        } catch (java.io.IOException e) {
            System.out.println(
                    "I'm afraid I cannot do that, sir. It appears my security clearance is insufficient.");
        }

        System.out.println("I have saved your tasks, sir.");
        C3PO.list();
    }
}
