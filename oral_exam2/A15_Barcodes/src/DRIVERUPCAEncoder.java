/**
 * This class contains one main method
 * @author Max Finch
 */
public class DRIVERUPCAEncoder {
    /**
     * This method will automatically be called when DRIVERUPCAEncoder
     * is run.
     * @param args Command line arguments.
     */

    public static void main(String [] args)
    {
        System.out.println("Enter a UPC-A product code to encode:");
        System.out.println(UPCAEncoder.encode(UPCAEncoder.inputValidation()));

    }
}

