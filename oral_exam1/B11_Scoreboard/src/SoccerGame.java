
/**
 * This class has
 *
 * @author Max Finch
 */
public class SoccerGame extends Game{
    /**
     * Maintains the period type for Soccer. This variable IS final
     * because we don't need the period type to change for Soccer.
     */
    private final String period;
    /**
     * Two-argument constructor. Calls the Game constructor to set team names. Decides
     * if overtime is allowed. Sets the maximum period amount. Sets the score types for
     * the game.
     * @param homeTeam name of homeTeam
     * @param awayTeam name of awayTeam
     */
    public SoccerGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(4);
        period = "quarter: ";
        getScoringTypes().put("goal", 1);

    }

    /**
     * This method selects the option the scorer selects and updates the game score or period accordingly.
     * @param selection the type of score or period switch the scorer would like to record.
     */
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
