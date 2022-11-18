import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class ElectoralCollegeController {

    @FXML
    private ComboBox <String> states = new ComboBox<>();

    @FXML
    private RadioButton democrat;

    @FXML
    private RadioButton republican;

    @FXML
    private RadioButton undecided;

    public void initialize()
    {
        states.getItems().addAll("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                "Kentucky", "Louisiana", "Maine 1st", "Maine 2nd", "Maine Popular", "Maryland", "Massachusetts",
                "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska 1st", "Nebraska 2nd",
                "Nebraska 3rd", "Nebraska Popular", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
                "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
                "West Virginia", "Wisconsin", "Wyoming");
        states.setOnAction(e -> {});

    }


}
