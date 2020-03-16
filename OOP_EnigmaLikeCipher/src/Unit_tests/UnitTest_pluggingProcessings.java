package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgramComponents.EnigmaPlugging;

class UnitTest_pluggingProcessings {

	final char[] CONFIGURATION1 = { 'b', '7', 'k', 'o', 'c', 'x' };

	@Test
	void test1_inputWithDemandToSwapp() {

		try {

			EnigmaPlugging ep = new EnigmaPlugging(CONFIGURATION1);

			if(ep.swapThisSpecificCharacter(CONFIGURATION1[0]) != CONFIGURATION1[1])
				fail("Not proper swapping - attempt 1");
			if(ep.swapThisSpecificCharacter(CONFIGURATION1[1]) != CONFIGURATION1[0])
				fail("Not proper swapping - attempt 2");
			
			if(ep.swapThisSpecificCharacter(CONFIGURATION1[4]) != CONFIGURATION1[5])
				fail("Not proper swapping - attempt 3");
			if(ep.swapThisSpecificCharacter(CONFIGURATION1[5]) != CONFIGURATION1[4])
				fail("Not proper swapping - attempt 4");
			
			if(ep.swapThisSpecificCharacter(CONFIGURATION1[2]) != CONFIGURATION1[3])
				fail("Not proper swapping - attempt 5");
			if(ep.swapThisSpecificCharacter(CONFIGURATION1[3]) != CONFIGURATION1[2])
				fail("Not proper swapping - attempt 6");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test2_inputIsNeturalContent() {
		
		try {
			
			EnigmaPlugging ep = new EnigmaPlugging(CONFIGURATION1);
			
			if(ep.swapThisSpecificCharacter('u') != 'u')
				fail("Spapping happened to netural character - attempt 7");
			if(ep.swapThisSpecificCharacter('1') != '1')
				fail("Spapping happened to netural character - attempt 8");
			if(ep.swapThisSpecificCharacter('z') != 'z')
				fail("Spapping happened to netural character - attempt 9");
			if(ep.swapThisSpecificCharacter('0') != '0')
				fail("Spapping happened to netural character - attempt 10");
			
		} catch( Exception e) {
			e.printStackTrace();
		}
		
	}

}
