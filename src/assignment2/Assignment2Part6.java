package assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    //Number of segments
    private static final int COUNT_SEGMENT = 7;
    //Segment size for caterpillar
    private static final double SIZE_CATERPILLAR = 100;

    //Run the method to create Caterpillar
    public void run() {
        CreateCaterpillar();
    }

    /**
     * The method creates a green caterpillar using a loop.
     * When the number in the cycle counter is paired,
     * the caterpillar remains the same.
     * If the number is not paired, the position of the segment changes
     */
    public void CreateCaterpillar() {
        double x = 50;//start x position
        double y = 50;//start y position

        double Radius = SIZE_CATERPILLAR / 2; // Radius segment
        for (int i = 0; i < COUNT_SEGMENT; i++) {
            //If the number in the counter is paired, then nothing changes
            if ((i % 2) == 0) {
                //create object circle
                GOval oval = new GOval(x += Radius, y, SIZE_CATERPILLAR, SIZE_CATERPILLAR);
                CreateCircle(oval);//We call our function that sets the value for the object and adds it
            }
            //Otherwise, if our number is not paired, then we change the position according to Y
            else {
                //create object circle
                GOval oval = new GOval(x += Radius, y + (Radius / 2), SIZE_CATERPILLAR, SIZE_CATERPILLAR);
                CreateCircle(oval);//We call our function that sets the value for the object and adds it
            }
        }
    }

    /*
     The method takes a circle object and adds the necessary
     parameters to it, such as color, stroke color, and adds this object
     */
    public void CreateCircle(GOval oval) {
        oval.setFilled(true);
        oval.setColor(Color.black);
        oval.setFillColor(Color.green);
        add(oval);
    }
}
