package ProgramComponents;

import java.io.OutputStreamWriter;
import java.util.List;

import ProgExceptions.RotorFunctionException;

public class EnigmaRotor implements IEnigmaRotorBehave {

	private final Boolean NEED_DEBUG_AT_INIT = false;
	private final Boolean NEED_DEBUG_AT_CIPHER = true;	//TESTING PURPOSE
	private final Boolean NEED_DEBUG_AT_CIPHER_MINOR = false;
	private final Boolean NEED_DEBUG_AT_REFLECTION = false;
	private final Boolean NEED_DEBUG_AT_STEPPING = false;
	
	private final Integer ROTOR_NUMBER_IN_THE_ROW;
	private final Boolean REFLECTION_IS_NEEDED_THIS_IS_THE_LAST_IN_ROW;
	private final List<Character> CHARACTER_COLLECTION;
	private final Integer FULL_LENGT_OF_CHARACTER_COLLECTION;
	private final Integer HALFWAY_IN_CHARACTER_COLLECTION;

	private Integer actualRotorPosition_ForwardShuffleRange = 0;
	private Integer actualRotorPosition_BackwardShullfleRange = 0;

	public EnigmaRotor(char startChar, int rotorNum, boolean theLast, List<Character> keys) 
			throws RotorFunctionException {

		if (keys == null)
			throw new RotorFunctionException("No characters added to the rotor-wheel");
		this.CHARACTER_COLLECTION = keys;
		this.FULL_LENGT_OF_CHARACTER_COLLECTION = keys.size();
		this.HALFWAY_IN_CHARACTER_COLLECTION = (keys.size() / 2);

		try {
			this.actualRotorPosition_ForwardShuffleRange = findThisPosition(startChar);
			this.actualRotorPosition_BackwardShullfleRange = FULL_LENGT_OF_CHARACTER_COLLECTION
					- actualRotorPosition_ForwardShuffleRange;
		} catch (Exception e) {
			throw new RotorFunctionException(e.getMessage());
		}

		if (rotorNum < 0)
			throw new RotorFunctionException("Wrong rotor-definition added to the wheel");
		this.ROTOR_NUMBER_IN_THE_ROW = rotorNum;

		this.REFLECTION_IS_NEEDED_THIS_IS_THE_LAST_IN_ROW = theLast;

		if (NEED_DEBUG_AT_INIT) {
			System.out.println("full length: " + FULL_LENGT_OF_CHARACTER_COLLECTION);
			System.out.println("half-index: " + HALFWAY_IN_CHARACTER_COLLECTION);
			System.out.printf("Rotor no. %d is ready to functionate\n", this.ROTOR_NUMBER_IN_THE_ROW);
			System.out.println("Datas:\n forward: " + actualRotorPosition_ForwardShuffleRange 
					+ "\n backward: " + actualRotorPosition_BackwardShullfleRange 
					+ "\n reflection: " + REFLECTION_IS_NEEDED_THIS_IS_THE_LAST_IN_ROW.toString());
			System.out.println();
		}
	}

	@Override
	public char doTheCipherShuffle(char c, boolean isForward) {

		try {
			if(c == '_')
				return '_';
			if (NEED_DEBUG_AT_CIPHER)
				System.out.printf("Cipher process of rotor no. %d\n", ROTOR_NUMBER_IN_THE_ROW);
			if (isForward) {
				int posOfInput = findThisPosition(c);
				char cipherResult = doTheCipher(posOfInput, actualRotorPosition_ForwardShuffleRange);
				if (NEED_DEBUG_AT_CIPHER)
					System.out.printf("Forward cipher turned '%s' to '%s'\n", c, cipherResult);
				if (REFLECTION_IS_NEEDED_THIS_IS_THE_LAST_IN_ROW) {
					posOfInput = findThisPosition(cipherResult);
					cipherResult = doTheReflection(posOfInput);
					if (NEED_DEBUG_AT_CIPHER)
						System.out.printf("Reflection happened at %d, result turned to %s\n",
								ROTOR_NUMBER_IN_THE_ROW,cipherResult);
				}
				return cipherResult;
			} else {
				int posOfInput = findThisPosition(c);
				char cipherResult = doTheCipher(posOfInput, actualRotorPosition_BackwardShullfleRange);
				if (NEED_DEBUG_AT_CIPHER)
					System.out.printf("Backward cipher turned '%s' to %s\n", c, cipherResult);

				return cipherResult;

			}
		} catch (RotorFunctionException e) {
			e.printStackTrace();
		}
		return '_';	//this is not permitted character -> in
	}

