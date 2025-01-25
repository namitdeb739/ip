import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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

        // Uninitialised but safe as it will be initialised in the
        // beginning of the do-while loop.
        String input;

        do {
            input = scanner.nextLine();
            try {
                if (input.trim().isEmpty()) {
                    throw new EmptyInputException();
                }

                Command command = Command.fromString(input.split(" ")[0]);
                if (command.requiresDescription() && input.indexOf(" ") == -1) {
                    throw new MissingFieldException("description");
                }

                String details = input.substring(input.indexOf(" ") + 1);

                switch (command) {

                case LIST:
                    C3PO.list();
                    break;
                case TODO:
                    C3PO.todo(details);
                    break;
                case DEADLINE:
                    C3PO.deadline(details, scanner);
                    break;
                case EVENT:
                    C3PO.event(details, scanner);
                    break;
                case MARK:
                    C3PO.mark(Integer.parseInt(details) - 1);
                    break;
                case UNMARK:
                    C3PO.unmark(Integer.parseInt(details) - 1);
                    break;
                case DELETE:
                    C3PO.delete(Integer.parseInt(details) - 1);
                    break;
                case BYE:
                    break;
                default:
                    System.out.println(
                            "My programming forbids me from translating this.");
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter a command.");
            } catch (MissingFieldException e) {
                System.out.println(e.getMessage());
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
                System.out
                        .println("Total number of tasks: " + C3PO.tasks.size());
            } finally {
                if (!input.equals("bye")) {
                    C3PO.printDivider();
                }
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
    }

    private static void mark(int taskNumber) throws TaskNotFoundException {
        if (taskNumber >= C3PO.tasks.size() || taskNumber < 0) {
            throw new TaskNotFoundException(taskNumber + 1);
        }

        C3PO.tasks.get(taskNumber).markAsDone();

        System.out.println(
                "Oh my! The odds of successfully completing this task were 3720 to 1.");
        System.out.println(C3PO.tasks.get(taskNumber));
    }

    private static void unmark(int taskNumber) throws TaskNotFoundException {
        if (taskNumber >= C3PO.tasks.size() || taskNumber < 0) {
            throw new TaskNotFoundException(taskNumber + 1);
        }
        C3PO.tasks.get(taskNumber).unmarkAsDone();

        System.out.println("I really don't see how that's going to help.");
        System.out.println(C3PO.tasks.get(taskNumber));
    }

    private static void delete(int taskNumber) throws TaskNotFoundException {
        if (taskNumber >= C3PO.tasks.size() || taskNumber < 0) {
            throw new TaskNotFoundException(taskNumber + 1);
        }

        Task task = C3PO.tasks.remove(taskNumber);

        System.out.println(
                "Surrender is a perfectly acceptable alternative in extreme circumstances. I have deleted this task:");
        System.out.println(task);
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void todo(String description) throws MissingFieldException {
        if (description.isEmpty() || description.isBlank()) {
            throw new MissingFieldException("description");
        }

        Task todo = new Todo(description);
        C3PO.tasks.add(todo);

        System.out.println("Very well, sir, I am now adding this task:");
        System.out.println(todo);
        System.out.println(
                "Now you have " + C3PO.tasks.size() + " tasks in the list.");
    }

    private static void deadline(String description, Scanner scanner)
            throws MissingFieldException {
        if (description.isEmpty() || description.isBlank()) {
            throw new MissingFieldException("description");
        }

        System.out.println(
                "On what date will this task be due, sir? (YYYY-MM-DD)");

        String date = scanner.nextLine();
        if (date.isEmpty() || date.isBlank()) {
            throw new MissingFieldException("date");
        }

        System.out.println("And at what time, sir? (HH:MM)");

        String time = scanner.nextLine();
        System.out.println(time);
        if (time.isEmpty() || time.isBlank()) {
            throw new MissingFieldException("time");
        }

        try {
            LocalDateTime by = LocalDateTime.parse(date + "T" + time);
            Task deadline = new Deadline(description, by);
            C3PO.tasks.add(deadline);

            System.out.println("Very well, sir, I am now adding this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + C3PO.tasks.size()
                    + " tasks in the list.");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(
                    "I'm afraid I cannot do that, sir. The date and time must be a valid datetime"
                            + " in the format YYYY-MM-DD and HH:MM respectively.");
            System.out.println("Please try again. What is your next command?");
        }
    }

    private static void event(String description, Scanner scanner)
            throws MissingFieldException {
        if (description.isEmpty() || description.isBlank()) {
            throw new MissingFieldException("description");
        }

        System.out.println(
                "On what date will this task begin, sir? (YYYY-MM-DD)");

        String fromDate = scanner.nextLine();
        if (fromDate.isEmpty() || fromDate.isBlank()) {
            throw new MissingFieldException("date");
        }

        System.out.println("And at what time, sir? (HH:MM)");

        String fromTime = scanner.nextLine();
        if (fromTime.isEmpty() || fromTime.isBlank()) {
            throw new MissingFieldException("time");
        }

        System.out.println("When will this task end, sir? (YYYY-MM-DD)");

        String toDate = scanner.nextLine();
        if (toDate.isEmpty() || toDate.isBlank()) {
            throw new MissingFieldException("date");
        }

        System.out.println("And at what time, sir? (HH:MM)");

        String toTime = scanner.nextLine();
        if (toTime.isEmpty() || toTime.isBlank()) {
            throw new MissingFieldException("time");
        }

        try {
            LocalDateTime from = LocalDateTime.parse(fromDate + "T" + fromTime);
            LocalDateTime to = LocalDateTime.parse(toDate + "T" + toTime);

            if (from.isAfter(to)) {
                throw new IllegalArgumentException();
            }

            Task event = new Event(description, from, to);
            C3PO.tasks.add(event);

            System.out.println("Very well, sir, I am now adding this task:");
            System.out.println(event);
            System.out.println("Now you have " + C3PO.tasks.size()
                    + " tasks in the list.");
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(
                    "I'm afraid I cannot do that, sir. The date and time must be in the format YYYY-MM-DD and HH:MM respectively.");
            System.out.println("Please try again. What is your next command?");
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "I'm afraid I cannot do that, sir. The start time must be before the end time.");
            System.out.println("Please try again. What is your next command?");
        }
    }

    private static void loadTasks() {
        try {
            File file = new File("./tasks.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                String[] taskComponents = taskString.split("\\|");

                String taskType = taskComponents[0].strip();
                boolean isDone = taskComponents[1].equals("1");
                String taskDescription = taskComponents[2].strip();

                // Uninitialised but safe as either it will be
                // initialised in the switch statement or an
                // exception will be thrown
                Task task;
                switch (taskType) {
                case "T":
                    task = new Todo(taskDescription);
                    break;
                case "D":
                    if (taskComponents.length < 4) {
                        throw new IllegalArgumentException();
                    }
                    LocalDateTime by = LocalDateTime
                            .parse(taskComponents[3].strip());
                    task = new Deadline(taskDescription, by);
                    break;
                case "E":
                    if (taskComponents.length < 5) {
                        throw new IllegalArgumentException();
                    }
                    LocalDateTime from = LocalDateTime
                            .parse(taskComponents[3].strip());
                    LocalDateTime to = LocalDateTime
                            .parse(taskComponents[4].strip());
                    task = new Event(taskDescription, from, to);
                    break;
                default:
                    throw new IllegalArgumentException();
                }

                if (isDone) {
                    task.markAsDone();
                }

                C3PO.tasks.add(task);
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

        System.out.println("Hooray! I have saved your tasks, sir.");
        C3PO.list();
    }
}
