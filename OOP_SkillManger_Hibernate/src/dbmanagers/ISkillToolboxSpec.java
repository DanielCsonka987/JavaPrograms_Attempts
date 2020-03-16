package dbmanagers;

import org.hibernate.SessionFactory;

import model.SkillBodyModel;
import model.SpecLevelModel;
import model.SpecialSkillModel;

public interface ISkillToolboxSpec {

	SpecialSkillModel addNewSpecial(SpecialSkillModel ssm, SpecLevelModel slm, SessionFactory fact);

	SkillBodyModel updateThisSpecSkill(SkillBodyModel sbm, SessionFactory fact);

	
	void removeALevel(SpecLevelModel slm, SessionFactory fact);
	
	void deleteSpecialisation(SpecialSkillModel ssm, SessionFactory fact);
}

