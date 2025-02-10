package c3po.ui;

import c3po.C3PO;
import c3po.command.CommandEnum;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMAGE_FILE_PATH = "/images/User.png";
    private static final String C3PO_IMAGE_FILE_PATH = "/images/C3PO.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private C3PO c3po;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream(MainWindow.USER_IMAGE_FILE_PATH));
    private final Image c3poImage =
            new Image(this.getClass().getResourceAsStream(MainWindow.C3PO_IMAGE_FILE_PATH));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot to use.
     *
     * @param c The chatbot to use.
     */
    public void setC3PO(C3PO c) {
        this.c3po = c;
        Response greeting = this.c3po.openGui();
        this.dialogContainer.getChildren().add(DialogBox.getC3PODialog(greeting, this.c3poImage));
    }

    /**
     * Handles the user input.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        Response response = this.c3po.getResponse(input);;
        this.dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, this.userImage),
                this.c3po.getMostRecentCommandType().isError()
                        ? DialogBox.getC3POErrorMessage(response, this.c3poImage)
                        : DialogBox.getC3PODialog(response, this.c3poImage));
        this.userInput.clear();

        CommandEnum commandType = this.c3po.getMostRecentCommandType();
        if (commandType.isExit()) {
            System.out.println("Exiting...");
            Platform.exit();
        }
    }

}
