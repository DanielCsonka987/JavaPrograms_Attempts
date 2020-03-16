package application.logic;

public class LetterContainer {

	private static LetterContainer ownAddress;
	
	/*
	 * ASCII UPPERCASE LETTERS ARE FROM 65 TO 90
	 * ASCII LOWERCASE LETTERS ARE FROM 97 TO 122
	 */
	
	public static LetterContainer getLetterContainer() {
		
		if(ownAddress == null) {
			ownAddress = new LetterContainer();
		}
		return ownAddress;
	}
	
	public static Character getLetter(LetterTypeDefiner type) {
		
		RandomDealer.getTheRandDealerObject();
		Integer rand = RandomDealer.generateTheRandom(26);
		
		if(type == LetterTypeDefiner.UPPERCASE)
			return new Character((char) (rand + 65));
		else
			return new Character((char) (rand + 97));
	}
	
	
	public static enum LetterTypeDefiner {LOWERCASE, UPPERCASE}
}
