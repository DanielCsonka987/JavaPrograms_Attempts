package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgExceptions.EnigmaLikeCircuitException;
import ProgramComponents.EnigmaLikeCipherCircuit;

class UnitTest_enigmaLikeCircuit_Process {

	final String TEST_ENGLISH_TEXT = "Test text to test text-encryption";
	
	@Test
	void test1_rotor4() {
		
		try {
			
			EnigmaLikeCipherCircuit elc = new EnigmaLikeCipherCircuit();
			elc.setTheCircuitConfiguration(new char[] {'t', 'j', '2', 'b'}, new char[] {'h', '5'}, false);
			
			System.out.println("Enigma process with 4 rotor started");
			String res = elc.doTheCipherOnThisText(TEST_ENGLISH_TEXT, false);
			System.out.println("Encryption resutl: " + res);
			
			elc.setTheCircuitConfiguration(new char[] {'t', 'j', '2', 'b'}, new char[] {'h', '5'}, false);
			res = elc.doTheCipherOnThisText(res, true);
			System.out.println("Decryption result: " +res);
			
		} catch (EnigmaLikeCircuitException e) {
			fail("Error appeared");
		} catch (Exception e) {
			fail("Unknown error happend");
		}
	}
	@Test
	void test2_rotor6() {
		
		try {
			
			EnigmaLikeCipherCircuit elc = new EnigmaLikeCipherCircuit();
			elc.setTheCircuitConfiguration(new char[] {'t', 'j', '2', 'b', 'e', 'x'},
					new char[] {'h', '5', '8', '0'}, false);
			
			System.out.println("Enigma process with 6 rotor started");
			String res = elc.doTheCipherOnThisText(TEST_ENGLISH_TEXT, false);
			System.out.println("Encryption resutl: " + res);
			
			elc.setTheCircuitConfiguration(new char[] {'t', 'j', '2', 'b', 'e', 'x'},
					new char[] {'h', '5', '8', '0'}, false);
			res = elc.doTheCipherOnThisText(res, true);
			System.out.println("Decryption result: " +res);
			
		} catch (EnigmaLikeCircuitException e) {
			fail("Error appeared");
		} catch (Exception e) {
			fail("Unknown error happend");
		}
	}

}
