package assignment5;

import java.util.Scanner;
import java.lang.*;
public class Assignment5Part1 {

    public static void main(String[] args)
    {
        //Create instance scanner for read data
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a single word: ");
            String word = scanner.nextLine();//Reading a word from the scanner
            System.out.println("  Syllable count: " + syllablesInWord(word));
        }
    }
    private static int syllablesInWord(String word){
        int numberSyllablesWord = 0;//count Syllables
        char[] letters = new char[]{'a','e','y','u','i','o','A','E','Y','U','I','O'};//All vowels

        //We go through all the letters of the word
        for(int index = 0; index < word.length(); index++){
            for (char letter : letters) {
                //Checking for the end of the word
                if (index + 1 < word.length()) {
                    char nextLetter = word.charAt(index + 1);
                    //If we have a vowel letter and there is an unvowel
                    // letter before it, then we count plus one to the syllables
                    if (word.charAt(index) == letter && nextLetter != letter && !isVowel(nextLetter)) {
                        numberSyllablesWord++;
                    }
                }
                //if we have a vowel at the end other than e, then we also count the syllable
                else if (word.charAt(index) == letter && word.charAt(index) != 'e') {
                    numberSyllablesWord++;
                }
            }
        }
        //If your program does not find any syllables,
        // then we return one syllable since any word has at least one syllable
        return (numberSyllablesWord == 0) ? 1 : numberSyllablesWord;
    }

    //The function checks the next letter after the vowel, if it is also a vowel then it will return true
    private static boolean isVowel(char letter){
        char[] vowels = {'a','e','y','u','i','o','A','E','Y','U','I','O'};//all vowel
        for (char vowel : vowels) {
            if (vowel == letter) {
                return true;
            }
        }
        return false;
    }
}
