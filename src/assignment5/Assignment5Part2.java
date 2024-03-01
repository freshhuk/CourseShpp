package assignment5;
import java.util.Scanner;

public class Assignment5Part2 {
    public static void main(String[] args) {
        //We run a program that calculates the sum of two numbers that are presented as a string
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            System.out.println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
        }
    }

    private static String addNumericStrings(String n1, String n2) {
        //Let's find out which of the lines is larger to find out the maximum number
        int maxLength = Math.max(n1.length(), n2.length());
        //Create an instance to work with strings
        StringBuilder result = new StringBuilder();
        int carry = 0;//number to carry
        //get updated numbers
        n1 = padWithZeros(n1, maxLength);
        n2 = padWithZeros(n2, maxLength);

        for (int i = maxLength - 1; i >= 0; i--) {
            //Find out the symbol number
            int digit1 = n1.charAt(i) - '0';
            int digit2 = n2.charAt(i) - '0';
            //Solve the sum of two numbers plus the carry if there is one
            int sum = digit1 + digit2 + carry;
            //we get the carry if there is one
            carry = sum / 10;
            result.insert(0, sum % 10);//replacing the string zero with the resulting number
        }

        if (carry > 0) {
            result.insert(0, carry);
        }

        //return string result
        return result.toString();
    }

    private static String padWithZeros(String input, int length) {
        //Calculates the number of zeros that must be added to a string to achieve the desired length.
        int padding = length - input.length();

        StringBuilder paddedString = new StringBuilder(input);
        //Inserts a sequence of zeros (obtained using repeat)
        // at the beginning of a paddedString to achieve the desired length.
        paddedString.insert(0, "0".repeat(padding)); // Java 11 and later
        return paddedString.toString();//Converts a StringBuilder object to a string and returns it as the result of the method.

    }

//Our method that displays the text that we give it and returns the scanner to receive data
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return new Scanner(System.in).nextLine();
    }
}