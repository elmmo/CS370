package karel_scanner_parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdhocScanner {
	private Scanner console; 
	private ArrayList<String> tokens; 
	private String[] validTokens = {"move", "turn", "take", "drop", "var", "dir"}; 
	private String[] connectTokens = {"(", ")", ":", "="};
	private HashMap<String, String> regexMatch = new HashMap<String, String>();
	
	AdhocScanner() { 
		this.console = new Scanner(System.in); 
		this.tokens = new ArrayList<String>(); 
		// compiles the regex for recognizing ids, counts, and directions
		this.regexMatch.put("1|2|3|4|5", "cnt"); 
		this.regexMatch.put("l|r", "dir");
		this.regexMatch.put("[a-z]", "id"); 
	}
	
	private int verifyDependentTokens(String input, boolean regex, boolean str, String p) {
		System.out.println("verifyDependentTokens called");
		String[] inputArr = input.split(""); 
		int result = 0; 
		if (regex) { 
			Pattern compare = Pattern.compile(p); 
			for (int i = 0; i < inputArr.length; i++) {
				if (compare.matcher(inputArr[i]).matches()) { 
					result++; 
				}
			}
			if (result > 0) { 
				System.out.println(regexMatch.get(p) + " has been logged"); 
				tokens.add(regexMatch.get(p)); 
			}
		}
		System.out.println("Returning the result of " + result + " places."); 
		return result; 
	}
	
	/** 
	 * 
	 * @param input			the String input to search tokens for 
	 * @param tokenIndex	the index to start searching for matching tokens. -1 if not applicable
	 * @param searchArr		the array to match corresponding tokens against 
	 * @param regexMatch 	if the entry can be categorized using a regex
	 * @return	the number of letters to skip over via the iterator 
	 */
	private int matchTokens(String input, int tokenIndex, String[] searchArr, boolean regexMatch) { 
		int lenToken = 0; 
		int iReturn = 0; 
		if (tokenIndex == -1) {
			for (int i = 0; i < searchArr.length; i++) {
				if (input.charAt(0) == searchArr[i].charAt(0)) { 
					System.out.println("ADDING " + searchArr[i] + " TO THE MIX :D"); 
					tokens.add(searchArr[i]); 
					if (searchArr[i].equals(":")) { 
						return -1 + iReturn + matchTokens(input.substring(i), 3, searchArr, true);
					} else if (searchArr[i].equals("(")) { 
						return -1 + iReturn + matchTokens(input.substring(i), -1, searchArr, true);
					}
				}
			}
			
		} else { 
			System.out.println("matchTokens called with " + input + " as the input"); 
			lenToken = searchArr[tokenIndex].length(); 
			iReturn = 0; 
			if (input.length() >= lenToken) { 
				// if the input matches one of the tokens
				System.out.println("Does " + input.substring(0, lenToken).toUpperCase() + " equal " + searchArr[tokenIndex].toUpperCase() + "?"); 
				if (input.substring(0, lenToken).equals(searchArr[tokenIndex])) { 
					tokens.add(searchArr[tokenIndex]); 
					iReturn += lenToken; 
				}
			}	
		}
		return (input.length() > lenToken) ? -1 + iReturn + matchTokens(input.substring(lenToken), -1, connectTokens, false) : iReturn - 1;
	}
	
	public void scan(String input) throws Exception { 
		System.out.println("scan called with the input of " + input.toUpperCase());
		String[] inputArr = input.split(" "); 
		for (int i = 0; i < inputArr.length; i++) { 
			int j = 0; 
			boolean unassigned = true; 
			while (unassigned) {
				if (inputArr[i].charAt(0) == validTokens[j].charAt(0)) { 
					int result = matchTokens(inputArr[i], j, validTokens, false); 
					if (result > 0) unassigned = false; 
				}
				if (j == validTokens.length-1 && !unassigned) { 
					System.out.println("j == validTokens.length && unassigned");
					if (inputArr[i].equals("var") && i+1 < inputArr.length) { 
						tokens.add("var"); 
						i += verifyDependentTokens(inputArr[i+1], true, false, "[a-z]"); 
					} else { 
						throw new Exception("Lexical Error: unrecognized token.");
					}
				}
				j++; 
			}
		}
		
	}
	
	/** 
	 * Squeezes all whitespace into the width of one space, regardless of how large the space is 
	 * @param input		the original string to condense
	 * @return			the modified string 
	 */
	public String condenseWhitespace(String input) { 
		Pattern p = Pattern.compile("[\\s]"); 
		return p.matcher(input).replaceAll(" ");
	}
	
	/** 
	 * Getter for the list of scanned tokens 
	 * @return	token list 
	 */
	public ArrayList<String> getTokens() { 
		return this.tokens; 
	}
	
	/**
	 * Will close the Scanner upon the end of the object lifecycle
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() {
		console.close();
	}
}
