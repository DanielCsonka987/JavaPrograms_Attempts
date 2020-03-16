package prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import containerunit.ISkillContainer;
import containerunit.SkillContainer;
import model.NormLevelModel;
import model.SkillBodyModel;
import model.SpecLevelModel;
import model.SpecialSkillModel;

public class SkillsManaging {

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CdiContext context = CdiContext.INSTANCE;

		ISkillContainer sc = context.getBean(ISkillContainer.class);
		sc.readInListOfSkills();

		sc.addNewSkillWithLvlOne(33, 8, 0, true, "kovácsolás", "mert hangos", 410, false);
		sc.printOutSkills();

		int res = sc.findThisSkillInList(33, 8, 0, true, "kovácsolás", "mert hangos").getId();
		System.out.println("The id of newly created:");
		System.out.println(res);
		holdThisExecution();
		
		try {

			sc.riseThisSkillLvls(res, new int[] { 2, 3 }, new int[] { 560, 845 }, new boolean[] { true, false });
			System.out.println("New skill lvl rise +2 +3");
			sc.printOutSkills();
			holdThisExecution();

			sc.riseThisSkillLvls(res, new int[] { 4 }, new int[] { 1010 }, new boolean[] { false });
			System.out.println("New skill lvl rise +4");
			sc.printOutSkills();
			holdThisExecution();

			sc.removeThisSkillLvls(res, new int[] { 3, 4 });
			System.out.println("New skill lvl rise -3 -4");
			sc.printOutSkills();
			holdThisExecution();

			sc.addNewSpecialis(res, 1, 2, "vértkovácsolás", "a védelem jó", 870);
			int resSpec1 = sc.findThisSkillInList(res).getSpecial().get(0).getSpecid();
			sc.printOutSkills();
			System.out.println("The id of newly created specialisation:");
			System.out.println(resSpec1);
			holdThisExecution();

			sc.riseThisSpecialisLvl(res, resSpec1, 1200);
			System.out.println("New skill spec1 lvl rising");
			sc.printOutSkills();
			holdThisExecution();

			sc.removeSomeSpecialisLvl(res, resSpec1, 2);
			System.out.println("New skill spec1 lvl removing");
			sc.printOutSkills();
			holdThisExecution();

			sc.addNewSpecialis(res, 1, 2, "vértkovácsolás", "a védelem jó", 870);
			int resSpec2 = sc.findThisSkillInList(res).getSpecial().get(1).getSpecid();
			sc.printOutSkills();
			System.out.println("The id of newly created specialisation:");
			System.out.println(resSpec2);
			holdThisExecution();

			sc.riseThisSpecialisLvl(res, resSpec2, 2100);
			System.out.println("New skill spec2 lvl rising");
			sc.printOutSkills();
			holdThisExecution();

			sc.removeSomeSpecialisLvl(res, resSpec2, 1);
			System.out.println("New skill spec2 all lvl-s");
			sc.printOutSkills();
			holdThisExecution();

			sc.deleteThisSpecialis(res, resSpec1);
			System.out.println("New skill spec2 deletion");
			sc.printOutSkills();
			holdThisExecution();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			sc.deleteThisSkill(res);
			System.out.println("New skill deleted");
			sc.printOutSkills();
		}
		SkillBodyModel sbm = null;
		res = -1;
		if((sbm = sc.findThisSkillInList(33, 8, 0, true, "kovácsolás", "mert hangos")) != null)
			res = sbm.getId();
		System.out.println("The id of deleted (if is still there):");
		System.out.println(res > 0?res:"Already deleted, no id got back");
		
	}

	
	
	
	
	private static void holdThisExecution() {
		
		System.out.println("Press a button to continue...");
		Scanner scannerObj = new Scanner(System.in);
		scannerObj.hasNextLine();
	}
	

	

	
	

	
	

}
