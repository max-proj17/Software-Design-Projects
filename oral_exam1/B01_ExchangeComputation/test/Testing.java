import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    @ParameterizedTest(name="run #{index} with [{arguments}]")
    @CsvSource({"-1.7f,\"bob\"", "-30.44f,frank,", "-2.7f,yong", "-.00002f,man", "2147483647,philip","-90.7f,Bart"})
    void invalidConstructor2Arguments(float balance, String name){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Customer(balance, name));
    }
    @Test
    void copyConstructor(){
        //deep copy not shallow
        Customer original = new Customer(100f,"Mark");
        Customer copy = new Customer(original);
        original.setName("Mike");

        assertEquals(copy.toString(),"Name: Mark\nBalance: 100.0");

    }
    @Test
    void setsAndGetsCorrectRate()
    {
        Customer.setRate(5);
        assertEquals(5, Customer.getRate());
    }
    @Test
    void getsCorrectBalance()
    {
        Customer tmp = new Customer(100,"Todd");
        assertEquals(100, tmp.getBalance());
    }
    @Test
    void deletesAccountReturnsCorrectUSDBalance()
    {
        //A 100USD account after exchanging 4 SWD (rate = 1) should have $96 and displayed as 20:4 10:1 5:1 1:1
        Customer bob = new Customer(100, "Bob");
        bob.exchangeSWD(4);
        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>();
        deductedAMTS.put(20, 4); deductedAMTS.put(10, 1); deductedAMTS.put(5, 1); deductedAMTS.put(1, 1);
        deductedAMTS.put(25, 0); deductedAMTS.put(100, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);
        assertEquals(bob.deleteAccount(), deductedAMTS);
        assertEquals(bob.getBalance(), 96);

    }
    @Test
    void exchangesCorrectSWD()
    {
        //A balance of 100 USD and a rate of 2 should return "10:2" when a request for 20 SWD is made.
        // After this request the balance should be $90
        Customer buggyDClown = new Customer(100f, "Buggy");
        Customer.setRate(2);
        HashMap<Integer, Integer> deductedAMTS  = new HashMap<Integer, Integer>();
        deductedAMTS.put(25, 0); deductedAMTS.put(10, 2); deductedAMTS.put(5, 0); deductedAMTS.put(1, 0);
        deductedAMTS.put(20, 0); deductedAMTS.put(8, 0); deductedAMTS.put(55, 0); deductedAMTS.put(11, 0);
        assertEquals(buggyDClown.exchangeSWD(20f), deductedAMTS);
        assertEquals(buggyDClown.getBalance(), 90);
    }
}
