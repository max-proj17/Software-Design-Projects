import java.util.HashMap;

public class UPCADecoder {
    //User input check
    //For now just require input to be spaced. We can change later.
    //Loop through input without 101's and middle and get integer.
    //Get check digit at end.

    final static HashMap<String, Integer> upc_w_b = new HashMap<>();
    final static HashMap<String, Integer> upc_b_w = new HashMap<>();


    private static void setup()
    {
        upc_w_b.put("0001101", 0); upc_w_b.put("0011001", 1); upc_w_b.put("0010011", 2); upc_w_b.put("0111101", 3); upc_w_b.put("0100011", 4);
        upc_w_b.put("0110001", 5); upc_w_b.put("0101111", 6); upc_w_b.put("0111011", 7); upc_w_b.put("0110111", 8); upc_w_b.put("0001011", 9);

        upc_b_w.put("1110010", 0); upc_b_w.put("1100110", 1); upc_b_w.put("1101100", 2); upc_b_w.put("1000010", 3); upc_b_w.put("1011100", 4);
        upc_b_w.put("1001110", 5); upc_b_w.put("1010000", 6); upc_b_w.put("1000100", 7); upc_b_w.put("1001000", 8); upc_b_w.put("1110100", 9);

    }
    public static String decode(String input)
    {
        setup();
        StringBuilder returnStr = new StringBuilder();

        //sliding window
        int i;
        //i 0,1,2 is "101"
        //i 3-50 is 7 digit code
        //i 46-27 is middle
        //i 28-
        for(i=3; i<=38; i+=7)
        {
          returnStr.append(upc_w_b.get(input.substring(i, i+7)));
        }
        for (i=50; i<=78; i+=7)
        {
            returnStr.append(upc_b_w.get(input.substring(i, i+7)));
        }


        int checkDigit = upc_b_w.get(input.substring(i, i+7));
        return "Check digit: " + checkDigit + "" +
                "\nProduct code: " + returnStr;
    }

    public static String inputValidation()
    {
        return "";
    }



}
