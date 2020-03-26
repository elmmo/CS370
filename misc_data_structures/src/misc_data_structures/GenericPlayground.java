//package misc_data_structures;
//
//import java.util.ArrayList;
//import java.util.List;
//
///* This file has no assignment answers (and not all code may work). It's a place for me to experiment with Java generics and wildcards. */ 
//
//public class GenericPlayground<E> {
//	
//	public static void main(String[] args) { 
//		Integer[] arr = {143,2,5,644,68,19,49};
//		System.out.println("Standard array before being converted to ArrayList:" + arr); 
//		System.out.println("Arraylist from getArrayListFromArray: " + getArrayListFromArray(arr)); 
//		
//		System.out.println("\nPrinting various types of numbers:"); 
//		printNumber(3); 
//		printNumber(3.11); 
//		printNumber(7/3);
//		// printNumber("helloWorld");	will not compile 
//		
//		ArrayList<Integer> hello = new ArrayList<Integer>(); 
//		hello.add(9); 
//		hello.add(10); 
//		hello.add(12); 
//		hello.add(14); 
//		printArrayList(hello);
//		ArrayList<String> hello2 = new ArrayList<String>(); 
//		hello2.add("Hello"); 
//		hello2.add("World"); 
//		
//		
//	}
//	
//	// will take every element from a generic array and populate an arraylist
//	public static <E> ArrayList<E> getArrayListFromArray(E[] arr) { 
//		ArrayList<E> newArr = new ArrayList<E>(); 
//		for (int i = 0; i < arr.length; i++) { 
//			newArr.add(arr[i]); 
//		}
//		return newArr; 
//	}
//	
//	// will take any parameter that inherits from Number and print it 
//	public static <T extends Number> void printNumber(T num) { 
//		System.out.println(num); 
//	}
//	
//	public static <T super Object> void printArrayListNum(ArrayList<T> arr) { 
//		System.out.println(arr); 
//	}
//	
//	public static void printArrayList(ArrayList<? extends Number> arr) { 
//		System.out.println(arr); 
//		
//	}
//	
//	public static <T, V super T, E extends T> void copy(List<V> dest,List<E> src) { 
//		
//	}
//	
//	
//	
//}
