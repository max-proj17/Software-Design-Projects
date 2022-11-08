import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class TipQuiz extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("tipquizfxml.fxml")));

        Scene scene = new Scene (root);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
