package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ProgExceptions.RotorFunctionException;
import ProgramComponents.EnigmaRotor;

class UnitTest_rotorFullIterative_SteppingTest {

	final List<Character> ROTORWHEEL_ANGLOGERMAINC = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'x', 'y', 'v', 'w', 'z' ));
	
	
	@Test 
	void test8_rotorCipher6_fullRotorTurn() {
		try {
			EnigmaRotor er1 = new EnigmaRotor('0', 1, true, ROTORWHEEL_ANGLOGERMAINC);
			for(int rotorStep = 0; rotorStep < ROTORWHEEL_ANGLOGERMAINC.size(); rotorStep++) {
				System.out.println("Rotor stepping no. " + rotorStep);
				if(rotorStep == ROTORWHEEL_ANGLOGERMAINC.size() - 1) {
				
					if(!er1.doASteppingOnThisWheel()) {
						fail("It did not full rotor turn, after whole way-length");
					} else {
						System.out.println("It shows properly to the next rotor, make a turn");
					}
					
				} else {
					if(er1.doASteppingOnThisWheel())
						fail("Did Full rotor turn, before whole way-length at " + rotorStep);
				}
			}
			
		} catch (RotorFunctionException e) {
			e.printStackTrace();
		}
	}
}
