package scanner_parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdhocScanner {
	private Scanner input; 
	private static final String FILE_NAME = "CalcInput1.txt";
	private String[] expNames = {"whitespace"};
	private String[] exp = {"[\\s]"};
	private HashMap<String,Pattern> regex;
	private ArrayList<String> tokens;
	
	// constructor that sets up the scanner and regexs to be used through the class
	public AdhocScanner() {
		// initializes the scanner for reading through the document
		File file = new File(FILE_NAME);
		try {
			this.input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("We're sorry, your input cannot be scanned at this time.");
			System.out.println(e.getMessage());
		}
		
		// compiles the regex to be used 
		this.regex = new HashMap<String,Pattern>();
		compileRegex();
		
		// sets up the list to print off
		this.tokens = new ArrayList<String>();
	}
	
	// called by the constructor to compile all of the regexs that will be used for scanning
	private void compileRegex() {
		for (int i = 0; i < exp.length; i++) {
			Pattern pt = Pattern.compile(exp[i]);
			regex.put(expNames[i], pt);
		}
	}
	
	
	/* will prompt the user for string to scan
	 * @param prompt	the string to prompt the user for input
	 */
	public String getInput(String prompt) {
		System.out.print(prompt);
		String res = input.next();
		// clears the buffer for the next input
		input.nextLine();
		return res;
	}
	
	public void scan() {
		String line = "";
		while (input.hasNextLine()) {
			// takes in input from next file and removes all whitespace
			line = input.nextLine();
			line = line.replace("\n", "");
			Pattern p = regex.get("whitespace");
			System.out.println(p.matcher(line).replaceAll("")); 
		}
	}
	
	/*
	 * Will close the Scanner upon the end of the object lifecycle
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() {
		input.close();
	}

}
