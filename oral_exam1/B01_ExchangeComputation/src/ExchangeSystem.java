import java.util.HashMap;

public class ExchangeSystem {

    private static float rate = 1; //default rate is 1 because a rate of 0 would return 0 SWD

    ExchangeSystem(float rate){
        this.rate = rate;

    }

    private boolean exchangeValid(float customerSWDBalance, float withdrawAMT)
    {
        if(customerSWDBalance - withdrawAMT <= 0){ //This subtracts the CONVERTED customer balance from the withdrawal request
            return false;
        }else {
            return true;
        }
    }
    private static HashMap<Integer, Integer> usdToSWD(float customerUSDBalance){

        float customerSWDBalance = customerUSDBalance * rate;
        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //55 represents the 5 SWD coin, 11 represents the 1 SWD coin
        deductedAMTS.put(25, 0); deductedAMTS.put(10, 0); deductedAMTS.put(5, 0); deductedAMTS.put(1, 0);
        deductedAMTS.put(20, 0); deductedAMTS.put(8, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);

        while(customerSWDBalance != 0) //deduction code for each unit of currency
        {
            if(customerSWDBalance > 25)
            {
                customerSWDBalance -= 25;
                deductedAMTS.put(25, deductedAMTS.get(25) + 1);
            }
            else if(customerSWDBalance > 10)
            {
                customerSWDBalance -= 10;
                deductedAMTS.put(10, deductedAMTS.get(10) + 1);
            }
            else if(customerSWDBalance > 5)
            {
                customerSWDBalance -= 5;
                deductedAMTS.put(5, deductedAMTS.get(5) + 1);
            }
            else if(customerSWDBalance > 1)
            {
                customerSWDBalance -= 1;
                deductedAMTS.put(1, deductedAMTS.get(1) + 1);
            }
            else if(customerSWDBalance > .20)
            {
                customerSWDBalance -= .20;
                deductedAMTS.put(20, deductedAMTS.get(20) + 1);
            }
            else if(customerSWDBalance > .08)
            {
                customerSWDBalance -= .08;
                deductedAMTS.put(8, deductedAMTS.get(8) + 1);
            }
            else if(customerSWDBalance > .05)
            {
                customerSWDBalance -= .05;
                deductedAMTS.put(55, deductedAMTS.get(55) + 1);
            }
            else if(customerSWDBalance > .01)
            {
                customerSWDBalance -= .01;
                deductedAMTS.put(11, deductedAMTS.get(11) + 1);
            }
        }
        return deductedAMTS;
    }
    public void exchangeSWD(Customer customer, float withdrawAMT){

        if(!exchangeValid(customer.getBalance(), withdrawAMT)) //if it is NOT valid
        {
            System.out.println("Insufficient funds");
        }else{
            HashMap<Integer, Integer> deductedAMTS = usdToSWD(customer.getBalance());


            System.out.println();
        }

    } //not static because we need to be able to edit a Customer object's balance

    public void setRate(float rate)
    {
        this.rate = rate;
    }

}
