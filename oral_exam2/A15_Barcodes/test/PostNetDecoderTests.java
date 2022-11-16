import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostNetDecoderTests {

    @Test
    void correctDecoderOutput()
    {
        PostNetDecoder.setup();
        String[] input_array = new String[]{"1", "01100", "11000", "01100",  "01001", "01100", "10010", "1"};
        assertEquals("60646", PostNetDecoder.decode(input_array));
        input_array = new String[]{"1", "01010", "00101", "00101", "01001", "00101", "00101", "11000", "00011", "10001", "01010", "1"};
        assertEquals("52242-2017", PostNetDecoder.decode(input_array));
        input_array = new String[]{"1", "01010", "01001", "00110", "10001", "00011", "00101", "00011", "00110", "01001", "11000", "01001", "01100", "1"};
        assertEquals("54371-2134-04", PostNetDecoder.decode(input_array));
        input_array = new String[]{"1", "00101", "00110", "00011", "01001", "00101", "10010", "1"};
        assertEquals("23142", PostNetDecoder.decode(input_array));
    }
}
