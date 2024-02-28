package assignment3;

import java.util.Scanner;

public class Assignment3Part2
{

    public static void main(String[] args)
    {
        //We create an instance of the Scanner class to help it read data from the console
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();//We request data from the user through a scanner

        //We run our method to make sure that the correct number was entered
        checkNumber(number);
        scanner.close();//close scanner
    }
    /*
    The method checks whether the entered number is correct.
    That is, the program will start when the number is greater than zero
     */
    private static void checkNumber(int number){
        if(number < 1)
        {
            System.out.println("Enter the correct number (the number must be > 0)");
        }
        else{
            //if the number is correct then we run our method to count
            countingNumbers(number);
        }
    }

    /*
     The method makes the necessary calculations with
     the number when it is even and when it is odd,
     in the end the number will always become 1
    */
    private static void countingNumbers(int number)
    {
        while(number != 1)
        {
            if((number % 2) == 0)
            {
                //if the number is even then we divide it by two
                System.out.print(number + " is even so I take half: ");
                number /=2;
                System.out.println(number);
            }
            else{
                //If it’s odd, then we multiply by 3 and add one
                System.out.print(number + " is odd so I make 3n + 1: ");
                number = number * 3 + 1;
                System.out.println(number);
            }
            //And so on until we get 1 as a result.
        }
        System.out.print("The end");
    }


}
