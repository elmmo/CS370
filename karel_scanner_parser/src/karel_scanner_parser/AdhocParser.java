package karel_scanner_parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;


public class AdhocParser {
	
	private void consumeInputToken(String expected, ArrayList<String> arr) { 
		arr.remove(0); 
		System.out.println(expected); 
	}
	
	private void match(String expected, ArrayList<String> arr) throws Exception { 
		if (arr.get(0).equals(expected)) { 
			consumeInputToken(expected, arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	
	public void program(ArrayList<String> arr) throws Exception { 
		try { 
			stmtList(arr);
			match("$$", arr); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	private ArrayList<String> stmtList(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("move", "turn", "decl", "assign", "take", "drop").contains(token)) { 
			stmt(arr); 
			stmtList(arr); 
		} else if (token.equals("$$")) { 
			return arr; 
		} else { 
			throw new Exception("Parse Error"); 
		}
		return arr; 
	}
	private void stmt(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		switch (token) { 
			case "move": 
				move(arr); 
				break; 
			case "turn": 
				turn(arr); 
				break; 
			case "decl": 
				decl(arr); 
				break; 
			case "assign": 
				assign(arr); 
				break; 
			case "drop":
			case "take": 
				match(token, arr); 
				break; 
			default: 
				throw new Exception("Parse Error"); 
		}
	}
	private void move(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("move") && arr.size() >= 4) { // size will be at least 4 for each remaining part of the move command 
			match("move", arr); 
			match("(", arr); 
			cnt(arr); 
			match(")", arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private void turn(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("turn") && arr.size() >= 4) { // size will be at least 4 for each remaining part of the turn command 
			match("turn", arr); 
			match("(", arr);
			dir(arr); 
			match(")", arr); 
		} else { 
			throw new Exception("Parse Error");
		}
	}
	private void cnt(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("0", "1", "2", "3", "4", "5").contains(token)) { 
			match(token, arr); 
		} else { 
			throw new Exception("Parse Error");
		}
	}
	private void dir(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("l", "r").contains(token)) { 
			match(token, arr); 
		} else { 
			throw new Exception("Parse Error");
		}
	}
	private void decl(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("var")) { 
			match(token, arr); 
			token = arr.get(1); 
			if (token.equals("id")) { 
				match(token, arr); 
			} else { 
				throw new Exception("Parse Error");
			}
		} else { 
			throw new Exception("Parse Error");
		}
	}
	private void assign(ArrayList<String> arr) throws Exception { 
		id(arr); 
		String token = arr.get(0); 
		if (token.equals(":=")) { 
			match(":=", arr);
		} else { 
			throw new Exception("Parse Error");
		}
		cnt(arr); 
	}
	private void id(ArrayList<String> arr) throws Exception { 
		Pattern p = Pattern.compile("[a-z]*"); 
		if (p.matcher(arr.get(0)).matches()) { 
			match(arr.get(0), arr); 
		} else { 
			throw new Exception("Parse Error");
		}
	}
}
