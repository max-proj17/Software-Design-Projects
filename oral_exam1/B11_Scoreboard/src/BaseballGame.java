import java.util.HashMap;

public class BaseballGame extends Game{


    public BaseballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
    }

    @Override
    protected HashMap<String, Integer> getScoringTypes() {
        return null;
    }

    @Override
    protected void addScore(String team, String scoreType) {

    }

    @Override
    protected void selectPlay(int selection) {

    }


}
