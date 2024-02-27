package assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 *     The program performs calculations of a quadratic equation.
 *     Considers all cases when the root is one, two or there is none
 *     Exemple quation : a*(x^2) + b*x + c = 0
 */


public class Assignment2Part1 extends TextProgram
{
    public void run()
    {
        /*
        We create an instance of our class to execute the method for solving the discriminant
         */
        SquareDiscriminant squareDiscriminant = new SquareDiscriminant();//Creating an Instance
        squareDiscriminant.inputData();//We call the method to get our data
        squareDiscriminant.solutionEquation();//Call the method to solve the equation
    }
}
/*
    This class provides the functionality for solving the discriminant
 */
class SquareDiscriminant extends TextProgram
{
    /*

     These are our variables in which the value will be
     stored and used in solving the equation itself
     The fields are made private so that they cannot be used outside the class

     */
    private double a;
    private double b;
    private double c;

    //A method that requests data for a b and c
    public void inputData()
    {
        println("Enter a value for this equation - a*(x^2) + b*x + c");
        a = readDouble("Please enter a:");
        b = readDouble("Please enter b:");
        c = readDouble("Please enter c:");
    }
    //A method that solves all leveling cases and returns the exact answer
    public void solutionEquation()
    {
        double resultEquation = (b * b) - (4 * a * c);//Solving the discriminant using the formula

        double resultSQRT = Math.sqrt(resultEquation);//We calculate the root of the equation

        /*
        We check all possible paths When we have:
        1. Has no roots.
        2. The discriminant has one root.
        3. Has two roots.
         */
        if(resultEquation < 0) {
            println("There are no real roots");//Conclusion of our results

        }
        else if(resultEquation == 0) {
            double x1 = (-b)/(2*a);//Let's find the roots using the formula
            println("There is one root: " + x1);//Conclusion of our results

        }
        else{
            double x1 = (-b + resultSQRT)/(2*a);//Let's find the roots using the formula
            double x2 = (-b - resultSQRT)/(2*a);//Let's find the roots using the formula

            println("There are two roots: " + x1 + " and " + x2);//Conclusion of our results

        }
    }
}

