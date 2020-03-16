package ProgramComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnigmaRevisor_WithCharacterSets implements IEnigmaRevisorBehave{

	final Boolean NEEDED_DEBUGGING = false; 
	
	final List<Character> ROTOR_WHEEL_WITH_HUNGARIAN_CHARACTERS = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h',
			'i', 'í', 'j', 'k', 'l', 'm', 'n', 'o', 'ó', 'ö',
			'ő', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'ű',
			'x', 'y', 'v', 'w', 'z', '@' ));
	
	final List<Character> ROTOR_WHEEL_WITH_ANGLOGERMAN_CHARACTERS = new ArrayList<Character>( Arrays.asList(
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'x', 'y', 'v', 'w', 'z' ));

	final List<Character> CHARACTERS_UNDER_USE;
	
	public EnigmaRevisor_WithCharacterSets(boolean type) {
		
		if(NEEDED_DEBUGGING) {
			System.out.println("Content of this language character-set");
			System.out.println(ROTOR_WHEEL_WITH_ANGLOGERMAN_CHARACTERS);
		}
		if(type)
			CHARACTERS_UNDER_USE = ROTOR_WHEEL_WITH_HUNGARIAN_CHARACTERS;
		else
			CHARACTERS_UNDER_USE = ROTOR_WHEEL_WITH_ANGLOGERMAN_CHARACTERS;
	}
	
	@Override
	public char reviseAndTransmitThisCharacter(char c) {
		
		if(Character.isWhitespace(c))
			return '_';

		char tempChar = c;
		if(Character.isUpperCase(tempChar))
			tempChar = Character.toLowerCase(tempChar);

		if(CHARACTERS_UNDER_USE.contains(tempChar))
			return tempChar;
		else
			return '_';
	}

	@Override
	public List<Character> getTheProperCharactersetToWheels() {

		return CHARACTERS_UNDER_USE;
	}
	
}
