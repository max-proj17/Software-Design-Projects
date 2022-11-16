import java.util.HashMap;

public class UPCAEncoder {

    //Use hashmap to get String equivalent. 101 + (6)w-b-w-b + 01010 + (6)b-w-b-w + 101
    final static HashMap<Integer, String> upc_w_b = new HashMap<>();
    final static HashMap<Integer, String> upc_b_w = new HashMap<>();

    private static void setup()
    {
        upc_w_b.put(0, "0001101"); upc_w_b.put(1, "0011001"); upc_w_b.put(2, "0010011"); upc_w_b.put(3, "0111101"); upc_w_b.put(4, "0100011");
        upc_w_b.put(5, "0110001"); upc_w_b.put(6, "0101111"); upc_w_b.put(7, "0111011"); upc_w_b.put(8, "0110111"); upc_w_b.put(9, "0001011");

        upc_b_w.put(0, "1110010"); upc_b_w.put(1, "1100110"); upc_b_w.put(2, "1101100"); upc_b_w.put(3, "1000010"); upc_b_w.put(4, "1011100");
        upc_b_w.put(5, "1001110"); upc_b_w.put(6, "1010000"); upc_b_w.put(7, "1000100"); upc_b_w.put(8, "1001000"); upc_b_w.put(9, "1110100");

    }
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
    public static String inputValidation()
    {
        return "";
    }


}
