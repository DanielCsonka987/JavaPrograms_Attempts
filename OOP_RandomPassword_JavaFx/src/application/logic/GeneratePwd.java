package application.logic;

import java.io.StringWriter;
import java.util.LinkedList;

import application.logic.LetterContainer.LetterTypeDefiner;

public class GeneratePwd {

	private static GeneratePwd ownAddress;
	private static LinkedList<Character> desiredTextParts;
	private static String desiredText;
	private static Integer cycleAmount; 
	private static int[] characterRatios;	//0->letters; 1->digits; 2->symbols; 3->input
	
	private static NeededMode modeOfCreation;
	private static NeededCharacters modeOfSelection;
	
	public static GeneratePwd getGeneratePwdObject() {
		
		if(ownAddress == null) {
			ownAddress = new GeneratePwd();
		}
		return ownAddress;
	}
	
	private static enum NeededMode { UNGAPPED_ADDEDTEXT, GAPPED_ADDEDTEXT, ALLALONE }
	private static enum NeededCharacters { ONLY_Digits, ONLY_Symbols,
		BOTH_DigtiAndSpecials, ONLY_Letters }
	
	public static void adjustDetails(Integer length,
			Boolean needNumber, Boolean needSpecLetter,
			String text, Boolean mustBeUngapped) {
		
		adjustTheLetterTypes(needNumber, needSpecLetter);
		adjustAccordingTextInput(text, mustBeUngapped);
		adjustCharacterRatios(length);
	}


	private static void adjustTheLetterTypes(Boolean needNumber,
			Boolean needSpecLetter) {
		
		if(needNumber && needSpecLetter)
			modeOfSelection = modeOfSelection.BOTH_DigtiAndSpecials;
		else if(needNumber)
			modeOfSelection = NeededCharacters.ONLY_Digits;
		else if (needSpecLetter)
			modeOfSelection = NeededCharacters.ONLY_Symbols;
		else 
			modeOfSelection = NeededCharacters.ONLY_Letters;

	}

	private static void adjustAccordingTextInput(String text,
			Boolean mustBeUngapped) {
		
		if(text.contentEquals("")) {
			modeOfCreation = NeededMode.ALLALONE;
			desiredText = "";

		} else {
			desiredText = text;
			if(mustBeUngapped) {
				modeOfCreation = NeededMode.UNGAPPED_ADDEDTEXT;
			}
			else {
				desiredTextParts = new LinkedList<Character>();
				for(Character ch : text.toCharArray())
					desiredTextParts.push(ch);
				modeOfCreation = NeededMode.GAPPED_ADDEDTEXT;
			}
		}
	}
	
	
	private static void adjustCharacterRatios(Integer lengthThatUserNeed) {
		
		if(desiredText.length() > 0) {
			lengthThatUserNeed = (lengthThatUserNeed <= desiredText.length()) ?
					desiredText.length() + 3 : lengthThatUserNeed;
			lengthThatUserNeed = (lengthThatUserNeed < desiredText.length() + 3) ?
					desiredText.length() + 3 : lengthThatUserNeed;
		}

		characterRatios = new int[4];	//0->letters; 1->digits; 2->symbols; 3->input
		Integer charAmountToDefine = 0;
		if(modeOfCreation == NeededMode.ALLALONE) {
			characterRatios[3] = 0;
			setTheRatioOfTypes(lengthThatUserNeed);
			cycleAmount = lengthThatUserNeed;
		} else {
			charAmountToDefine = lengthThatUserNeed - desiredText.length();
			
			if (modeOfCreation == NeededMode.GAPPED_ADDEDTEXT) {
				setTheRatioOfTypes(charAmountToDefine);
				characterRatios[3] = desiredText.length();
				cycleAmount = lengthThatUserNeed;
			} else if (modeOfCreation == NeededMode.UNGAPPED_ADDEDTEXT) {
				setTheRatioOfTypes(charAmountToDefine);
				characterRatios[3] = 1;
				cycleAmount = charAmountToDefine + 1;
			}
		}
	}
	
	private static void setTheRatioOfTypes(Integer charAmountToDefine) {	//0->letters; 1->digits; 2->symbols; 3->input
		
		if(modeOfSelection == NeededCharacters.BOTH_DigtiAndSpecials) {
			characterRatios[0] = Math.round((charAmountToDefine * 6) / 10);
			charAmountToDefine = charAmountToDefine - characterRatios[0];
			characterRatios[1] = Math.round((charAmountToDefine * 8) / 10);
			charAmountToDefine = charAmountToDefine - characterRatios[1];
			characterRatios[2] = charAmountToDefine;
		}
		if(modeOfSelection == NeededCharacters.ONLY_Digits) {
			characterRatios[0] = Math.round((charAmountToDefine * 6) / 10);
			charAmountToDefine = charAmountToDefine - characterRatios[0];
			characterRatios[1] = charAmountToDefine;
			characterRatios[2] = 0;
		}
		if(modeOfSelection == NeededCharacters.ONLY_Symbols) {
			characterRatios[0] = Math.round((charAmountToDefine * 6) / 10);
			charAmountToDefine = charAmountToDefine - characterRatios[0];
			characterRatios[1] = 0;
			characterRatios[2] = charAmountToDefine;
		}
		if(modeOfSelection == NeededCharacters.ONLY_Letters) {
			characterRatios[0] = charAmountToDefine;
			characterRatios[1] = 0;
			characterRatios[2] = 0;
		}
		
	}
	
	
	public static String getTheDesiredPwd() {
		
		RandomDealer.getTheRandDealerObject();
		StringWriter strwr = new StringWriter();
		for(int i = 0; i < cycleAmount; i++) {
			
			Integer type = 0;
			if(modeOfCreation == NeededMode.ALLALONE) {
				type = RandomDealer.generateTheRandom(3);
			} else {
				type = RandomDealer.generateTheRandom(4);
			}
			
			if(characterRatios[type] <= 0) {
				i--;
				continue;	//redefinition is needed
			} else {
				strwr.write(defineTheCharacter(type));
			}
		}
		strwr.flush();
		return strwr.toString();
	}

	private static String defineTheCharacter(Integer type) {
		
		RandomDealer.getTheRandDealerObject();
		LetterContainer.getLetterContainer();
		SpecLetterContainer.getTheSpecLetterContainer();

		//0->letters; 1->digits; 2->symbols; 3->input
		characterRatios[type]--;
		if(type == 0) {
			return (RandomDealer.generateTheRandom(2) == 1 )? 
					LetterContainer.getLetter(LetterTypeDefiner.UPPERCASE) + "" :
					LetterContainer.getLetter(LetterTypeDefiner.LOWERCASE) + "";
		} else if (type == 1){
			return RandomDealer.generateTheRandom(10) + "";
		} else if (type == 2) {
			return SpecLetterContainer.getTheSpecLetter() + "";
		} else {
			if(modeOfCreation == NeededMode.UNGAPPED_ADDEDTEXT)
				return desiredText;
			else if (modeOfCreation == NeededMode.GAPPED_ADDEDTEXT)
				return desiredTextParts.pollLast() + "";
			return "";
		}
	}
	
}
