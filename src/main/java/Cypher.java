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

        String[] list = new String[100];
        int i = 0;

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            switch (input) {
            case "bye":
                break;
            case "list":
                System.out.println("____________________");
                for (int j = 0; j < i; j++) {
                    System.out.println((j + 1) + ". " + list[j]);
                }
                System.out.println("____________________");
                break;
            default:
                System.out.println("____________________");
                System.out.println("Added: " + input);
                System.out.println("____________________");
                list[i] = input;
                i++;
                break;
            }
        } while (!input.equals("bye"));

        System.out.println("____________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________");

        scanner.close();
    }
}
