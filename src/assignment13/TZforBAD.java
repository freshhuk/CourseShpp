package assignment13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TZforBAD {



    public static void main(String[] args) throws IOException{

        try{
            // Get start time
            long startTime = System.currentTimeMillis();

            //Array with number from file
            ArrayList<Long> array = readFile("10m.txt");

            //Run method in order to get median number
            long num = getMedianNumber(array);
            //Run method in order to get average number
            long averageNum = getAverageNumber(array);
            //Run method in order to get min and max numbers
            long[] minMaxNums = getMinAndMaxNumber(array);
            //Output of results
            System.out.println("Median: " + num);
            System.out.println("Average number: " + averageNum);
            System.out.println("Min: " + minMaxNums[0] + " Max: " + minMaxNums[1]);


            // Get finish time
            long endTime = System.currentTimeMillis();

            double durationSeconds = (double) (endTime - startTime) / 1000.0;

            //Write program execution
            System.out.println("Program execution: " + durationSeconds + " seconds");
        }
        catch (Exception ex){
            System.out.println("Error with file, error massage: " + ex);
        }

    }

    private static long[] getMinAndMaxNumber(ArrayList<Long> array){
        long[] minMaxNums = {array.get(0), array.get(0)}; // first element its min, second its max number

        for (Long integer : array) {
            //for min
            if(minMaxNums[0] > integer){
                minMaxNums[0] = integer;
            }
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
    private static Long getAverageNumber(ArrayList<Long> array){
        long averageNumber = 0;
        for (Long integer : array) {
            averageNumber += integer;
        }
        return averageNumber / array.size();
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
