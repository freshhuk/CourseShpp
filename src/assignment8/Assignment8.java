package assignment8;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class Assignment8 extends WindowProgram {
    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 800;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 600;
    //Size of all objects
    private final int CELL_SIZE = 40;
    //Number of seeds
    private final int NUM_SEED = 10;
    //One second delay
    private static final int PAUSE_TIME = 1000;
    //Grid of all objects that are on the screen
    private GRect[][] cells;

    public void run() {
        //The method sets the initial dimensions of the entire mesh and places the seeds
        setUpGrid();
        //Mouse click tracking
        addMouseListeners();
        while (true) {
            //Method that moves all objects to the bottom
            moveElementsDown();
            pause(PAUSE_TIME);
        }
    }

    private void setUpGrid() {
        //Find out the number of our columns and rows for objects that will be on the screen
        int rows = (getHeight() / CELL_SIZE);
        int cols = (getWidth() / CELL_SIZE);

        // Calculation of balance to the bottom of the screen
        int heightRemainder = (getHeight() % CELL_SIZE);
        //We transfer our data to the array
        cells = new GRect[rows][cols];
        //Filling the grid with objects
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = createCell(j * CELL_SIZE, i * CELL_SIZE + heightRemainder, CELL_SIZE, CELL_SIZE);
            }
        }
        //A method that randomly places seeds
        placeSeeds(cells, NUM_SEED);
    }

    //Creating empty squares for screen
    private GRect createCell(double x, double y, double width, double height) {
        GRect cell = new GRect(x, y, width, height);
        cell.setFilled(true);
        cell.setColor(Color.WHITE);
        add(cell);
        return cell;
    }

    //The method will randomly create seeds on the grid
    private void placeSeeds(GRect[][] cells, int numSeeds) {
        //Find out the mesh sizes
        int rows = cells.length;
        int cols = cells[0].length;

        for (int i = 0; i < numSeeds; i++) {
            int row = randomInt(0, rows - 1);
            int col = randomInt(0, cols - 1);
            if(isWhite(cells[row][col])){
                cells[row][col].setColor(Color.BLACK);
            }
        }
    }

    //The method moves elements down
    private void moveElementsDown() {
        //Find out the mesh sizes
        int rows = cells.length;
        int cols = cells[0].length;

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                //If the object is a seed, then he moves it down
                if (isBlack(cells[i][j])) {
                    moveSeedDown(i, j);
                    //If the object is water, then it calls a special method for the movement of water
                } else if (isBlue(cells[i][j])) {
                    handleWaterDrop(i, j);
                }
            }
        }
        //If water comes into contact with the border of the screen,
        //the program will remove this water
        removeOutOfBoundsWaterDrops(rows, cols);
    }

    //The method simulates the movement of water by replacing the current
    //object with a white square and the lower one making the desired color
    private void moveSeedDown(int i, int j) {
        int rows = cells.length;

        if (i < rows - 1 && isWhite(cells[i + 1][j])) {
            cells[i + 1][j].setColor(Color.BLACK);
            cells[i][j].setColor(Color.WHITE);
        }
    }

    //Method for water movement.
    //REPLACES the current object with white and the bottom one with blue,
    //but if the water reaches the seed, it will be replaced with green
    private void handleWaterDrop(int i, int j) {
        int rows = cells.length;

        if (i < rows - 1) {
            if (isWhite(cells[i + 1][j])) {
                cells[i + 1][j].setColor(Color.BLUE);
                cells[i][j].setColor(Color.WHITE);
            } else if (isBlack(cells[i + 1][j])) {
                handleWaterDropOnSeed(i, j);

            }
            //If we reach a green object, we simply create a new green one to simulate plant growth
            else if (isGreen(cells[i + 1][j])) {
                cells[i][j].setColor(Color.GREEN);
            }
        }
    }
    //A method for simulating watering a plant. If it hits black, then two green objects appear
    private void handleWaterDropOnSeed(int i, int j) {
        int rows = cells.length;

        //We remove the water, replacing it with green if we come into contact with a green object
        if (i + 1 < rows && isWhite(cells[i + 1][j])) {
            cells[i + 1][j].setColor(Color.GREEN);
        } else if (i + 1 < rows && isBlack(cells[i + 1][j])) {
            cells[i + 1][j].setColor(Color.GREEN);
        }

        cells[i][j].setColor(Color.GREEN);

        if (i > 0 && isWhite(cells[i - 1][j])) {
            cells[i - 1][j].setColor(Color.WHITE);
        }
    }
    //A method that removes a water object if it reaches the screen border
    private void removeOutOfBoundsWaterDrops(int rows, int cols) {
        for (int j = 0; j < cols; j++) {
            if (isBlue(cells[rows - 1][j])) {
                handleWaterDropOnBottom(rows - 1, j);
            }
        }
    }

    private void handleWaterDropOnBottom(int i, int j) {
        cells[i][j].setColor(Color.WHITE);

        if (i > 0 && isWhite(cells[i - 1][j])) {
            cells[i - 1][j].setColor(Color.WHITE);
        }
    }

    public void mouseClicked(MouseEvent e) {
        //Let's find out a place to create a blue object there
        int row = (e.getY() / CELL_SIZE);
        int col = (e.getX() / CELL_SIZE);
        //We check if the place is empty, then only then will we create a drop there
        if (isWhite(cells[row][col])) {
            cells[row][col].setColor(Color.BLUE);
        }
    }
    //Method for generating a random number
    private int randomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    //Auxiliary methods for determining the color of an object
    private boolean isBlack(GRect cell) {
        return cell.getColor().equals(Color.BLACK);
    }

    private boolean isWhite(GRect cell) {
        return cell.getColor().equals(Color.WHITE);
    }

    private boolean isBlue(GRect cell) {
        return cell.getColor().equals(Color.BLUE);
    }

    private boolean isGreen(GRect cell) {
        return cell.getColor().equals(Color.GREEN);
    }
}