package scanner_parser;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		AdhocScanner a = new AdhocScanner();
		ArrayList<String> scannedResult = a.scan(); 
		AdhocParser b = new AdhocParser(); 
		// can also use input to test if scannedResult doesn't work 
		ArrayList<String> input = new ArrayList<String>(); 
		input.add("id"); 
		input.add("assign"); 
		input.add("number"); 
		input.add("$$"); 
		try {
			b.program(scannedResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
