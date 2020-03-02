package karel_scanner_parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdhocScanner {
	private Scanner console; 
	private ArrayList<String> tokens; 
	private String[] validTokens = {"move", "turn", "take", "drop", "var", ":=", "(", ")"}; 
	private HashMap<Pattern, String> connectTokens = new HashMap<Pattern, String>();
	
	AdhocScanner() { 
		this.console = new Scanner(System.in); 
		this.tokens = new ArrayList<String>(); 
		// compiles the regex for recognizing ids, counts, and directions
		this.connectTokens.put(Pattern.compile("1|2|3|4|5"), "cnt"); 
		this.connectTokens.put(Pattern.compile("l|r"), "dir");
		this.connectTokens.put(Pattern.compile("[a-z]"), "id"); 
	}
	
	/** 
	 * Scans the input string for tokens 
	 * @param input		the string to search for tokens 
	 * @param verbose	a debugging option to get the full text output as the program is scanning 
	 * @throws Exception	if lexical error 
	 */
	public void scan(String input, boolean verbose) throws Exception { 
		int initial = 0; 
		int stop = 0; 
		String lastEntry = ""; 
		// iterate over entire input string 
		for (int i = 0; i < input.length(); i++) { 
			// check against connecting symbols 
			for (Map.Entry<Pattern,String> entry : connectTokens.entrySet()) { 
				if (verbose) System.out.println("Key: " + entry.getKey() + " / Value: " + entry.getValue()); 
				Pattern key = entry.getKey(); 
				// case: the current character matches one of the regexs 
				if (key.matcher(String.valueOf(input.charAt(i))).matches()) { 
					if (verbose) System.out.println("Matched " + key + " with " + String.valueOf(input.charAt(i))); 
					if (!lastEntry.equals(entry.getValue())) { 
						if (lastEntry.equals("var") && entry.getValue().equals("id")) { 
							tokens.add(entry.getValue()); 
							tokens.add(":="); 
							lastEntry = entry.getValue(); 
							initial++; 
							stop++; 
							if (verbose) System.out.println("ADDED CONNECTOR. Current state of tokens: " + tokens); 
							break; 
						} else if (lastEntry.equals("(") && entry.getValue().equals("dir") || entry.getValue().equals("cnt")) { 
							tokens.add(entry.getValue()); 
							lastEntry = entry.getValue(); 
							initial++; 
							stop++; 
							if (verbose) System.out.println("ADDED CONNECTOR. Current state of tokens: " + tokens); 
							break; 
						}
					}
				}
			}
			// check against all the primary tokens 
			for (int j = 0; j < validTokens.length; j++) { 
				if (verbose) System.out.println("Comparing " + input.charAt(i) + " to " + validTokens[j].charAt(0));
				// check first character against the token 
				if (input.charAt(i) == validTokens[j].charAt(0)) { 
					stop += validTokens[j].length(); 
					if (verbose) System.out.println("Substring " + input.substring(initial, stop)); 
					// check substring against the token 
					if (input.substring(initial, stop).equals(validTokens[j])) { 
						tokens.add(validTokens[j]);
						lastEntry = validTokens[j]; 
						i += validTokens[j].length() - 1; // need to subtract one to account for automatic iteration
						if (verbose) System.out.println("ADDED TOKEN " + validTokens[j].toUpperCase());
						if (verbose) System.out.println("New substring:" + input.substring(i)); 
						initial += validTokens[j].length();
						break; 
					} else { 
						stop -= validTokens[j].length(); 
					}
				}
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
		return p.matcher(input).replaceAll("");
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
