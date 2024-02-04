//package prog06;
//import java.util.Map;
//import java.util.Random;
//
//public class SkipMap<K extends Comparable<K>, V> extends LinkedMap<K, V> {
//    // SkipMap containing half the elements chosen at random.
//    SkipMap<K, Entry> skip;
//
//    // Coin flipping code.
//    Random random = new Random(1);
//    /** Flip a coin.
//     * @return true if you flip heads.
//     */
//    boolean heads () {
//        return random.nextInt() % 2 == 0;
//    }
//
//    protected void add (Entry nextEntry, Entry newEntry) {
//        super.add(nextEntry, newEntry);
//
//        // 
//        // Flip a coin.  If you flip heads, put newEntry into skip, using
//        // its own key as key.  Don't forget to allocate skip if it hasn't
//        // been allocated yet.
//        ///
//
//
//        if (heads() == true){ //if heads put new entry
//            if (skip == null) { //allocate space to skip
//                skip = new SkipMap<>();
//            }
//            skip.put(newEntry.getKey(),newEntry); //put new entry in skip
//        }
//
//
//
//
//        ///
//    }
//
//    protected Entry find (K key) {
//        Entry entry = first;
//        // 
//        // Call find for the key in skip.
//        // Set entry to the value of that Entry in skip.
//        // Check for null so you don't crash.
//        ///
//
////        if (skip == null || skip.isEmpty()) return last; //will reverse some things so don't uncomment
//        if (skip !=  null){
//            Entry skipEnt = skip.get(key);
//            if (skipEnt != null){
//                entry = skipEnt;
//            }
//        }
//
//
//
//
//        ///
//
//        // 
//        // Use the same search as in LinkedMap.find,
//        // but use the current value of entry
//        // instead of starting at first.
//        ///
//
//        for (Entry i = entry; i != null; i = i.next){
//
//            // Compare entry.key to key.
//            int compareKeys = key.compareTo(i.key);
//            //outputs negative if key is less than entry.key value
//            //0 if same values
//            //outputs positive if key is more than entry.key value
//
//
//            // return entry if entry.key is equal to key
//            if (compareKeys == 0)return i;
//
//                // return entry.previous if entry.key is greater than key
//            else if (compareKeys < 0) return i.previous;
//        }
//
//
//        ///
//
//        return last;
//    }
//
//    protected void remove (Entry entry) {
//        super.remove(entry);
//        // 
//        // Remove the key of entry from skip.  (Use public remove.)
//        // If skip becomes empty, set it to null.
//        ///
//
//        if (skip != null){
//            skip.remove(entry.getKey());
//            if (skip.size() == 0)skip = null;
//        }
//
//
//        ///
//    }
//
//    public String toString () {
//        if (skip == null)
//            return super.toString();
//        return skip.toString() + "\n" + super.toString();
//    }
//
//    public static void main (String[] args) {
//        Map<String, Integer> map = new SkipMap<String, Integer>();
//        test(map);
//    }
//}
//
