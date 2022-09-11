public class ExchangeSystem {

    private static float rate = 1; //default rate is 1 because a rate of 0 would return 0 SWD

    ExchangeSystem(float rate){
        this.rate = rate;

    }

    private boolean exchangeValid(){return true;}
    private static float usdToSWD(){
        return 0.0f;
    }
    public void exchangeSWD(){}

    public void setRate(float rate)
    {
        this.rate = rate;
    }

}
