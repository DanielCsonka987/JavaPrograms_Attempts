package ProgramComponents;

import java.util.List;

public interface IEnigmaRevisorBehave {

	/*
	 * this revise the input character, 
	 * if it is permitted to the enigma circuit to work with
	 * if it is not acceptable, gives no result or
	 * change it a possible permitted (non-accented version, ect.)  
	 */
	char reviseAndTransmitThisCharacter(char c);
	
	List<Character> getTheProperCharactersetToWheels();
}
