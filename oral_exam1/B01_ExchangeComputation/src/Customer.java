import java.util.HashMap;

public class Customer {

    //float precision is used because double precision will result in an off by one error
    private float balance;
    private static float rate = 1;

    private String name;

    Customer(float startBalance, String name){
        this.name = name;
        balance = startBalance;
    }
    public float getBalance() //not static because all Customer's have a different balance
    {
        return balance;
    }
    private boolean exchangeValid(float customerSWDBalance, float withdrawAMT)
    {
        if(customerSWDBalance - withdrawAMT <= 0){ //This subtracts the CONVERTED customer balance from the withdrawal request
            return false;
        }else {
            return true;
        }
    }
    private static HashMap<Integer, Integer> displaySWD(float customerSWDBalance){

        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //55 represents the 5 SWD coin, 11 represents the 1 SWD coin
        deductedAMTS.put(25, 0); deductedAMTS.put(10, 0); deductedAMTS.put(5, 0); deductedAMTS.put(1, 0);
        deductedAMTS.put(20, 0); deductedAMTS.put(8, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);


        customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
        while(customerSWDBalance > 0) //deduction code for each unit of currency
        {
            //System.out.println(customerUSDBalance);
            if(customerSWDBalance >= 25f)
            {
                customerSWDBalance -= 25f;

                deductedAMTS.put(25, deductedAMTS.get(25) + 1);
            }
            else if(customerSWDBalance >= 10f)
            {
                customerSWDBalance -= 10f;

                deductedAMTS.put(10, deductedAMTS.get(10) + 1);
            }
            else if(customerSWDBalance >= 5f)
            {
                customerSWDBalance -= 5f;

                deductedAMTS.put(5, deductedAMTS.get(5) + 1);
            }
            else if(customerSWDBalance >= 1f)
            {
                customerSWDBalance -= 1f;

                deductedAMTS.put(1, deductedAMTS.get(1) + 1);
            }
            else if(customerSWDBalance >= .20f)
            {
                customerSWDBalance -= .20f;

                deductedAMTS.put(20, deductedAMTS.get(20) + 1);
            }
            else if(customerSWDBalance >= .08f)
            {
                customerSWDBalance -= .08f;

                deductedAMTS.put(8, deductedAMTS.get(8) + 1);
            }
            else if(customerSWDBalance >= .05f)
            {
                customerSWDBalance -= .05f;

                deductedAMTS.put(55, deductedAMTS.get(55) + 1);
            }
            else if(customerSWDBalance >= .01f)
            {
                customerSWDBalance -= .01f;

                deductedAMTS.put(11, deductedAMTS.get(11) + 1);
            }else{
                if(customerSWDBalance >= .005) {

                    deductedAMTS.put(11, deductedAMTS.get(11) + 1);
                }
                customerSWDBalance = 0;
            }
        }
        return deductedAMTS;
    }

    private static HashMap<Integer, Integer> displayUSD(float customerUSDBalance){

        HashMap<Integer, Integer> returnAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //100 represents the dime. 55 represents the nickels. 11 represents the penny.
        returnAMTS.put(20, 0); returnAMTS.put(10, 0); returnAMTS.put(5, 0); returnAMTS.put(1, 0);
        returnAMTS.put(25, 0); returnAMTS.put(100, 0); returnAMTS.put(55, 0); returnAMTS.put(11, 0);

        customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
        while(customerUSDBalance > 0) //deduction code for each unit of currency
        {
            //System.out.println(customerUSDBalance);
            if(customerUSDBalance >= 20f)
            {
                customerUSDBalance -= 20f;

                returnAMTS.put(20, returnAMTS.get(20) + 1);
            }
            else if(customerUSDBalance >= 10f)
            {
                customerUSDBalance -= 10f;

                returnAMTS.put(10, returnAMTS.get(10) + 1);
            }
            else if(customerUSDBalance >= 5f)
            {
                customerUSDBalance -= 5f;

                returnAMTS.put(5, returnAMTS.get(5) + 1);
            }
            else if(customerUSDBalance >= 1f)
            {
                customerUSDBalance -= 1f;

                returnAMTS.put(1, returnAMTS.get(1) + 1);
            }
            else if(customerUSDBalance >= .25f)
            {
                customerUSDBalance -= .25f;

                returnAMTS.put(25, returnAMTS.get(25) + 1);
            }
            else if(customerUSDBalance >= .10f)
            {
                customerUSDBalance -= .10f;

                returnAMTS.put(100, returnAMTS.get(100) + 1);
            }
            else if(customerUSDBalance >= .05f)
            {
                customerUSDBalance -= .05f;

                returnAMTS.put(55, returnAMTS.get(55) + 1);
            }
            else if(customerUSDBalance >= .01f)
            {
                customerUSDBalance -= .01f;

                returnAMTS.put(11, returnAMTS.get(11) + 1);
            }else{
                if(customerUSDBalance >= .005) {

                    returnAMTS.put(11, returnAMTS.get(11) + 1);
                }
                customerUSDBalance = 0;
            }
        }
        return returnAMTS;
    }
    public void exchangeSWD(float withdrawAMT){

        if(!exchangeValid(balance * rate, withdrawAMT)) //if balance in SWD is NOT valid
        {
            System.out.println("\n" + name + " has insufficient funds");
        }else{
            HashMap<Integer, Integer> deductedAMTS = displaySWD(withdrawAMT);
            System.out.println("\n" + name + "'s Transaction");
            System.out.println("Balance: " + balance + " USD");
            System.out.println("EXCHANGING YOUR USD FOR SWD. Amount Requested: " + withdrawAMT + " SWD");
            System.out.println("Current exchange rate: USD * " + rate + " = SWD");

            System.out.println("Currency Unit Breakdown: ");
            System.out.println("25: " + deductedAMTS.get(25) + "\n10: " + deductedAMTS.get(10) + "\n5: " + deductedAMTS.get(5)
                    + "\n1: " + deductedAMTS.get(1) + "\n20 coins: " + deductedAMTS.get(20) + "\n8 coins: " + deductedAMTS.get(8)
                    + "\n5 coins: " + deductedAMTS.get(55) + "\n1 coins: " + deductedAMTS.get(11));
            balance -= (withdrawAMT/rate);
            balance = Math.round(balance * 100.0f)/100.0f;
            System.out.println("New balance: $" + balance + " USD");
        }

    } //not static because we need to be able to edit a Customer object's balance

    public static void setRate(float newRate)
    {
        rate = newRate;
    }

    public static float getRate(){return rate;}

    public void deleteAccount()
    {
        System.out.println("\nDELETING " + name + "'s ACCOUNT");
        System.out.println("Balance: $" + balance);
        HashMap<Integer, Integer> deductedAMTS = displayUSD(balance);

        System.out.println("Amount of USD in account: ");
        System.out.println("$20: " + deductedAMTS.get(20) + " $10: " + deductedAMTS.get(10) + " $5: " + deductedAMTS.get(5)
                + " $1: " + deductedAMTS.get(1) + " Quarters: " + deductedAMTS.get(25) + " Dimes: " + deductedAMTS.get(100)
                + " Nickels: " + deductedAMTS.get(55) + " Pennies: " + deductedAMTS.get(11));

    }



}
