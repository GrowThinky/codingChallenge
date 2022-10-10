package heindl.codingChallenge;

import java.util.HashMap;

public class Translator {

	String input;
	String[] tokens;
	
	public HashMap<String, Float> orePrices;
	public HashMap<String, Integer> romanDictionary;
	public HashMap<String, Integer> newDictionary;

	public Translator() {
		newDictionary = new HashMap<String, Integer>();
		orePrices = new HashMap<String, Float>();
		romanDictionary = new HashMap<String, Integer>();
		romanDictionary.put("V", 5000);
		romanDictionary.put("M", 1000);
		romanDictionary.put("D", 500);
		romanDictionary.put("C", 100);
		romanDictionary.put("L", 50);
		romanDictionary.put("X", 10);
		romanDictionary.put("V", 5);
		romanDictionary.put("I", 1);
	}

	/*
	 * Takes user input and delegates it to the correct method to process.
	 */
	public int currentResult;
	
	
	public void evalInput(String input) {
		String[] tokens = input.split(" ");
		String cleanedInput;

		if (isRoman(tokens[tokens.length - 1]) && !input.contains("Credits")) {
			learn(input);
		}
		if (input.endsWith("Credits")) {
			calculateOrePrice(input);
		}
		if (input.startsWith("how much is ")) {
			cleanedInput = input.split(" is ")[1];
			cleanedInput = cleanedInput.replace(" ?", "");
			currentResult = translate(cleanedInput);
		}
		if (input.startsWith("how many")) {
			cleanedInput = input.split(" is ")[1];
			cleanedInput = cleanedInput.replace(" Iron ", "");
			cleanedInput = cleanedInput.replace(" Silver ", "");
			cleanedInput = cleanedInput.replace(" Gold ", "");
			cleanedInput = cleanedInput.replace("?", "");
			
			currentResult = (int)(translate(cleanedInput) * orePrices.get(tokens[tokens.length - 2]));
		}

	}
	
	/*
	 * Translates a given String input to integer.
	 */
	public int translate(String input) {
		tokens = input.split(" ");
		return eval(0, tokens);
	}


	private boolean isRoman(String symbol) {
		return romanDictionary.containsKey(symbol);
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
	 * Infers value of an unknown ore and adds it as a new key-value pair to
	 * the ore prices dictionary.
	 */
	private void calculateOrePrice(String input) {
		String[] inputParts = input.split(" is ");
		float result = Integer.valueOf(inputParts[1].split(" ")[0]);
		tokens = inputParts[0].split(" ");
		String[] knownSymbols = new String[tokens.length - 1];
		String oreName = null;
		int j = 0;
		for (int i = 0; i < tokens.length; i++) {
			if (newDictionary.containsKey(tokens[i])) {
				knownSymbols[j] = tokens[i];
				j++;
			} else {
				oreName = tokens[i];
			}
		}
		float orePrice = result / eval(0, knownSymbols);
		orePrices.put(oreName, orePrice);
	}

	/*
	 * Evaluates an array of known symbols that follows the rules of roman numerals 
	 * to an integer value.
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
