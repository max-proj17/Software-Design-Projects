
/**
 * This class has only a no argument constructor, no methods, 13 private final variables and
 * 1 private variable.
 *
 * @author Max Finch
 */
public class BasketballGame extends Game{

    private final String period;
    public BasketballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(5);
        period = "quarter: ";
        getScoringTypes().put("2-Point Basket", 2);
        getScoringTypes().put("3-Point Basket", 3);
        getScoringTypes().put("Free throw", 1);

    }

    @Override
    protected void selectPlay(int selection) {
        switch (selection) {
            case 1 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("3-Point Basket"));
                getGameLog().add(getHomeTeam() + " 3-Point Basket");
            }
            case 2 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("2-Point Basket"));
                getGameLog().add(getHomeTeam() + " 2-Point Basket");
            }
            case 3 -> {
                setHomeScore(getHomeScore() + getScoringTypes().get("Free throw"));
                getGameLog().add(getHomeTeam() + " Free throw");
            }
            case 4 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("3-Point Basket"));
                getGameLog().add(getAwayTeam() + " 3-Point Basket");
            }
            case 5 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("2-Point Basket"));
                getGameLog().add(getAwayTeam() + " 2-Point Basket");
            }
            case 6 -> {
                setAwayScore(getAwayScore() + getScoringTypes().get("Free throw"));
                getGameLog().add(getAwayTeam() + " Free throw");
            }
            case 7 -> {
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
