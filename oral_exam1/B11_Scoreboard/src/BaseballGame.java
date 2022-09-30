
/**
 * This class has a two-argument instructor 2 private variables
 *
 * @author Max Finch
 */
public class BaseballGame extends Game{

    /**
     * Maintains the period type for Baseball. This variable is not final
     * because we must switch from Top and Bottom Innings.
     */
    private String period;
    /**
     * Signifies in selectPlay(int selection) to switch between Top and Bottom innings.
     */
    private int periodHalf;

    /**
     * Two-argument constructor. Calls the Game constructor to set team names. Decides
     * if overtime is allowed. Sets the maximum period amount. Sets the score types for
     * the game.
     * @param homeTeam name of homeTeam
     * @param awayTeam name of awayTeam
     */
    public BaseballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        periodHalf = 1;
        setPeriodNum(1);
        setMaxPeriodNum(9);
        period = "Top of Inning: ";
        getScoringTypes().put("Grand Slam", 4);
        getScoringTypes().put("Two-run homer", 2);
        getScoringTypes().put("Three-run homer", 3);
        getScoringTypes().put("Single run", 1);

    }

    /**
     * This method selects the option the scorer selects and updates the game score or period accordingly.
     * @param selection the type of score or period switch the scorer would like to record.
     */
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
                    getGameLog().add(period + getPeriodNum() + " END");
                    period = "Bottom of Inning: ";
                    periodHalf = 2;
                }else if (periodHalf == 2)
                {
                    getGameLog().add(period + getPeriodNum() + " END");
                    setPeriodNum(getPeriodNum()+1);
                    period = "Top of Inning: ";
                    periodHalf = 1;
                }

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
