package misc_data_structures;

import java.util.EmptyStackException;
import java.util.Hashtable;

public class Test {

	public static void main(String[] args) {
		System.out.println("TESTING: STACK AS LINKED LIST"); 
		StackAsList<Integer> stack = new StackAsList<Integer>(); 
		int[] pushArr = {5,7,10,22, 193, 44}; 
		for (int i = 0; i < pushArr.length; i++) { 
			stack.push(pushArr[i]);
			System.out.printf("%d was added\n", pushArr[i]);
		}
		System.out.println(); 
		for (int i = 0; i < pushArr.length; i++) { 
			try { 
				int popped = stack.pop(); 
				System.out.printf("%d was popped\n", popped); // should pop in order 44, 193, 22, 10, 7, 5
			} catch (EmptyStackException e) { 
				e.printStackTrace();
			}
		}
		System.out.println(); 
		
		System.out.println("TESTING: PRIORITY QUEUE AS HEAP");
		QueueAsHeap<Integer> queue = new QueueAsHeap<Integer>(); 
		int[] queueArr = {35,33,42,10,14,19,27,44,26,31}; 
		for (int i = 0; i < queueArr.length; i++) { 
			queue.push(queueArr[i]);
		}
		System.out.print("Queue:"); 
		queue.print(); 
		for (int i = 0; i < 5; i++) { 
			System.out.println(queue.pop() + " popped"); 
			System.out.print("Produces ");
			if (!queue.isEmpty()) queue.print(); 
		}
		System.out.println();
		
		Hashtable<Integer, String> t = new Hashtable<Integer, String>(); 
		t.put(5, "Hello"); 
		
		System.out.println("TESTING: DICTIONARY AS HASH TABLE");
		DictAsHashTable<Integer, String> dict = new DictAsHashTable<Integer, String>(); 
		dict.put(5, "Hello");
		dict.put(5, "World");
		dict.put(90, "Hello World");
		System.out.println(dict.get(5) + " (second value) printed"); 
		System.out.println("If there are multiple values, the hash table will store the last added value, as is the case with hashtables built into Java.");
		System.out.println("Value mapped to key 90 is " + dict.get(90));
		dict.remove(90); 
		System.out.println("Value mapped to key 90 post-removal is " + dict.get(90)); 
	}
}
