package heindl.codingChallenge.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import heindl.codingChallenge.Translator;

class TranslatorTest {

	static HashMap<String, Integer> romanTestInput;
	HashMap<String, Integer> learnTestInput;

	@BeforeAll
	static void setUp() {
		romanTestInput = new HashMap<String, Integer>();
		romanTestInput.put("I", 1);
		romanTestInput.put("I I", 2);
		romanTestInput.put("I I I", 3);
		romanTestInput.put("I V", 4);
		romanTestInput.put("V", 5);
		romanTestInput.put("V I", 6);
		romanTestInput.put("V I I", 7);
		romanTestInput.put("V I I I", 8);
		romanTestInput.put("I X", 9);
		romanTestInput.put("X", 10);
		romanTestInput.put("X I I", 12);
		romanTestInput.put("X V", 15);
		romanTestInput.put("X V I", 16);
		romanTestInput.put("X X V", 25);

	}

	@Test
	void romanDictionaryTest() {
		Translator translator = new Translator();
		translator.newDictionary = translator.romanDictionary;

		String input;
		Integer solution;
		int result;

		for (Map.Entry<String, Integer> entry : romanTestInput.entrySet()) {
			input = entry.getKey();
			solution = entry.getValue();
			result = translator.translate(input);
			assertEquals(solution, result);
		}
	}

	@Test
	void learnTest() {
		Translator translator = new Translator();
		translator.evalInput("blob is V");
		assertTrue(translator.newDictionary.containsKey("blob"));
		assertEquals(5, translator.newDictionary.get("blob"));
	}

	@Test
	void translateTest() {

		Translator translator = new Translator();
		translator.evalInput("blob is V");
		translator.evalInput("vlob is X");
		translator.evalInput("tlob is I");

		translator.evalInput("how many Credits is blob tlob ?");
		assertEquals(6, translator.currentResult);

	}

	@Test
	void inferValueTest() {
		
		Translator translator = new Translator();
		translator.newDictionary = translator.romanDictionary;
		
		translator = new Translator();
		translator.newDictionary = translator.romanDictionary;
		translator.newDictionary.remove("X");
		assertFalse(translator.newDictionary.containsKey("X"));

		translator.evalInput("X C is 90 Credits");
		assertTrue(translator.newDictionary.containsKey("X"));
		assertEquals(10, translator.newDictionary.get("X"));
	}

}
