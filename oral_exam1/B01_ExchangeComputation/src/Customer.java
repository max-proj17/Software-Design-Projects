
public class Customer {

    //float precision is used because double precision will result in an off by one error
    private float balance;

    Customer(float startBalance){
        balance = startBalance;
    }
    public float getBalance() //not static because all Customer's have a different balance
    {
        return balance;
    }


}
