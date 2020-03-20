package misc_data_structures;

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
			int popped = stack.pop(); 
			System.out.printf("%d was popped\n", popped); // should pop in order 44, 193, 22, 10, 7, 5
		}
		System.out.println(); 

	}

}
