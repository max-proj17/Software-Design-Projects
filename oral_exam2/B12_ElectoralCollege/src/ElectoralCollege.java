import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class has two public methods.
 * @author Max Finch
 */
public class ElectoralCollege extends Application {

    /**
     * This method is called when ElectoralCollege is run.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method overrides the default start method in Application.
     * @param stage Area in which all GUI elements will be displayed.
     * @throws IOException throws during any interrupted I/O operations.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("electoralcollegefxml.fxml")));

        Scene scene = new Scene (root);
        stage.setTitle("Electoral College Application");
        stage.setScene(scene);
        stage.show();
    }
}
