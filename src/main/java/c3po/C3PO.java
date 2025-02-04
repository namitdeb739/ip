package c3po;

import c3po.command.Command;
import c3po.command.CommandEnum;
import c3po.exception.StorageLoadingException;
import c3po.parser.Parser;
import c3po.storage.Storage;
import c3po.task.TaskList;
import c3po.ui.Response;
import c3po.ui.UserInterface;

/**
 * Represents the main class of the C3PO chatbot.
 */
public class C3PO {
    private static final String DEFAULT_STORAGE_FILE_PATH = "./data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;
    private CommandEnum mostRecentCommandType = CommandEnum.GREET;

    /**
     * Constructs a chatbot with the specified file path.
     *
     * @param filePath The file path to store the tasks.
     */
    public C3PO() {
        this.ui = new UserInterface();
        this.storage = new Storage(C3PO.DEFAULT_STORAGE_FILE_PATH);
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
            this.mostRecentCommandType = command.getCommandType();
            command.execute(this.tasks, this.ui, this.storage);
            isExit = command.isExit();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public Response getResponse(String input) {
        Command command = Parser.parse(input);
        command.execute(tasks, ui, storage);
        this.mostRecentCommandType = command.getCommandType();
        return command.getResponse();
    }

    /**
     * Returns the type of the most recent command.
     *
     * @return The type of the most recent command.
     */
    public CommandEnum getMostRecentCommandType() {
        return this.mostRecentCommandType;
    }

    /**
     * Represents the main method of the chatbot. The chatbot will greet the user and prompt for
     * commands. The chatbot will continue to prompt for commands until the user types "bye".
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new C3PO().run();
    }

    /**
     * Opens the chatbot.
     *
     * @return The greeting message.
     */
    public Response openGui() {
        Response greeting = this.ui.openGui(this.tasks);
        return greeting;
    }
}
