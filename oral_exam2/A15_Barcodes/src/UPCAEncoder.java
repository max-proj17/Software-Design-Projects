import java.util.HashMap;
import java.util.Scanner;

/**
 * This class has two private methods and two public methods.
 * @author Max Finch
 */

public abstract class UPCAEncoder {

    //Use hashmap to get String equivalent. 101 + (6)w-b-w-b + 01010 + (6)b-w-b-w + 101
    /**
     * HashMap containing the decimal digit to w-b-w-b pattern equivalent.
     */
    final static HashMap<Integer, String> upc_w_b = new HashMap<>();
    /**
     * HashMap containing the decimal digit to b-w-b-w pattern equivalent.
     */
    final static HashMap<Integer, String> upc_b_w = new HashMap<>();
    /**
     * Sets up the w-b-w-b and b-w-b-w pattern HashMaps
     */
    private static void setup()
    {
        upc_w_b.put(0, "0001101"); upc_w_b.put(1, "0011001"); upc_w_b.put(2, "0010011"); upc_w_b.put(3, "0111101"); upc_w_b.put(4, "0100011");
        upc_w_b.put(5, "0110001"); upc_w_b.put(6, "0101111"); upc_w_b.put(7, "0111011"); upc_w_b.put(8, "0110111"); upc_w_b.put(9, "0001011");

        upc_b_w.put(0, "1110010"); upc_b_w.put(1, "1100110"); upc_b_w.put(2, "1101100"); upc_b_w.put(3, "1000010"); upc_b_w.put(4, "1011100");
        upc_b_w.put(5, "1001110"); upc_b_w.put(6, "1010000"); upc_b_w.put(7, "1000100"); upc_b_w.put(8, "1001000"); upc_b_w.put(9, "1110100");

    }

    /**
     * Creates the check digit for the input decimal UPC-A code.
     * @param nums array of numbers of the previously accounted for digits.
     * @return The computed check digit.
     */
    private static int checkDigitCreator(int[] nums) {
        int odd_digits = nums[0] + nums[2] + nums[4] + nums[6] + nums[8] + nums[10];
        int even_digits = nums[1] + nums[3] + nums[5] + nums[7] + nums[9];

        int result = (3 * (odd_digits) + even_digits) % 10;
        if(result != 0)
        {
            result = 10 - result;
        }
        return result;

    }

    /**
     * Encodes the decimal UPC-A code into its Binary equivalent.
     * @param input The String to be encoded.
     * @return The encoded String.
     */
    public static String encode(String input)
    {
        setup();
        int[] nums = new int[12];
        StringBuilder returnStr = new StringBuilder();
        for(int i=0; i<input.length(); i++)
        {
            nums[i] = Character.getNumericValue(input.charAt(i));
        }
        nums[11] = checkDigitCreator(nums);
        returnStr.append("101");
        for (int i=0; i<6; i++)
        {
            returnStr.append(upc_w_b.get(nums[i]));
        }
        returnStr.append("01010");
        for (int i=6; i<12; i++)
        {
            returnStr.append(upc_b_w.get(nums[i]));
        }
        returnStr.append("101");
        return returnStr.toString();
    }

    /**
     * Validates the UPC-A String given by the user
     * @return Validated String input
     */
    public static String inputValidation()
    {
        final Scanner sc = new Scanner(System.in);
        boolean valid = false;



        String returnStr = "";

        do {
            try {
                returnStr = sc.nextLine();
                if(returnStr.length()!=11)
                {
                    throw new Exception("Invalid UPC-A Code : not correct amount of digits");
                }
                for(int i=0; i<returnStr.length(); i++)
                {
                    if(!Character.isDigit(returnStr.charAt(i)))
                    {
                        throw new Exception("Invalid UPC-A Code : at least one character isn't a digit");
                    }
                }
                valid = true;

            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }while(!valid);
        return returnStr;
    }


}
