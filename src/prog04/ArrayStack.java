package prog04;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implementation of the interface StackInterface<E> using an array.
 *
 * @author vjm
 */

public class ArrayStack<E> implements StackInterface<E> {
    // Data Fields
    /**
     * Storage for stack.
     */
    E[] stackArray;

    /**
     * Index to top of stack.
     */
    int topIndex = -1; // initially -1 because there is no top

    private static final int INITIAL_CAPACITY = 4;

    /**
     * Construct an empty stack with the default initial capacity.
     */
    public ArrayStack() {
        stackArray = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Pushes an item onto the top of the stack and returns the item
     * pushed.
     *
     * @param obj The object to be inserted.
     * @return The object inserted.
     */
    public E push(E obj) {
        topIndex++;

        if (stackArray.length == topIndex){
            reallocate();
        }

        stackArray[topIndex] = obj;
        return obj;
    }

    private void reallocate() {

        // This works as well
        // not sure it its allowed to be used
//        Object[] reallocatedArray = Arrays.copyOf(stackArray, stackArray.length+1);


       Object[] reallocatedArray = new Object[stackArray.length+1];

        for (int i = 0; i < stackArray.length; i++) {
            reallocatedArray[i] = stackArray[i];
        }


        stackArray = (E[]) reallocatedArray;
    }

    /**
     * Returns the object at the top of the stack and removes it.
     * post: The stack is one item smaller.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if stack is empty.
     */
    public E pop() {
        if (empty())
            throw new EmptyStackException();

        /****  ****/

        E obj = stackArray[topIndex]; // store value at top index value in obj
        topIndex--; // remove the top index
        return obj; // return the old top index value
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

        return stackArray[topIndex];
    }

    /**
     * Returns true if the stack is empty; otherwise it returns false.
     *
     * @return true if the stack is empty; false if not
     */
    public boolean empty() {

        if (stackArray.length == 0 || stackArray == null || topIndex == -1)return true;
        else return false;

    }

}
