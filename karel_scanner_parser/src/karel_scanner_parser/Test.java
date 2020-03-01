package karel_scanner_parser;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in); 
		AdhocScanner scanner = new AdhocScanner(); 
		boolean repeat = true; 
		do { 
			System.out.println("What input would you like to scan and parse? "); 
			String input = console.nextLine(); 
			
			// interact with scanner 
			input = scanner.condenseWhitespace(input); 
			scanner.scan(input);
			System.out.println("Tokens: " + scanner.getTokens()); 
			
			System.out.print("Would you like to scan and parse another input? (y/n) "); 
			input = console.next().toLowerCase(); 
			if (input.equals("n")) {
				repeat = false; 
				System.out.println("Thank you for using the scanner. Goodbye!"); 
			}
			console.nextLine(); // necessary for clearing the text buffer 
		} while (repeat); 
		console.close(); 
	}

}
