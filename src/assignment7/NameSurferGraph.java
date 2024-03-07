package assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    //an array with the current names that were added
    private ArrayList<NameSurferEntry> dataEntered = new ArrayList<>();
    //add I follow the movements
    public NameSurferGraph() {
        addComponentListener(this);
    }
    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {dataEntered.clear();}
    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (checkEntry(entry)) {
            dataEntered.add(entry);
        }
    }
    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawChart();
        drawLabels();
        //If there are any names in the array, then we display their graph
        if (!dataEntered.isEmpty()) {
            for (int i = 0; i < dataEntered.size(); i++) {

                drawEntry(dataEntered.get(i), i);
            }
        }
    }
    //We draw our mother's rosette
    private void drawChart() {
        //Find out the space between the lines
        double xSpacing = getWidth() / (double) NDECADES;
        //Through a loop with the received data, we add parallel lines
        for (int i = 0; i < NDECADES; i++) {
            GLine line = new GLine(xSpacing * i, 0, xSpacing * i, getHeight());
            add(line);
        }
        //Create two horizontal lines at the top and bottom of the screen
        GLine line = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(line);
        GLine line2 = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(line2);
    }
    //Creating a text that stores information about the current year
    private void drawLabels() {
        double xSpacing = getWidth() / (double) NDECADES;
        int date = START_DECADE;
        //Create a text object so that it is exactly in each column
        for (int i = 0; i < NDECADES; i++) {

            String dateString = Integer.toString(date);
            GLabel label = new GLabel(dateString, xSpacing * i, getHeight());
            label.setFont("Courier-20");
            add(label);
            date += 10;
        }
    }
    private void drawEntry(NameSurferEntry entry, int value) {
        double xSpacing = getWidth() / (double) NDECADES;
        GLine line;

        for (int i = 0; i < NDECADES - 1; i++) {
            //Create a line with basic parameters
            line = new GLine(xSpacing * i, GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (entry.getRank(i) / (double) MAX_RANK),
                    xSpacing * (i + 1), GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (entry.getRank(i + 1) / (double) MAX_RANK));
            if (entry.getRank(i) == 0) {
                // Let's find out the beginning of our line
                line.setStartPoint(xSpacing * i, getHeight() - GRAPH_MARGIN_SIZE);
            }
            //we find out the next coordinate where the graph will go by the next number
            if (entry.getRank(i + 1) == 0) {
                line.setEndPoint(xSpacing * (i + 1), getHeight() - GRAPH_MARGIN_SIZE);
            }
            //Displaying the name above the line itself
            String phrase = entry.getName();
            if (entry.getRank(i) == 0) {
                phrase += " *";
            } else {
                phrase += " " + entry.getRank(i);
            }
            GLabel label = new GLabel(phrase, xSpacing * i + 5, line.getY());
            label.setFont("Courier-10");
            //Cyclic change of graphics color
            line.setColor(setColor(value));
            label.setColor(setColor(value));
            add(line);
            add(label);

            if (i == 10) {
                //Displaying the name above the line itself
                GLabel lastLabel;
                String lastPhrase = entry.getName();
                if (entry.getRank(NDECADES - 1) == 0) {
                    lastPhrase += " *";
                    lastLabel = new GLabel(lastPhrase, xSpacing * (i + 1) + 5, getHeight() - GRAPH_MARGIN_SIZE);
                } else {
                    lastPhrase += " " + entry.getRank(i + 1);
                    lastLabel = new GLabel(lastPhrase, xSpacing * (i + 1) + 5, GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (entry.getRank(i + 1) / (double) MAX_RANK));
                }
                //Cyclic change of graphics column
                lastLabel.setColor(setColor(value));
                lastLabel.setFont("Courier-10");
                add(lastLabel);
            }
        }
    }
//The method checks whether the name is already in the list of graphs that need to be drawn
    private boolean checkEntry(NameSurferEntry entry) {
        for (var model : dataEntered) {
            if (entry == model) {
                return false;
            }
        }
        return true;
    }
//Method for getting a new color for a graph
    private Color setColor(int value) {
        if (value % 4 == 1) {
            return Color.RED;
        } else if (value % 4 == 2) {
            return Color.MAGENTA;
        } else if (value % 4 == 3) {
            return Color.BLACK;
        } else {
            return Color.BLUE;
        }
    }
    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentResized(ComponentEvent e) {update();}
    public void componentShown(ComponentEvent e) {}
}