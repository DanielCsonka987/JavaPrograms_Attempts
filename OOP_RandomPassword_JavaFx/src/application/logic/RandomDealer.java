package application.logic;

import java.util.Random;

public class RandomDealer {

	private static RandomDealer ownAddress;
	private static Random randomObj;
	
	public static RandomDealer getTheRandDealerObject() {
		
		if(ownAddress == null) {
			ownAddress = new RandomDealer();
		}
		return ownAddress;
	}
	
	public static Integer generateTheRandom(Integer intervUp) {
		
		if(randomObj == null) {
			randomObj = new Random(); 
		}
		
		return randomObj.nextInt(intervUp);
	}
}
