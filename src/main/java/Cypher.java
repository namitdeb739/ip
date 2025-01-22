import java.util.Scanner;

public class Cypher {
    private static final String logo = ""
            + " ██████╗██╗   ██╗██████╗ ██╗  ██╗███████╗██████╗\n"
            + "██╔════╝╚██╗ ██╔╝██╔══██╗██║  ██║██╔════╝██╔══██╗\n"
            + "██║      ╚████╔╝ ██████╔╝███████║█████╗  ██████╔╝\n"
            + "██║       ╚██╔╝  ██╔═══╝ ██╔══██║██╔══╝  ██╔══██╗\n"
            + "╚██████╗   ██║   ██║     ██║  ██║███████╗██║  ██║\n"
            + "╚═════╝   ╚═╝   ╚═╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n";

    public static void main(String[] args) {
        System.out.println("____________________");
        System.out.println("Hello, I'm \n" + Cypher.logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            String command = input.split(" ")[0];
            int taskNumber;
            String taskDescription;

            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                break;
            case "mark":
                taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber]);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].unmarkAsDone();
                System.out.println("Nice! I've unmarked this task as done:");
                System.out.println(tasks[taskNumber]);
                break;
            case "todo":
                taskDescription = input.split(" ")[1];
                tasks[taskCount++] = new Todo(taskDescription);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                break;
            case "deadline":
                taskDescription = input.split(" ")[1];
                String by = input.substring(input.indexOf("/by") + 4)
                        .split(" ")[0];
                tasks[taskCount++] = new Deadline(taskDescription, by);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                break;
            case "event":
                taskDescription = input.split(" ")[1];
                String from = input.substring(input.indexOf("/from") + 6)
                        .split(" ")[0];
                String to = input.substring(input.indexOf("/to") + 4)
                        .split(" ")[0];
                tasks[taskCount++] = new Event(taskDescription, from, to);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                break;
            }
            System.out.println(
                    "Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________");
        } while (!input.equals("bye"));

        scanner.close();
    }
}
