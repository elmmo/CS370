package misc_data_structures;
import java.util.ArrayList; 

public class QueueAsHeap<E extends Comparable<E>> {
	Heap<E> heap; 
	// nested class that holds the heap 
	private static class Heap<E extends Comparable<E>> { 
		private ArrayList<E> array; 
		
		// constructor for the heap 
		Heap() { 
			this.array = new ArrayList<E>(); 
		}
		
		private void add(E data) { 
			array.add(data); 
			System.out.println("Before heaping: " + array);
			heapify(); 
			System.out.println("After heaping" + array); 
		}
		
		// maintains max-heapness for the arraylist 
		private void heapify() { 
			int size = array.size()-1; 
			for (int i = size/2; i > 1; i--) { 
				int k = i; 
				E v = array.get(k);
				boolean heap = false; 
				do { 
					int j = 2*k; 
					if (j < size) { // there are two children
						if (array.get(j).compareTo(array.get(j+1)) < 0) { 
							j++; 
						}
					}
					if (v.compareTo(array.get(j)) >= 0) { 
						heap = true; 
					} else { 
						array.set(k, array.get(j)); 
						k = j; 
					}
				} while (!heap && 2*k <= size); 
				array.set(k, v); 
			}
		}
	}
	
	// constructor for the queue 
	QueueAsHeap() { 
		this.heap = new Heap<E>(); 
	}
	
	public void push(E data) { 
		heap.add(data);
	}
}
