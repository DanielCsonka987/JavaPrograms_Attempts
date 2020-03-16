package ProgramComponents;

public interface IEnigmaRotorBehave {
	
	
	/*
	 * enigma-rotor like cipher process
	 * true = first rotor input, forward to the cipher process
	 * 		if it is the last, it does the reflection as well,
	 * 		shuffle by half of the wheel-width
	 * false = second rotor input, backward to the user-interface
	 */
	char doTheCipherShuffle(char c, boolean isForward);
	
	/*
	 * inter-rotor conection
	 * shifting on rotor, in need after process
	 * if needed to step the rotor after this return true
	 */
	boolean doASteppingOnThisWheel();
	

}
