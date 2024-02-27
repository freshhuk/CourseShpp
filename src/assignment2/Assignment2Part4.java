package assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram
{

    //Dimensions of our square
    private static final double SQUARE_SIZE_W = 500;
    private static final double SQUARE_SIZE_H = 200;

    //We run our method that creates the flag
    public void run()
    {
        CreateFlag();
    }

    /*
    The method creates a flag in the middle of the screen based on the parameters of our flag
    */
    public void CreateFlag()
    {
        double x = (getWidth() - SQUARE_SIZE_W) / 2;
        double y = (getHeight() - SQUARE_SIZE_H) / 2;
        double widthOfThirdPartOfFlag = SQUARE_SIZE_W / 3; //width 1/3 of the flag
        //array with flag colors arranged alternately
        Color[] arrColors = {Color.RED, Color.YELLOW, Color.BLACK};
        //this cycle draws a flag. 1/3 of the flag width is added to the start point (x)
        for (int i = 0; i < 3; i++) {
            double radius = widthOfThirdPartOfFlag * i;
            CreateSquare(x + radius, y, widthOfThirdPartOfFlag, SQUARE_SIZE_H, arrColors[i], arrColors[i]);
        }
        //Call a method that prints the text name of the flag
        PrintText();

    }

    private void PrintText()
    {
        //Create a text object in the upper left corner to get its dimensions to create the actual text
        GLabel testText = new GLabel("Flag of Belgium", 0,0);
        //Get the coordinates of the corner where we will place our text
        double x = testText.getWidth()*1.7;
        double y = testText.getDescent();
        testText.setColor(Color.white);//We set the parameters so that our text is not visible
        testText.setFont("Verdana-17");//set font

        add(testText);//add text

        /*
        We create our text in the opposite corner with the given coordinates
         to get the name of the flag in the lower right corner
        */
        GLabel flag_text = new GLabel("Flag of Belgium",getWidth()-x, getHeight()-y);
        flag_text.setFont("Verdana-17");//set font
        add(flag_text);//add text
    }

    public void CreateSquare(double x, double y,
                         double width, double height,
                         Color colorStroke, Color colorFilled) {
        GRect rectangle = new GRect(x, y, width, height);
        rectangle.setColor(colorStroke);
        rectangle.setFilled(true); //trust filled
        rectangle.setFillColor(colorFilled);
        add(rectangle);
    }
}
