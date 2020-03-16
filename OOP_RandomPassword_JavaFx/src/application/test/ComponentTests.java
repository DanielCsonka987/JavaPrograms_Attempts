package application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.logic.LetterContainer;
import application.logic.LetterContainer.LetterTypeDefiner;
import application.logic.RandomDealer;
import application.logic.SpecLetterContainer;

class ComponentTests {

	@Test
	void testOfRandDealer() {
		
		RandomDealer.getTheRandDealerObject();
		
		for(int i = 1; i < 11; i++) {
			
			Integer genNumber = RandomDealer.generateTheRandom(10);
			if (genNumber > 10)
				fail("1-Wrong upper intervalum " + genNumber);
			if (genNumber < 0)
				fail("1-Wrong lower intervalum" + genNumber);

		}
		
		for(int i = 1; i < 21; i++) {
			
			Integer genNumber = RandomDealer.generateTheRandom(100);
			if (genNumber > 100)
				fail("1-Wrong upper intervalum " + genNumber);
			if (genNumber < 0)
				fail("1-Wrong lower intervalum" + genNumber);

		}
	}

	
	@Test
	void testOfSpecialCharactCont() {
		
		SpecLetterContainer.getTheSpecLetterContainer();
		
		for(int i = 1; i<101; i++) {

			Character resultChar = SpecLetterContainer.getTheSpecLetter();
			if (Character.isLetter(resultChar))
				fail(i + "-Not letter arrived " + resultChar);
			if (	(resultChar.equals('_') || resultChar.equals('(') 
					|| resultChar.equals(')') || resultChar.equals('@')
					|| resultChar.equals('%') || resultChar.equals('/') 
					|| resultChar.equals('&')	) == false)
				fail(i + "-Wrong type of spec letter " + resultChar);

		}

	}
	
	@Test
	void testOfLetterCont() {
		
		LetterContainer.getLetterContainer();
		
		for (int i = 1; i < 101; i++) {
			try {

				Character res =  LetterContainer.getLetter(LetterTypeDefiner.LOWERCASE);
				if(res == null)
					fail(i + "-No result at this process");
				if( ! Character.isLetter(res))
					fail(i + "-No letter arrived " + res);
				if( ! Character.isLowerCase(res))
					fail(i + "-No lowercase arrived " + res);
				
			} catch (Exception e) {
				fail(i + "-Process error");
			}
		}
		
		
		for (int i = 101; i < 201; i++) {
			try {

				Character res =  LetterContainer.getLetter(LetterTypeDefiner.UPPERCASE);
				if(res == null)
					fail(i + "-No result at this process");
				if( ! Character.isLetter(res))
					fail(i + "-No letter arrived " + res);
				if( ! Character.isUpperCase(res))
					fail(i + "-No uppercase arrived " + res);
				
			} catch (Exception e) {
				fail(i + "-Process error");
			}
		}
	}
}
