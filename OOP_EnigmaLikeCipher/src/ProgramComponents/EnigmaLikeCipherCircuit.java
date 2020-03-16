package ProgramComponents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ProgExceptions.EnigmaLikeCircuitException;
import ProgExceptions.PluggingFunctionException;
import ProgExceptions.RotorFunctionException;

public class EnigmaLikeCipherCircuit implements IEnigmaLikeCircuitBehave {

	private final Boolean NEED_DEBUG_SETTING = false;
	private final Boolean NEED_DEBUG_REVISION = false;
	private final Boolean NEED_DEBUG_ROTOR_FOLW = false;

	private List<EnigmaRotor> eRotorRow;
	private EnigmaRevisor_WithCharacterSets eRevisor;
	private EnigmaPlugging ePluggings;

	@Override
	public void setTheCircuitConfiguration(char[] rotorConfig, char[] plugConf, boolean charSetNeeded)
			throws EnigmaLikeCircuitException {

		try {
			initTheRevisor(charSetNeeded);
			initThePlugging(plugConf);
			initTheRotors(rotorConfig);
		} catch (PluggingFunctionException e) {
			// TODO Auto-generated catch block
			throw new EnigmaLikeCircuitException(e.getMessage());

		} catch (RotorFunctionException e1) {
			// TODO Auto-generated catch block
			throw new EnigmaLikeCircuitException(e1.getMessage());
		}
	}

	@Override
	public String doTheCipherOnThisText(String textToEncode, boolean toDecode) {

		char[] revisedCharacters = reviseAllCharactersAhead(textToEncode);
		char[] outputTextContent = new char[revisedCharacters.length];

		if (!toDecode)
			revisedCharacters = doThePluggingOnCharacters(revisedCharacters);
		for (int charPointer = 0; charPointer < revisedCharacters.length; charPointer++) {

			char underProc = revisedCharacters[charPointer];
			// cipher forward and reflection at the last
			underProc = doRotorRowCycleWithACharacter(underProc, true);
			// cipher backward
			underProc = doRotorRowCycleWithACharacter(underProc, false);

			outputTextContent[charPointer] = underProc;
			// stepping the rotors
			doTheRotorStepping();
			if (NEED_DEBUG_ROTOR_FOLW)
				System.out.println();
		}
		if (toDecode)
			outputTextContent = doThePluggingOnCharacters(outputTextContent);
		
		return String.valueOf(outputTextContent, 0, outputTextContent.length); 
	}

	private void initTheRevisor(boolean charSetNeeded) {

		eRevisor = new EnigmaRevisor_WithCharacterSets(charSetNeeded);
	}

	private void initThePlugging(char[] plugConf) throws PluggingFunctionException, EnigmaLikeCircuitException {

		if (NEED_DEBUG_SETTING)
			System.out.println("Plugging input is: " + Arrays.toString(plugConf));

		for (int i = 0; i < plugConf.length; i++) {
			char temp = eRevisor.reviseAndTransmitThisCharacter(plugConf[i]);
			if (temp != '_')
				plugConf[i] = temp;
			else
				throw new EnigmaLikeCircuitException("Plugging character content is inappopiate");
		}

		if (NEED_DEBUG_SETTING)
			System.out.println("Plugging after revision: " + Arrays.toString(plugConf));
		ePluggings = new EnigmaPlugging(plugConf);
	}

	private void initTheRotors(char[] rotorConfig) throws RotorFunctionException, EnigmaLikeCircuitException {

		eRotorRow = new ArrayList<EnigmaRotor>();

		for (int i = 0; i < rotorConfig.length; i++) {
			boolean last = (i == (rotorConfig.length - 1)) ? true : false;

			char conf = eRevisor.reviseAndTransmitThisCharacter(rotorConfig[i]);
			if (conf == '_')
				throw new EnigmaLikeCircuitException("Rotor config content is inappropiate");

			EnigmaRotor er = new EnigmaRotor(conf, i + 1, last, eRevisor.getTheProperCharactersetToWheels());
			eRotorRow.add(er);
		}
		if (NEED_DEBUG_SETTING)
			System.out.println("Rotor initalization finished, amount to operate: " + eRotorRow.size());
	}

	private char[] reviseAllCharactersAhead(String textToEncode) {

		char[] temp = textToEncode.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			temp[i] = eRevisor.reviseAndTransmitThisCharacter(temp[i]);
		}

		if (NEED_DEBUG_REVISION)
			System.out.println("Result on input revision is: " + Arrays.toString(temp));
		return temp;
	}

	private char[] doThePluggingOnCharacters(char[] underProcessCharacters) {

		for (int i = 0; i < underProcessCharacters.length; i++) {
			underProcessCharacters[i] = ePluggings.swapThisSpecificCharacter(underProcessCharacters[i]);
		}
		return underProcessCharacters;
	}

	private char doRotorRowCycleWithACharacter(char underProc, boolean isItForward) {
		if (isItForward) {
			for (int rotorForwPointer = 0; rotorForwPointer < eRotorRow.size(); rotorForwPointer++) {

				underProc = eRotorRow.get(rotorForwPointer).doTheCipherShuffle(underProc, true);
				if (NEED_DEBUG_ROTOR_FOLW)
					System.out.print("Forw:" + underProc + ";");
			}
		} else {
			for (int rotorBackPointer = eRotorRow.size() - 1; rotorBackPointer >= 0; rotorBackPointer--) {

				underProc = eRotorRow.get(rotorBackPointer).doTheCipherShuffle(underProc, false);
				if (NEED_DEBUG_ROTOR_FOLW)
					System.out.print("Back:" + underProc + ";");
			}
		}
		return underProc;
	}
	
	private void doTheRotorStepping() {
		for (int i = 0; i < eRotorRow.size(); i++) {
			if (!eRotorRow.get(i).doASteppingOnThisWheel())
				break;
		}
	}
}
