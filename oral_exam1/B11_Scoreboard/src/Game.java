import java.util.ArrayList;
import java.util.HashMap;

public abstract class Game {

    private final ArrayList<String> gameLog = new ArrayList<String>();
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String winner;

    public ArrayList<String> getGameLog() {
        return gameLog;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Game(){

    }

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }


    protected abstract HashMap<String, Integer> getScoringTypes();
    protected abstract void setMaxPeriodNum(int maxPeriodNum);
    protected abstract int getMaxPeriodNum();
    protected abstract void selectPlay(int selection);
    protected abstract String getPeriodOfPlay();
    protected abstract Boolean gameOverCheck();
    protected abstract int determineWinner();

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
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