	@Override
	public boolean doASteppingOnThisWheel() {

		if (NEED_DEBUG_AT_STEPPING) {
			System.out.println("Stepping at rotor no. " + ROTOR_NUMBER_IN_THE_ROW);
			System.out.printf("Rotor no. %d has position of: %s\n", ROTOR_NUMBER_IN_THE_ROW,
					findThisLetter(actualRotorPosition_ForwardShuffleRange));
		}
		actualRotorPosition_ForwardShuffleRange++;
		actualRotorPosition_BackwardShullfleRange--;
		if (NEED_DEBUG_AT_STEPPING) {
			System.out.println("Actually the forward direct. shuffle: " 
					+ actualRotorPosition_ForwardShuffleRange);
			System.out.println("Actually the backward direct. shuffle: " 
					+ actualRotorPosition_BackwardShullfleRange);
		}
		
			
		if (FULL_LENGT_OF_CHARACTER_COLLECTION == actualRotorPosition_ForwardShuffleRange) {
			actualRotorPosition_ForwardShuffleRange = 0;
			actualRotorPosition_BackwardShullfleRange = 36;
			
			if (NEED_DEBUG_AT_STEPPING) {
				System.out.println("Pointer re-definition is needed!");
				System.out.printf("Rotor no. %d reposition to this again: %s\n", ROTOR_NUMBER_IN_THE_ROW,
						findThisLetter(actualRotorPosition_ForwardShuffleRange));
				System.out.println("Again the forward direct. shuffle: " 
						+ actualRotorPosition_ForwardShuffleRange);
				System.out.println("Again the backward direct. shuffle: " 
						+ actualRotorPosition_BackwardShullfleRange);
			}
			return true;
		}
		return false;
	}

	private char findThisLetter(int posOfInput) {

		return this.CHARACTER_COLLECTION.get(posOfInput);
	}

	private int findThisPosition(Character letter) throws RotorFunctionException {

		for (int i = 0; i < FULL_LENGT_OF_CHARACTER_COLLECTION; i++) {
			if (CHARACTER_COLLECTION.get(i).equals(letter))
				return i;
		}
		throw new RotorFunctionException("No such letter " + letter);
	}

	private char doTheCipher(int posOfInput, int shuffleRange) {
		
		char cipheredChar;
		int nextNeededPos = posOfInput + shuffleRange;

		if (NEED_DEBUG_AT_CIPHER_MINOR) {
			System.out.println("-Pos: " + posOfInput + " range: " + shuffleRange);
			System.out.println("-Next char would be: " + nextNeededPos);
		}

		if (nextNeededPos >= FULL_LENGT_OF_CHARACTER_COLLECTION)
			nextNeededPos -= FULL_LENGT_OF_CHARACTER_COLLECTION;

		if (NEED_DEBUG_AT_CIPHER_MINOR)
			System.out.println("Used shuffle char: " + nextNeededPos);
		cipheredChar = findThisLetter(nextNeededPos);

		return cipheredChar;
	}

	private char doTheReflection(int posOfInput) {

		char outputChar;
		if (NEED_DEBUG_AT_REFLECTION)
			System.out.println("-input to reflect: " + posOfInput);
		int nextNeededPos;
		if (posOfInput >= HALFWAY_IN_CHARACTER_COLLECTION) {
			nextNeededPos = posOfInput - HALFWAY_IN_CHARACTER_COLLECTION;
			if (NEED_DEBUG_AT_REFLECTION)
				System.out.println("->ref back");
		} else {
			nextNeededPos = posOfInput + HALFWAY_IN_CHARACTER_COLLECTION;
			if (NEED_DEBUG_AT_CIPHER)
				System.out.println("->ref forw");
		}
		if (nextNeededPos >= FULL_LENGT_OF_CHARACTER_COLLECTION)
			nextNeededPos -= FULL_LENGT_OF_CHARACTER_COLLECTION;
		if (NEED_DEBUG_AT_REFLECTION)
			System.out.println("-Reflection needed to farther: " + nextNeededPos);
		outputChar = findThisLetter(nextNeededPos);
		if (NEED_DEBUG_AT_REFLECTION)
			System.out.println("-that is: " + outputChar);

		return outputChar;
	}
}
