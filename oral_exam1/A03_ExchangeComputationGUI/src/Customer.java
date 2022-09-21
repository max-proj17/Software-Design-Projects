import java.util.HashMap;

/**
 * This class has a two-argument constructor, 3 private methods and 7 public methods.
 *
 * @author Max Finch
 */
public class Customer {

    /**
     * The amount of money a Customer has in USD
     * A float is used because double precision is not needed.
     * The max decimal we need to represent is .99 for 99 cents
     */
    private float balance;
    /**
     * The exchange rate for all customers.
     * This variable is static because all Customers should share 1 rate variable
     *
     */
    private static float rate = 1;


    /**
     * Keeps the name of the Customer
     * Main use is to label transactions, account closings and the toString method.
     */
    private String name;
    /**
     * No argument constructor
     */
    Customer(){};
    /**
     * Two-argument constructor
     * @param startBalance Amount of starting money in USD
     * @param name Name associated with "Account"
     */
    Customer(float startBalance, String name){
        if(startBalance < 0 || startBalance >= Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException("Starting balance must be greater than 0 and less than 2147483647");
        }
        this.name = name;
        balance = startBalance;
        String strDouble = String. format("%.2f", balance);
        System.out.println("Welcome " + this.name + " your balance is " + strDouble + " USD");
    }
    /**
     * One-argument Copy Constructor
     * @param customer Amount of starting money in USD
     */
    public Customer(Customer customer) {
        this.balance = customer.balance;
        this.name = customer.name;
    }
    /**
     * Determines whether a transaction is valid by subtracting a customers
     * SWD balance and subtracting it by the requested withdrawal amount.
     * Returns a boolean indicating the validity of the transaction.
     * @param customerSWDBalance The value of the customer's balance in SWD (USD * Rate = SWD)
     * @param withdrawAMT The amount requested to withdraw in SWD
     * @return true: transaction valid false: transaction invalid
     */
    private boolean exchangeValid(float customerSWDBalance, float withdrawAMT)
    {
        if(customerSWDBalance - withdrawAMT <= 0){ //This subtracts the CONVERTED customer balance from the withdrawal request
            return false;
        }else {
            return true;
        }
    }
    /**
     * Takes in the requested withdrawal amount and fills a HashMap with the most minimal
     * divisions of SWD currency to give back to the Customer. The function returns this HashMap.
     * @param swdWithdrawalAMT The amount requested in SWD
     * @return a HashMap of the individual units of SWD currency that must be given back to the Customer
     */
    private static HashMap<Integer, Integer> displaySWD(float swdWithdrawalAMT){

        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>(); //contains return amount in currency units

        //55 represents the 5 SWD coin, 11 represents the 1 SWD coin
        deductedAMTS.put(25, 0); deductedAMTS.put(10, 0); deductedAMTS.put(5, 0); deductedAMTS.put(1, 0);
        deductedAMTS.put(20, 0); deductedAMTS.put(8, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);


        swdWithdrawalAMT = Math.round(swdWithdrawalAMT * 100.0f)/100.0f;
        while(swdWithdrawalAMT > 0) //deduction code for each unit of currency
        {
            if(swdWithdrawalAMT >= 25f)
            {
                swdWithdrawalAMT -= 25f;

                deductedAMTS.put(25, deductedAMTS.get(25) + 1);
            }
            else if(swdWithdrawalAMT >= 10f)
            {
                swdWithdrawalAMT -= 10f;

                deductedAMTS.put(10, deductedAMTS.get(10) + 1);
            }
            else if(swdWithdrawalAMT >= 5f)
            {
                swdWithdrawalAMT -= 5f;

                deductedAMTS.put(5, deductedAMTS.get(5) + 1);
            }
            else if(swdWithdrawalAMT >= 1f)
            {
                swdWithdrawalAMT -= 1f;

                deductedAMTS.put(1, deductedAMTS.get(1) + 1);
            }
            else if(swdWithdrawalAMT >= .20f)
            {
                swdWithdrawalAMT -= .20f;

                deductedAMTS.put(20, deductedAMTS.get(20) + 1);
            }
            else if(swdWithdrawalAMT >= .08f)
            {
                swdWithdrawalAMT -= .08f;

                deductedAMTS.put(8, deductedAMTS.get(8) + 1);
            }
            else if(swdWithdrawalAMT >= .05f)
            {
                swdWithdrawalAMT -= .05f;

                deductedAMTS.put(55, deductedAMTS.get(55) + 1);
            }
            else if(swdWithdrawalAMT >= .01f)
            {
                swdWithdrawalAMT -= .01f;

                deductedAMTS.put(11, deductedAMTS.get(11) + 1);
            }else{
                if(swdWithdrawalAMT >= .005) {

                    deductedAMTS.put(11, deductedAMTS.get(11) + 1);
                }
                swdWithdrawalAMT = 0;
            }
        }
        return deductedAMTS;
    }
    /**
     * Takes in the Customer's USD balance and fills a HashMap with the most minimal
     * divisions of USD currency. The function returns this HashMap.
     * @param customerUSDBalance The value of the customer's balance member variable
     * @return a HashMap of the individual units of USD currency that will be displayed in
     * deleteAccount()
     */
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
    /**
     * Takes in the requested withdrawal amount and fills a HashMap with the most minimal
     * divisions of SWD currency to give back to the Customer. Displays to the console
     * the currency division breakdown. The function returns this HashMap.
     * @param withdrawAMT The amount requested in SWD
     * @return a HashMap of the individual units of SWD currency that must be given back to the Customer.
     * The return value has no function in the driver but is used in a JUNIT test named exchangesCorrectSWD()
     */
    public HashMap<Integer, Integer> exchangeSWD(float withdrawAMT){

        HashMap<Integer, Integer> deductedAMTS = displaySWD(withdrawAMT); //This statement is here to test valid & invalid exchanges
        if(!exchangeValid(balance * rate, withdrawAMT)) //if balance in SWD is NOT valid
        {
            System.out.println("\n" + name + " has insufficient funds");
        }else{

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
        return deductedAMTS;

    } //not static because we need to be able to edit a Customer object's balance
    /**
     * Returns nothing. Sets new rate for ALL customers. This function belongs
     * to the definition of the Customer class.
     * @param newRate rate to replace the old rate
     */
    public static void setRate(float newRate)
    {

        System.out.println("Changing rate to " + rate + " for all Customers...");
        rate = newRate;
    }
    /**
     * Returns the current rate
     *
     * @return rate as a float value
     */
    public static float getRate(){return rate;}
    /**
     * Returns the customer's name
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }
    /**
     * Returns nothing. Sets name for the customer the function was called on.
     * @param name rate to replace the old rate
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the Customer's current balance.
     *
     * @return balance in USD as a float value.
     */
    public float getBalance() {
        return balance;
    }
    /**
     * Deletes Customer that the function was called on. "Deletion" for this program is simply returning the
     * amount of USD left in the account. Displays to the console the currency division breakdown.
     * The function returns this HashMap.
     * @return a HashMap of the individual units of USD currency that must be given back to the Customer.
     * The return value has no function in the driver but is used in a JUNIT test named deletesAccountReturnsCorrectUSDBalance()
     */

    public HashMap<Integer, Integer> deleteAccount()
    {
        System.out.println("\nDELETING " + name + "'s ACCOUNT");
        System.out.println("Balance: $" + balance);
        HashMap<Integer, Integer> deductedAMTS = displayUSD(balance);

        System.out.println("Amount of USD in account: ");
        System.out.println("$20: " + deductedAMTS.get(20) + " $10: " + deductedAMTS.get(10) + " $5: " + deductedAMTS.get(5)
                + " $1: " + deductedAMTS.get(1) + " Quarters: " + deductedAMTS.get(25) + " Dimes: " + deductedAMTS.get(100)
                + " Nickels: " + deductedAMTS.get(55) + " Pennies: " + deductedAMTS.get(11));

        return deductedAMTS;

    }
    /**
     * Returns the Customer's "credentials" which include the name and balance
     * @return String representing the Customer Object
     */
    public String toString(){

        return "Name: " + name + "\n| Balance: " + balance;

    }



}
