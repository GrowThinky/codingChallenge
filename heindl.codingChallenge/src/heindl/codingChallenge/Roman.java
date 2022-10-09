package heindl.codingChallenge;

import java.util.HashMap;

public class Roman {

	String input;
	String[] tokens;
	HashMap<String, Integer> newDictionary;
	HashMap<String, Integer> romanDictionary;

	public Roman() {
		romanDictionary = new HashMap<String, Integer>();
		romanDictionary.put("X", 10);
		romanDictionary.put("V", 5);
		romanDictionary.put("I", 1);
	}

	
	/*
	 * Translates a given String input to integer. 
	 */
	public int translate(String input) {
		tokens = input.split(" ");
		return eval(0, tokens);
	}

	/*
	 * Fills the dictionary with new key value pairs. 
	 */
	private void learn(String input) {
		tokens = input.split(" is ");
		if (tokens.length != 2) {
			throw new IllegalArgumentException();
		}
		newDictionary.put(tokens[0], romanDictionary.get(tokens[1]));
	}

	/*
	 * Infers value of unknown symbol and adds it as new key-value pair to dictionary 
	 */
	private void inferValue(String input) {
		String[] parts = input.split(" is ");
		int result = Integer.valueOf(parts[1].split(" ")[0]);
		tokens = parts[0].split(" ");
		String[] knownSymbols = new String[tokens.length-1];
		String newSymbol = null;
		int j = 0;
		for (int i = 0; i < tokens.length; i++) {
			if (newDictionary.containsKey(knownSymbols[i])) {
				knownSymbols[j] = tokens[i];
				j++;
			} else {
				newSymbol = tokens[i];
			}
		}
		int newTranslation = Math.abs(result - eval(0, knownSymbols));
		newDictionary.put(newSymbol, newTranslation);
	}

	/*
	 * Evaluates an array of known symbols to an integer value.
	 */
	private int eval(int i, String[] tokens) {
		if (i == tokens.length - 1) {
			return newDictionary.get(tokens[i]);
		}
		
		int first = newDictionary.get(tokens[i]);
		int second = newDictionary.get(tokens[i + 1]);

		if (first >= second) {
			return first + eval(i + 1, tokens);
		} else {
			return (-1) * first + eval(i + 1, tokens);
		}

	}
	

}
