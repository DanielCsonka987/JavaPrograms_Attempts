package application.logic;

public class SpecLetterContainer {

	private static SpecLetterContainer ownAddress;
	private static Character[] elements = 
		{'_', '(', ')', '@', '%', '/' };
	/*
	 * The other types of special characters may be misunderstandable by RegExp
	 * algorithms and more difficult to maintain in reality
	*/
	
	public static SpecLetterContainer getTheSpecLetterContainer() {
		
		if(ownAddress == null) {
			ownAddress = new SpecLetterContainer();
		}
		return ownAddress;
	}
	
	public static Character getTheSpecLetter() {
		
		RandomDealer.getTheRandDealerObject();
		return elements[(RandomDealer.generateTheRandom(6))];
	}
}
