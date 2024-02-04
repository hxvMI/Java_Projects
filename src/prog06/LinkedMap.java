package prog06;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractSet;
import java.util.Set;
import java.util.Iterator;

public class LinkedMap <K extends Comparable<K>, V>
    extends AbstractMap<K, V> {

    protected class Entry implements Map.Entry<K, V> {
	K key;
	V value;
	Entry previous, next;
    
	Entry (K key, V value) {
	    this.key = key;
	    this.value = value;
	}
    
	public K getKey () { return key; }
	public V getValue () { return value; }
	public V setValue (V newValue) {
	    V oldValue = value;
	    value = newValue;
	    return oldValue;
	}

	public String toString () {
	    return "{" + key + "=" + value + "}";
	}
    }
  
    protected Entry first, last;
  
    /**
     * Find the Entry with key or the previous key.
     * @param key The Key to be found.
     * @return The Entry e with e.key.equals(key)
     * or the last Entry with e.key < key
     * or null
     */
    protected Entry find (K key) {
	// 
	// Look at size() method.
	///
		for (Entry entry = first; entry != null; entry = entry.next){

			// Compare entry.key to key.
		int compareKeys = key.compareTo(entry.key);
		//outputs negative if key is less than entry.key value
		//0 if same values
		//outputs positive if key is more than entry.key value


	    // return entry if entry.key is equal to key
		if (compareKeys == 0)return entry;

		// return entry.previous if entry.key is greater than key
		else if (compareKeys < 0) return entry.previous;
	}
	///


	return last; // Did not find the entry, so last must be previous.
    }    
  
    /**
     * Determine if the Entry returned by find is the one we are looking
     * for.
     * @param entry The Entry returned by find.
     * @param key The Key to be found.
     * @return true if find found the entry with that key
     * or false otherwise
     */
    protected boolean found (Entry entry, K key) {
	// 
	// Fix this.


		if (entry != null) {//if entry is valid
			if (entry.key.equals(key)){//and if entryk = key return true
				return true;
			}
			else return false;//otherwise return false
		}
		else return false;


	///
//	return false; // wrong
	///
    }

    public boolean containsKey (Object keyAsObject) {
	K key = (K) keyAsObject;
	Entry entry = find(key);
	return found(entry, key); //returns true if found, false if not
    }

	//if found returns value of keyOb
	//else it returns null
    public V get (Object keyAsObject) {
	// 
	// Look at containsKey.
	// If Entry with key was found, return its value.
	///

		if (containsKey(keyAsObject) == true){ //if found return its value
			return find((K) keyAsObject).value; //we have the key so find() it and get is .value to return
		}

	///
	return null;
    }
  
    /**
     * Add newEntry just after previousEntry or as first Entry if
     * previousEntry is null.
     * @param previousEntry Entry before newEntry or null if there isn't one.
     * @param newEntry The new Entry to be inserted after previousEntry.
     */
    protected void add (Entry previousEntry, Entry newEntry) {
	// 
	Entry nextEntry = null;
	///
	// Set nextEntry.  Two cases.
		if (previousEntry != null) //if prevEnt not null set next to prevEnt
			nextEntry = previousEntry.next;
		else if (first != null) //if first entry not null nextEnt can be first
			nextEntry = first;
	// Set previousEntry.next or first to newEntry.
		if (previousEntry != null) //if prevEnt not null set prevEnt to newEnt
		 	previousEntry.next = newEntry;
		else //else first can be new
			first = newEntry;
	// Set nextEntry.previous or last to newEntry.
		if(nextEntry != null) // if there is still a next entry set it to newEntry
			nextEntry.previous = newEntry;
		else
			last = newEntry; //else that means it is the last entry so set last to newEntry

	// Set newEntry.previous and newEntry.next.
		newEntry.previous = previousEntry;
		newEntry.next = nextEntry;

	///
    }

    public V put (K key, V value) {
	Entry entry = find(key);
	// 
	///
	// Handle the case that the key is already there.
	// Save yourself typing:  setValue returns the old value!


		if (found(entry,key) == true){ //if already there
			V temp = entry.value; //store old value for return
			entry.value = value; //set new value
			return temp; //return old value
		}
	// key was not found:
		if (found(entry,key) == false){ //if not found
			Entry newEntry = new Entry(key,value); //make new entry
			add(entry,newEntry); //add new entries
		}
	///
	return null;
    }
  
    /**
     * Remove Entry entry from list.
     * @param entry The entry to remove.
     */
    protected void remove (Entry entry) {
	// 
	///

		if (size() == 0)return; // if empty return nothing to remove
		if (size() == 1){ // if size 1 set first and last to null
			first = null; // entry is removed by doing so
			last = null;
			return;
		}

		if (entry.previous == null){// means entry is first entry
			if (entry.next != null){
				first = entry.next; // so set first to entry.next
				first.previous = null; // there is no previous so set to null
			}
		}

		if (entry.previous != null){// means entry is the current last in list
			if (entry.next == null){// if previous is not null but next is null
				last = entry.previous;// set last to prev entry
				last.next = null; //set last.next to null
				return;
			}
		}

		if (entry.previous != null){// if both are null
			if (entry.next != null){ //this is the middle of the list
				entry.previous.next = entry.next; //set previous.next to next
				entry.next.previous = entry.previous;//set next.previous to previous
			}
		}



	///
    }

    public V remove (Object keyAsObject) {
	// 
	// Use find, but make sure you got the right Entry!
	// Look at containsKey.
	// If you do, then remove it and return its value.
	///

		Entry keyEnt = find((K) keyAsObject);

		if (containsKey(keyAsObject) == true){
			remove(keyEnt); //remove the entry of the key
			return keyEnt.value; //return the value of the keyEnt
		}




	///
	return null;
    }

    protected class Iter implements Iterator<Map.Entry<K, V>> {
	// 
	// Set entry to the first entry.
	///
	Entry entry = first;
	///
    
	public boolean hasNext () { 
	    // 
	    ///
		if (entry != null)return true;
		else return false;
		///
	}
    
	public Map.Entry<K, V> next () {
	    Entry ret = entry;
	    // 
	    // Set entry to the next value of entry.
	    ///
		entry = entry.next;
		///
	    return ret;
	}
    
	public void remove () {
	    throw new UnsupportedOperationException();
	}
    }//End of Iter class
  
    public int size () {
	int count = 0;
	for (Entry entry = first; entry != null; entry = entry.next)
	    count++;
	return count;
    }

    protected class Setter extends AbstractSet<Map.Entry<K, V>> {
	public Iterator<Map.Entry<K, V>> iterator () {
	    return new Iter();
	}
    
	public int size () { return LinkedMap.this.size(); }
    }
  
    public Set<Map.Entry<K, V>> entrySet () { return new Setter(); }
  
    static void test (Map<String, Integer> map) {
	if (false) {
	    map.put("Victor", 50);
	    map.put("Irina", 45);
	    map.put("Lisa", 47);
    
	    for (Map.Entry<String, Integer> pair : map.entrySet())
		System.out.println(pair.getKey() + " " + pair.getValue());
    
	    System.out.println(map.put("Irina", 55));

	    for (Map.Entry<String, Integer> pair : map.entrySet())
		System.out.println(pair.getKey() + " " + pair.getValue());

	    System.out.println(map.remove("Irina"));
	    System.out.println(map.remove("Irina"));
	    System.out.println(map.get("Irina"));
    
	    for (Map.Entry<String, Integer> pair : map.entrySet())
		System.out.println(pair.getKey() + " " + pair.getValue());
	}
	else {
	    String[] keys = { "Vic", "Ira", "Sue", "Zoe", "Bob", "Ann", "Moe" };
	    for (int i = 0; i < keys.length; i++) {
		System.out.print("put(" + keys[i] + ", " + i + ") = ");
		System.out.println(map.put(keys[i], i));
		System.out.println(map);
		System.out.print("put(" + keys[i] + ", " + -i + ") = ");
		System.out.println(map.put(keys[i], -i));
		System.out.println(map);
		System.out.print("get(" + keys[i] + ") = ");
		System.out.println(map.get(keys[i]));
		System.out.print("remove(" + keys[i] + ") = ");
		System.out.println(map.remove(keys[i]));
		System.out.println(map);
		System.out.print("get(" + keys[i] + ") = ");
		System.out.println(map.get(keys[i]));
		System.out.print("remove(" + keys[i] + ") = ");
		System.out.println(map.remove(keys[i]));
		System.out.println(map);
		System.out.print("put(" + keys[i] + ", " + i + ") = ");
		System.out.println(map.put(keys[i], i));
		System.out.println(map);
	    }
	    for (int i = keys.length; --i >= 0;) {
		System.out.print("remove(" + keys[i] + ") = ");
		System.out.println(map.remove(keys[i]));
		System.out.println(map);
		System.out.print("put(" + keys[i] + ", " + i + ") = ");
		System.out.println(map.put(keys[i], i));
		System.out.println(map);
	    }
	}
    }

    public static void main (String[] args) {
	Map<String, Integer> map = new LinkedMap<String, Integer>();
	test(map);
    }
}
