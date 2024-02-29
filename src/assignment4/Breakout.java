package assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import acm.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Breakout extends WindowProgram {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 2;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 2;

    /** Separation between bricks */
    private static final int BRICK_SEP = 20;

     private double BRICK_WIDTH;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;


    //Diameter
    private static final int BALL_DIAMETER = BALL_RADIUS*2;
    //Field for the selected Paddle
    private GObject selectedPaddle ;
    //Variables for the velocity vector
    private double vx = 0;
    private double vy = 5;

    private static final int FPS = 60; // The number of frames per second
    private static final int PAUSE_TIME = 1000 / FPS; // Delay between frames in milliseconds




    public void run(){

        //Let's find out the length of our brick
        BRICK_WIDTH = calculateBrickWidth();
        //The method that will build all our bricks
        buildBrickGrid();
        //The method generates a starting vector for X
        generationVectorX();
        //Create a paddle object
        GRect paddle = createPaddle();
        add(paddle);
        //A method that allows you to control using the mouse
        addMouseListeners();
        //Create our ball object
        GOval ball = createBall(calculateInitialBallY());
        add(ball);
        //We run the start method that pauses the game
        startGame();
        //And let's start our game
        startMoveBall(ball, paddle);

    }






    private void startMoveBall(GOval ball, GRect paddle){
        //game score
        int totalScore = 0;
        //Counting the number of attempts
        int attempt = 0;
        //We start the game until our attempts end
        while (attempt < NTURNS) {
            //Moving our ball using our vectors
            ball.move(vx, vy);
            //Let's find out the collider of our object that our racket collided with
            GObject collider = getCollidingObject(ball);
            //Check we collided with a wall, if so then change our vector
            if (ball.getX() <= 0 || ball.getX() + BALL_DIAMETER >= getWidth()) {
                vx = -vx; // Changing direction horizontally
            }

            // Check we collided with a wall, if so then change our vector
            if (ball.getY() <= 0 || collider == paddle) {
                vy = -vy; // Changing the vertical direction
            }
            //Check if we collided not with a racket but with a brick, then remove the brick
            if (collider != null && collider != paddle) {
                remove(collider);
                totalScore++;
                vy = -vy;
            }
            //If we touched the floor then we add the number of attempts and start over
            if (ball.getY() + BALL_DIAMETER >= getHeight()) {
                ball.setLocation((getWidth() - BALL_DIAMETER) / 2.0, calculateInitialBallY());
                generationVectorX();
                attempt++;
                if (attempt < (NTURNS)) {
                    startGame();
                }
            }
            pause(PAUSE_TIME);
            //If we break all the bricks then we win
            if (totalScore == (NBRICK_ROWS * NBRICKS_PER_ROW)) {
                printText("You won");
                break;
            }
        }
        //If we have exhausted all our attempts then we have lost and the corresponding text is displayed
        if (NTURNS == attempt) {
            printText("Game over!");
        }
    }

    private GObject getCollidingObject(GOval ball){
        // Checking all four corners of the ball for collision
        GObject[] corners = new GObject[4];
        corners[0] = getElementAt(ball.getX(), ball.getY());
        corners[1] = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY());
        corners[2] = getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER);
        corners[3] = getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER);

        // Return the first object found if there is a collision
        for (GObject corner : corners) {
            if (corner != null) {
                return corner;
            }
        }
        return null;
    }

    //Building our all bricks
    private void buildBrickGrid(){

        //Set the height
        double y = BRICK_Y_OFFSET;
        Color currentColor = Color.RED;//Set the start color

        // Calculate the total brick width, including separators
        int totalBrickWidth = NBRICKS_PER_ROW * (int)BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;

        // Calculate the starting x-coordinate for centered placement
        double startX = (getWidth() - totalBrickWidth) / 2.0;

        int count = 0;

        for (int i = 0; i < NBRICK_ROWS; i++) {
            double x = startX; // Reset x for each row to ensure centering
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                //If all the colors have already been started, start over
                if(count > 9){
                    count = 0;
                }
                //We line it up so that every two rows there is a new color
                currentColor = switch (count) {
                    case 0 -> Color.RED;
                    case 2 -> Color.ORANGE;
                    case 4 -> Color.YELLOW;
                    case 6 -> Color.GREEN;
                    case 8 -> Color.CYAN;
                    default -> currentColor;
                };
                createBrick(x, y, currentColor);//Creating our brick according to the parameters we need
                x += BRICK_WIDTH + BRICK_SEP;
            }
            count++;
            y += BRICK_HEIGHT + BRICK_SEP;
        }
    }
    //Generation of our Vekor X
    private void generationVectorX(){
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
    }


    /**
     * Repositions the dragged object to the mouse's location when the mouse
     * is moved.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        double y = getHeight() - PADDLE_HEIGHT;
        /* If there is something to drag at all, go move it. */

        if (selectedPaddle != null) {
            //We make calculations so that the racket does not come out of the screen
            double newX = Math.max(0, Math.min(e.getX() - selectedPaddle.getWidth() / 2.0, getWidth() - selectedPaddle.getWidth()));
            selectedPaddle.setLocation(newX, y-PADDLE_Y_OFFSET);
        }
    }
    //Making the racket itself
    private GRect createPaddle(){
        //calculate the coordinates for the racket
        double x = (getWidth() - PADDLE_WIDTH) / 2.0;
        double y = getHeight() - PADDLE_HEIGHT;
        //We create a racket object with the parameters
        //we need and indicate that this is our object that we will control
        GRect paddle = new GRect(x,y-PADDLE_Y_OFFSET, PADDLE_WIDTH,PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.black);
        selectedPaddle = paddle;
        return  paddle;
    }
    //We calculate the location of the ball taking into account the bricks
    private double calculateInitialBallY() {
        double paddleY = getHeight() - PADDLE_HEIGHT;
        double bricksY = BRICK_Y_OFFSET + NBRICK_ROWS * (BRICK_HEIGHT + BRICK_SEP);
        return (paddleY + bricksY) / 2.0 - BALL_RADIUS;
    }

    //Creating the ball object itself with the given parameters
    private GOval createBall(double initialY) {
        double x = (getWidth() - BALL_DIAMETER) / 2.0;
        GOval ball = new GOval(x, initialY, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setFillColor(Color.black);
        return ball;
    }
    //We create our object square which will be our racket and put the fields we need there
    private void createBrick(double x, double y, Color color){
       GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
       brick.setFilled(true);
       brick.setFillColor(color);
       add(brick);
    }
    //Method for creating text that we will pass to it
    private GLabel printText(String text){
        GLabel newText = new GLabel(text);
        newText.setColor(Color.black);
        newText.setFont("Verdana-17");
        //coordinates for all text
        double x = (getWidth() - newText.getWidth())/2;
        double y = (getHeight() - newText.getHeight())/2;

        add(newText, x, y);
        return newText;
    }
    //a method that calculates the length of a brick based on the size of the screen and other things
    private int calculateBrickWidth(){
        return (getWidth()-(NBRICK_ROWS-1)*BRICK_SEP)/NBRICKS_PER_ROW;
    }
    //Method that pauses the game
    private void startGame(){
        GLabel startText = printText("To start, tap on the screen");
        waitForClick();
        remove(startText);
    }
}
