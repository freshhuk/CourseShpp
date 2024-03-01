package assignment5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5Part4 {
    private static final String FILE_PATH = "test.csv";


    public static void main(String[] args) throws IOException {
        startProgram();
    }
    private static void startProgram()throws IOException{
        //Create instance scanner for get data
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write columnIndex please");
        int index = scanner.nextInt(); //get index
        ArrayList<String> wordList = extractColumn(FILE_PATH,index);
        //output words through foreach
        for (String word : wordList) {
            System.out.println(word);
        }
    }
    private static ArrayList<String> extractColumn(String filename,int columnIndex) throws IOException {
        try {
            //Create an instance to read data from a file at the specified path
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            //Our result that we will get
            ArrayList<String> wordResult = new ArrayList<>();
            String line;
            //Read one line at a time from the file until the lines run out
            while((line = bufferedReader.readLine()) != null){
                //We run our function that converts a string into an array of words or sentences
                ArrayList<String> wordsInLine = fieldsIn(line);
                //Add a row at the specified index to our final array
                wordResult.add(wordsInLine.get(columnIndex));
            }
            return wordResult;

        } catch (FileNotFoundException e) {return null;
        }
    }
    //We get an array of words in a row
    private static ArrayList<String> fieldsIn(String line){
        //Our array with words that we get from the row
        ArrayList<String> wordList = new ArrayList<>();
        //A variable to track whether our word still has quotation marks
        boolean insideQuotes = false;
        //We create an instance of a class for working on strings
        StringBuilder currentWord = new StringBuilder();
        //Through a loop we trace the words up to the comma and write this string to an array
        for (char c : line.toCharArray()) {
            if (c == ',' && !insideQuotes) {
                wordList.add(currentWord.toString().trim());//remove first and last space
                currentWord.setLength(0);  // Resetting the buffer
            } else if (c == '"') {//if we have Quotes , change boolean flag at opposite
                insideQuotes = !insideQuotes;
                currentWord.append(c);//add letter
            } else {
                currentWord.append(c);//add letter
            }
        }

        // Add the last word after the last comma or at the end of the line
        wordList.add(currentWord.toString().trim());

        // Remove the outer quotes, leaving the inner ones
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if (word.startsWith("\"") && word.endsWith("\"")) {
                wordList.set(i, word.substring(1, word.length() - 1));
            }
        }
        return wordList;

    }

}



