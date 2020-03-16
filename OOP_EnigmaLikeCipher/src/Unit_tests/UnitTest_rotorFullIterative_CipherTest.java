package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ProgExceptions.RotorFunctionException;
import ProgramComponents.EnigmaRotor;

class UnitTest_rotorFullIterative_CipherTest {

	final List<Character> ROTORWHEEL_ANGLOGERMAINC = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'x', 'y', 'v', 'w', 'z' ));
	
	@Test
	void test7_rotorCipher5_reversingAttempt() {

		try {
			int counter = 1;
			EnigmaRotor er1;
			for (int adjusts = 0; adjusts < ROTORWHEEL_ANGLOGERMAINC.size(); adjusts++) {
				for (int alphab = 0; alphab < ROTORWHEEL_ANGLOGERMAINC.size(); alphab++) {
					System.out.println();
					System.out.println("Full test " + counter++);
					er1 = new EnigmaRotor(ROTORWHEEL_ANGLOGERMAINC.get(adjusts),
							1, true, ROTORWHEEL_ANGLOGERMAINC);
					System.out.println("Rotor configured: " + ROTORWHEEL_ANGLOGERMAINC.get(adjusts)
							+ " Encode start with: " + ROTORWHEEL_ANGLOGERMAINC.get(alphab));
					char res = er1.doTheCipherShuffle(ROTORWHEEL_ANGLOGERMAINC.get(alphab), true);
					System.out.println("Encoding forward with reflection: " + res);
					res = er1.doTheCipherShuffle(res, false);
					System.out.println("Encoding backward: " + res);

					res = er1.doTheCipherShuffle(res, true);
					System.out.println("Decoding forward with reflection: " + res);
					res = er1.doTheCipherShuffle(res, false);
					System.out.println("Decoding backward: " + res);
					
					assertEquals(ROTORWHEEL_ANGLOGERMAINC.get(alphab), res);
				}
			}

		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}
	}

}
