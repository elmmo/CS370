package scanner_parser;

import java.util.ArrayList;
import java.util.Arrays;

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
		String token = arr.get(0); 
		if (Arrays.asList("id", "read", "write", "$$").contains(token)) { 
			stmtList(arr); 
			match("$$", arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private ArrayList<String> stmtList(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("id", "read", "write").contains(token)) { 
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
		if (token.equals("id")) { 
			match("id", arr); 
			match("assign", arr); 
			expr(arr); 
		} else if (token.equals("read")) { 
			match("read", arr); 
			match("id", arr); 
		} else if (token.equals("write")) { 
			match("write", arr); 
			expr(arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private void expr(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("id", "number", "(").contains(token)) { 
			term(arr); 
			termTail(arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private ArrayList<String> termTail(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("+", "-").contains(token)) { 
			addOp(arr); 
			term(arr); 
			termTail(arr); 
		} else if (Arrays.asList(")", "id", "read", "write", "$$").contains(token)) { 
			return arr; 
		} else { 
			throw new Exception("Parse Error"); 
		}
		return arr; 
	}
	private void term(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("id", "number", "(").contains(token)) { 
			factor(arr); 
			factorTail(arr); 
		} else {
			throw new Exception("Parse Error"); 
		}
	}
	private ArrayList<String> factorTail(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (Arrays.asList("*", "/").contains(token)) { 
			multOp(arr); 
			factor(arr); 
			factorTail(arr); 
		} else if (Arrays.asList("+", "-", ")", "id", "read", "write", "$$").contains(token)) { 
			return arr; 
		} else { 
			throw new Exception("Parse Error"); 
		}
		return arr; 
	}
	private void factor(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("id")) { 
			match("id", arr); 
		} else if (token.equals("number")) { 
			match("number", arr); 
		} else if (token.equals("(")) {
			match("(", arr); 
			expr(arr); 
			match(")", arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private void addOp(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("+")) { 
			match("+", arr); 
		} else if (token.equals("-")) { 
			match("-", arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
	private void multOp(ArrayList<String> arr) throws Exception { 
		String token = arr.get(0); 
		if (token.equals("*")) { 
			match("*", arr); 
		} else if (token.equals("/")) { 
			match("/", arr); 
		} else { 
			throw new Exception("Parse Error"); 
		}
	}
}
