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
    private static HashMap<Integer, Integer> usdToSWD(float customerUSDBalance){

        float customerSWDBalance = customerUSDBalance * rate;
        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //55 represents the 5 SWD coin, 11 represents the 1 SWD coin
        deductedAMTS.put(25, 0); deductedAMTS.put(10, 0); deductedAMTS.put(5, 0); deductedAMTS.put(1, 0);
        deductedAMTS.put(20, 0); deductedAMTS.put(8, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);

        while(customerSWDBalance != 0) //deduction code for each unit of currency
        {
            if(customerSWDBalance >= 25)
            {
                customerSWDBalance -= 25;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(25, deductedAMTS.get(25) + 1);
            }
            else if(customerSWDBalance >= 10)
            {
                customerSWDBalance -= 10;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(10, deductedAMTS.get(10) + 1);
            }
            else if(customerSWDBalance >= 5)
            {
                customerSWDBalance -= 5;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(5, deductedAMTS.get(5) + 1);
            }
            else if(customerSWDBalance >= 1)
            {
                customerSWDBalance -= 1;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(1, deductedAMTS.get(1) + 1);
            }
            else if(customerSWDBalance >= .20)
            {
                customerSWDBalance -= .20;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(20, deductedAMTS.get(20) + 1);
            }
            else if(customerSWDBalance >= .08)
            {
                customerSWDBalance -= .08;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(8, deductedAMTS.get(8) + 1);
            }
            else if(customerSWDBalance >= .05)
            {
                customerSWDBalance -= .05;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(55, deductedAMTS.get(55) + 1);
            }
            else if(customerSWDBalance >= .01)
            {
                customerSWDBalance -= .01;
                customerSWDBalance = Math.round(customerSWDBalance * 100.0f)/100.0f;
                deductedAMTS.put(11, deductedAMTS.get(11) + 1);
            }
        }
        return deductedAMTS;
    }

    private static HashMap<Integer, Integer> usdUnitConversion(float customerUSDBalance){

        HashMap<Integer, Integer> returnAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //100 represents the dime. 55 represents the nickels. 11 represents the penny.
        returnAMTS.put(20, 0); returnAMTS.put(10, 0); returnAMTS.put(5, 0); returnAMTS.put(1, 0);
        returnAMTS.put(25, 0); returnAMTS.put(100, 0); returnAMTS.put(55, 0); returnAMTS.put(11, 0);

        while(customerUSDBalance != 0) //deduction code for each unit of currency
        {
            if(customerUSDBalance >= 20)
            {
                customerUSDBalance -= 20;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(20, returnAMTS.get(20) + 1);
            }
            else if(customerUSDBalance >= 10)
            {
                customerUSDBalance -= 10;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(10, returnAMTS.get(10) + 1);
            }
            else if(customerUSDBalance >= 5)
            {
                customerUSDBalance -= 5;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(5, returnAMTS.get(5) + 1);
            }
            else if(customerUSDBalance >= 1)
            {
                customerUSDBalance -= 1;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(1, returnAMTS.get(1) + 1);
            }
            else if(customerUSDBalance >= .25)
            {
                customerUSDBalance -= .25;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(25, returnAMTS.get(25) + 1);
            }
            else if(customerUSDBalance >= .10)
            {
                customerUSDBalance -= .10;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(100, returnAMTS.get(100) + 1);
            }
            else if(customerUSDBalance >= .05)
            {
                customerUSDBalance -= .05;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(55, returnAMTS.get(55) + 1);
            }
            else if(customerUSDBalance >= .01)
            {
                customerUSDBalance -= .01;
                customerUSDBalance = Math.round(customerUSDBalance * 100.0f)/100.0f;
                returnAMTS.put(11, returnAMTS.get(11) + 1);
            }
        }
        return returnAMTS;
    }
    public void exchangeSWD(float withdrawAMT){

        if(!exchangeValid(balance * rate, withdrawAMT)) //if it is NOT valid
        {
            System.out.println("\n" + name + " has insufficient funds");
        }else{
            HashMap<Integer, Integer> deductedAMTS = usdToSWD(balance);
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
        HashMap<Integer, Integer> deductedAMTS = usdUnitConversion(balance);

        System.out.println("Amount of USD in account: ");
        System.out.println("$20: " + deductedAMTS.get(20) + " $10: " + deductedAMTS.get(10) + " $5: " + deductedAMTS.get(5)
                + " $1: " + deductedAMTS.get(1) + " Quarters: " + deductedAMTS.get(25) + " Dimes: " + deductedAMTS.get(100)
                + " Nickels: " + deductedAMTS.get(55) + " Pennies: " + deductedAMTS.get(11));

    }



}
