
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostNetEncoderTests {

    @Test
    void computesCorrectChecksum()
    {
        assertEquals(5,PostNetEncoder.checkSum(15));
        assertEquals(8,PostNetEncoder.checkSum(22));
        assertEquals(9,PostNetEncoder.checkSum(21));
        assertEquals(4,PostNetEncoder.checkSum(26));
        assertEquals(7,PostNetEncoder.checkSum(23));
    }
    @Test
    void returnsCorrectString()
    {
        PostNetEncoder.setup();
        int[] input_array = new int[]{6, 0, 6, 4, 6, 8};
        assertEquals("1 01100 11000 01100 01001 01100 10010 1\n" +
        "|.||..||....||...|..|.||..|..|.|",PostNetEncoder.returnStringCreator(input_array, input_array.length));
        input_array = new int[]{5, 2, 2, 4, 2, 2, 0, 1, 7, 5};
        assertEquals("1 01010 00101 00101 01001 00101 00101 11000 " + "00011 10001 01010 1\n|.|.|...|.|..|"
        +".|.|..|..|.|..|.|||......|||...|.|.|.|",PostNetEncoder.returnStringCreator(input_array, input_array.length));
        input_array = new int[]{5, 4, 3, 7, 1, 2, 1, 3, 4, 0, 4, 6};
        assertEquals("1 01010 01001 00110 10001 00011 00101 00011 00110 01001 11000 01001 " +
        "01100 1\n|.|.|..|..|..||.|...|...||..|.|...||..||..|..|||....|..|.||..|",PostNetEncoder.returnStringCreator(input_array, input_array.length));
        input_array = new int[]{2, 3, 1, 4, 2, 8};
        assertEquals("1 00101 00110 00011 01001 00101 10010 1\n|..|.|..||" +
        "....||.|..|..|.||..|.|",PostNetEncoder.returnStringCreator(input_array, input_array.length));

    }


}
