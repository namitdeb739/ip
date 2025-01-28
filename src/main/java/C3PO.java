
/**
 * Represents the main class of the Cypher chatbot. The chatbot can manage
 * tasks, including todos, deadlines, and events.
 */
public class C3PO {

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    /**
     * Constructs a chatbot with the specified file path.
     *
     * @param filePath The file path to store the tasks.
     */
    public C3PO(String filePath) {
        this.ui = new UserInterface();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (StorageLoadingException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.open(this.tasks);

        boolean isExit = false;
        while (!isExit) {
            String input = this.ui.getInput();
            Command command = Parser.parse(input);
            command.execute(this.tasks, this.ui, this.storage);
            isExit = command.isExit();
        }
    }

    /**
     * Represents the main method of the chatbot. The chatbot will greet the
     * user and prompt for commands. The chatbot will continue to prompt for
     * commands until the user types "bye".
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new C3PO("data/tasks.txt").run();
    }

}
