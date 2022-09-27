public abstract class Game {

    private String homeTeam;
    private String awayTeam;
    private String[] footBallScoring = {"touchdown", "field goal", "extra-point", "two-point conversion", "safety"};
    private String[] hockeyScoring = {};
    private String[] soccerScoring = {};
    private String[] baseBallScoring = {};
    private String[] basketBallScoring = {};

    public Game(){

    }

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    protected abstract void addScore(String Team);
    protected abstract String[] getScoringTypes();

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
