
public class BaseballGame extends Game{

    private String period;
    private int periodHalf;

    public BaseballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        periodHalf = 1;
        setPeriodNum(1);
        setMaxPeriodNum(10);
        period = "Top of Inning: ";
        getScoringTypes().put("Grand Slam", 4);
        getScoringTypes().put("Two-run homer", 2);
        getScoringTypes().put("Three-run homer", 3);
        getScoringTypes().put("Single run", 1);

    }

    @Override
    protected void selectPlay(int selection) {
        switch (selection) {
            case 1 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("Two-run homer"));
                getGameLog().add(getHomeTeam() + " Two-run homer");

            }
            case 2 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("Three-run homer"));
                getGameLog().add(getHomeTeam() + " Three-run homer");

            }
            case 3 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("Single run"));
                getGameLog().add(getHomeTeam() + " Single run");
            }
            case 4 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("Grand Slam"));
                getGameLog().add(getHomeTeam() + " Grand Slam");
            }
            case 5 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("Two-run homer"));
                getGameLog().add(getAwayTeam() + " Two-run homer");
            }
            case 6 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("Three-run homer"));
                getGameLog().add(getAwayTeam() + " Three-run homer");
            }
            case 7 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("Single run"));
                getGameLog().add(getAwayTeam() + " Single run");
            }
            case 8 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("Grand Slam"));
                getGameLog().add(getAwayTeam() + " Grand Slam");
            }

            case 9 -> {
                if(periodHalf == 1)
                {
                    getGameLog().add(period + (getPeriodNum() - 1) + " END");
                    period = "Bottom of Inning: ";
                    periodHalf = 2;
                }else if (periodHalf == 2)
                {
                    getGameLog().add(period + (getPeriodNum() - 1) + " END");
                    setPeriodNum(getPeriodNum()+1);
                    period = "Top of Inning: ";
                    periodHalf = 1;
                }

            }
        }
    }

    @Override
    protected String getPeriodOfPlay() {
        return period + getPeriodNum();
    }

    @Override
    protected Boolean gameOverCheck() {
        return getPeriodNum() == getMaxPeriodNum() && period.equals("Bottom of Inning: ");
    }


}
