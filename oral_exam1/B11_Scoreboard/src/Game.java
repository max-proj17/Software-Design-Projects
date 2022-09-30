import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class has a two-argument constructor, two protected abstract methods, 17 protected methods, 4 private final variables, 6 private variables
 * @author Max Finch
 */
public abstract class Game {

    /**
     * gameLog keeps track of every option made in the game and is replayed at the
     * end of gameLoop() in the ScoreLoop class. This variable is final because there is no need
     * to reassign the gameLog reference to another ArrayList.
     */
    private final ArrayList<String> gameLog = new ArrayList<>();
    /**
     * scoringTypes contains the type of scores that can be made in the specified Game.
     * This variable is final because there is no need to reassign the scoringTypes reference to
     * another HashMap.
     */
    private final HashMap<String, Integer> scoringTypes  = new HashMap<>();
    /**
     * Keeps the name of the home team. This variable is final because the name of the team should not change
     * mid-game.
     */
    private final String homeTeam;
    /**
     * Keeps the name of the away team. This variable is final because the name of the team should not change
     * mid-game.
     */
    private final String awayTeam;
    /**
     * Keeps track of the home team's score.
     */
    private int homeScore;
    /**
     * Keeps track of the away team's score.
     */
    private int awayScore;
    /**
     * Selects the winner based on final period results. Not final because it would not make sense to declare
     * the winner in the constructor (the beginning of the game).
     */
    private String winner;
    /**
     * Keeps track of the current period
     */
    private int periodNum;
    /**
     * Keeps track of the max allowed period in a game. This variable is not final because subclasses
     * can add an extra period for overtime.
     */
    private int maxPeriodNum;
    /**
     * Can be set by a subclass to allow for overtime.
     */
    private boolean overtime;

    /**
     * Two-argument constructor
     * @param homeTeam name of homeTeam
     * @param awayTeam name of awayTeam
     */
    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }
    /**
     * All subclasses MUST implement this method. It selects the option the scorer selects
     * and updates the game score or period accordingly. See default five games as reference
     * implementations.
     * @param selection the type of score or period switch the scorer would like to record.
     */
    protected abstract void selectPlay(int selection);
    /**
     * All subclasses MUST implement this method. Although all implementations are the same, the String
     * (period) found in the default subclasses (except for BaseballGame) are final variables. Therefore,
     * we cannot implement getPeriodOfPlay() in the Game class.
     * @return String value signifying the current period of play.
     */
    protected abstract String getPeriodOfPlay();
    /**
     * This method determines whether the game is over after a selection was made.
     * @return boolean signifying with true/false if the game is over or not.
     */
    protected Boolean gameOverCheck() {
        return periodNum > maxPeriodNum;
    }
    /**
     * This method determines whether the game is allowed to have overtime.
     * @return boolean signifying with true/false if the game gets overtime or not.
     */
    protected boolean getOvertime() {
        return overtime;
    }
    /**
     * This method allows a subclass to decide whether a game has overtime or not
     * @param overtime true/false if the game gets overtime or not.
     */
    protected void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }
    /**
     * This method returns the scoring types (Ex: touchdown, goal, run) of a Game.
     * @return HashMap<String, Integer> of a subclass's scoring type where the keys
     * are the string representation for the UI and the values are int number value
     * of the score type.
     */
    protected HashMap<String, Integer> getScoringTypes(){return scoringTypes;}
    /**
     * This method determines the winner based on comparing homeScore and awayScore.
     * 1: home team wins 2: away team wins 3: Tie
     * In case of a tie, the result of this method will trigger another period if the
     * game allows overtime.
     * @return int value signifying the final game state.
     */
    protected int determineWinner() {
        if(homeScore > awayScore)
        {
            winner = "Winner: " + homeTeam;
            return 1; //Home team wins
        }else if(homeScore < awayScore)
        {
            winner = "Winner: " + awayTeam;
            return 2; //Away team wins
        }else {
            winner = "Tie";
            return 3; //It was a tie
        }
    }
    /**
     * Gets the max number of periods allowed for the game.
     * @return int value signifying max number of periods.
     */
    protected int getMaxPeriodNum() {
        return maxPeriodNum;
    }
    /**
     * This method allows a subclass to decide how many periods a Game should have.
     * @param maxPeriodNum int value signifying max number of periods.
     */
    protected void setMaxPeriodNum(int maxPeriodNum) {
        this.maxPeriodNum = maxPeriodNum;
    }
    /**
     * This method gets the current period
     * @return current period number.
     */
    protected int getPeriodNum() {
        return periodNum;
    }
    /**
     * This method allows a subclass to change the current period.
     * @param periodNum int value signifying current period number.
     */
    protected void setPeriodNum(int periodNum) {
        this.periodNum = periodNum;
    }
    /**
     * This method gets the game log. Subclasses add the score type and period
     * shifts to this ArrayList<String>.
     * @return an ArrayList<String> of game history made in a games selectPlay() method.
     */
    protected ArrayList<String> getGameLog() {
        return gameLog;
    }
    /**
     * This method gets the winner of the Game.
     * @return the winner or "tie" as a String
     */
    protected String getWinner() {
        return winner;
    }
    /**
     * This method gets the home team's name.
     * @return home team's name.
     */
    protected String getHomeTeam() {
        return homeTeam;
    }
    /**
     * This method gets the away team's name.
     * @return away team's name.
     */
    protected String getAwayTeam() {
        return awayTeam;
    }
    /**
     * This method gets the home team's score.
     * @return home team's score.
     */
    protected int getHomeScore() {
        return homeScore;
    }
    /**
     * This method sets the home team's score.
     * @param homeScore new home team's score.
     */
    protected void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }
    /**
     * This method gets the away team's score.
     * @return away team's score.
     */
    protected int getAwayScore() {
        return awayScore;
    }
    /**
     * This method sets the away team's score.
     * @param awayScore new away team's score.
     */
    protected void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }


}
