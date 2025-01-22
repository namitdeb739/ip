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

            switch (command) {
            case "bye":
                break;
            case "list":
                System.out.println("____________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________");
                break;
            case "mark":
                taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("____________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber]);
                System.out.println("____________________");
                break;
            case "unmark":
                taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].unmarkAsDone();
                System.out.println("____________________");
                System.out.println("Nice! I've unmarked this task as done:");
                System.out.println(tasks[taskNumber]);
                System.out.println("____________________");
                break;
            default:
                System.out.println("____________________");
                System.out.println("Added: " + input);
                System.out.println("____________________");
                tasks[taskCount++] = new Task(input);
                break;
            }
        } while (!input.equals("bye"));

        System.out.println("____________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________");

        scanner.close();
    }
}
