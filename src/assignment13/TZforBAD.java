package assignment13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Test task from Become a Developer 2024
 * The program reads a file with numbers and finds the following values:
 * 1)The maximum number in the file
 * 2)The minimum number in the file
 * 3)Median
 * 4)Arithmetic mean value
 * 5)The largest increasing sequence of numbers (consecutive)
 * 6)The largest (consecutive) sequence of numbers that is decreasing
 */
public class TZforBAD {

    /* Constant to store the path to the file */
    private  final static String PATH = "10m.txt";


    public static void main(String[] args) throws IOException{

        try{
            // Get start time
            long startTime = System.currentTimeMillis();

            //Array with number from file
            ArrayList<Long> array = readFile(PATH);

            //Run method in order to get median number
            long num = getMedianNumber(array);
            //Run method in order to get average number
            double averageNum = getAverageNumber(array);
            //Run method in order to get min and max numbers
            long[] minMaxNums = getMinAndMaxNumber(array);
            //Output of results
            System.out.println("Median: " + num);
            System.out.println("Average number: " + averageNum);
            System.out.println("Min: " + minMaxNums[0] + " Max: " + minMaxNums[1]);
            outputLargestSmallestSequence(array);

            // Get finish time
            long endTime = System.currentTimeMillis();
            //Count the time spent on executing the program
            double durationSeconds = (double) (endTime - startTime) / 1000.0;

            //Write program execution
            System.out.println("Program execution: " + durationSeconds + " seconds");
        }
        catch (Exception ex){
            System.out.println("Error with file, error massage: " + ex);
        }

    }
    private static void outputLargestSmallestSequence(ArrayList<Long> array){
        //Final sequences
        ArrayList<Long> increasingSequence = new ArrayList<>();
        ArrayList<Long> declineSequence = new ArrayList<>();

        //Temp sequences
        ArrayList<Long> increasingSequenceTemp = new ArrayList<>();
        ArrayList<Long> declineSequenceTemp = new ArrayList<>();
        //Get first element
        increasingSequenceTemp.add(array.get(0));
        declineSequenceTemp.add(array.get(0));

            //Find sequences
            for(int i = 1; i < array.size(); i++){
            if (array.get(i) > array.get(i - 1)) {
                increasingSequenceTemp.add(array.get(i));
            } else {
                if (increasingSequenceTemp.size() > increasingSequence.size()) {
                    increasingSequence = new ArrayList<>(increasingSequenceTemp);
                }
                increasingSequenceTemp.clear();
                increasingSequenceTemp.add(array.get(i));
            }

            if (array.get(i) < array.get(i - 1)) {
                declineSequenceTemp.add(array.get(i));
            } else {
                if (declineSequenceTemp.size() > declineSequence.size()) {
                    declineSequence = new ArrayList<>(declineSequenceTemp);
                }
                declineSequenceTemp.clear();
                declineSequenceTemp.add(array.get(i));
            }
        }
        // Check last sequences
        if (increasingSequenceTemp.size() > increasingSequence.size()) {
            increasingSequence = new ArrayList<>(increasingSequenceTemp);
        }
        if (declineSequenceTemp.size() > declineSequence.size()) {
            declineSequence = new ArrayList<>(declineSequenceTemp);
        }

        //Output result
        System.out.println("Largest sequence (increasing): ");
        for (Long number : increasingSequence) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
        System.out.println("Largest sequence (decline): ");
        for (Long number : declineSequence) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
    }
    private static long[] getMinAndMaxNumber(ArrayList<Long> array){
        long[] minMaxNums = {array.get(0), array.get(0)}; // first element its min, second its max number

        for (Long integer : array) {
            //for min
            if(minMaxNums[0] > integer){
                minMaxNums[0] = integer;
            }//for max
            else if(minMaxNums[1] < integer){
                minMaxNums[1] = integer;
            }
        }

        return minMaxNums;
    }
    private static Long getMedianNumber(ArrayList<Long> array){
        if(array.size() % 2 == 0){
            long firstNum = array.get((array.size() / 2) - 1);
            long secondNum = array.get(array.size() / 2);
            return (firstNum + secondNum) / 2;
        }
        else{
            return array.get(array.size() / 2);
        }
    }
    private static Double getAverageNumber(ArrayList<Long> array){
        long averageNumber = 0;
        for (Long integer : array) {
            averageNumber += integer;
        }
        return (double) averageNumber / array.size();
    }
    private static ArrayList<Long> readFile(String path) throws IOException {
        //Array with words
        ArrayList<Long> numbers = new ArrayList<>();
        //Create an instance to read data from a file at the specified path
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String numText;
            long numInt;
            while ((numText = reader.readLine()) != null) {
                numInt = Long.parseLong(numText);
                numbers.add(numInt);
            }
        }

        return numbers;
    }
}
