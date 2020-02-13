package scanner_parser;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AdhocScanner {
	

	public static void main(String[] args) {
		boolean again;
		do {
			// collects and evaluates user input
			File file = new File("hangman.txt");
			console = new Scanner(file);
			String res = getInput("Please enter text for the scanner to evaluate. ");
			scan(res);
			
			// checks if the user wants to continue entering input
			again = getInput("Would you like to enter another input? (y/n) ").equals("y") ? true : false;
			if (!again) System.out.println("Thanks for using this scanner. Goodbye!");
		} while (again);
	}
	
	
	
	/* will prompt the user for string to scan
	 * @param prompt	the string to prompt the user for input
	 */
	public static String getInput(String prompt) {
		System.out.print(prompt);
		String res = console.next();
		// clears the buffer for the next input
		console.nextLine();
		return res;
	}
	
	public static void scan(String str) {
		str = str.replace("\n", "");
		str = str.replaceAll("\\s", "");
		System.out.println(str);
//		String[] arr = str.split("");
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i]);
//		}
	}
	
	/*
	 * Will close the Scanner upon the end of the object lifecycle
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() {
		console.close();
	}

}
