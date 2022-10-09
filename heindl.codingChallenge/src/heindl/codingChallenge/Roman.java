package heindl.codingChallenge;

import java.util.HashMap;

public class Roman {

	String input;
	String[] tokens;
	public HashMap<String, Integer> newDictionary;
	public HashMap<String, Integer> romanDictionary;

	public Roman() {
		newDictionary = new HashMap<String, Integer>();
		romanDictionary = new HashMap<String, Integer>();
		romanDictionary.put("C", 100);
		romanDictionary.put("X", 10);
		romanDictionary.put("V", 5);
		romanDictionary.put("I", 1);
	}

	/*
	 * Takes user input and delegates it to the correct method to process.
	 */
	public void evalInput(String input) {
		String[] tokens = input.split(" ");

		if (isRoman(tokens[tokens.length - 1]) && !input.contains("Credits")) {
			learn(input);
		}
		if (input.endsWith("Credits")) {
			inferValue(input);
		}
		if (input.startsWith("how many ")) {
			translate(input);
		}
	}

	private boolean isRoman(String symbol) {
		return romanDictionary.containsKey(symbol);
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
	 * Infers value of unknown symbol and adds it as new key-value pair to
	 * dictionary
	 */
	private void inferValue(String input) {
		String[] parts = input.split(" is ");
		int result = Integer.valueOf(parts[1].split(" ")[0]);
		tokens = parts[0].split(" ");
		String[] knownSymbols = new String[tokens.length - 1];
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
