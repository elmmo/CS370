package misc_data_structures;

import java.util.Arrays;
import java.util.LinkedList;

public class DictAsHashTable<K,V> {
	private LinkedList<Entry<K,V>>[] entries; 
	private static final int SIZE = 100; 
	LinkedList<Entry<K,V>> array; 
	// nested class for each entry in the hash table 
	private static class Entry<K,V> {
		private K key; 
		private V value; 
		
		Entry(K key, V value) { 
			this.key = key; 
			this.value = value; 
		}
	}
	
	DictAsHashTable() { 
		this.entries = new LinkedList[SIZE]; 
		Arrays.fill(this.entries, new LinkedList<Entry<K,V>>());
	}
	
	/** 
	 * adds the key-value pair specified through the parameters to the hashtable 
	 * @param key	the key to identify the inserted value with 
	 * @param value	the value to insert into the hashtable 
	 * @return the previous value of the key stored at the specified location, or null if there wasn't one
	 */
	public V put(K key, V value) {
		V result = null; 
		LinkedList<Entry<K,V>> list = entries[key.hashCode()]; 
		Entry<K,V> e = new Entry<K,V>(key, value); 
		if (list.isEmpty()) { 
			list.add(e); 
		} else { 
			for (int i = 0; i < list.size(); i++) { 
				if (list.get(i).key.equals(key)) {
					result = list.get(i).value; 
					list.set(i, e); 
					return result; 
				} else { 
					list.add(e); 
				}
			}
		}
		return result; 
	}
	
	public V get(K key) {
		LinkedList<Entry<K,V>> list = entries[key.hashCode()]; 
		for (int i = 0; i < list.size(); i++) { 
			Entry<K,V> e = list.get(i); 
			if (e.key.equals(key)) return e.value; 
		}
		return null; 
	}
	
	public V remove(K key) { 
		LinkedList<Entry<K, V>> list = entries[key.hashCode()]; 
		for (int i = 0; i < list.size(); i++) { 
			Entry<K,V> e = list.get(i); 
			if (e.key.equals(key)) {
				list.remove(i); 
				return e.value; 
			}
		}
		return null; 
	}
}

