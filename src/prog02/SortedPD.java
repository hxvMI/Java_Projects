package prog02;

import java.io.*;

/**
 * This is an implementation of PhoneDirectory that uses a sorted
 * array to store the entries.
 *
 * @author 
 */
public class SortedPD extends ArrayBasedPD {

    /**
     * Find an entry in the directory.
     *
     * @param name The name to be found
     * @return The index of the entry with that name or, if it is not
     * there, (-insert_index - 1), where insert_index is the index
     * where should be added.
     */
    protected int find(String name) {
//        for (int i = 0; i < size; i++)                    //Old Code
//            if (theDirectory[i].getName().equals(name))
//                return i;
//        return -size - 1;


        int highIndex = size - 1;
        int lowIndex = 0;

        while(lowIndex <= highIndex){
            int middleIndex = (highIndex+lowIndex)/2;

            if (theDirectory[middleIndex].getName().compareTo(name) < 0)lowIndex = middleIndex+1;
            else if (theDirectory[middleIndex].getName().compareTo(name) > 0)highIndex = middleIndex-1;
            else return middleIndex;
        }
        return -lowIndex - 1;

    }

    /**
     * Add an entry to the directory.
     *
     * @param index    The index at which to add the entry to theDirectory.
     * @param newEntry The new entry to add.
     * @return The DirectoryEntry that was just added.
     */
    protected DirectoryEntry add(int index, DirectoryEntry newEntry) {
//        index = 0; //For Testing

        if (size == theDirectory.length) reallocate();


        try {
            for (int i = size; i > index; i--) {
                if(i-1 > -1)theDirectory[i] = theDirectory[i-1];
            }
        }catch (IndexOutOfBoundsException e){
            theDirectory[size] = theDirectory[index]; //Part of Old Code
        }


        theDirectory[index] = newEntry;
        size++;
        return newEntry;
    }


    /**
     * Remove an entry from the directory.
     *
     * @param index The index in theDirectory of the entry to remove.
     * @return The DirectoryEntry that was just removed.
     */
    protected DirectoryEntry remove(int index) {
//        DirectoryEntry entry = theDirectory[index];       //old code
//        theDirectory[index] = theDirectory[size - 1];

        DirectoryEntry entry = theDirectory[index];
        for (int i = index; i < size; i++) {
            if (i == size-1)break;
            theDirectory[i] = theDirectory[i+1];    //Everything is copied over the next index
        }

        size--;
        return entry;
    }

}
