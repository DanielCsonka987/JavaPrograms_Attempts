package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ProgramComponents.EnigmaRotor;

class UnitTest_rotorInstatiation {

	final List<Character> ROTORWHEEL_ANGLOGERMAINC = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'x', 'y', 'v', 'w', 'z' ));
	
	
	
	@Test
	void test_rotorInstatiation1() {

		try {
			
			EnigmaRotor er = new EnigmaRotor('a', 1, false, ROTORWHEEL_ANGLOGERMAINC);
			
		}catch(Exception e) {
			fail("Error:");
			e.printStackTrace();
		}
	}

	@Test
	void test_rotorInstatiation2_wrongStarterChar() {

		try {
			
			EnigmaRotor er = new EnigmaRotor(' ', 1, false, ROTORWHEEL_ANGLOGERMAINC);
			fail("Error:");
		}catch(Exception e) {
			
			if(!e.getMessage().equals("No such letter"))
				fail("Wrong type of error arrived!");
		}
	}
	
	@Test
	void test_rotorInstatiation3_noAlphabetColl() {

		try {
			
			EnigmaRotor er = new EnigmaRotor('b', 1, false, null);
			fail("Error:");
		}catch(Exception e) {
			
			if(!e.getMessage().equals("No characters added to the rotor-wheel"))
				fail("Wrong type of error arrived! " + e.getMessage());
		}
	}
	
	@Test
	void test_rotorInstatiation4_noCorrectRotorNaming() {

		try {
			
			EnigmaRotor er = new EnigmaRotor('b', -2, false, ROTORWHEEL_ANGLOGERMAINC);
			fail("Error:");
		}catch(Exception e) {
			
			if(!e.getMessage().equals("Wrong rotor-definition added to the wheel"))
				fail("Wrong type of error arrived!");
		}
	}
}
