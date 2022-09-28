
public class BasketballGame extends Game{

    public BasketballGame(String homeTeam, String awayTeam)
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
