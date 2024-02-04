package prog07;

import prog02.GUI;
import prog02.UserInterface;
import prog05.ArrayQueue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class NotWordle { // NotWordle class
    List<String> allWords = new ArrayList<>();
    UserInterface ui;

    NotWordle(UserInterface ui) { // constructor that takes a UserInterface
        this.ui = ui; // and stores it in a class variable
    }

    //play is sus check again later
    void play (String start, String target){

        if (start == null || target == null)return;
        String currWord;
        boolean exit = false;

        while(exit == false) {
            ui.sendMessage("Current word: " + start + "\n" + "Target word: " + target);

            currWord = ui.getInfo("What is your next word?");
            if (currWord == null) return;
            if (find(currWord) < 0)//if not found
                ui.sendMessage(currWord + " is not in the dictionary.");
            else if (oneLetterDifferent(start, currWord) == false) //if oneLet diff send
                ui.sendMessage("Sorry, but " + currWord + " differs by more than one letter from " + start);
            else {

                if (currWord.equals(target)) {
                    ui.sendMessage("You win!");
                    return;
                }
                start = currWord;
            }
        }
    }



    void solve (String start, String target) {
        int[] parentIndices = new int[allWords.size()];
//        Queue<Integer> intQueue = new ArrayQueue<>();
//        Queue<Integer> intQueue = new PriorityQueue<>(new IndexComparator(parentIndices,target));
        Queue<Integer> intQueue = new Heap<>(new IndexComparator(parentIndices,target));
        int startIndex = find(start);//index of start word
        boolean pathExists = false;
        String path = "";


        if (startIndex == -1){ //to get rid of the index out of bounds error
            ui.sendMessage("Start word is not in the dictionary.");
            return;
        }

        //set all elements to -1
        Arrays.fill(parentIndices, -1);

        //store index of start word
        intQueue.offer(startIndex); // store index in intQueue

        int pollNum = 0;
        while (intQueue.isEmpty() == false) {//while intQueue not empty
            int parentIndex = intQueue.poll(); //remove and index and store it
            pollNum++; //Count Number of times poll was ran
            String parentWord = allWords.get(parentIndex); //get the word from its index

            for (int index = 0; index < allWords.size(); index++) {//go through list of allWords


                //considers both parentIndices[index] == -1 and the other condition
                if ((parentIndices[index] == -1 && index != startIndex) ||
                        (numSteps(parentIndices, parentIndex) + 1 < numSteps(parentIndices, index) && index != startIndex)) {
                    String childWord = allWords.get(index);
                    if (numDifferent(childWord,parentWord) == 1){//CHANGED THIS FROM oneLetterDifferent


                        if (parentIndices[index] != -1) { //if this then remove it
                            intQueue.remove(index);
                        }
                        parentIndices[index] = parentIndex;
                        intQueue.offer(index);

                        if (childWord.equals(target)){ //if equals target it exists
                            pathExists = true;  //set path to true
                            ui.sendMessage("The target is " + numSteps(parentIndices,index)
                                    + " steps away from the start");//how many steps it is away from the start

                            int currIndex = index;
                            String currWord; //stores current word
                            while (currIndex != startIndex) { //will continue till traced back
                                currWord = allWords.get(currIndex);
                                if (childWord == null){ //if word is null end
                                    ui.sendMessage("No path from " + start + " to " + target);
                                    ui.sendMessage("Poll has been run " + pollNum + " times"); // SEND MESSAGE DISPLAYING TIMES POLL RAN
                                    return;
                                }
                                path = currWord + "\n" + path; //else make path of words
                                currIndex = parentIndices[currIndex];
                        }
                        path = allWords.get(startIndex) + "\n" + path; // start word and the following words
                        ui.sendMessage(path);
                        ui.sendMessage("Poll has been run " + pollNum + " times");// SEND MESSAGE DISPLAYING TIMES POLL RAN
                            return;
                        }
                    }
                }
            }
        }
        //if no path exists send this message
        if (pathExists == false) ui.sendMessage("No path from " + start + " to " + target);

        return;
    }

    class IndexComparator implements Comparator<Integer>{
        int[] parentIndices;
        String target;

        //Take pareIndices and target words and store them for the class
        IndexComparator(int[] parentIndices, String target){
            this.parentIndices = parentIndices;
            this.target = target;
        }

        //return num of steps from index + num of differences between currWord and target word
        int priority(int index){
            int steps = numSteps(parentIndices, index); //get the numOfSteps
            String currWord = allWords.get(index); //get the word from currInd for numOfDiffers
            int diff = numDifferent(currWord, target); //get the numOfDiffers

            return steps + diff;
        }

        //Compare:
        //if 1st > 2nd it returns positive int
        //if 1st < 2nd it returns negative int
        //if 1st = 2nd compare returns 0
        @Override
        public int compare(Integer o1, Integer o2) {
            //first > second returns positive result
            //if first = 7 second = 5 result = 2
            //first < second returns negative result
            //if first = 5 second = 7 result = -2
            //first = second returns zero
            //if first = 5 second = 5 result = 0
            return priority(o1) - priority(o2);
        }
    }


    static int numSteps(int[] parentIndices, int index){

        //Conditional Statement in case of out of bounds
        if (index < 0 || index >= parentIndices.length)return -1;

        int steps = 0;
        while (index != -1){ //while currIndex isn't startIndex
            steps++;
            index = parentIndices[index]; //move up 1
        }

        return steps-1; //steps excluding the start index
    }

    static int numDifferent(String input1, String input2){

        if (input1 == null || input2 == null) return -1;
        if (input1.length() != input2.length()) return -1;

        int numDif = 0;
        for (int i = 0; i < input1.length(); i++) { //till end of word
            if (input1.charAt(i) != input2.charAt(i)){
                numDif++; //if letters are diff increase numDif by 1
            }
        }

//        if (input1.length() > input2.length()){
//            numDif = numDif + (input1.length() - input2.length());
//        }
//        else if (input2.length() > input1.length()){
//            numDif = numDif + (input2.length() - input1.length());
//        }

        return numDif;
    }


    static boolean oneLetterDifferent(String input1, String input2){

        if (input1 == null || input2 == null)return false;

        if (input1.length() != input2.length()){
            return false;
        }
        else{
            int difCount = 0;
            for (int i = 0; i < input1.length(); i++) {
                if (input1.charAt(i) != input2.charAt(i)){
                    difCount++;
                }
            }
            if (difCount == 1)return true;
            else return false;
        }
    }

    /*void*/ boolean loadWords(String path) /*throws FileNotFoundException*/{


//            Scanner input = new Scanner(new File(path));
//
//            while(input.hasNext()){
//                allWords.add(input.next());
//            }
//            input.close();

        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            String currWord;
            while ((currWord = fileReader.readLine()) != null) {
                allWords.add(currWord);
            }
        }
        catch (Exception e){
            ui.sendMessage("Uh oh: " + e);
            return false;
        }

        return true;
    }

    int find(String word){
        //binary search

        int low = 0;
        int high = allWords.size()-1;


        while(low <= high || high >= low){
            int middle = (low+high)/2;
            int compare = allWords.get(middle).compareTo(word);//gets the word in the middle of allWords list
            //compareTo returns 0 if words are same, and a num < 0 if lexo is greater the string argument
            if (compare == 0)return middle; //if same return the middle index
            if (compare < 0)low=middle+1; //if greater than move floor up
            else high=middle-1; //else move celling down
        }

        return -low-1;
    }


    //sus check again later
    public static void main(String[] args) {

        GUI gui = new GUI("Not Wordle");
        NotWordle game = new NotWordle(gui);



        boolean fileLoaded = false;     // while fileLoaded is false
        while(fileLoaded == false){     // words from file still haven't been loaded
            String path = gui.getInfo("Enter word file:"); //keep asking till valid input
            if (path == null)return;
            if (game.loadWords(path) == true){
                fileLoaded = true;
            }// once words have been loaded exit loop
        }


//        String fileName = "";
//        boolean file = false;
//        while(!file){
//            try {
//                fileName = game.ui.getInfo("Enter word file:");
//                if (fileName == null)return;
//                game.loadWords(fileName);
//                file = true;
//            }
//            catch (FileNotFoundException e){
//                gui.sendMessage("Uh oh: " + e + ": " + fileName +
//                        "(The system cannot find the file specified)");
//            }
//        }




        String startWord = gui.getInfo("Enter starting word");
        if (startWord == null){
            while(startWord == null){
                startWord = gui.getInfo("Enter starting word");
            }
        }

        String targetWord = gui.getInfo("Enter target word");
        if (targetWord == null){
            while (targetWord == null){
                targetWord = gui.getInfo("Enter target word");
            }
        }


        String[] commands = { "Human plays.", "Computer plays." };
        if (gui.getCommand(commands) == 0){ // if selects human call play
            game.play(startWord,targetWord);
        }
        else{ // else call solve for computer
            game.solve(startWord,targetWord);
        }
    }
}//End of Class

