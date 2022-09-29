

public class SoccerGame extends Game{
    private final String period;
    public SoccerGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(4);
        period = "quarter: ";
        getScoringTypes().put("goal", 1);

    }

    @Override
    protected void selectPlay(int selection) {
        switch (selection) {
            case 1 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("goal"));
                getGameLog().add(getHomeTeam() + " goal");

            }
            case 2 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("goal"));
                getGameLog().add(getAwayTeam() + " goal");

            }
            case 3 -> {
                setPeriodNum(getPeriodNum()+1);
                getGameLog().add(period + (getPeriodNum() - 1) + " END");
            }
        }

    }

    @Override
    protected String getPeriodOfPlay() {
        return period + getPeriodNum();
    }

    @Override
    protected Boolean gameOverCheck() {
        return getPeriodNum() == getMaxPeriodNum();
    }


}
