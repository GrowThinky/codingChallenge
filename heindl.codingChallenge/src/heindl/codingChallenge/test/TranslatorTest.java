package heindl.codingChallenge.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import heindl.codingChallenge.Translator;

class TranslatorTest {

	static LinkedHashMap<String, Integer> testInput;

	String input;
	Integer solution;
	int result;


	@Test
	void specificationTest() {
		testInput = new LinkedHashMap<String, Integer>();
		testInput.put("glob is I", null);
		testInput.put("prok is V", null);
		testInput.put("pish is X", null);
		testInput.put("tegj is L", null);
		testInput.put("glob glob Silver is 34 Credits", null);
		testInput.put("glob prok Gold is 57800 Credits", null);
		testInput.put("pish pish Iron is 3910 Credits", null);
		testInput.put("how much is pish tegj glob glob ?", 42);
		testInput.put("how many Credits is glob prok Silver ?" , 68);
		testInput.put("how many Credits is glob prok Gold ?", 57800);
		testInput.put("how many Credits is glob prok Iron ?", 782);
		testInput.put("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?", null );
		
		Translator translator = new Translator();
		
		for (Map.Entry<String, Integer> entry : testInput.entrySet()) {
			input = entry.getKey();
			solution = entry.getValue();
			translator.passInput(input);
			if (solution != null) {
				assertEquals(solution, translator.currentResult);
			}
		}
		for (Map.Entry<String, Integer> entry : translator.newDictionary.entrySet()) {
			input = entry.getKey();
		}

	}

	@Test
	void romanDictionaryTest() {
		testInput = new LinkedHashMap<String, Integer>();
		testInput.put("I", 1);
		testInput.put("I I", 2);
		testInput.put("I I I", 3);
		testInput.put("I V", 4);
		testInput.put("V", 5);
		testInput.put("V I", 6);
		testInput.put("V I I", 7);
		testInput.put("V I I I", 8);
		testInput.put("I X", 9);
		testInput.put("X", 10);
		testInput.put("X I I", 12);
		testInput.put("X V", 15);
		testInput.put("X V I", 16);
		testInput.put("X X V", 25);
		
		Translator translator = new Translator();
		translator.newDictionary = translator.romanDictionary;

		for (Map.Entry<String, Integer> entry : testInput.entrySet()) {
			input = entry.getKey();
			solution = entry.getValue();
			result = translator.translate(input);
			assertEquals(solution, result);
		}
	}

	@Test
	void learnTest() {
		Translator translator = new Translator();
		translator.passInput("blob is V");
		assertTrue(translator.newDictionary.containsKey("blob"));
		assertEquals(5, translator.newDictionary.get("blob"));
	}

	@Test
	void translateTest() {

		Translator translator = new Translator();
		translator.passInput("blob is V");
		translator.passInput("vlob is X");
		translator.passInput("tlob is I");

		int result = translator.translate("blob tlob");
		assertEquals(6, result);

	}

	@Test
	void calculateOrePriceTest() {
		Translator translator = new Translator();
		translator.passInput("blob is V");
		translator.passInput("vlob is X");
		translator.passInput("tlob is I");
		assertFalse(translator.orePrices.containsKey("Unobtainium"));

		translator.passInput("vlob tlob Unobtainium is 110000 Credits");
		assertTrue(translator.orePrices.containsKey("Unobtainium"));
		assertEquals(10000, translator.orePrices.get("Unobtainium"));
	}

}
