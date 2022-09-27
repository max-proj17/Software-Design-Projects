public class BaseballGame extends Game{
    private int numRunsHome;
    private int numRunsAway;

    public BaseballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
    }

    @Override
    protected void addScore(String team) {
        if(team.equals(getHomeTeam()))
        {
            numRunsHome+=1;
        }else if(team.equals(getAwayTeam()))
        {
            numRunsAway+=1;
        }

    }



}
