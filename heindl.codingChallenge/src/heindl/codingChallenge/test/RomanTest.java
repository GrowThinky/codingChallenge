package heindl.codingChallenge.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import heindl.codingChallenge.Roman;

class RomanTest {
	
	HashMap<String,Integer> testInput;

			
	@BeforeEach
	void setUp() {
		testInput = new HashMap<String,Integer>();
		testInput.put("I",1);
		testInput.put("I I",2);
		testInput.put("I I I",3);
		testInput.put("I V",4);
		testInput.put("V",5);
		testInput.put("V I",6);
		testInput.put("V I I",7);
		testInput.put("V I I I",8);
		testInput.put("I X", 9);
		testInput.put("X", 10);
		testInput.put("X I I", 12);
		testInput.put("X V", 15);
		testInput.put("X V I", 16);
		testInput.put("X X V", 25);
	}

	@Test
	void test() {
		Roman roman = new Roman();
		
		String input;
		Integer solution;
		int result;
		
		for (Map.Entry<String, Integer> entry : testInput.entrySet()) {
			input = entry.getKey();
			solution = entry.getValue();
			result = roman.translate(input);
			assertEquals(solution, result);
		}
	}

}
