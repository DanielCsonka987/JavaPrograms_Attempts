package ProgramComponents;

import ProgExceptions.EnigmaLikeCircuitException;

public interface IEnigmaLikeCircuitBehave {

	void setTheCircuitConfiguration(char[] rotorConfig, char[] plugConf, boolean charSetNeeded)
			throws EnigmaLikeCircuitException;
	
	String doTheCipherOnThisText(String textToEncode, boolean toDecode);
}
