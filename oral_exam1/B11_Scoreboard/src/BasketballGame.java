import java.util.HashMap;

public class BasketballGame extends Game{

    public BasketballGame(String homeTeam, String awayTeam)
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
