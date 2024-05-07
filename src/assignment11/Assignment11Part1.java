package assignment11;

import acm.graphics.GLine;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.awt.*;
import java.util.ArrayList;

/*
The program creates a graph of a function based on the input string.
 */
public class Assignment11Part1 extends WindowProgram {

    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 1000;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 1000;

    /**
     * A constant for increasing indents, created so
     * that the result of the program can be better seen,
     * since if you enter a small number, the changes are not visible
     */
    private static final int ZOOM_FACTOR = 10;
    /**
     * All objects on the screen, buttons, and text
     */
    private JTextField nameField = new JTextField(30);
    private JButton graphButton = new JButton("Graph");
    private JButton clearButton = new JButton("Clear");

    //todo Небольшой баг при сдвиге влево и при этом увеличивать высоту то она не увеличится
    @Override
    public void init() {
        super.init();
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);

        addActionListeners();
        nameField.addActionListener(this);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {

        //Here are the implementations of all the actions of each Interactors
        //If you press the button, we resize the graph and update it
        if (e.getSource() == graphButton || e.getSource() == nameField) {
            try {
                //We pass the resulting formula to our method for drawing it
                String func = nameField.getText();
                drawFunc(func);
            } catch (Exception ex) {
                System.out.println("Ops... Error");
            }

        } else if (e.getSource() == clearButton) {
            //We clean everything we have
            clear();
        }
    }

    /**
     *A method that deletes all objects and then draws everything again
     */
    public void clear() {
        removeAll();
        run();
        init();
    }

    /**
     * Creating graph lines in the wound,
     * this is done so that the intersection of the lines is exactly in the center
     */
    public void run() {createLine();}

    /**
     * A method that will draw the function itself by formula.
     * First, he parses it into individual values, then uses the resulting data for calculations
     * @param func - Function formula received from the user
     */
    private void drawFunc(String func) {
        //List where the formula will be stored after parsing
        ArrayList<String> parsedFunc = parser(func);

        if (parsedFunc != null) {
            //We determine the type of function so that we know what graph we should draw.
            char typeFunc = getTypeFunc(parsedFunc.get(0));
            //If the function is just a number, then we draw
            if (typeFunc == 'n') {
                // first element - X, second - Y, third - width
                double[] cordYX = changeGraph(parsedFunc, false);
                double y = cordYX[1]; // get y cord from array

                y -= Double.parseDouble(parsedFunc.get(0));
                //We call our method that creates a line
                //according to the specified formula since the formula is linear
                createLineFunc(0, y, getWidth(), y);

            } else if (typeFunc == 'x') {
                //If our function is linear, that is, it contains X
                double width;
                // first element - X, second - Y, third - width
                double[] cordYX = changeGraph(parsedFunc, true);
                width = cordYX[2] < 0 ? -1 * cordYX[2] : cordYX[2];//We find out the size from the received data to know our function to narrow or expand
                //If our function is just X
                if (parsedFunc.get(0).equals("x")) {
                    // Let's draw our function with all parameters
                    createLineFunc(0 + width * ZOOM_FACTOR, getHeight() + (cordYX[1] * ZOOM_FACTOR), getWidth() + (cordYX[0] * ZOOM_FACTOR - width * 10), 0);
                }
                //If a function has a minus, we must expand it in a different direction
                else {
                    createLineFunc(0, 0 + width * ZOOM_FACTOR, getWidth() + (cordYX[0] * ZOOM_FACTOR), getHeight() + (cordYX[1] * ZOOM_FACTOR) -width * ZOOM_FACTOR);
                }
            } else if (typeFunc == 'p') { //If the function is a parabola
                // first element - X, second - Y, third - width
                double[] cord = changeGraph(parsedFunc, false);
                double centerX = cord[0];
                double centerY = cord[1];
                //If we have a regular function, then render it with the usual parameters
                if (parsedFunc.get(0).equals("x^2")) {
                    drawParabola(centerX, centerY, cord[2], true, true);  // Right
                    drawParabola(centerX, centerY, cord[2], false, true); // Left
                }
                //Otherwise, our branches will be directed downward since there will be a minus in front of X
                else {
                    drawParabola(centerX, centerY, cord[2], true, false);  // Left
                    drawParabola(centerX, centerY, cord[2], false, false); // Left
                }

            } else if (typeFunc == 'g') { //If our function is a hyperbola, then we must draw the corresponding graph
                // first element - X, second - Y, third - width
                double[] cord = changeGraph(parsedFunc, false);
                double centerX = cord[0];
                double centerY = cord[1];
                //If we draw the usual formula as it should according to the parameters,
                //otherwise we draw everything in a different direction
                if (parsedFunc.get(0).equals("x^3")) {
                    drawParabola(centerX, centerY, cord[2], true, true);  // Right
                    drawParabola(centerX, centerY, cord[2], false, false); // Left
                } else {
                    drawParabola(centerX, centerY, cord[2], true, false);  // Left
                    drawParabola(centerX, centerY, cord[2], false, true); // Right
                }

            } else if (typeFunc == '0') {//If the function was not defined then print a message
                System.out.println("Unknown func");
            }
        }

    }

