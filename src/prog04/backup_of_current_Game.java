//package prog04;
//
//import prog02.UserInterface;
//import prog02.GUI;
//
//public class Game {
//    //    static UserInterface ui = new ConsoleUI();
//    static UserInterface ui = new GUI("Tower of Hanoi");
//
//    static public void main(String[] args) {
//        int n = getInt("How many disks?");
//        if (n <= 0) return;
//        Game tower = new Game(n);
//
//        String[] commands = {"Human plays.", "Computer plays."};
//        int c = ui.getCommand(commands);
//        if (c == -1) return;
//        if (c == 0) tower.play();
//        else tower.solve();
//    }
//
//    /**
//     * Get an integer from the user using prompt as the request.
//     * Return 0 if user cancels.
//     */
//    static int getInt(String prompt) {
//        while (true) {
//            String number = ui.getInfo(prompt);
//            if (number == null) return 0;
//            try {
//                return Integer.parseInt(number);
//            } catch (Exception e) {
//                ui.sendMessage(number + " is not a number.  Try again.");
//            }
//        }
//    }
//
//    int nDisks;
//    StackInterface<Integer>[] pegs = (StackInterface<Integer>[]) new ArrayStack[3];
//
//    Game(int nDisks) {
//        this.nDisks = nDisks;
//        for (int i = 0; i < pegs.length; i++)
//            pegs[i] = new ArrayStack<Integer>();
//
//        // : Initialize game with pile of nDisks disks on peg 'a' (pegs[0]).
//
//        //push nDisks disks(integers) onto pegs[0]
//        //1 on the top      //what does this mean
//        //pets[1] probably means put pegs[largestValue] on top?  9->8->7....->1->0
//        //smallest nDisks on bottom
//        //nDisks is how many disks you have which decrease over runs
//        //want to put the largest number at top of stack
//        //repeat loop until you run out of nDisks
//        //nDisks = 0 should be at bottom
//        //nDisks = starting# should be at top
//        //reverse forloop where you start at end and put highest nDisk at top of .pegs[]
//
//        int i = nDisks;
//        while (i > 0){       //until "i" is equal to 0
//            pegs[0].push(i);  //push nDisk to pegs[0]
//            i--;              //decrease i by 1
//        }
//
//
//
//    }
//
//    void play() {
//        String[] moves = {"ab", "ac", "ba", "bc", "ca", "cb"};
//        int[] froms = {0, 0, 1, 1, 2, 2};
//        int[] tos = {1, 2, 0, 2, 0, 1};
//
//        boolean fixthis = true;
//
//        /* :  player has not moved all the disks to 'c'. */
//        //true while player is not finished
//        //false if player has been moved all disks to pegs[2]
//
//        while (fixthis == true) {
//
//            displayPegs();
//            int imove = ui.getCommand(moves);
//            if (imove == -1) return;
//            int from = froms[imove];
//            int to = tos[imove];
//            move(from, to);
//
//            if (pegs[0].empty() && pegs[1].empty()){// if they are empty that means
//                fixthis = false;            //all nDisks have been moved to pegs[2]
//            }
//        }
//
//        displayPegs();
//        ui.sendMessage("You win!");
//    }
//
//    String stackToString(StackInterface<Integer> peg) {
//        StackInterface<Integer> helper = new ArrayStack();
//
//        // String to append items to.
//        String s = "";
//
//        // :  append the items in peg to s from bottom to top.
//
//        while (peg.empty() == false){// put all peg inside of helper to reverse order
//            helper.push(peg.pop());
//        }
//
//
//        if (peg.empty() == true){// once peg is empty that means helper must be full and in reverse order
//            while (helper.empty() == false){// so until helper is empty pop everything into "s"
//
//                Object temp = helper.peek();
//                s = s + " " + helper.pop(); //thus appending everything to "s" in reverse order
//                peg.push((Integer) temp);
//
//            }
//        }
//
//
//        return s;
//    }
//
//    void displayPegs() {
//        String s = "";
//        for (int i = 0; i < pegs.length; i++) {
//            char abc = (char) ('a' + i);
//            s = s + abc + stackToString(pegs[i]);
//            if (i < pegs.length - 1) s = s + "\n";
//        }
//        ui.sendMessage(s);
//    }
//
//    void move(int from, int to) {
//
//        // :  move one disk form pegs[from] to pegs[to].
//        // Don't allow illegal moves:  send a warning message instead.
//        // For example "Cannot place disk 2 on top of disk 1."
//        // Use ui.sendMessage() to send messages.
//
//        if (pegs[from].empty() == true){
//            ui.sendMessage("Cannot move disk from empty stack.");
//        }
//        else if (pegs[to].empty() == true) {
//            pegs[to].push(pegs[from].pop());
//        }
//        else if (pegs[from].peek() > pegs[to].peek()){
//            ui.sendMessage("Cannot place disk " + pegs[from].peek() + " on top of " + pegs[to].peek() + ".");
//        }
////        else if (pegs[to].empty() == true && pegs[to].peek() < pegs[from].peek()){
////            ui.sendMessage("Cannot place disk " + pegs[from].peek() + " on top of " + pegs[to].peek() + ".");
////          //or replace the 2 above with this one
////        }
//        else {
//            pegs[to].push(pegs[from].pop());
//        }
//    }
//
//    // 
//    public class Goal{
//
//        int fromPeg, toPeg, nMove;
//        Goal(int setfromPeg, int settoPeg, int setnMove){   //constructor to set variables
//            fromPeg = setfromPeg;
//            toPeg = settoPeg;
//            nMove = setnMove;
//        }
//
//        public String toString(){
//            String s = "";
//            String fromLetter = null;
//            String toLetter = null;
//
//
//            //0 = a, 1 = b, 2 = c
//            if (fromPeg == 0)fromLetter = "a";
//            else if (fromPeg == 1)fromLetter = "b";
//            else if (fromPeg == 2)fromLetter = "c";
//
//            //0 = a, 1 = b, 2 = c
//            if (toPeg == 0)toLetter = "a";
//            else if (toPeg == 1)toLetter = "b";
//            else if (toPeg == 2)toLetter = "c";
//
//            if (nMove > 1) {s = s + "Move " + nMove + " disks from peg " + fromLetter + " to peg " + toLetter + ".";} //disks if mor than 1
//            else {s = s + "Move " + nMove + " disk from peg " + fromLetter + " to peg " + toLetter + ".";} //disk if just 1
//
//
//            return s;
//        }
//
////        public String display(){    //look at again later
////            String s = toString();
////
////            s = s + "\n";
////            return s;
////        }
//    }// End of Goal Class
//
//    // 
//    void display(StackInterface<Goal> allGoals){ //look at again later
//        String s = "";  // String of allGoals
//        StackInterface<Goal> temp = new ArrayStack<>(); // to store allGoals as they are popped for the string
//
//        // repeat until allGoals is empty meaning you've appended all of them to s
//        while (allGoals.empty() == false){
//            // if not empty make a new line at end
//            if (allGoals.empty() == false){
//                temp.push(allGoals.peek());
//                s = s + allGoals.pop() + "\n";}
//            // if empty don't make a new line after
//            else{
//                temp.push(allGoals.peek());
//                s = s + allGoals.pop();
//            }
//        }
//
//        // refilling allGoals stack
//        while (temp.empty() == false){
//            allGoals.push(temp.pop());
//        }
//
//
//        ui.sendMessage(s);
//    }
//
//
//    void solve() {
//        //  13
//
//        StackInterface<Goal> allGoals = new ArrayStack();
//        allGoals.push(new Goal(0,2,nDisks)); //make an initial goal  //0 = a, 1 = b, 2 = c
//        // since we only go to c set to as 2
//        displayPegs();  //initial displayPeg
//
//        while (allGoals.empty() == false) {
//            display(allGoals); //display all goals constantly
//            Goal currentGoal = allGoals.pop(); //set current goal to top of allGoals
//            int cg_nMove = currentGoal.nMove; //current num of disks you want to move
//            int cg_from = currentGoal.fromPeg; //0 = a, 1 = b, 2 = c
//            int cg_to = currentGoal.toPeg; //0 = a, 1 = b, 2 = c
//            int pegNotInUse = 3 - (cg_from + cg_to); //the largest num to+from can make is 3
//            //so if you add them up and subtract them from 3 you can determine is not in use
//
//
//            if (cg_nMove == 1){ //if nMove is 1 you can remove peg from and move it to peg to
////                pegs[cg_to].push(pegs[cg_from].pop());
//                move(cg_from,cg_to); // pop cgfrom and push it to cgto
//                displayPegs();  //then you can display the pegs
//            }
//            else if (!(cg_nMove == 1)){  //break goals down in to smaller sub-goals that make use of the pegNotInUse to reach the final goal
//                allGoals.push(new Goal(pegNotInUse, cg_to, cg_nMove-1)); //3ab  nMove = 3-1 (is -1 so it stays in range of setnMove)
//                // , a = pegNotInUse(from), b = to
//                allGoals.push(new Goal(cg_from,cg_to,1));   //1ac nMove = 1, a = from, c = to
//                allGoals.push(new Goal(cg_from,pegNotInUse,cg_nMove-1) // 3bc nMove = 3-1, b = from, c = pegNotInUse
//                );
//            }
//        }
//
//
//    }
//
//
//}//End of Game Class
