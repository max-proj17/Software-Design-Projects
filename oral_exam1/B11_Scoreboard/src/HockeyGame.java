import java.util.HashMap;

public class HockeyGame extends Game{


    public HockeyGame(String homeTeam, String awayTeam)
    {

        super(homeTeam, awayTeam);
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


}
