package assignment6.tm;

import acm.graphics.GImage;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // Summation of sound samples for active cells in the current column
        for (int row =  0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                for (int i =  0; i < result.length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }

        // Finding the maximum intensity in the resulting sound wave
        double maxIntensity = findMaxIntensity(result);

        //If the maximum intensity is not found (all values are 0), return an array of zeros
        if (maxIntensity ==  0.0) {
            return new double[ToneMatrixConstants.sampleSize()];
        }

        // Sound Wave Normalization
        for (int i =  0; i < result.length; i++) {
            result[i] /= (maxIntensity*-1);
        }

        return result;
    }

    // Helper method for finding maximum intensity in an array
    private static double findMaxIntensity(double[] array) {
        double maxIntensity =  0.0;
        for (double value : array) {
            //Looking for the maximum value among the array
            maxIntensity = Math.max(maxIntensity, Math.abs(value));
        }
        return maxIntensity;
    }
}
