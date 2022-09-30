
/**
 * This class has
 *
 * @author Max Finch
 */
public class BasketballGame extends Game{
    /**
     * Maintains the period type for Basketball. This variable IS final
     * because we don't need the period type to change for Basketball.
     */
    private final String period;
    /**
     * Two-argument constructor. Calls the Game constructor to set team names. Decides
     * if overtime is allowed. Sets the maximum period amount. Sets the score types for
     * the game.
     * @param homeTeam name of homeTeam
     * @param awayTeam name of awayTeam
     */
    public BasketballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(4);
        period = "quarter: ";
        getScoringTypes().put("2-Point Basket", 2);
        getScoringTypes().put("3-Point Basket", 3);
        getScoringTypes().put("Free throw", 1);

    }

    /**
     * This method selects the option the scorer selects and updates the game score or period accordingly.
     * @param selection the type of score or period switch the scorer would like to record.
     */
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
                getGameLog().add(period + getPeriodNum() + " END");
                setPeriodNum(getPeriodNum()+1);


            }

        }

    }
    /**
     * All subclasses MUST implement this method. Although all implementations are the same, the String
     * (period) found in the default subclasses (except for BaseballGame) are final variables. Therefore,
     * we cannot implement getPeriodOfPlay() in the Game class.
     * @return String value signifying the current period of play.
     */
    @Override
    protected String getPeriodOfPlay() {
        return period + getPeriodNum();
    }



}
