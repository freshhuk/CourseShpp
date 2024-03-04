package assignment6.sg;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {

        try{
            int[][] pixels = source.getPixelArray();
            // find out the pixel size of the image
            boolean[][] result = new boolean[pixels.length][pixels[0].length]; // исправлен порядок индексов
            //Walk through each pixel
            for (int y = 0; y < pixels[0].length; y++) {
                for (int x = 0; x < pixels.length; x++) {
                    //We get red color
                    int redColor = GImage.getRed(pixels[x][y]);
                    //If the number is not even, write true otherwise false
                    result[x][y] = redColor % 2 == 1;
                }
            }
            return result;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + " - findMessage method");
            return null;
        }

    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {

        try{
            int[][] pixels = source.getPixelArray();
            // find out the pixel size of an image
            for (int y = 0; y < pixels.length; y++) {
                for (int x = 0; x < pixels[y].length; x++) {

                    //We get all the colors
                    int green = GImage.getGreen(pixels[y][x]);
                    int blue = GImage.getBlue(pixels[y][x]);
                    int red = GImage.getRed(pixels[y][x]);
                    // if black
                    if (message[y][x]) {
                        //if the color is even then make it odd
                        if (red % 2 == 0 && red < 255) {
                            red++;
                        }
                    }
                    // if white
                    if (!message[y][x]) {
                        //And if white is an odd color, then make it even
                        if (red % 2 == 1 && red < 255) {
                            red++;
                        }
                        if (red == 255) {
                            red--;
                        }
                    }
                    //Set new color
                    pixels[y][x] = GImage.createRGBPixel(red, green, blue);
                }
            }
            return new GImage(pixels);
        }
        catch (Exception ex){
            System.out.println("\n"+ex.getMessage() + " - hideMessage method");
            return null;
        }
    }
}
