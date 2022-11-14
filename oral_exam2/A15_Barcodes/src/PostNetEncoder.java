import java.util.HashMap;

public abstract class PostNetEncoder {

    final HashMap<Integer, String>  binary_equiv = new HashMap<>();

    private void setup()
    {
        binary_equiv.put(0, "11000"); binary_equiv.put(1, "00011"); binary_equiv.put(2, "00101");
        binary_equiv.put(3, "00110"); binary_equiv.put(4, "01001"); binary_equiv.put(5, "01010");
        binary_equiv.put(6, "01100"); binary_equiv.put(7, "10001"); binary_equiv.put(8, "10010");
        binary_equiv.put(9, "10100");
    }
    private int checkSum()
    {
        return 5;
    }
    public String encode(String zip)
    {
        setup();

        return "";
    }
}
