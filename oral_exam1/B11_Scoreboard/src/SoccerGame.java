import java.util.HashMap;

public class SoccerGame extends Game{

    public SoccerGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
    }

    @Override
    protected HashMap<String, Integer> getScoringTypes() {
        return null;
    }

    @Override
    protected void setMaxPeriodNum(int maxPeriodNum) {

    }

    @Override
    protected int getMaxPeriodNum() {
        return 0;
    }


    @Override
    protected void selectPlay(int selection) {

    }

    @Override
    protected String getPeriodOfPlay() {
        return null;
    }

    @Override
    protected Boolean gameOverCheck() {
        return null;
    }

    @Override
    protected int determineWinner() {
        return 0;
    }


}
