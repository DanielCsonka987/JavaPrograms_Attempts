package Unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ProgExceptions.RotorFunctionException;
import ProgramComponents.EnigmaRotor;

class UnitTest_rotorProcessings {

	final List<Character> ROTORWHEEL_ANGLOGERMAINC = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'x', 'y', 'v', 'w', 'z' ));
	
	@Test
	void test1_rotorStepping1() {

		try {
			System.out.println("Test1");
			EnigmaRotor er = new EnigmaRotor('a', 1, false, ROTORWHEEL_ANGLOGERMAINC);
			for (int i = 0; i < ROTORWHEEL_ANGLOGERMAINC.size() - 11; i++)
				er.doASteppingOnThisWheel();
			if (!er.doASteppingOnThisWheel())
				fail("It dont shows the next wheel need to step by 1 position");
			System.out.println();

		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}

	}

	@Test
	void test2_rotorStepping2() {

		try {

			System.out.println("Test2");
			EnigmaRotor er1 = new EnigmaRotor('b', 1, false, ROTORWHEEL_ANGLOGERMAINC);
			EnigmaRotor er2 = new EnigmaRotor('a', 2, false, ROTORWHEEL_ANGLOGERMAINC);

			for (int i = 0; i < ROTORWHEEL_ANGLOGERMAINC.size() - 12; i++)
				er1.doASteppingOnThisWheel();
			if (!er1.doASteppingOnThisWheel())
				fail("It dont shows the next wheel need to step by 1 position");
			else
				er2.doASteppingOnThisWheel();
			System.out.println();

		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}

	}

	@Test
	void test3_rotorCipher1_noStepping() {

		try {
			System.out.println("Test3");
			EnigmaRotor er1 = new EnigmaRotor('a', 1, false, ROTORWHEEL_ANGLOGERMAINC);

			System.out.println("Result: " + er1.doTheCipherShuffle('a', true));
			System.out.println("Result: " + er1.doTheCipherShuffle('a', true));

			System.out.println();
		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}
	}

	@Test
	void test4_rotorCipher2_withStepping() {

		try {
			System.out.println("Test4");
			EnigmaRotor er1 = new EnigmaRotor('a', 1, false, ROTORWHEEL_ANGLOGERMAINC);

			System.out.println("Result: " + er1.doTheCipherShuffle('a', true));
			er1.doASteppingOnThisWheel();
			System.out.println("Result: " + er1.doTheCipherShuffle('a', true));

			System.out.println();
		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}
	}

	@Test
	void test5_rotorCipher3_reversingAttempt() {

		try {
			System.out.println("Test5");
			EnigmaRotor er1 = new EnigmaRotor('a', 1, true, ROTORWHEEL_ANGLOGERMAINC);

			System.out.println("Encode start with 'a'");
			char res = er1.doTheCipherShuffle('a', true);
			System.out.println("Encoding forward with reflection: " + res);
			res = er1.doTheCipherShuffle(res, false);
			System.out.println("Encoding backward: " + res);
			System.out.println();

			res = er1.doTheCipherShuffle(res, true);
			System.out.println("Decoding forward with reflection: " + res);
			res = er1.doTheCipherShuffle(res, false);
			System.out.println("Decoding backward: " + res);

		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}
	}

	@Test
	void test6_rotorCipher4_reversingAttempt() {

		try {
			System.out.println("Test6");
			EnigmaRotor er1 = new EnigmaRotor('5', 1, true, ROTORWHEEL_ANGLOGERMAINC);

			System.out.println("Encode start with 'z'");
			char res = er1.doTheCipherShuffle('z', true);
			System.out.println("Encoding forward with reflection: " + res);
			res = er1.doTheCipherShuffle(res, false);
			System.out.println("Encoding backward: " + res);
			System.out.println();

			res = er1.doTheCipherShuffle(res, true);
			System.out.println("Decoding forward with reflection: " + res);
			res = er1.doTheCipherShuffle(res, false);
			System.out.println("Decoding backward: " + res);

		} catch (RotorFunctionException e) {
			e.printStackTrace();
			fail("Error happend: " + e.getMessage());
		}
	}


	


}
