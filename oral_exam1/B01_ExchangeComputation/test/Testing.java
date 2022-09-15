import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    @Test
    void setsAndGetsCorrectRate()
    {
        Customer.setRate(5);
        assertEquals(5, Customer.getRate());
    }
    @Test
    void actuallyDeletesAccount()
    {

    }
    @Test
    void exchangesCorrectSWD()
    {

    }
}
