package karel_scanner_parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdhocScanner {
	private Scanner console; 
	private ArrayList<String> tokens; 
	private String[] validTokens = {"move", "turn", "take", "drop", "var", "id", "dir", "cnt"}; 
	private String[] connectTokens = {"(", ")", ":", "="};
	
	AdhocScanner() { 
		this.console = new Scanner(System.in); 
		this.tokens = new ArrayList<String>(); 
	}
	
	/** 
	 * 
	 * @param input
	 * @param tokenIndex
	 * @return	the number of letters to skip over via the iterator 
	 */
	private int matchTokens(String input, int tokenIndex) { 
		int lenToken = validTokens[tokenIndex].length(); 
		int iReturn = 0; 
		if (input.length() >= lenToken) { 
			// if the input matches one of the tokens
			if (input.substring(0, lenToken).equals(validTokens[tokenIndex])) { 
				tokens.add(validTokens[tokenIndex]); 
				iReturn += lenToken; 
			}
		}
		return iReturn - 1; // have to subtract one to account for the automatic iterator addition 
	}
	
	public void scan(String input) { 
		String[] inputArr = input.split(" "); 
		for (int i = 0; i < inputArr.length; i++) { 
			int j = 0; 
			boolean unassigned = true; 
			while (unassigned) { 
				if (inputArr[i].charAt(0) == validTokens[j].charAt(0)) { 
					int result = matchTokens(input, j); 
					if (result > 0) unassigned = false; 
					i += result; 
				}
			}
			j++; 
		}
		
	}
	
	public String condenseWhitespace(String input) { 
		Pattern p = Pattern.compile("[\\s]"); 
		return p.matcher(input).replaceAll(" ");
	}
	
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
