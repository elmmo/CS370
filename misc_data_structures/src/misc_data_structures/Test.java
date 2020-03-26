package misc_data_structures;

import java.util.EmptyStackException;

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
		
		System.out.println("TESTING: DICTIONARY AS HASHTABLE");
		QueueAsHeap<Integer> queue = new QueueAsHeap<Integer>(); 
		int[] queueArr = {35,33,42,10,14,19,27,44,26,31}; 
		for (int i = 0; i < queueArr.length; i++) { 
			queue.push(queueArr[i]);
		}

	}

}
