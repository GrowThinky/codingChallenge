package heindl.codingChallenge;

import java.util.HashMap;

/*
 * Processes and stores fictional aliases for Roman numerals and handles translation and 
 * calculation of fictional ore prices. 
 */
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

	
	public int currentResult;

	/*
	 * Takes user input and delegates it to the correct method to process.
	 */
	public void passInput(String input) {
		try {
			
			evalInput(input);
			
		} catch (Exception e) {
			System.out.println("I have no idea what you are talking about ");
		}
	}

	/*
	 * Takes user input and delegates it to the correct method to process.
	 * I consider the string handling to be a "quick and dirty" solution, which should be cleaned up. 
	 */
	public void evalInput(String input) throws IllegalArgumentException {
		String[] tokens = input.split(" ");
		String cleanedInput;

		try {
		// input contains translation information
		if (isRoman(tokens[tokens.length - 1])) {
			learn(input);
			return;
			
		// input contains orePrice information
		} else if (input.endsWith("Credits")) {
			calculateOrePrice(input);
			return;
			
			// input asks for unit conversion 
		} else if (input.startsWith("how much is ")) {
			cleanedInput = input.split(" is ")[1];
			cleanedInput = cleanedInput.replace(" ?", "");
			currentResult = translate(cleanedInput);

			System.out.println(cleanedInput + " is " + currentResult);
			return;
			
			// input asks for price of ore amount 
		} else if (input.startsWith("how many")) {
			cleanedInput = input.split(" is ")[1];
			cleanedInput = cleanedInput.replace(" Iron ", "");
			cleanedInput = cleanedInput.replace(" Silver ", "");
			cleanedInput = cleanedInput.replace(" Gold ", "");
			cleanedInput = cleanedInput.replace("?", "");
			String oreName = tokens[tokens.length - 2];

			currentResult = (int) (translate(cleanedInput) * orePrices.get(oreName));
			System.out.println(cleanedInput + " " + oreName + " is " + currentResult + " Credits");
			return;
		}
		
	} catch (Exception e) {
		throw new IllegalArgumentException();
	}
	throw new IllegalArgumentException();

}

	/*
	 * Translates a given sequence of symbols to integer.
	 */
	public int translate(String input) {
		tokens = input.split(" ");
		return eval(0, tokens);
	}

	/*
	 * Checks if symbol is a key in the roman dictionary. 
	 */
	private boolean isRoman(String symbol) {
		return romanDictionary.containsKey(symbol);
	}

	/*
	 * Fills the dictionary with new key value pairs.
	 */
	private void learn(String input) throws IllegalArgumentException{
		tokens = input.split(" is ");
		if (tokens.length != 2) {
			throw new IllegalArgumentException();
		}
		newDictionary.put(tokens[0], romanDictionary.get(tokens[1]));
	}

	/*
	 * Infers value of an unknown ore and adds it as a new key-value pair to the ore
	 * prices dictionary.
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
	 * into an integer value.
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
