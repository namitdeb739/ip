package c3po.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String FXML_FILE_PATH = "/view/DialogBox.fxml";

    @FXML
    private TextFlow dialog;

    @FXML
    private Text commandWord;

    @FXML
    private Text restText;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource(DialogBox.FXML_FILE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setStyledText(text);
        this.displayPicture.setImage(img);
        this.dialog.setMaxHeight(USE_PREF_SIZE);
    }

    private void setStyledText(String text) {
        String[] words = text.split(" ", 2);
        this.commandWord.setText(words[0] + " ");
        this.commandWord.getStyleClass().add("command-word");

        this.restText.setText(words.length > 1 ? words[1] : "");
        this.restText.getStyleClass().add("rest-text");
        this.dialog.getStyleClass().add("text-flow");
    }


    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        this.dialog.getStyleClass().add("reply-label");
        this.commandWord.getStyleClass().add("reply-text");
        this.restText.getStyleClass().add("reply-text");

    }

    /**
     * Gets a dialog box for the user.
     *
     * @param text The text to display.
     * @param img The image to display.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets a dialog box for C3PO.
     *
     * @param response The text to display.
     * @param img The image to display.
     * @return The dialog box for C3PO.
     */
    public static DialogBox getC3PODialog(Response response, Image img) {
        DialogBox db = new DialogBox(response.getMessage(), img);
        db.flip();
        return db;
    }

    /**
     * Gets a dialog box for an error message from C3PO.
     *
     * @param response The response containing the error message.
     * @param img The image to display.
     * @return The dialog box for the error message.
     */
    public static Node getC3POErrorMessage(Response response, Image img) {
        DialogBox db = new DialogBox(response.getMessage(), img);
        db.dialog.getStyleClass().add("error-reply-label");
        db.flip();
        return db;
    }
}
