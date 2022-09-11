import java.util.HashMap;

public class Customer {

    //float precision is used because double precision will result in an off by one error
    private float balance;
    private static float rate = 1;

    Customer(float startBalance){
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

    private static HashMap<Integer, Integer> usdUnitConversion(float customerUSDBalance){

        HashMap<Integer, Integer> returnAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //100 represents the dime. 55 represents the nickels. 11 represents the penny.
        returnAMTS.put(20, 0); returnAMTS.put(10, 0); returnAMTS.put(5, 0); returnAMTS.put(1, 0);
        returnAMTS.put(25, 0); returnAMTS.put(100, 0); returnAMTS.put(55, 0); returnAMTS.put(11, 0);

        while(customerUSDBalance != 0) //deduction code for each unit of currency
        {
            if(customerUSDBalance > 20)
            {
                customerUSDBalance -= 20;
                returnAMTS.put(20, returnAMTS.get(20) + 1);
            }
            else if(customerUSDBalance > 10)
            {
                customerUSDBalance -= 10;
                returnAMTS.put(10, returnAMTS.get(10) + 1);
            }
            else if(customerUSDBalance > 5)
            {
                customerUSDBalance -= 5;
                returnAMTS.put(5, returnAMTS.get(5) + 1);
            }
            else if(customerUSDBalance > 1)
            {
                customerUSDBalance -= 1;
                returnAMTS.put(1, returnAMTS.get(1) + 1);
            }
            else if(customerUSDBalance > .25)
            {
                customerUSDBalance -= .25;
                returnAMTS.put(25, returnAMTS.get(25) + 1);
            }
            else if(customerUSDBalance > .10)
            {
                customerUSDBalance -= .10;
                returnAMTS.put(100, returnAMTS.get(100) + 1);
            }
            else if(customerUSDBalance > .05)
            {
                customerUSDBalance -= .05;
                returnAMTS.put(55, returnAMTS.get(55) + 1);
            }
            else if(customerUSDBalance > .01)
            {
                customerUSDBalance -= .01;
                returnAMTS.put(11, returnAMTS.get(11) + 1);
            }
        }
        return returnAMTS;
    }
    public void exchangeSWD(Customer customer, float withdrawAMT){

        if(!exchangeValid(customer.getBalance(), withdrawAMT)) //if it is NOT valid
        {
            System.out.println("Insufficient funds");
        }else{
            HashMap<Integer, Integer> deductedAMTS = usdToSWD(customer.getBalance());


            System.out.println("Current exchange rate: USD * " + rate + " = SWD");

            System.out.println("Amount Requested: ");
            System.out.println("25: " + deductedAMTS.get(25) + " 10: " + deductedAMTS.get(10) + " 5: " + deductedAMTS.get(5)
                    + " 1: " + deductedAMTS.get(1) + " 20: " + deductedAMTS.get(20) + " 8: " + deductedAMTS.get(8)
                    + " 5: " + deductedAMTS.get(55) + " 1: " + deductedAMTS.get(11));
            balance -= (withdrawAMT/rate);
            System.out.println("New balance: " + balance + "USD");
        }

    } //not static because we need to be able to edit a Customer object's balance

    public void setRate(float newRate)
    {
        rate = newRate;
    }

    public void deleteAccount(Customer customer)
    {
        System.out.println("Balance: " + balance);
        HashMap<Integer, Integer> deductedAMTS = usdUnitConversion(customer.getBalance());

        System.out.println("Amount Requested: ");
        System.out.println("20: " + deductedAMTS.get(20) + " 10: " + deductedAMTS.get(10) + " 5: " + deductedAMTS.get(5)
                + " 1: " + deductedAMTS.get(1) + " Quarters: " + deductedAMTS.get(25) + " Dimes: " + deductedAMTS.get(100)
                + " Nickels: " + deductedAMTS.get(55) + " Pennies: " + deductedAMTS.get(11));

    }



}
