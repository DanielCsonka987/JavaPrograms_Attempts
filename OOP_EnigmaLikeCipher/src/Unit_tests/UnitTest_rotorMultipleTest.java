package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgramComponents.EnigmaRevisor_WithCharacterSets;
import ProgramComponents.EnigmaRotor;

class UnitTest_rotorMultipleTest {

	@Test
	void test1_withTwoRotor_withoutStepping() {
		try {
			
			EnigmaRevisor_WithCharacterSets eRev = new EnigmaRevisor_WithCharacterSets(false);
			
			EnigmaRotor er1 = new EnigmaRotor('p', 1, false, eRev.getTheProperCharactersetToWheels());
			EnigmaRotor er2 = new EnigmaRotor('3', 2, true, eRev.getTheProperCharactersetToWheels());
			
			String str = "Test text for testing this and it must be so long to rotate the rotor 2";
			String result = "";
			
			int counter = 0;
			for(char c : str.toCharArray()) {
				
				char revised = eRev.reviseAndTransmitThisCharacter(c);
				char forwShu = er2.doTheCipherShuffle(er1.doTheCipherShuffle(revised, true), true);
				result += er1.doTheCipherShuffle(er2.doTheCipherShuffle(forwShu, false), false);
				
				//System.out.printf("Coding %s finished\n", c);
				counter++;
				if(counter == 0)
					break;
			}
			
			System.out.println("Result of encoding: " + result);
			str = result;
			result = "";
			
			for(char c : str.toCharArray()) {
				
				char revised = eRev.reviseAndTransmitThisCharacter(c);
				char forwShu = er2.doTheCipherShuffle(er1.doTheCipherShuffle(revised, true), true);
				result += er1.doTheCipherShuffle(er2.doTheCipherShuffle(forwShu, false), false);
			}
			
			System.out.println("Result of decoding: " + result);
			
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	@Test
	void test2_withTwoRotor_withStepping() {
		try {
			
			EnigmaRevisor_WithCharacterSets eRev = new EnigmaRevisor_WithCharacterSets(false);
			
			EnigmaRotor er1 = new EnigmaRotor('p', 1, false, eRev.getTheProperCharactersetToWheels());
			EnigmaRotor er2 = new EnigmaRotor('3', 2, true, eRev.getTheProperCharactersetToWheels());
			
			String str = "Test text for testing this and it must be so long to rotate the rotor 2";
			String result = "";
			int counter = 0;
			
			for(char c : str.toCharArray()) {
				
				char revised = eRev.reviseAndTransmitThisCharacter(c);
				char forwShu = er2.doTheCipherShuffle(er1.doTheCipherShuffle(revised, true), true);
				result += er1.doTheCipherShuffle(er2.doTheCipherShuffle(forwShu, false), false);
				
				boolean stepToNext = er1.doASteppingOnThisWheel();
				if(stepToNext)
					er2.doASteppingOnThisWheel();
				
				//System.out.printf("Coding %s finished\n", c);
				counter++;
				if(counter == 0)
					break;
			}
			
			System.out.println("Result of encoding: " + result);
			str = result;
			result = "";
			
			
			er1 = new EnigmaRotor('b', 1, false, eRev.getTheProperCharactersetToWheels());
			er2 = new EnigmaRotor('0', 2, true, eRev.getTheProperCharactersetToWheels());
			for(char c : str.toCharArray()) {
				
				char revised = eRev.reviseAndTransmitThisCharacter(c);
				char forwShu = er2.doTheCipherShuffle(er1.doTheCipherShuffle(revised, true), true);
				result += er1.doTheCipherShuffle(er2.doTheCipherShuffle(forwShu, false), false);
				
				boolean stepToNext = er1.doASteppingOnThisWheel();
				if(stepToNext)
					er2.doASteppingOnThisWheel();
			}
			
			System.out.println("Result of decoding: " + result);
			
		} catch (Exception c) {
			c.printStackTrace();
		}
	}

}
