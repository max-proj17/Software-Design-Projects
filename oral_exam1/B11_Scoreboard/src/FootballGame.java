/**
 * This class has a two-argument constructor, 1 private variable and two override protected methods.
 * @author Max Finch
 */
public class FootballGame extends Game{
    /**
     * Maintains the period type for Football. This variable IS final
     * because we don't need the period type to change for Football.
     */
    private final String period;
    /**
     * Two-argument constructor. Calls the Game constructor to set team names. Decides
     * if overtime is allowed. Sets the maximum period amount. Sets the score types for
     * the game.
     * @param homeTeam name of homeTeam
     * @param awayTeam name of awayTeam
     */
    public FootballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        setOvertime(true);
        setPeriodNum(1);
        setMaxPeriodNum(4);
        period = "quarter: ";
        getScoringTypes().put("touchdown", 6);
        getScoringTypes().put("field goal", 3);
        getScoringTypes().put("extra-point", 1);
        getScoringTypes().put("two-point conversion", 2);
        getScoringTypes().put("safety", 2);

    }
    /**
     * This method selects the option the scorer selects and updates the game score or period accordingly.
     * @param selection the type of score or period switch the scorer would like to record.
     */
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
                getGameLog().add(period + getPeriodNum()  + " END");
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
