package application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.logic.GeneratePwd;

class LogicTests {

	@Test
	void test1_noInput() {
		
		GeneratePwd.getGeneratePwdObject();
		
		System.out.println("Start - no input");
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(7, true, true, "", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 7)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(8, true, false, "", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 8)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(9, false, true, "", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 9)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
	}
	
	
	@Test
	void test2_wothInputWithGaps() {

		GeneratePwd.getGeneratePwdObject();
		
		System.out.println("Start - with input and gaps");
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(10, true, true, "zzz", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 10)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(8, true, false, "zzz", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 8)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(8, false, true, "zzz", false);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 8)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
	}
	
	@Test
	void test3_wothInputWithNoGaps() {
		
		GeneratePwd.getGeneratePwdObject();
		
		System.out.println("Start - with input no gaps");
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(8, true, true, "zzz", true);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 8)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(6, true, false, "zzz", true);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 6)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
		
		for (int i = 0; i < 11; i++) {
			
			GeneratePwd.adjustDetails(10, false, false, "zzz", true);
			String result = GeneratePwd.getTheDesiredPwd();
			
			if (result.length() != 10)
				fail(i + "-Not correct size " + result);
			System.out.print(result + " ");
		}
		System.out.println();
	}
}
