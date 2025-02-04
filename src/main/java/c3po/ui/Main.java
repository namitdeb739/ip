package c3po.ui;

import java.io.IOException;

import c3po.C3PO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String FXML_FILE_PATH = "/view/MainWindow.fxml";

    private C3PO c3po = new C3PO();

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Main.FXML_FILE_PATH));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setC3PO(this.c3po);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
