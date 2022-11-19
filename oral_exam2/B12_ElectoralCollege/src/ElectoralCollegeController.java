import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
    private RadioButton democrat;

    @FXML
    private RadioButton republican;
    private ToggleGroup options = new ToggleGroup();

    @FXML
    private RadioButton undecided;
    private int democrat_score = 0;
    private int republican_score = 0;

    public void initialize()
    {
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
        states.setOnAction(e -> {});
        democrat.setToggleGroup(options);
        republican.setToggleGroup(options);
        undecided.setToggleGroup(options);
        options.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) options.getSelectedToggle();
                if(rb != null)
                {

                    String selected = rb.getText();
                    String state = states.getValue();
                    System.out.println(state);
                    System.out.println(selected);
                    //If democrat is selected, democrats don't have it and republicans have it
                    if(selected.equals("Democrat") && !democrat_votes.get(state) && republican_votes.get(state))
                    {
                        System.out.println("Vote for democrat");
                        democrat_score+=vote_amount.get(state);
                        republican_score-=vote_amount.get(state);
                        republican_votes.put(state, false);
                        democrat_votes.put(state, true);

                    }else if(selected.equals("Democrat") && !democrat_votes.get(state) && !republican_votes.get(state))
                    {
                        System.out.println("Vote for democrat");
                        democrat_score+=vote_amount.get(state);
                        democrat_votes.put(state, true);

                    }else if(selected.equals("Republican") && !republican_votes.get(state) && democrat_votes.get(state))
                    {
                        System.out.println("Vote for republican");
                        democrat_score-=vote_amount.get(state);
                        republican_score+=vote_amount.get(state);
                        republican_votes.put(state, true);
                        democrat_votes.put(state, false);

                    }else if(selected.equals("Republican") && !republican_votes.get(state) && !democrat_votes.get(state))
                    {
                        System.out.println("Vote for republican");
                        republican_score+=vote_amount.get(state);
                        republican_votes.put(state, true);
                    }else if(selected.equals("Undecided"))
                    {
                        if(democrat_votes.get(state))
                        {
                            democrat_votes.put(state, false);
                            democrat_score-=vote_amount.get(state);
                        }else if(republican_votes.get(state))
                        {
                            republican_votes.put(state, false);
                            republican_score-=vote_amount.get(state);
                        }
                    }
                    democratLabel.setText("Democrat Votes: " + democrat_score);
                    republicanLabel.setText("Republican Votes: " + republican_score);

                }
            }
        });

    }



}
