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
	private String[] expNames = {"whitespace", "standardops", "assign_2", "assign_1", "number_d", "point"};
	private String[] exp = {"[\\s]", "\\(|\\)|\\+|\\-|\\*", ":", "=", "\\d", "\\."};
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
			System.out.println(e.getStackTrace());
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
							int tokenNum = -1; 
							// verifies multi-digit numbers or multi-digit characters 
							tokenNum = (name[1].equals("d")) ? getAllDigits(characters, i) : verifyDependentTokens(characters, i, j, 1, 1, true, "", 0); 
							if (tokenNum > 0) {
								// if stopped on an operator, evaluate the operator 
								Pattern ops = regex.get(expNames[1]); // standardops regex
								if (i+tokenNum < characters.length && ops.matcher(characters[i+tokenNum]).matches()) tokenNum -= 1; 
								i += tokenNum; 
								System.out.println("Adding token"); 
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
	 * Retrieves all the digits in a number using the verifyIndependentTokens method 
	 * @param characters	the array of characters to analyze 
	 * @param charIndex		the index at which to start checking characters
	 * @return				an int saying how many indices to skip 
	 * @throws Exception	throws exception in the case of a lexical error 
	 */
	private int getAllDigits(String[] characters, int charIndex) throws Exception { 
		return verifyDependentTokens(characters, charIndex, 4, 1, 0, false, "point", 1); 
	}
	
	/** 
	 * verifies that the necessary characters follow a starting character for a given token 
	 * @param characters	characters inputed by the user 
	 * @param charIndex		the index to start searching from in the characters
	 * @param tokenIndex	the index to start searching from in the tokens 
	 * @param charIncrement		how much to increment the character increment by 
	 * @param tokenIncrement 	how much to increment the token index by 
	 * @param knownLimit	if the number of following characters to expect is known 
	 * @param check			the name of the pattern that is allowed in the sequence 
	 * @param allowPatternCheck	the number of times the pattern check is allowed 
	 * @return				an int indicating how many tokens match the pattern 
	 * @throws Exception	throws an error in the case that there are no more characters to match 
	 */
	private int verifyDependentTokens(String[] characters, int charIndex, int tokenIndex, int charIncrement, int tokenIncrement, boolean knownLimit, String check, int allowPatternCheck) throws Exception { 
		// if there's a specific number of following characters to check for 
		if (knownLimit && charIndex + 1 < characters.length) { 
			String[] name = expNames[tokenIndex+1].split("_");
			Pattern p = regex.get(expNames[tokenIndex+1]);
			if (p.matcher(characters[charIndex+1]).matches()) {
				return (Integer.parseInt(name[1]) == 1) ? 1 : verifyDependentTokens(characters, charIndex+charIncrement, tokenIndex+tokenIncrement, 1, 1, true, "", 0) + 1;
			}
		} else if (!knownLimit) {
			Pattern p = regex.get(expNames[tokenIndex]);
			// if input stops matching, check for decimal point 
			if (!p.matcher(characters[charIndex]).matches()) {
				System.out.println("Character stopped matching at " + characters[charIndex]); 
				Pattern c = regex.get(check); 
				if (c.matcher(characters[charIndex]).matches() && allowPatternCheck > 0) {
					allowPatternCheck--; 
					return verifyDependentTokens(characters, charIndex+charIncrement, tokenIndex+tokenIncrement, 1, 0, false, check, allowPatternCheck); 
				} else { 
					return 0; 
				}
			}
			// if numbers keep matching or end isn't next, continue iterating 
			return (p.matcher(characters[charIndex]).matches() && charIndex != characters.length-1) ? verifyDependentTokens(characters, charIndex+charIncrement, tokenIndex+tokenIncrement, 1, 0, false, "point", allowPatternCheck)+1 : 1;
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
			System.out.println(line); 
			
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
