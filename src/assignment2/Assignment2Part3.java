package assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part3 extends WindowProgram
{
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */

    //Dimensions for our application window
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    //Running our methods for drawing paws
    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y)//Х и У это координаты где должна быть лапа
    {
        /*

        We run our auxiliary method that creates circles of given sizes and in given locations

         */
        drawCircle((FIRST_TOE_OFFSET_X + x) , (FIRST_TOE_OFFSET_Y+y), TOE_WIDTH, TOE_HEIGHT);
        drawCircle((SECOND_TOE_OFFSET_X+ x), (SECOND_TOE_OFFSET_Y+y), TOE_WIDTH, TOE_HEIGHT);
        drawCircle((THIRD_TOE_OFFSET_X+ x), (THIRD_TOE_OFFSET_Y+y), TOE_WIDTH, TOE_HEIGHT);
        drawCircle((HEEL_OFFSET_X+ x), (HEEL_OFFSET_Y+y), HEEL_WIDTH, HEEL_HEIGHT);


    }
    /*
        A method that takes the coordinates and sizes of paws in order
        to create a circle using these parameters
     */
    private void drawCircle(double x, double y, double toe_width, double toe_height)
    {
        //Create object circle
        GOval oval = new GOval(x, y, toe_width, toe_height);
         /*
        We set the settings for our figure:
        1. Is it possible to change the background color.
        2. Replacing the background
         */
        oval.setFilled(true);
        oval.setFillColor(Color.BLACK);
        add(oval);//add object circle
    }
}
