// Good amount of commits - Ben
public class ExchangeSystemDriver {

    public static void main (String [] args){

        //Customer creation
        System.out.println("Creating two customers...");
        Customer marco = new Customer(100f, "Marco");
        Customer bart = new Customer(121.75f, "Bart");

        System.out.println("The rate is: " + Customer.getRate());

        //specify exchange rate for ALL customers
        System.out.println("Changing rate for all Customers...");
        Customer.setRate(2);

        //Add customer AFTER rate change
        Customer walter = new Customer(245.63f, "Walter");
        System.out.println("Creating 1 more customer...");

        System.out.println("The rate is: " + Customer.getRate());

        marco.exchangeSWD(20f);

        Customer.setRate(3);
        bart.exchangeSWD(56.7f);
        marco.exchangeSWD(200f);
        marco.exchangeSWD(200f); //not enough balance

        marco.deleteAccount();

        return;

    }



}
