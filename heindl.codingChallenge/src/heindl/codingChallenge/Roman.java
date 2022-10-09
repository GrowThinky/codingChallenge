package heindl.codingChallenge;

import java.util.HashMap;

public class Roman {

	String input;
	String[] tokens;
	HashMap<String, Integer> dictionary;

	public Roman() {
		dictionary = new HashMap<String, Integer>();
		dictionary.put("X", 10);
		dictionary.put("V", 5);
		dictionary.put("I", 1);
	}

	public int translate(String input) {
		tokens = input.split(" ");
		return eval(0, tokens);
	}


	
	public int eval(int i, String[] tokens) {
		if (i == tokens.length - 1) {
			return dictionary.get(tokens[i]);
		}
		int first = dictionary.get(tokens[i]);
		int second = dictionary.get(tokens[i + 1]);

		if (first >= second) {
			return first + eval(i + 1, tokens);
		} else {
			return (-1) * first + eval(i + 1, tokens);
		}

	}

}
