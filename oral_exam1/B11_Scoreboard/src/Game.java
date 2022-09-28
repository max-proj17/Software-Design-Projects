import java.util.HashMap;

public abstract class Game {

    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    public Game(){

    }

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    protected abstract HashMap<String, Integer> getScoringTypes();
    protected abstract void addScore(String team, String scoreType);
    protected abstract void selectPlay(int selection);

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

}
