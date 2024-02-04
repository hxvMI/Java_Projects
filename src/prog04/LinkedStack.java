package prog04;

import java.util.EmptyStackException;

/**
 * Class to implement interface StackInterface<E> as a linked list.
 *
 * @author vjm
 */

public class LinkedStack<E> implements StackInterface<E> {

    /**
     * A Node is the building block for a singly-linked list.
     */
    // NOTICE:  You can put a (private) class INSIDE your public class!
    // That way, only this class can use it.
    private class Node {
        // Data Fields
        /**
         * The reference to the data.
         */
        private E data;

        /**
         * The reference to the next node.
         */
        private Node next;

        // Constructors

        /**
         * Creates a new node with a null next field.
         *
         * @param data The data stored
         */
        private Node(E data) {
            this.data = data;
            next = null; // Necessary in C++ but not in Java.
        }
    } //end class Node

    // Data Fields
    /**
     * The reference to the top stack node.
     */
    private Node topNode = null;

    /**
     * Pushes an item onto the top of the stack and returns the item
     * pushed.
     *
     * @param obj The object to be inserted.
     * @return The object inserted.
     */
    public E push(E obj) {
        Node newNode = new Node(obj);
        newNode.next = topNode;
        topNode = newNode;
        return obj;
    }

    /****  ****/


    /**
     * Returns the object at the top of the stack without removing it
     * or changing the stack.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if stack is empty.
     */
    public E peek() {
        if (empty())
            throw new EmptyStackException();

        return topNode.data;    //returns data at topNode
    }

    /**
     * Returns the object at the top of the stack and removes it.
     * post: The stack has one less item.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if stack is empty.
     */
    public E pop() {
        if (empty())
            throw new EmptyStackException();

        E obj = topNode.data; //stores data at top node in obj
        topNode = topNode.next; //sets new topNode to the next one under

        return obj; //returns old top
    }

    /**
     * Returns true if the stack is empty; otherwise it returns false.
     *
     * @return true if the stack is empty; false if not
     */
    public boolean empty() {
        if (topNode == null)return true;    //if there's not topNode that means there is no data in the stack
        else return false;
    }
}
