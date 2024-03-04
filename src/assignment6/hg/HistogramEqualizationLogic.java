package assignment6.hg;

import acm.graphics.GImage;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] histogram = new int[MAX_LUMINANCE + 1];

        // Walk through each pixel of the image and increase the corresponding histogram element
        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                //Get the current brightness number
                int luminance = luminances[i][j];

                // Make sure the brightness is within the acceptable range
                if (luminance >= 0 && luminance <= MAX_LUMINANCE) {
                    histogram[luminance]++;//Add plus one amount of this brightness
                }
            }
        }

        return histogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {

        int[] cumulativeSum = new int[histogram.length];

        // Инициализация первого элемента кумулятивной суммы
        cumulativeSum[0] = histogram[0];

        // Вычисление кумулятивной суммы для остальных элементов
        for (int i = 1; i < histogram.length; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + histogram[i];
        }

        return cumulativeSum;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {return luminances.length*luminances[0].length;}

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        // Step 1: Calculate Histogram
        int[] histogram = histogramFor(luminances);

        // Step 2: Calculate cumulative frequencies
        int[] cumulativeSum = cumulativeSumFor(histogram);

        // Step 3: Calculate the total number of pixels
        int totalPixels = totalPixelsIn(luminances);

        // Step 4: Normalization of Cumulative Frequencies
        double[] normalizedCumulative = new double[cumulativeSum.length];
        for (int i = 0; i < cumulativeSum.length; i++) {
            normalizedCumulative[i] = (double) cumulativeSum[i] / totalPixels;
        }

        // Step 5: Multiplying the normalized cumulative frequencies by the maximum brightness value
        int[] equalizedValues = new int[cumulativeSum.length];
        for (int i = 0; i < equalizedValues.length; i++) {
            equalizedValues[i] = (int) (normalizedCumulative[i] * MAX_LUMINANCE);
        }

        // Step 6: Convert the Image Using the New Values
        int[][] equalizedImage = new int[luminances.length][luminances[0].length];
        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                equalizedImage[i][j] = equalizedValues[luminances[i][j]];
            }
        }

        return equalizedImage;
    }
}