    /**
     * A function that parses a string and puts all elements into an array
     * @param func The original function entered by the user
     * @return Return a list of elements that make up the function
     */
    private ArrayList<String> parser(String func) {
        ArrayList<String> parsedFunc = new ArrayList<>();
        if (func.startsWith("y=")) {
            func = func.substring(2).replace(" ", "").toLowerCase(); // Delete "y="
            //We put spaces before the operators and also remove the bracket
            //if it is there and also separate it with spaces
            func = func.replaceAll("\\+", " +")
                    .replaceAll("-", " -")
                    .replaceAll("\\*", " *")
                    .replaceAll("/", " /")
                    .replaceAll("\\(", "")
                    .replace(")", " )")
                    .trim();
            //We divide the string with spaces into alimony and assign it to an array
            String[] tokens = func.split(" ");
            //We add elements from the array to our list
            parsedFunc.addAll(Arrays.asList(tokens));
        } else {
            return null;
        }
        return parsedFunc;
    }

    /**
     * A function that takes the first element
     *of a parsed function and determines what kind of function it is and then sends the resulting result
     * @param func - Function formula received from the user
     * @return Function type
     */
    private char getTypeFunc(String func) {
        char type = '0';
        if (func.equals("x") || func.equals("-x")) {
            type = 'x';
        } else if (func.equals("x^2") || func.equals("-x^2")) {
            type = 'p';
        } else if (func.equals("x^3") || func.equals("-x^3")) {
            type = 'g';
        } else if (Character.isDigit(func.charAt(0)) || Character.isDigit(func.charAt(1))) {
            type = 'n';
        }
        return type;
    }

    /**
     *A function that simply creates a cross from lines so that you can then draw a graph on it
     */
    private void createLine() {
        double x = getWidth() / 2.;
        double y = getHeight() / 2.;

        GLine line = new GLine(0, y, getWidth(), y);
        add(line);
        GLine line2 = new GLine(x, 0, x, getHeight());
        add(line2);
    }

    /**
     * A function that creates lines at specified coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @param x2 second X coordinate
     * @param y2 second Y coordinate
     */
    private void createLineFunc(double x, double y, double x2, double y2) {
        GLine line = new GLine(x, y, x2, y2);
        line.setColor(Color.RED);
        add(line);
    }

    /**
     * A function that draws a parabola branch based on the received parameters
     * @param startX Start drawing a parabola branch along the X coordinate
     * @param startY Start drawing a parabola branch along the Y coordinate
     * @param width Width for our function
     * @param toRight Does the branch have a positive sign? If not, it should be drawn down
     * @param itsParabola Checkbox to check whether it is a parabola or not
     */
    private void drawParabola(double startX, double startY, double width, boolean toRight, boolean itsParabola) {
        double x = startX;
        double y = startY;
        double increment = toRight ? 1 : -1;
        double lineDirection = itsParabola ? 1 : -1;
        for (int i = 1; i <= getWidth(); i++) {
            double nextX = x + (increment * i) - 1;
            double nextY;
            if (width == 0) {
                nextY = y - lineDirection * ((nextX - startX) * (nextX - startX) / ZOOM_FACTOR);
            } else if (width > 0) {
                nextY = y - lineDirection * ((nextX - startX) * (nextX - startX) * width);
            } else {
                nextY = y - lineDirection * ((nextX - startX) * (nextX - startX) / (ZOOM_FACTOR + (-1 * width * ZOOM_FACTOR)));
            }
            createLineFunc(x, y, nextX, nextY);
            x = nextX;
            y = nextY;
        }
    }

    /**
     * Method for changing a function, since the function can be narrowed or moved
     * @param parsedFunc List of parsed function elements
     * @param itsLine The parameter to check is whether our function is linear or not
     * @return Returns an array of three elements where the first two
     * are the coordinates and the third element is the change in the width of the function
     */
    private double[] changeGraph(ArrayList<String> parsedFunc, boolean itsLine) {
        double[] cordXY = new double[3]; // first element - X, second - Y, third - width
        double x = itsLine ? 0 : getWidth() / 2.;
        double y = itsLine ? 0 : getHeight() / 2.;
        double width = 0;

        //We check what element we have and what operator it has
        //and perform the necessary actions with the coordinates based on the received number
        for (int i = 1; i < parsedFunc.size(); i++) {
            if (parsedFunc.get(i).charAt(0) == '+') {
                y -= Double.parseDouble(parsedFunc.get(i).substring(1));
            }
            if (parsedFunc.get(i).charAt(0) == '-') {
                y += Double.parseDouble(parsedFunc.get(i).substring(1));
            }
            if (parsedFunc.get(1).charAt(0) == '*') {
                width += Double.parseDouble(parsedFunc.get(i).substring(1));
            }
            if (parsedFunc.get(1).charAt(0) == '/') {
                width -= Double.parseDouble(parsedFunc.get(i).substring(1));
            }
            if (parsedFunc.get(i).charAt(0) == ')') {
                if (parsedFunc.get(i - 1).charAt(0) == '-') {
                    x += Double.parseDouble(parsedFunc.get(i - 1).substring(1));
                } else {
                    x -= Double.parseDouble(parsedFunc.get(i - 1).substring(1));
                }
            }
        }
        //We assign coordinates to an array and send them
        cordXY[0] = x;
        cordXY[1] = y;
        cordXY[2] = width;

        return cordXY;
    }

}