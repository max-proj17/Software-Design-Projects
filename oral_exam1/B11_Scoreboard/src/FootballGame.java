import java.util.HashMap;

public class FootballGame extends Game{

    private final HashMap<String, Integer> scoringTypes ;
    public FootballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        scoringTypes = new HashMap<String, Integer>();
        scoringTypes.put("touchdown", 6);
        scoringTypes.put("field goal", 3);
        scoringTypes.put("extra-point", 1);
        scoringTypes.put("two-point conversion", 2);
        scoringTypes.put("safety", 2);

    }

    @Override
    protected void addScore(String team, String scoreType) {
        if(team == getHomeTeam())
        {

        }else if(team == getAwayTeam())
        {

        }

    }
    @Override
    protected void selectPlay(int selection)
    {

    }
    @Override
    protected HashMap<String, Integer> getScoringTypes() {
        return scoringTypes;
    }

}
