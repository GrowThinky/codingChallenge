package heindl.codingChallenge;

import java.util.HashMap;

public class Roman {
	
	String input; 
	String[] tokens;
	HashMap<String,Integer> dictionary;
	
	public  Roman() {
		dictionary = new HashMap<String,Integer>();
		dictionary.put("X", 10);
		dictionary.put("V", 5);
		dictionary.put("I", 1);
	}
	
	public int translate(String input)  {
		tokens = input.split(" ");
		return eval(0);
	}
	
	
	public int eval(int i) {
		
		return 0;
		
	}

}
