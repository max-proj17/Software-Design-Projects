/**
 * This class has only a no argument constructor, no methods, 13 private final variables and
 * 1 private variable.
 *
 * @author Max Finch
 */
public class FootballGame extends Game{
    private final String period;
    public FootballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(5);
        period = "quarter: ";
        getScoringTypes().put("touchdown", 6);
        getScoringTypes().put("field goal", 3);
        getScoringTypes().put("extra-point", 1);
        getScoringTypes().put("two-point conversion", 2);
        getScoringTypes().put("safety", 2);

    }
    @Override
    protected void selectPlay(int selection)
    {
        switch (selection) {
            case 1 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("safety"));
                getGameLog().add(getHomeTeam() + " safety");
            }
            case 2 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("touchdown"));
                getGameLog().add(getHomeTeam() + " touchdown");
            }
            case 3 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("extra-point"));
                getGameLog().add(getHomeTeam() + " extra-point");
            }
            case 4 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("two-point conversion"));
                getGameLog().add(getHomeTeam() + " two-point conversion");
            }
            case 5 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("field goal"));
                getGameLog().add(getHomeTeam() + " field goal");
            }
            case 6 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("safety"));
                getGameLog().add(getAwayTeam() + " safety");
            }
            case 7 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("touchdown"));
                getGameLog().add(getAwayTeam() + " touchdown");
            }
            case 8 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("extra-point"));
                getGameLog().add(getAwayTeam() + " extra-point");
            }
            case 9 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("two-point conversion"));
                getGameLog().add(getAwayTeam() + " two-point conversion");
            }
            case 10 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("field goal"));
                getGameLog().add(getAwayTeam() + " field goal");
            }
            case 11 -> {
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
