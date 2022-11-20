import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ElectoralCollege extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("electoralcollegefxml.fxml")));
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED)
                {
                    System.out.println(mouseEvent.getSceneX() + ", " + mouseEvent.getSceneY());
                }
            }
        });
        Scene scene = new Scene (root);
        stage.setTitle("Electoral College Application");
        stage.setScene(scene);
        stage.show();
    }
}
