package assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part5 extends WindowProgram
{
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 2;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 30;

    //we run our method that creates the illusion
    public void run()
    {
        CreateIllusion();
    }

    /**
     * The method uses a nested loop to improve understanding of the code.
     * Although it would have been worth moving it into different methods,
     * in my opinion, the code is better readable this way
     */

    private void CreateIllusion()
    {
        //Depending on the size of the illusion square, we get the middle of our illusion

        double pos_y = (getHeight() - NUM_ROWS * BOX_SIZE - (NUM_ROWS - 1) * BOX_SPACING) / 2;

        double sumBoxSpicingAndSize = BOX_SIZE + BOX_SPACING;
        /*
        Through a nested loop we create our illusion using black squares and indents between them
         */
        for (int i = 0; i < NUM_ROWS; i++)
        {
            //We update the position for X every time we start from a new row
            double pos_x = (getWidth() - NUM_COLS * BOX_SIZE - (NUM_COLS - 1) * BOX_SPACING) / 2;
            for (int j = 0; j < NUM_COLS; j++)
            {
                //draws a predefined number of squares in a row (NUM_COLS)
                GRect square = new GRect(pos_x,pos_y,BOX_SIZE,BOX_SIZE);
                /*
                We set the settings for our figure:
                1. Is it possible to change the background color.
                2. Replacing the background
                3. Edge color
                */
                square.setFilled(true);
                square.setFillColor(Color.black);
                square.setColor(Color.black);
                add(square);//add object
                //offsets the x coordinates by the sum of the square size + track
                pos_x += sumBoxSpicingAndSize;
            }
            //offsets the y coordinates by the sum of the square size + track
            pos_y += sumBoxSpicingAndSize;
        }
    }
}
