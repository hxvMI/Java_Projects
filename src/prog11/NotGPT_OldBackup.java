//ORIGINAL
//
//
//
//
//        package prog11;
//
//import prog05.ArrayQueue;
//
//import java.util.*;
//
//public class NotGPT implements SearchEngine{
//
//    //Are both treemaps so you can use .get .put and so on
//    Map<String, Long> urlToIndex = new TreeMap<>();
//    HardDisk pageDisk = new HardDisk();
//
//    @Override
//    public void collect(Browser browser, List<String> startingURLs) {
//        Queue pageQueue = new ArrayQueue();
//        System.out.println("starting pages " + startingURLs);
//
//        //check all starting urls
//        for (String currUrl : startingURLs){
//            if (urlToIndex.containsKey(currUrl) == false){//check if not indexed     can also use this as a condition  urlToIndex.get(currUrl) == null
//                Long tempUrl = indexPage(currUrl);//index currUrl using indexPage
//                pageQueue.offer(tempUrl);//and store it in temp to put back in Queue
//            }
//        }
//
//
//
//
//
//        //while queue not empty
//        while (pageQueue.isEmpty() == false){//dequeue a page index and since it returns a value store it for later
//            System.out.println("queue " + pageQueue);
//            Long oldPageIndex = (Long) pageQueue.poll();
//
//
//            String oldPageUrl = pageDisk.get(oldPageIndex).data;//get its url from pageDisk using its index
//            boolean loaded = browser.loadPage(oldPageUrl);
//
//            if (loaded == true){//if loaded get the list of its urls
//                List<String> listOfUrl = browser.getURLs();//.getURLs returns a list
//
//                for (String currUrl : listOfUrl){//for each url in list
//                    if (urlToIndex.containsKey(currUrl) == false){ //if it has not been indexed
//                        Long tempUrl = indexPage(currUrl);//then index them like before
//                        pageQueue.offer(tempUrl);
//                    }}
//
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
//
//}
