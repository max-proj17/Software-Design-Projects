public class FootballGame extends Game{


    public FootballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
    }

    @Override
    protected void addScore(String Team) {


    }
    @Override
    protected String[] getScoringTypes() {
        return new String[0];
    }

}
