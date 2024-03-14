package assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.ErrorException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    HashMap<String, NameSurferEntry> nameDB = new HashMap<String, NameSurferEntry>();

    /* Constructor: NameSurferDataBase(filename) */
    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            //We read all the data into our array
            BufferedReader rd = new BufferedReader(new FileReader(filename));

            while (true) {
                String line = rd.readLine();
                if (line == null) break;//if the file has ended we exit the loop
                NameSurferEntry entry = new NameSurferEntry(line);
                nameDB.put(entry.getName(), entry);//put the received data into our hashmap
            }
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }
    /* Method: findEntry(name) */
    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        //method for finding by object name if it is not found we get null
        if(!name.isEmpty()){
            name = name.toLowerCase();
            String newName = (name.substring(0, 1).toUpperCase()) + name.substring(1);
            if (nameDB.containsKey(newName)) {
                return nameDB.get(newName);
            }
        }
        return null;
    }
}

