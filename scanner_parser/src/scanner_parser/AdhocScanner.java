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
	private static final int NEG_CONSTANT = -100; // token dependency length not to exceed this
	private String[] expNames = {"whitespace", "standardops", "assign_2", "assign_1"};
	private String[] exp = {"[\\s]", "\\(|\\)|\\+|\\-|\\*", ":", "="};
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
	
	/* called by the scan function to go through character by character and find tokens
	 * @param	line	the string to search for tokens
	 */
	private void scanForTokens(String line) {
		String[] characters = line.split("");
		try {
			for (int i = 0; i < characters.length; i++) {
				boolean unassigned = true; 
				int j = 1; 
				
				while (j < expNames.length && unassigned) {
					Pattern p = regex.get(expNames[j]);
					if (p.matcher(characters[i]).matches()) {
						String[] name = expNames[j].split("_"); 
						
						// in the case that the pattern involves more than just one character 
						if (name.length > 1) {
							int tokenNum = verifyDependentTokens(characters, i, j);
							i += tokenNum; 
							if (tokenNum > 0) { 
								tokens.add(name[0]);
							}
						} else { 
							tokens.add(characters[i]);
						}
						unassigned = false; 
					} else if (j+1 >= expNames.length) {
						System.out.println("Problem");
						throw new Exception("Lexical Error. Please try again.");
					}
					j++; 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * verifies that the necessary characters follow a starting character for a given token 
	 * @param characters	characters inputed by the user 
	 * @param charIndex		the index to start searching from in the characters
	 * @param tokenIndex	the index to start searching from in the tokens 
	 * @return				an int indicating how many tokens match the pattern 
	 * @throws Exception	throws an error in the case that there are no more characters to match 
	 */
	private int verifyDependentTokens(String[] characters, int charIndex, int tokenIndex) throws Exception { 
		if (charIndex + 1 < characters.length) { 
			String[] name = expNames[tokenIndex+1].split("_");
			Pattern p = regex.get(expNames[tokenIndex+1]);
			if (p.matcher(characters[charIndex+1]).matches()) {
				return (Integer.parseInt(name[1]) == 1) ? 1 : verifyDependentTokens(characters, charIndex+1, tokenIndex+1) + 1;
			}
		
		}
		return NEG_CONSTANT; 
	}
	
	// scans through the input file and returns the tokens picked up by the scanner
	public ArrayList<String> scan() {
		String line = "";
		while (input.hasNextLine()) {
			// takes in input from next file and removes all whitespace
			line = input.nextLine();
			line = line.replace("\n", "");
			Pattern p = regex.get("whitespace");
			line = p.matcher(line).replaceAll("");
			
			// scans through the line char by char
			scanForTokens(line);
		}
		System.out.println(tokens);
		return tokens;
	}
	
	/*
	 * Will close the Scanner upon the end of the object lifecycle
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() {
		input.close();
	}

}
