import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.util.HashMap;

public class ElectoralCollegeController {

    @FXML
    private ComboBox <String> states = new ComboBox<>();
    private final HashMap<String, Boolean> democrat_votes = new HashMap<>();
    private final HashMap<String, Boolean> republican_votes = new HashMap<>();
    private final HashMap<String, Integer> vote_amount = new HashMap<>();

    @FXML
    private Label democratLabel;
    @FXML
    private Label republicanLabel;
    @FXML
    private Label winner;
    @FXML
    private PieChart piechart = new PieChart();


    @FXML
    private RadioButton democrat;

    @FXML
    private RadioButton republican;
    private final ToggleGroup options = new ToggleGroup();

    @FXML
    private RadioButton undecided;
    private int democrat_score = 0;
    private int republican_score = 0;
    private int undecided_score = 0;

    public void initialize()
    {

        ObservableList<PieChart.Data> pie_chart_data = FXCollections.observableArrayList(
                 new PieChart.Data("Democrat", 0),
                 new PieChart.Data("Republican", 0),
                 new PieChart.Data("Undecided", 538)
        );

        piechart.setData(pie_chart_data);
        piechart.setTitle("Vote Distribution");
        piechart.getData().get(0).getNode().setStyle("-fx-pie-color: blue");
        piechart.getData().get(1).getNode().setStyle("-fx-pie-color: red");
        piechart.getData().get(2).getNode().setStyle("-fx-pie-color: purple");
        piechart.setLegendVisible(false);


        final String[] states_list;
        states_list = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                "Kentucky", "Louisiana", "Maine 1st", "Maine 2nd", "Maine Popular", "Maryland", "Massachusetts",
                "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska 1st", "Nebraska 2nd",
                "Nebraska 3rd", "Nebraska Popular", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
                "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
                "West Virginia", "Wisconsin", "Wyoming"};
        final int[] vote_worth = new int[]{ 9, 3, 11, 6, 55, 9, 7, 3, 29, 16, 4, 4, 20, 11,
                                            6, 6, 8, 8, 1, 1, 2, 10, 11, 16, 10, 6, 10, 3,
                                            1, 1, 1, 2, 6, 4, 14, 5, 29, 15, 3, 18, 7, 7,
                                            20, 4, 9, 3, 11, 38, 6, 3, 13, 12, 5, 10, 3, 3 };
        //Data structure setup with similar values
        for(int i=0; i<states_list.length; i++)
        {
            states.getItems().add(states_list[i]);
            democrat_votes.put(states_list[i], false);
            republican_votes.put(states_list[i], false);
            vote_amount.put(states_list[i], vote_worth[i]);
        }
        democratLabel.setText("Democrat Votes: " + democrat_score);
        republicanLabel.setText("Republican Votes: " + republican_score);
        winner.setText("Winner: ");
        states.setOnAction(e -> {});
        democrat.setToggleGroup(options);
        republican.setToggleGroup(options);
        undecided.setToggleGroup(options);
        options.selectedToggleProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) options.getSelectedToggle();
                if (rb != null) {

                    String selected = rb.getText();
                    String state = states.getValue();
                    //If democrat is selected, democrats don't have it and republicans have it
                    if (selected.equals("Democrat") && !democrat_votes.get(state) && republican_votes.get(state)) {
                        democrat_score += vote_amount.get(state);
                        republican_score -= vote_amount.get(state);
                        republican_votes.put(state, false);
                        democrat_votes.put(state, true);

                    } else if (selected.equals("Democrat") && !democrat_votes.get(state) && !republican_votes.get(state)) {
                        democrat_score += vote_amount.get(state);
                        democrat_votes.put(state, true);

                    } else if (selected.equals("Republican") && !republican_votes.get(state) && democrat_votes.get(state)) {
                        democrat_score -= vote_amount.get(state);
                        republican_score += vote_amount.get(state);
                        republican_votes.put(state, true);
                        democrat_votes.put(state, false);

                    } else if (selected.equals("Republican") && !republican_votes.get(state) && !democrat_votes.get(state)) {
                        republican_score += vote_amount.get(state);
                        republican_votes.put(state, true);
                    } else if (selected.equals("Undecided")) {
                        if (democrat_votes.get(state)) {
                            democrat_votes.put(state, false);
                            democrat_score -= vote_amount.get(state);
                        } else if (republican_votes.get(state)) {
                            republican_votes.put(state, false);
                            republican_score -= vote_amount.get(state);
                        }
                    }
                    democratLabel.setText("Democrat Votes: " + democrat_score);
                    republicanLabel.setText("Republican Votes: " + republican_score);
                    undecided_score = 538 - (democrat_score + republican_score);

                    //set pie values
                    piechart.getData().get(0).setPieValue(democrat_score);
                    piechart.getData().get(1).setPieValue(republican_score);
                    piechart.getData().get(2).setPieValue(undecided_score);


                    //See if there is a winner
                    if ((democrat_score > republican_score) && democrat_score > 270) {
                        winner.setText("Winner: Democrats");
                    } else if ((democrat_score < republican_score) && republican_score > 270) {
                        winner.setText("Winner: Republicans");
                    } else {
                        winner.setText("Winner: ");
                    }

                }
            }
        });

    }



}
