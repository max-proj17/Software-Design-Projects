import java.util.ArrayList;
import java.util.HashMap;

public abstract class Game {

    private final ArrayList<String> gameLog = new ArrayList<>();
    private final HashMap<String, Integer> scoringTypes  = new HashMap<>();
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private String winner;
    private int periodNum;
    private int maxPeriodNum;


    public Game(){ //Is not used BUT is required for final variable declaration
        this.homeTeam = "Team1";
        this.awayTeam = "Team2";
    }

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }

    protected HashMap<String, Integer> getScoringTypes(){return scoringTypes;};

    protected abstract void selectPlay(int selection);
    protected abstract String getPeriodOfPlay();
    protected abstract Boolean gameOverCheck();
    protected abstract int determineWinner();

    protected int getMaxPeriodNum() {
        return maxPeriodNum;
    }

    protected void setMaxPeriodNum(int maxPeriodNum) {
        this.maxPeriodNum = maxPeriodNum;
    }
    protected int getPeriodNum() {
        return periodNum;
    }

    protected void setPeriodNum(int periodNum) {
        this.periodNum = periodNum;
    }

    public ArrayList<String> getGameLog() {
        return gameLog;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getHomeTeam() {
        return homeTeam;
    }
    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }


}
