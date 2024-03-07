package assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;


import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    //Field for our class to work with your data
    private NameSurferDataBase namesDB = new NameSurferDataBase(NAMES_DATA_FILE);
    private NameSurferGraph graph;
    //All objects on the screen, buttons, and text
    private JLabel nameLabel = new JLabel("Name: ");
    private JTextField nameField = new JTextField(30);
    private JButton graphButton = new JButton("Graph");
    private JButton clearButton = new JButton("Clear");



    public void init() {

        graph = new NameSurferGraph();
        add(graph);
        //create the JTextfield initializers
        add(nameLabel,NORTH);
        add(nameField,NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        //Launch to track button clicks
        addActionListeners();
        nameField.addActionListener(this);
    }
    /* Method: actionPerformed(e) */
    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {

        //Here are the implementations of all the actions of each Interactors
        //If you press the button, we resize the graph and update it
        if( e.getSource() == graphButton || e.getSource() == nameField){
            var entry = namesDB.findEntry(nameField.getText());
            if(entry != null){
                graph.addEntry(namesDB.findEntry(nameField.getText()));
                graph.update();
            }
        }else if( e.getSource() == clearButton){
            //We clean everything we have
            graph.clear();
            graph.update();
        }
    }
}
