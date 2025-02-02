package c3po.gui;

import c3po.C3PO;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private C3PO c3po;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image c3poImage = new Image(this.getClass().getResourceAsStream("/images/C3PO.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the C3PO instance */
    public void setC3PO(C3PO c) {
        this.c3po = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = c3po.getResponse(input);
        String commandType = c3po.getCommandType();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getC3PODialog(response, c3poImage, commandType));
        userInput.clear();
    }

}
