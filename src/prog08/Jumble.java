package prog08;

import prog02.ConsoleUI;
import prog02.UserInterface;
import prog07.BST;
import prog09.BTree;

import java.io.File;
import java.util.*;

public class Jumble {
  /**
   * Sort the letters in a word.
   * @param word Input word to be sorted, like "computer".
   * @return Sorted version of word, like "cemoptru".
   */
  public static String sort (String word) {
    char[] sorted = new char[word.length()];
    for (int n = 0; n < word.length(); n++) {
      char c = word.charAt(n);
      int i = n;

      while (i > 0 && c < sorted[i-1]) {
        sorted[i] = sorted[i-1];
        i--;
      }

      sorted[i] = c;
    }

    return new String(sorted, 0, word.length());
  }






  public static void main (String[] args) {
      //UserInterface ui = new GUI("Jumble");
    UserInterface ui = new ConsoleUI();

//    Map<String,String> map = new TreeMap<String,String>();
//     Map<String,String> map = new PDMap();
//     Map<String,String> map = new LinkedMap<String,String>();
//     Map<String,String> map = new SkipMap<String,String>();
//    BST<String,String> map = new BST<String,String>();

    BTree<String, List<String>> map = new BTree<>();


    /////////////////////THIS IS GOOD
    Scanner in = null;
    do {
      try {
        in = new Scanner(new File(ui.getInfo("Enter word file.")));
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("Try again.");
      }
    } while (in == null);




    /////////////////////Probably good but kinda sus
    int n = 0;
    while (in.hasNextLine()) {
      String word = in.nextLine();
      String sortedWord = sort(word);
      if (n++ % 1000 == 0)
	      System.out.println(word + " sorted is " + sortedWord);


//      map.remove(sortedWord);
//      if (map.get(sortedWord) != null){
//        System.out.println("warning warning warning warning warning");
//      }
//      map.put(sortedWord,new ArrayList<String>());


      if (!map.containsKey(sortedWord)){ //if does not contain make new place
        map.put(sortedWord, new ArrayList<String>());
      }
      map.get(sortedWord).add(word);

    }




    /////////////////////THIS IS GOOD
    while (true) {
      String jumble = ui.getInfo("Enter jumble.");
      if (jumble == null) break;


      String sortedWords = sort(jumble);
      if (map.containsKey(sortedWords) == true) { //if contains print them
        List<String> wordsList = map.get(sortedWords);
        ui.sendMessage(jumble + " unjumbled is " + sortedWords + " : " + wordsList);
      }
      else //else say not match
        ui.sendMessage("No match for " + jumble);
      }




    while (true){
//prompt + conditionals
      String clueLets = ui.getInfo("Enter a set of clue letters: ");
      if (clueLets == null || clueLets.isEmpty() || clueLets.equals(null)) break;
      String clueLength = ui.getInfo("Enter the clue letter length: ");
      if (clueLength == null || clueLength.isEmpty() || clueLength.equals(null)) break;

//Sorting + Formatting
      String sortedLets = sort(clueLets);
      int clueLengthInt = Integer.parseInt(clueLength);
      int key1index = 0;                      //Declare key1index and initialize it to zero


      for(String key1 : map.keySet()){
        key1index = 0;                        //need to reset


        if (key1.length() == clueLengthInt){ //For each key (key1) that has the right length:
          String key2 = "";                  //Create an empty string key2
          for(char sortedLet : sortedLets.toCharArray()){
            if (key1index >= key1.length()) key2 = key2 + sortedLet;
            else if (sortedLet == key1.charAt(key1index)) key1index++;
            else if (sortedLet > key1.charAt(key1index)) break;
            else key2 = key2 + sortedLet;
          }


          if (key2.length() + key1index == sortedLets.length() && map.containsKey(key2))
            ui.sendMessage(map.get(key1) + " " + map.get(key2));


        }
      }
    }
  }
}




