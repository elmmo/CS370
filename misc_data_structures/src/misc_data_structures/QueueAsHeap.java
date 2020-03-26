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
		
		private void add(E data, boolean verbose) { 
			array.add(data); 
			if (verbose) { 
				System.out.println("Before heaping: " + array);
			}
			heapify(); 
			if (verbose) { 
				System.out.println("After heaping" + array); 
			}
		}
		
		// maintains max-heapness for the arraylist 
		private void heapify() { 
			int size = array.size()-1; 
			for (int i = size/2; i >= 0; i--) { 
				int parentIndex = i; 
				E parentValue = array.get(parentIndex); 
				boolean heap = false; 
				while (!heap && 2*parentIndex <= size) { 
					int childIndex = 2*parentIndex; 
					// if right child bigger than left child, switch comparison child 
					if (childIndex < size) { // there are two children
						if (array.get(childIndex).compareTo(array.get(childIndex+1)) < 0) childIndex++; 
					}
					// if parent is bigger than child 
					if (parentValue.compareTo(array.get(childIndex)) >= 0) { 
						heap = true; 
					} else { 
						// move child value to parent position 
						array.set(parentIndex, array.get(childIndex)); 
						parentIndex = childIndex; 
					}
					// delayed swap: at end, set last child index to parent value 
					array.set(parentIndex, parentValue);
				}
			}
		}
	}
	
	// constructor for the queue 
	QueueAsHeap() { 
		this.heap = new Heap<E>(); 
	}
	
	public void push(E data) { 
		heap.add(data, false);
	}
}
