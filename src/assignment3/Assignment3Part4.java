package assignment3;


import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
public class Assignment3Part4 extends WindowProgram {


    //Constants for brick size
    private static final int BRICK_HEIGHT = 50;
    private static final int BRICK_WIDTH = 100;
    private static final int BRICKS_IN_BASE  = 5;


    public void run()
    {
        //Running our method that will build a pyramid
        build();
    }

    private  void build()
    {
        //Find out the starting number of bricks in the base
        int currentCountBrick = BRICKS_IN_BASE;

        //Find out the coordinates for the base of the pyramid
        double x = (getWidth() - BRICK_WIDTH * currentCountBrick) / 2.0;
        double y = getHeight() - BRICK_HEIGHT;
        /*
        We start a cycle that will draw a pyramid
        until there is not a single brick left at the base of the line
         */
        while (currentCountBrick != 0)
        {

            //The loop builds a line of bricks in the center
            for (int i = 0; i < currentCountBrick; i++) {
                createBrick(x, y);
                x += BRICK_WIDTH;
            }
            //Raise the Y coordinate to the height of the brick
            y-=BRICK_HEIGHT;
            //We take one brick from the base to make a pyramid
            currentCountBrick--;
            //We calculate the center at the X coordinate with the updated number of bricks at the base
            x = (getWidth() - BRICK_WIDTH * currentCountBrick)/2.0;
        }

    }
    //The method creates a square according to the parameters given to it
    private void createBrick(double x, double y)
    {
        GRect brick = new GRect(x,y,BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(Color.YELLOW);
        add(brick);
    }
}
