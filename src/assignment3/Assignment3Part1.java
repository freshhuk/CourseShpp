package assignment3;

import java.util.Scanner;

public class Assignment3Part1
{

    /**
     * The program asks how many minutes a day he devoted to exercise and,
     * based on the data received, makes the necessary calculations
     */
    public static void main(String[] args)
    {
        howMinuteOnDays();//We launch our method that will request our data
    }
    private static void howMinuteOnDays()
    {
        //We create an instance of the Scanner class to help it read data from the console
        Scanner scanner = new Scanner(System.in);

        //These variables mean how many days the regimen was followed
        int totalCardioDays = 0;
        int totalLowPressureDays = 0;

        //We run our cycle which will ask the user 7 times how many minutes he spent a day on exercises
        for (int i = 1; i <= 7; i++) {
            System.out.print("How many minutes did you do on day " + i + "? ");
            int minutes = scanner.nextInt();//We request data from the user through a scanner

            /*
            We check through the condition whether the
            time norm has been met or not; if the norm is met,
            then we add that this day is counted
             */
            if (minutes >= 30) {
                totalCardioDays++;
            }
            if (minutes >= 40) {
                totalLowPressureDays++;
            }
        }
        scanner.close();
        //We count how many days cardio was not followed
        int remainingCardioDays = 5 - totalCardioDays;
        //We count how many days the blood pressure exercise was not followed
        int remainingLowPressureDays = 3  - totalLowPressureDays;

        //We run our method which, based on your results, displays the appropriate text
        printResult(remainingCardioDays, remainingLowPressureDays);
    }

    //The method accepts our results and displays the necessary information
    private static void printResult(int remainingCardioDays, int remainingLowPressureDays)
    {
        System.out.println("\nCardiovascular health:");
        if (remainingCardioDays > 0) {
            System.out.println("\tYou needed to train hard for at least " + remainingCardioDays + " more day(s) a week!");
        } else {
            System.out.println("\tGreat job! You've done enough exercise for cardiovascular health.");
        }

        System.out.println("Blood pressure:");
        if (remainingLowPressureDays > 0) {
            System.out.println("\tYou needed to train hard for at least " + remainingLowPressureDays + " more day(s) a week!");
        } else {
            System.out.println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        }
    }
}
