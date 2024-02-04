package prog03;

import prog02.UserInterface;
import prog02.GUI;


/**
 * @author vjm
 */
public class Main {
    /**
     * Use this variable to store the result of each call to fib.
     */
    public static double fibn;

    /**
     * Determine the time in microseconds it takes to calculate the
     * n'th Fibonacci number.
     *
     * @param fib an object that implements the Fib interface
     * @param n   the index of the Fibonacci number to calculate
     * @return the time for the call to fib(n)
     */
    public static double time(Fib fib, int n) {
        // Get the current time in nanoseconds.
        long start = System.nanoTime();

        // Calculate the n'th Fibonacci number.  Store the
        // result in fibn.
        fibn = fib.fib(n); // fix this

        // Get the current time in nanoseconds.
        long end = System.nanoTime();                               // FIXED

        // Return the difference between the end time and the
        // start time divided by 1000.0 to convert to microseconds.
        return (end-start)/1000.0;                                   // FIXED
    }

    /**
     * Determine the average time in microseconds it takes to calculate
     * the n'th Fibonacci number.
     *
     * @param fib    an object that implements the Fib interface
     * @param n      the index of the Fibonacci number to calculate
     * @param ncalls the number of calls to average over
     * @return the average time per call
     */
    public static double averageTime(Fib fib, int n, int ncalls) {
        double totalTime = 0;

        // Add up the total call time for ncalls calls to time (above).

        for (int i = 0; i < ncalls; i++) {
            totalTime = totalTime + time(fib,n);
        }

        // Return the average time.
        return totalTime / ncalls;
    }

    /**
     * Determine the time in microseconds it takes to to calculate the
     * n'th Fibonacci number.  Average over enough calls for a total
     * time of at least one second.
     *
     * @param fib an object that implements the Fib interface
     * @param n   the index of the Fibonacci number to calculate
     * @return the time it takes to compute the n'th Fibonacci number
     */
    public static double accurateTime(Fib fib, int n) {
        // Get the time in microseconds for one call.
        double t = time(fib,n); // fix this

        // If the time is (equivalent to) more than a second, return it.
        if (t >= (1000000.0)){
            return t;
        }

        // Estimate the number of calls that would add up to one second.
        // Use   (int)(YOUR EXPESSION)   so you can save it into an int variable.
        int numcalls = (int)((1000000.0)/t); // fix this

        if (numcalls > 1000000.0){  //if numcalls is more than 1mil set numcall = 1mil
            numcalls = 1000000;
        }

        System.out.println("numcalls " + numcalls);

        // Get the average time using averageTime above with that many
        // calls and return it.
        return averageTime(fib,n,numcalls); // fix this
    }

//    private static UserInterface ui = new GUI("Fibonacci experiments");     //Test on your Own
    private static UserInterface ui = new TestUI("Fibonacci experiments");     //Test with program

    /**
     * Get a non-negative integer from the using using ui.
     * If the user enters a negative integer, like -2, say
     * "-2 is negative...invalid"
     * If the user enters a non-integer, like abc, say
     * "abc is not an integer"
     * If the user clicks cancel, return -1.
     *
     * @return the non-negative integer entered by the user or -1 for cancel or -2 for an invalid input.
     */
    static int getInteger() {
        String s = ui.getInfo("Enter n");

        try {
            if (s == null) return -1; // user clicked cancel
            if (Integer.parseInt(s) < 0) { // number is negative return -2 to repeat loop
                ui.sendMessage(s + " is negative. Input is invalid.");
                return -2;
            }
        }
        catch (NumberFormatException e){ // is not number return -2 to repeat loop
            ui.sendMessage(s + " is not an integer.");
            return -2;
        }


        return Integer.parseInt(s); // does not catch any errors
    }

