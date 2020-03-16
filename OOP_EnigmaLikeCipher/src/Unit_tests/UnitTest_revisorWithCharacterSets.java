package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgramComponents.EnigmaRevisor_WithCharacterSets;

class UnitTest_revisorWithCharacterSets {

	@Test
	void test1_instantiation() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			if(er_ang.getTheProperCharactersetToWheels().get(0) != '0')
				fail("Wrong character at attempt 1");
			if(er_ang.getTheProperCharactersetToWheels().get(10) != 'a')
				fail("Wrong character at attempt 2");
			if(er_ang.getTheProperCharactersetToWheels().get(35) != 'z')
				fail("Wrong character at attempt 3");
			if(er_ang.getTheProperCharactersetToWheels().get(17) != 'h')
				fail("Wrong character at attempt 4");
			
			System.out.println("Test revisor init done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test2_incorrectCharacters1_spaceSep() {
		try {
			
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter(' ');
			if(res != '_') fail("Wrong revision space at 1"); 
			res = er_ang.reviseAndTransmitThisCharacter('\n');
			if(res != '_') fail("Wrong revision space at 2"); 
			res = er_ang.reviseAndTransmitThisCharacter('\r');
			if(res != '_') fail("Wrong revision space at 3"); 
			res = er_ang.reviseAndTransmitThisCharacter('\f');
			if(res != '_') fail("Wrong revision space at 4"); 

			System.out.println("Test revisor with incorr space sep done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test3_incorrectCharacters2_mathSymbols() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter('%');
			if(res != '_') fail("Wrong revision math at 1"); 
			res = er_ang.reviseAndTransmitThisCharacter('-');
			if(res != '_') fail("Wrong revision math at 2"); 
			res = er_ang.reviseAndTransmitThisCharacter('/');
			if(res != '_') fail("Wrong revision math at 3"); 
			res = er_ang.reviseAndTransmitThisCharacter('+');
			if(res != '_') fail("Wrong revision math at 4"); 
			res = er_ang.reviseAndTransmitThisCharacter('(');
			if(res != '_') fail("Wrong revision math at 5"); 
			
			System.out.println("Test revisor with math symbols done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test4_incorrectCharacters3_currenySymbols() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter('$');
			if(res != '_') fail("Wrong revision currency at 1"); 
			res = er_ang.reviseAndTransmitThisCharacter('€');
			if(res != '_') fail("Wrong revision currency at 2"); 

			
			System.out.println("Test revisor with currency symbols done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test5_incorrectCharacters4_punctuationSymbols() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter('"');
			if(res != '_') fail("Wrong revision punctuat at 1"); 
			res = er_ang.reviseAndTransmitThisCharacter(':');
			if(res != '_') fail("Wrong revision punctuat at 2"); 
			res = er_ang.reviseAndTransmitThisCharacter('!');
			if(res != '_') fail("Wrong revision punctuat at 3"); 
			res = er_ang.reviseAndTransmitThisCharacter('?');
			if(res != '_') fail("Wrong revision punctuat at 4"); 
			
			System.out.println("Test revisor with currency symbols done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test6_correctCharacters1_capital() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter('A');
			if(res != 'a') fail("Wrong lower-character arrived at 1");
			res = er_ang.reviseAndTransmitThisCharacter('Z');
			if(res != 'z') fail("Wrong lower-character arrived at 2");
			res = er_ang.reviseAndTransmitThisCharacter('E');
			if(res != 'e') fail("Wrong lower-character arrived at 3");
			res = er_ang.reviseAndTransmitThisCharacter('P');
			if(res != 'p') fail("Wrong lower-character arrived at 4");
			
			System.out.println("Test revisor with capital-letters done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test7_correctCharacters2_randomEnglish() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(false);
			
			char res = er_ang.reviseAndTransmitThisCharacter('a');
			if(res != 'a') fail("Wrong random-character arrived at 1");
			res = er_ang.reviseAndTransmitThisCharacter('Z');
			if(res != 'z') fail("Wrong random-character arrived at 2");
			res = er_ang.reviseAndTransmitThisCharacter('2');
			if(res != '2') fail("Wrong random-character arrived at 3");
			res = er_ang.reviseAndTransmitThisCharacter('ű');
			if(res != '_') fail("Wrong random-character arrived at 4");
			res = er_ang.reviseAndTransmitThisCharacter('ß');
			if(res != '_') fail("Wrong random-character arrived at 5");
			res = er_ang.reviseAndTransmitThisCharacter('L');
			if(res != 'l') fail("Wrong random-character arrived at 6");
			res = er_ang.reviseAndTransmitThisCharacter(' ');
			if(res != '_') fail("Wrong random-character arrived at 7");
			
			System.out.println("Test revisor with random-letters done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void test8_correctCharacters3_randomHungarian() {
		try {
			EnigmaRevisor_WithCharacterSets er_ang = new EnigmaRevisor_WithCharacterSets(true);
			
			char res = er_ang.reviseAndTransmitThisCharacter('a');
			if(res != 'a') fail("Wrong random-hun-character arrived at 1");
			res = er_ang.reviseAndTransmitThisCharacter('Z');
			if(res != 'z') fail("Wrong random-hun-character arrived at 2");
			res = er_ang.reviseAndTransmitThisCharacter('é');
			if(res != 'é') fail("Wrong random-hun-character arrived at 3");
			res = er_ang.reviseAndTransmitThisCharacter('Ű');
			if(res != 'ű') fail("Wrong random-hun-character arrived at 4");
			res = er_ang.reviseAndTransmitThisCharacter('ß');
			if(res != '_') fail("Wrong random-hun-character arrived at 5");
			res = er_ang.reviseAndTransmitThisCharacter('Á');
			if(res != 'á') fail("Wrong random-hun-character arrived at 6");
			res = er_ang.reviseAndTransmitThisCharacter('@');
			if(res != '@') fail("Wrong random-hun-character arrived at 7");
			
			System.out.println("Test revisor with random-hun-letters done well");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
