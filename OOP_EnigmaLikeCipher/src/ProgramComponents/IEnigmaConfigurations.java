package ProgramComponents;

public interface IEnigmaConfigurations {

	/*
	 * starter define which character collection is needed to work with
	 */
	void setTheAlphabetics(boolean t);
	
	/*
	 * starter rotor-alphabet configuration
	 */
	void setStarterRotorStates(char[] configOfWheels);
	
	/*
	 * starter plugging, that shuffles at the begining and end the alphabet
	 * it must be even amount and in the proper alphabet collection
	 */
	void setTheStarterPlugging(char[] plugs);
}
