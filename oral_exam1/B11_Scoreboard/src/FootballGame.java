import java.util.HashMap;

public class FootballGame extends Game{

    private final HashMap<String, Integer> scoringTypes ;
    private final String period;
    private int periodNum;
    private int maxPeriodNum;
    public FootballGame(String homeTeam, String awayTeam)
    {
        super(homeTeam, awayTeam);
        scoringTypes = new HashMap<String, Integer>();
        periodNum = 1;
        maxPeriodNum = 5;
        period = "quarter: ";
        scoringTypes.put("touchdown", 6);
        scoringTypes.put("field goal", 3);
        scoringTypes.put("extra-point", 1);
        scoringTypes.put("two-point conversion", 2);
        scoringTypes.put("safety", 2);

    }

    @Override
    public int getMaxPeriodNum() {
        return maxPeriodNum;
    }

    protected void setMaxPeriodNum(int maxPeriodNum) {
        this.maxPeriodNum = maxPeriodNum;
    }

    @Override
    protected void selectPlay(int selection)
    {
        switch (selection) {
            case 1:
                setHomeScore(getHomeScore() + scoringTypes.get("safety"));
                getGameLog().add(getHomeTeam() + " safety");
                break;
            case 2:
                setHomeScore(getHomeScore() + scoringTypes.get("touchdown"));
                getGameLog().add(getHomeTeam() + " touchdown");
                break;
            case 3:
                setHomeScore(getHomeScore() + scoringTypes.get("extra-point"));
                getGameLog().add(getHomeTeam() + " extra-point");
                break;
            case 4:
                setHomeScore(getHomeScore() + scoringTypes.get("two-point conversion"));
                getGameLog().add(getHomeTeam() + " two-point conversion");
                break;
            case 5:
                setHomeScore(getHomeScore() + scoringTypes.get("field goal"));
                getGameLog().add(getHomeTeam() + " field goal");
                break;
            case 6:
                setAwayScore(getAwayScore() + scoringTypes.get("safety"));
                getGameLog().add(getAwayTeam() + " safety");
                break;
            case 7:
                setAwayScore(getAwayScore() + scoringTypes.get("touchdown"));
                getGameLog().add(getAwayTeam() + " touchdown");
                break;
            case 8:
                setAwayScore(getAwayScore() + scoringTypes.get("extra-point"));
                getGameLog().add(getAwayTeam() + " extra-point");
                break;
            case 9:
                setAwayScore(getAwayScore() + scoringTypes.get("two-point conversion"));
                getGameLog().add(getAwayTeam() + " two-point conversion");
                break;
            case 10:
                setAwayScore(getAwayScore() + scoringTypes.get("field goal"));
                getGameLog().add(getAwayTeam() + " field goal");
                break;
            case 11:
                periodNum+=1;
                getGameLog().add(period + (periodNum-1) + " END");
                break;
        }

    }

    @Override
    protected String getPeriodOfPlay() {
        return period + periodNum;
    }

    @Override
    protected Boolean gameOverCheck() {
        return periodNum == maxPeriodNum;
    }

    @Override
    protected int determineWinner() {
        if(getHomeScore() > getAwayScore())
        {
            setWinner(getHomeTeam());
            return 1; //Home team wins
        }else if(getHomeScore() < getAwayScore())
        {
            setWinner(getAwayTeam());
            return 2;
        }else if(getHomeScore() == getAwayScore())
        {
            return 3;
        }else{
            return 0; //arbitrary value to satisfy return requirement
        }
    }

    @Override
    protected HashMap<String, Integer> getScoringTypes() {
        return scoringTypes;
    }

}
