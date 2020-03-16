package dbmanagers;

import org.hibernate.SessionFactory;

import model.SkillBodyModel;

public interface ISkillToolboxNorm {

	
	SkillBodyModel addNewSkillWithLvlOne(SkillBodyModel sbm, SessionFactory fact );
	
	void deletSkillBody(int skillid, SessionFactory fact);
	
	SkillBodyModel riseSkillLevel(SkillBodyModel smb, int[] lvlmark, int[] costing, boolean[] benef,
			SessionFactory fact );
	
	SkillBodyModel removeSomeNormLevel(SkillBodyModel smb, int[] pointedLvls, SessionFactory fact );
	
}
