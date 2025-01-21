import java.util.Scanner;

public class Cypher {
    public static void main(String[] args) {
        String logo = " ██████╗██╗   ██╗██████╗ ██╗  ██╗███████╗██████╗\n"
                + "██╔════╝╚██╗ ██╔╝██╔══██╗██║  ██║██╔════╝██╔══██╗\n"
                + "██║      ╚████╔╝ ██████╔╝███████║█████╗  ██████╔╝\n"
                + "██║       ╚██╔╝  ██╔═══╝ ██╔══██║██╔══╝  ██╔══██╗\n"
                + "╚██████╗   ██║   ██║     ██║  ██║███████╗██║  ██║\n"
                + "╚═════╝   ╚═╝   ╚═╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n";

        System.out.println("____________________");
        System.out.println("Hello, I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            switch (input) {
            case "bye":
                break;
            default:
                System.out.println("____________________");
                System.out.println(input);
                System.out.println("____________________");
                break;
            }
        } while (!input.equals("bye"));

        System.out.println("____________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________");

        scanner.close();
    }
}
