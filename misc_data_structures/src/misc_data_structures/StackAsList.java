package misc_data_structures;

import java.util.EmptyStackException;

public class StackAsList<E> {
	private ListNode<E> head; 
	// nested class for the LinkedList node
	private static class ListNode<E> {
		private E value; 
		private ListNode<E> next; 
		
		ListNode(E value) { 
			this.value = value; 
			this.next = null; 
		}
		
	}
	
	// returns the last item pushed onto the stack 
	public E top() { 
		if (head == null) { 
			System.out.println("Stack is empty :("); 
			return null; 
		}
		return (E)head.value; 
	}
	
	// pushes a node with the parameterized data to the stack 
	public void push(E data) { 
		ListNode<E> node = new ListNode<E>(data); 
		node.next = this.head; 
		this.head = node; 
	}
	
	// pops the top off the stack and returns the value popped off
	public E pop() throws EmptyStackException {
		if (this.head != null) { 
			E popped = this.head.value; 
			this.head = this.head.next; 
			return popped; 
		} else { 
			throw new EmptyStackException(); 
		}
	}
}
