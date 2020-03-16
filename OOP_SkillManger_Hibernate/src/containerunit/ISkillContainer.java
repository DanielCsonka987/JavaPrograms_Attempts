package containerunit;

import model.SkillBodyModel;

public interface ISkillContainer {

	void readInListOfSkills();
	
	void printOutSkills();
	
	void addNewSkillWithLvlOne(int skillidentif, int skillattrib, int skillrequir,
			boolean skillbenef, String skillname, String skillnote,
			int firstlvlcost, boolean firstlvlbenef);
	
	void deleteThisSkill(int skillid);
	
	void riseThisSkillLvls(int skillid, int[] neededLevels,
			int[] levelCosts, boolean[] levelbenef);
	
	void removeThisSkillLvls(int skillid, int[] neededLevels);
	
	
	SkillBodyModel findThisSkillInList(SkillBodyModel sbm);
	
	SkillBodyModel findThisSkillInList(int skillidentif, int attribId, int requir, boolean benefit,
			String name, String note);
	
	SkillBodyModel findThisSkillInList(int skillid);
	
	void addNewSpecialis(int skillid, int specidentif, int specrequir,
			String specname, String specnote, int lvlcost);
	void deleteThisSpecialis(int skillid, int specid);
	
	void riseThisSpecialisLvl(int skillid, int specid, int levelcost);
	
	void removeSomeSpecialisLvl(int skillid, int specid, int lvldelet);
}
