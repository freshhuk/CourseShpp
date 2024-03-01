package assignment5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment5Part3 {
    private static final String FILE_PATH = "en-dictionary.txt";

    public static void main(String[] args) throws IOException {
        //Create instance scanner for read data
        Scanner scanner = new Scanner(System.in);

        /*
          Let's enter a command that will constantly ask
          for a number and if it is correct in format,
          it will display all the numbers, otherwise it
          will simply not work the code, and it will ask you to enter the number again
         */
        while(true){
            System.out.println("Enter the number");
            String carNumber = scanner.nextLine();
            carNumber = deleteDigits(carNumber);
            getWord(carNumber);
        }
    }
 //The method searches for words with a sequence of letters and outputs these words
    private static void getWord(String sequence) throws IOException {
        //list words
        List<String> words = readWords(FILE_PATH);
        //Through a loop we check all the words to see
        // if they are similar to the number and if there is a sequence
        for (String word : words) {
            if (containsSequence(word, sequence)) {
                System.out.println(word);

            }
        }
    }

    //A method that searches for a sequence of letters from a number in words from a list
    private static boolean containsSequence(String word, String sequence) {
        int sequenceIndex = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == sequence.charAt(sequenceIndex)) {
                sequenceIndex++;
                if (sequenceIndex == sequence.length()) {
                    return true; // Found a sequence in a word
                }
            }
        }

        return false;
    }
    //A method that counts all words from a file and puts them in an array
    private static List<String> readWords(String path) throws IOException {
        //Array with words
        List<String> words = new ArrayList<>();
        //Create an instance to read data from a file at the specified path
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String word;
            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
        }

        return words;
    }
    //Method for removing numbers from our number to get only letters
    private static String deleteDigits(String num){
        return num.toLowerCase()
                .replace("1","")
                .replace("2", "")
                .replace("3", "")
                .replace("4", "")
                .replace("5", "")
                .replace("6", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "")
                .replace("0", "");
    }


}

