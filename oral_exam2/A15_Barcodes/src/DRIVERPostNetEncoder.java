
/**
 * This class contains one main method
 * @author Max Finch
 */
public class DRIVERPostNetEncoder {

    /**
     * This method will automatically be called when DRIVERPostNetEncoder
     * is run.
     * @param args Command line arguments.
     */
    public static void main (String [] args)
    {

        System.out.println("Enter a Zip code to encode. Ex: 52242, 52242-2017, or 52242-2017-03");
        System.out.println(PostNetEncoder.encode());
    }



}


