//package prog11;
//
//import prog05.ArrayQueue;
//
//import java.util.*;
//
//public class NotGPT implements SearchEngine{
//
//    //Are both maps so you can use .get .put and so on
//    Map<String, Long> urlToIndex = new TreeMap<>();
//    Map<String, Long> wordToIndex = new HashMap<>();
//    HardDisk pageDisk = new HardDisk();
//    HardDisk wordDisk = new HardDisk();
//
//    @Override
//    public void collect(Browser browser, List<String> startingURLs) {
//        Queue pageQueue = new ArrayQueue(); //page index queue
//        System.out.println("starting pages " + startingURLs);
//
//
//
//        //check all starting urls
//        for (String currUrl : startingURLs){
//            if (urlToIndex.containsKey(currUrl) == false){//check if not indexed
//                pageQueue.offer(indexPage(currUrl));//index currUrl using indexPage and store it in temp to put back in Queue
//            }
//        }
//
//
//
//        //while queue not empty
//        while (pageQueue.isEmpty() == false){//dequeue a page index and since it returns a value store it for later
//            System.out.println("queue " + pageQueue);
//
//
//            Map<String, Boolean> seenWordsMap = new HashMap<>(); //Set of seen
//            Set<String> seenURLSet = new HashSet<>(); //Set of seen
//            Long pageIndex = (Long) pageQueue.poll(); //Dequeue and store
//            InfoFile pageInfoFile = pageDisk.get(pageIndex);
//            String pageURL = pageInfoFile.data;//get its url from pageDisk using its index
//            boolean loaded = browser.loadPage(pageURL);
//
//
//            System.out.println("dequeued " + pageInfoFile);
//
//
//            ///////////////////////////URL SECTION
//            //Index each URL on page if not indexed yet and add it to page index indices list the first time its seen
//            if (loaded == true){//if loaded get the list of its urls
//                List<String> listOfUrl = browser.getURLs();//.getURLs returns a list
//                System.out.println("urls " + listOfUrl);
//                for (String currIndex : listOfUrl) {// go through each url
//                    if (seenURLSet.contains(currIndex) == false) {// if not in seenSet
//                        long urlIndex;
//                        if (urlToIndex.containsKey(currIndex)){ //check if currIndex is in urlToIndex
//                            urlIndex = urlToIndex.get(currIndex); //if yes retrieve the index
//                        }
//                        else {
//                            urlIndex = indexPage(currIndex);//else index it and set urlIndex to it
//                            pageQueue.offer(urlIndex);//put it in pageQueue
//                        }
//
//                        pageInfoFile.indices.add(urlIndex);//add to indices of page
//                        seenURLSet.add(currIndex); //add to seen set
//                    }
//                }
//                pageDisk.put(pageIndex, pageInfoFile); //update pageDisk and print statement for update
//                System.out.println("updated page file " + pageInfoFile);
//
//
//
//
//
//
//
//                ///////////////////////////WORDS SECTION
//                //Index each word on page if not indexed yet and add it to page index indices list the first time its seen
//                List<String> listOfWords = browser.getWords();//.getWords from browser
//                System.out.println("words " + listOfWords);//print list of words
//                for (String currIndex : listOfWords){//go through each word
//                    if (seenWordsMap.containsKey(currIndex) == false){ //if not see do this
//                        long wordIndex;
//                        if (wordToIndex.containsKey(currIndex)){//check if wordToIndex has currIndex
//                            wordIndex = wordToIndex.get(currIndex);//if yes set it to the index in there
//                        }
//                        else {
//                            wordIndex = indexWord(currIndex);//if no index, index it using indexWord method
//                        }
//
//
//                        InfoFile wordInfoFile = wordDisk.get(wordIndex); //get infoFile of the wordIndex
//                        wordInfoFile.indices.add(pageIndex); //add index to indices list of word
//                        seenWordsMap.put(currIndex, true);//update seenMap
//                        wordDisk.put(wordIndex, wordInfoFile);//update wordDisk         //has to be in forloop because uses thing from in here
//                        System.out.println("updated word file " + wordInfoFile);//make a print statement to detail update
//                        if (seenWordsMap.containsKey(currIndex) == false) seenWordsMap.put(currIndex, true); //check to make sure it updated seenMap
//                    }
//                }
//            }
//        }
//
//
//    }
//
//    @Override
//    public void rank(boolean fast) {
//
//    }
//
//    @Override
//    public String[] search(List<String> searchWords, int numResults) {
//        return new String[0];
//    }
//
//
//
//
//    public long indexPage(String url){
//
//        long index = pageDisk.newFile();// .newFile returns an index
//        InfoFile infoFile = new InfoFile(url);//takes a String
//
//
//        //index comes from pageDisk.newFile
//        //store infoFile in pageDisk
//        pageDisk.put(index,infoFile);
//        urlToIndex.put(url,index); //map url to index
//
//        //Put a print statement inside your indexPage method.
//        //use + url if you don't want the [] at the end
//        System.out.println("indexing page " + index + " " + infoFile);
//
//
//
//        return index; //return index
//
//    }
//
//
//    //Basically the same as indexPage just for words instead
//    public long indexWord(String word) {
//        long index = wordDisk.newFile();// .newFile returns an index
//        InfoFile infoFile = new InfoFile(word);//takes a String
//
//
//        //index comes from pageDisk.newFile
//        //store infoFile in pageDisk
//        wordDisk.put(index,infoFile);
//        wordToIndex.put(word,index); //map url to index
//
//        //Put a print statement inside your indexPage method.
//        //use + url if you don't want the [] at the end
//        System.out.println("indexing word " + index + " " + infoFile);
//
//
//        return index; //return index
//    }
//
//
//
//}
