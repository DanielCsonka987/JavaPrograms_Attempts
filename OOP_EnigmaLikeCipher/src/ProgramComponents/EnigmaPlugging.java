package ProgramComponents;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ProgExceptions.PluggingFunctionException;

public class EnigmaPlugging implements IEnigmaPlugBehave {

	final Boolean NEEDED_DEBUG = false;
	final List<Character> SWAPPABLE_CHARACTERS;

	public EnigmaPlugging(char[] keys) throws PluggingFunctionException {

		if (!revisionOfCharactersContent_isItGood(keys))
			throw new PluggingFunctionException("Wrong plugging configuration");

		this.SWAPPABLE_CHARACTERS = IntStream.range(0, keys.length)
				.mapToObj(i -> keys[i])
				.collect(Collectors.toList());

		if (NEEDED_DEBUG)
			System.out.println("The plugging konfiguration is: " + SWAPPABLE_CHARACTERS);
	}

	@Override
	public char swapThisSpecificCharacter(char c) {

		char theSwappedOne;

		int pointer = SWAPPABLE_CHARACTERS.indexOf(c);
		if (pointer == -1) {
			if (NEEDED_DEBUG)
				System.out.println("No swapping, remaned: " + c);
			return c;
		}

		if(pointer == 0) {
			theSwappedOne = SWAPPABLE_CHARACTERS.get(pointer + 1);
		} else {
			theSwappedOne = pointer % 2 == 0 ? SWAPPABLE_CHARACTERS.get(pointer + 1)
					: SWAPPABLE_CHARACTERS.get(pointer - 1);
		}
		
		if (NEEDED_DEBUG)
			System.out.printf("Swapping result is: %s swapped to %s\n", c, theSwappedOne);
		return theSwappedOne;
	}

	private boolean revisionOfCharactersContent_isItGood(char[] keys) {

		if (keys.length % 2 != 0)
			return false;
		if (keys.length > 8)
			return false;

		Stream<Character> tempStream = IntStream.range(0, keys.length).mapToObj(i -> keys[i]).distinct();
		if (tempStream.count() != keys.length)
			return false;

		return true;
	}
}