    public static void doExperiments(Fib fib) {
        System.out.println("doExperiments " + fib);
        boolean leaveLoop = false;
        boolean firstLoop = true;
        int n = 0;
        double accurateTime = 0;
        double errorPercent = 0;


        while(leaveLoop == false) {

            double estimatedTime = 0;   // to reset estimated time per-loop
            int yesORno = 0;         // needs to be inside to reset back to 0 in-case we change it to 1
            n = getInteger();       //calls getInteger and stores its value inside "n"



                if (n == -1)return; // if null or cancel ends the current loop and goes back to fibPicker
                if (n == -2)continue; // if invalid input repeats the loop until input is valid



            if (firstLoop != true){ // if not first loop
                estimatedTime = fib.estimateTime(n);        //calculates and stores estimated time
                ui.sendMessage("Estimated time from input " + n + " is " + estimatedTime + " microseconds.");
            }



            if (estimatedTime > (3.6*Math.pow(10,9))){ // if greater than an hour
                ui.sendMessage("Estimated time is more than an hour. \n" +
                        "I will ask you if you really want to run it.");
                yesORno = ui.getCommand(new String[]{"yes", "no"});
            }     // will set value to 0 or 1 depending on input



            if (yesORno == 0){
                accurateTime = accurateTime(fib,n);
                fib.saveConstant(n,accurateTime);

                if (firstLoop != true){ // if not first loop
                    errorPercent = ((estimatedTime-accurateTime)/accurateTime)*100.0;
                    ui.sendMessage("fib(" + n + ") = " + fibn + " in " + accurateTime + " microseconds. " + errorPercent + "% error.");}
                else {
                    ui.sendMessage("fib(" + n + ") = " + fibn + " in " + accurateTime + " microseconds.");
                }
            }
//            else if (yesORno == 1) {
//                continue;
//            }


            if (firstLoop == true)firstLoop = false;        //sets first loop to false at the end of the first loop


        }
    }

    public static void doExperiments() {
        // Give the user a choice instead, in a loop, with the option to exit.
//        doExperiments(new ExponentialFib());


        boolean leaveLoop = false;
        int fibPicker;


        while(leaveLoop == false) {
            fibPicker = ui.getCommand(new String[]{"ExponentialFib",
                    "LinearFib", "LogFib", "ConstantFib", "MysteryFib", "EXIT"});


            switch (fibPicker) {
                case -1: return; //top right X
                case 0: doExperiments(new ExponentialFib()); break;
                case 1: doExperiments(new LinearFib()); break;
                case 2: doExperiments(new LogFib()); break;
                case 3: doExperiments(new ConstantFib()); break;
                case 4: doExperiments(new MysteryFib()); break;
                case 5: return; //EXIT
            }
        }
    }

    static void labExperiments() {
        // Create (Exponential time) Fib object and test it.
//        Fib efib = new ExponentialFib();
//        Fib efib = new LinearFib();                       ////Uncomment for #5
        Fib efib = new LogFib();
        System.out.println(efib);
        for (int i = 0; i < 11; i++)
            System.out.println(i + " " + efib.fib(i));

        // Determine running time for n1 = 20 and print it out.
        int n1 = 20;
//        double time1 = averageTime(efib, n1, 45454);/********************************************/
        double time1 = accurateTime(efib,n1);

        System.out.println("n1 " + n1 + " time1 " + time1);

        // Calculate constant:  time = constant times O(n).
        double c = time1 / efib.O(n1);
        System.out.println("c " + c);

        // Estimate running time for n2=30.
        int n2 = 30;
        double time2est = c * efib.O(n2);
        System.out.println("n2 " + n2 + " estimated time " + time2est);

        // Calculate actual running time for n2=30.
//        double time2 = averageTime(efib, n2, 125);/********************************************/
        double time2 = accurateTime(efib,n2);

        System.out.println("n2 " + n2 + " actual time " + time2);

        // Estimate how long ExponentialFib.fib(100) would take.
        // Estimate running time for n3=100.
        int n3 = 100;
        double time3est = c * efib.O(n3);
        double seconds = time3est/1000000.0;
        double minutes = seconds/60.0;
        double hours = minutes/60.0;
        double days = hours/24.0;
        double years = days/365.0;
        System.out.println("n3 " + n3 + " estimated time in years: " + years);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        labExperiments();
        doExperiments();
    }
}
