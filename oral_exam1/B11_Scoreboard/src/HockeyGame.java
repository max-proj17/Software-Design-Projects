/**
 * This class has only a no argument constructor, no methods, 13 private final variables and
 * 1 private variable.
 *
 * @author Max Finch
 */
public class HockeyGame extends Game{

    private final String period;
    public HockeyGame(String homeTeam, String awayTeam)
    {
        //International hockey doesn't have overtime. Declares a draw only.
        super(homeTeam, awayTeam);
        setOvertime(false);
        setPeriodNum(1);
        setMaxPeriodNum(4);
        period = "period: ";
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
