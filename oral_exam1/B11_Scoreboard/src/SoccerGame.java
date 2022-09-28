

public class SoccerGame extends Game{

    public SoccerGame(String homeTeam, String awayTeam)
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
