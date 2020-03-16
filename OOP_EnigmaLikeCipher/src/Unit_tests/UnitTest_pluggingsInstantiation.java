package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ProgExceptions.PluggingFunctionException;
import ProgramComponents.EnigmaPlugging;

class UnitTest_pluggingsInstantiation {

	final char[] ALPHABETS_PROPER1_3P = new char[] { 'a', 'd', 't', 'k', 'z', 'b' };
	final char[] ALPHABETS_PROPER2_4P= new char[] { 'a', 'd', 't', 'k', 'z', 'b', '9', 'f' };

	final char[] ALPHABETS_IMBALANCED = new char[] { 'a', 'd', 't', 'k', 'z' };
	final char[] ALPHABETS_TOO_MUCH_PAIRS = new char[] { 'a', 'd', 't', 'k', 'z', 'b', 'g', 'p', '3', '6' };
	final char[] ALPHABETS_REPETABLE1 = new char[] { 'a', 'a', 'k', 'z', '6', 'p' };
	final char[] ALPHABETS_REPETABLE2 = new char[] { 'a', '6', 'k', 'z', 'a', 'p' };

	@Test
	void test1_goodConfigInstantiate1() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_PROPER1_3P);
		} catch (PluggingFunctionException e) {
			fail("Sudden error");
			e.printStackTrace();
		}
	}
	@Test
	void test2_goodConfigInstantiate1() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_PROPER2_4P);
		} catch (PluggingFunctionException e) {
			fail("Sudden error");
			e.printStackTrace();
		}
	}

	@Test
	void test3_badConfigInstantiate1_noAllPairs() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_IMBALANCED);
			fail("Not identifies the improper pairs");
		} catch (PluggingFunctionException e) {
			if (e.getMessage() != "Wrong plugging configuration")
				fail("Inappropiate error message");
		}
	}

	@Test
	void test4_badConfigInstantiate2_moreThan4Pair() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_TOO_MUCH_PAIRS);
			fail("Not identifies the too muwch pairs");
		} catch (PluggingFunctionException e) {
			if (e.getMessage() != "Wrong plugging configuration")
				fail("Inappropiate error message");
		}
	}

	@Test
	void test5_badConfigInstantiate3_repetableCharacters1() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_REPETABLE1);
			fail("Not identifies the repetation");
		} catch (PluggingFunctionException e) {
			if (e.getMessage() != "Wrong plugging configuration")
				fail("Inappropiate error message");
		}
	}

	@Test
	void test6_badConfigInstantiate4_repetableCharacters2() {

		try {
			EnigmaPlugging ep = new EnigmaPlugging(ALPHABETS_REPETABLE2);
			fail("Not identifies the repetation");
		} catch (PluggingFunctionException e) {
			if (e.getMessage() != "Wrong plugging configuration")
				fail("Inappropiate error message");
		}
	}
}
