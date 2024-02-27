package assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The program creates an illusion.
 * She will make 4 circles in the corners and one white circle in the middle to get the illusion
 */

public class Assignment2Part2 extends WindowProgram {

    //Dimensions for our application window
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 300;
    //Calculate the size of a circle based on its smallest side
    private static final int CIRCLE_DIAMETER = (APPLICATION_WIDTH > APPLICATION_HEIGHT) ?  (APPLICATION_HEIGHT / 2) /2 : (APPLICATION_WIDTH / 2) /2;

    /*
    Let's run our two methods.
     CreateBlackCirclesAtTheCorners - create circles.
     CreateWhiteSquareInCircle - create a square.
     */
    public void run(){

        CreateBlackCirclesAtTheCorners();
        CreateWhiteSquareInCircle();

    }
    /*
    We use our method for creating a circle, thereby creating 4 circles in the coordinates we need
    */
    public void CreateBlackCirclesAtTheCorners()
    {
        //We get the width and length without taking into account the length of the circle
        double x = getWidth() - CIRCLE_DIAMETER;
        double y = getHeight() - CIRCLE_DIAMETER;
        //We call our functions that create a circle at a given coordinate
        CreateCircle(0, 0);
        CreateCircle(x, 0);
        CreateCircle(0, y);
        CreateCircle(x, y);
    }

    //Creating the circle itself in the received coordinates
    public void CreateCircle(double x, double y)
    {
        //Create object circle
        GOval o = new GOval(x , y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        /*
        We set the settings for our figure:
        1. Is it possible to change the background color.
        2. Replacing the background
        3. Edge color
         */
        o.setFilled(true);
        o.setFillColor(Color.black);
        o.setColor(Color.black);
        add(o);//add object
    }
    //The method creates one white square so that it creates the illusion
    public void CreateWhiteSquareInCircle()
    {
        //We get the width and length without taking into account the length of the circle
        double x = getWidth() - CIRCLE_DIAMETER;
        double y = getHeight() - CIRCLE_DIAMETER;
        //We get radius
        double Radius = (double) CIRCLE_DIAMETER /2;
        //Create a square at a given point with given dimensions
        GRect rect = new GRect(CIRCLE_DIAMETER - Radius, CIRCLE_DIAMETER-Radius, x, y);
        /*
        We set the settings for our figure:
        1. Is it possible to change the background color.
        2. Replacing the background
        3. Edge color
         */
        rect.setFilled(true);
        rect.setFillColor(Color.white);
        rect.setColor(Color.white);
        add(rect);//add object

    }

}
