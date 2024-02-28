package assignment3;

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part3 extends TextProgram {
    //This is a program for calculating powers of a number.
    public void run()
    {
        /*
         We run our method by passing it two values.
         Where the first number is the number to which we
         will raise the degree and the second number is the degree itself
         */
        double result = raiseToPower(5, -2);
        //output result
        System.out.println(result);

    }
    /*
     The method works in all cases.
     When our degree is 0, we simply return 1.
     If our degree is negative, then there will be calculations for this, or a regular degree,
     then there will be regular calculations
     */
    private double raiseToPower(double base, int exponent)
    {
        double numberToThePower = base;//The number that will be raised to a power

        //If the degree is greater than 0 then we start a
        //cycle where the number can be added to
        //itself as many times as we have the number to the degree
        if(exponent > 0){
            for(int i = 1; i < exponent; i++)
            {
                base *= numberToThePower;
            }
            return  base;
        }
        /*
         if the degree is less than zero,
         then we divide our number by ourselves,
         thereby calculating the minus degree
         */
        else if(exponent < 0) {
            for(int i = 1; i > exponent; exponent++)
            {
                base /= numberToThePower;
            }
            return  base;
        }
        //otherwise if the degree is 0 then return 1
        return 1;
    }
}
