import java.util.HashMap;

public class PostNetDecoder {

    final static HashMap<String, Integer> binary_equiv = new HashMap<>();

    private static void setup()
    {
        binary_equiv.put("11000", 0); binary_equiv.put("00011", 1); binary_equiv.put("00101", 2);
        binary_equiv.put("00110", 3); binary_equiv.put("01001", 4); binary_equiv.put("01010", 5);
        binary_equiv.put("01100", 6); binary_equiv.put("10001", 7); binary_equiv.put("10010", 8);
        binary_equiv.put("10100", 9);


    }
    public static String decode(String input)
    {
        // Make array of the regex of only numbers
        // Take off dividers and the checksum
        // Go through each String in array and grab numerical value from hashmap
        // - once 6 digits are used add a -, check the rule for the DPC bc IDK
        // - return the String when built
        return "";
    }
}
