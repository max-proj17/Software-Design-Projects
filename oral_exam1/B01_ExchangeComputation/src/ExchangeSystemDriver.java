public class ExchangeSystemDriver {

    public static void main (String [] args){

        //Customer creation
        System.out.println("Creating two customers...");
        Customer marco = new Customer(100f);
        Customer bart = new Customer(121.75f);

        System.out.println("The rate is: " + Customer.getRate());

        //specify exchange rate for ALL customers
        System.out.println("Changing rate for all Customers...");
        Customer.setRate(2);

        //Add customer AFTER rate change
        Customer walter = new Customer(245.63f);
        System.out.println("Creating 1 more customer...");

        System.out.println("The rate is: " + Customer.getRate());

        marco.exchangeSWD(20f);

        Customer.setRate(3);
        bart.exchangeSWD(56.7f);

        marco.deleteAccount();

    }



}
