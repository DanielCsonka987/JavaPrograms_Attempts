package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgExceptions.EnigmaLikeCircuitException;
import ProgramComponents.EnigmaLikeCipherCircuit;

class UnitTest_enigmaLikeCircuit_Setting {

	@Test
	void test1_enigmaNormalSet1() {
		
		try {
			
			EnigmaLikeCipherCircuit enigma = new EnigmaLikeCipherCircuit();
			enigma.setTheCircuitConfiguration(new char[] {'a','b'}, new char[] {'z', 't'}, false);

			System.out.println("Normal setting done");
		} catch (EnigmaLikeCircuitException e) {
			e.printStackTrace();
		}
	}
	@Test
	void test2_enigmaBadSet1() {
		
		try {
			
			EnigmaLikeCipherCircuit enigma = new EnigmaLikeCipherCircuit();
			enigma.setTheCircuitConfiguration(new char[] {'a','b'}, new char[] {'_', 't'}, false);
			fail("Bad plugging is not identified");
		} catch (EnigmaLikeCircuitException e) {
			if(e.getMessage() != "Plugging character content is inappopiate")
				fail("Plugging mistake not appeared");
			System.out.println("Plugging mistake identified");
		}
	}
	@Test
	void test3_enigmaBadSet2() {
		
		try {
			
			EnigmaLikeCipherCircuit enigma = new EnigmaLikeCipherCircuit();
			enigma.setTheCircuitConfiguration(new char[] {'a',' '}, new char[] {'z', 't'}, false);
			fail("Bad rotor config is not identified");
		} catch (EnigmaLikeCircuitException e) {
			if(e.getMessage() != "Rotor config content is inappropiate")
				fail("Rotor config mistake is not appeared");
			System.out.println("Rotor config mistake identified");
		}
	}
	@Test
	void test4_enigmaEncriptAttempt1() {
		
		try {
			
			EnigmaLikeCipherCircuit enigma = new EnigmaLikeCipherCircuit();
			enigma.setTheCircuitConfiguration(new char[] {'a','b'}, new char[] {'z', 't'}, false);
			
			System.out.println("");
		} catch (EnigmaLikeCircuitException e) {
			e.printStackTrace();
		}
	}

}
