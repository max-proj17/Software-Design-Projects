// Good amount of commits - Ben
/**
 * Main driver class that tests Customer.java
 *
 * @author Max Finch
 */
public class ExchangeSystemDriver {
    /**
     * This method will automatically be called when ExchangeSystemDriver
     * is run.
     *
     * @param args Command line arguments.
     */
    @SuppressWarnings("unused")

    public static void main (String [] args){

        //Customer creation
        Customer marco = new Customer(100f, "Marco");
        Customer bart = new Customer(121.75f, "Bart");

        System.out.println("The rate is: " + Customer.getRate());

        //specify exchange rate for ALL customers
        Customer.setRate(2);

        //Add customer AFTER rate change
        Customer walter = new Customer(245.63f, "Walter");
        System.out.println("The rate is: " + Customer.getRate());

        marco.exchangeSWD(20f);

        Customer.setRate(3);
        bart.exchangeSWD(56.7f);
        marco.exchangeSWD(200f);
        marco.exchangeSWD(200f); //not enough balance

        marco.deleteAccount();

    }



}
