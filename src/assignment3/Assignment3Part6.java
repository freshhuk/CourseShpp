package assignment3;

import acm.graphics.GLabel;
import com.shpp.cs.a.graphics.WindowProgram;
import acm.graphics.GRect;
import java.awt.*;

public class Assignment3Part6 extends WindowProgram {

    //screen sizes for proper animation
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 300;
    //rectangle dimensions
    private static final int SIZE_WIDTH = 100;
    private static final int SIZE_HEIGHT = 50;

    private static final int FPS = 60; // The number of frames per second
    private static final int PAUSE_TIME = 1000 / FPS; // Delay between frames in milliseconds

    public void run() {
        long startTime = System.currentTimeMillis(); // Program start time

        //method that starts our animation
        playAnimation(startTime);
        //a method that displays the time spent executing a program
        printProgramTime(startTime);
    }
    /*
     The method creates a square that
     will move from one side of the screen to the other, simulating an old DVD loading
     */
    private  void playAnimation(long startTime){

        //We calculate the origin coordinates for our rectangle
        double x = getWidth() / 2.0 - SIZE_WIDTH / 2.0;
        double y = getHeight() / 2.0 - SIZE_HEIGHT / 2.0;

        //We create our rectangle in the obtained coordinates
        GRect ball = new GRect(SIZE_WIDTH, SIZE_HEIGHT);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball, x, y);

        //Create text in the center of our rectangle
        GLabel text = new GLabel("DVD");
        text.setColor(Color.yellow);
        text.setFont("Verdana-17");
        add(text, x + SIZE_WIDTH / 3.0, y + SIZE_HEIGHT / 2.0);


        //Variables responsible for gravity
        double dx = 2;
        double dy = 2;

        //We play our animation until 5 seconds have passed
        while (System.currentTimeMillis() - startTime <= 5000) {

            ball.move(dx, dy);
            text.move(dx, dy);

            // Checking for collision with screen boundaries
            if (ball.getX() <= 0 || ball.getX() + SIZE_WIDTH >= getWidth()) {
                dx = -dx; // Changing direction horizontally
            }
            // Checking for collision with screen boundaries
            if (ball.getY() <= 0 || ball.getY() + SIZE_HEIGHT >= getHeight()) {
                dy = -dy; // Changing the vertical direction
            }
            //Making a stop in the frames
            pause(PAUSE_TIME);
        }
    }

    private void printProgramTime(long startTime){
        long endTime = System.currentTimeMillis(); // Program end time
        long elapsedTime = endTime - startTime; // Time taken to complete the animation

        System.out.println("Time passed (milliseconds): " + elapsedTime);
    }

}
