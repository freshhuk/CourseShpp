package assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /* Constructor: NameSurferEntry(line) */
    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
//Our default fields for the model are the name.
//a whole line with information and the popularity of the name
    private String entryLine = "";
    private String name = "";
    private Integer[] decades = new Integer[NDECADES];

    public NameSurferEntry(String line) {
        // You fill this in //

        //Setting a standard line
        entryLine = line;
        //Separating the name from the string
        name = entryLine.substring(0,entryLine.indexOf(" "));

        entryLine = entryLine.substring(entryLine.indexOf(" ")).trim();

        int i = 0;

        //Through a loop we take, select all the numbers from the
        // string and put them in the queue into an array
        StringTokenizer tokenizer = new StringTokenizer(entryLine, " " , false);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            int digit = Integer.parseUnsignedInt(token);
            decades[i] = digit;
            i++;
        }
    }







    /* Method: getName() */

    /*
     * Returns the name associated with this entry.
     */
    public String getName() {
        // You need to turn this stub into a real implementation //
        return name;
    }

    /* Method: getRank(decade) */
    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        // You need to turn this stub into a real implementation //
        return decades[decade];
    }

    /* Method: toString() */
    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String result = "";
        for(int i = 0; i < NDECADES; i++){
            result += decades[i] + " ";
        }
        result = result.trim();
        return name + " [" + result + "]";
    }
}

