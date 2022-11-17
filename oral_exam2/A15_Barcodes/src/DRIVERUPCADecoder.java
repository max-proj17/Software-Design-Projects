public class DRIVERUPCADecoder {

    public static void main (String [] args)
    {
        System.out.println("Enter a UPC-A Barcode to decode:");
        System.out.println(UPCADecoder.decode(UPCADecoder.inputValidation()));
    }
}
//  S                                                    M                                                    E
// 101 0001101 0011001 0010011 0110001 0100011 0101111 01010 1010000 1000100 1000010 1000100 1001110 1011100 101
// 012 3456789 $$$$$$$ %%%%%%% ^^^^^^^ &&&&&&& ******* ((((( ))))))) _______ ------- +++++++ ======= {{{{{{{ }}}



// Sample input: 10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101