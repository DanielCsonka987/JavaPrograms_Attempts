package containerunit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dbmanagers.GeneralSkillTools;
import dbmanagers.ISkillToolboxNorm;
import dbmanagers.ISkillToolboxSpec;
import dbmanagers.SpecialSkillTools;
import model.NormLevelModel;
import model.SkillBodyModel;
import model.SpecLevelModel;
import model.SpecialSkillModel;
import prog.CdiContext;

@ApplicationScoped
public class SkillContainer implements ISkillContainer {

	@Inject
	protected ISkillContainer itself;

	private SessionFactory fact;
	private List<SkillBodyModel> list;

	private ISkillToolboxNorm skillBodyTool;
	private ISkillToolboxSpec skillSpecialTool;

	public SkillContainer() {
		try {
			fact = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			list = new ArrayList<SkillBodyModel>();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CdiContext context = CdiContext.INSTANCE;

		skillBodyTool = context.getBean(ISkillToolboxNorm.class);
		skillSpecialTool = context.getBean(ISkillToolboxSpec.class);
	}

	public void readInListOfSkills() {

		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {

			list = sess.createQuery("from SkillBodyModel", SkillBodyModel.class).list();
			tr.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			sess.close();
			printOutSkills();
		}

	}

	public void addNewSkillWithLvlOne(int skillidentif, int skillattrib, int skillrequir, boolean skillbenef,
			String skillname, String skillnote, int firstlvlcost, boolean firstlvlbenef) {

		SkillBodyModel sbm = new SkillBodyModel(skillidentif, skillattrib, skillrequir, skillbenef, skillname,
				skillnote);
		List<NormLevelModel> lvl = new ArrayList<NormLevelModel>();
		lvl.add(new NormLevelModel(sbm, 1, firstlvlcost, firstlvlbenef));
		sbm.setLevels(lvl);

		if (findThisSkillInList(sbm) == null) {
			sbm = skillBodyTool.addNewSkillWithLvlOne(sbm, fact);
			list.add(sbm);
		}
	}

	public void deleteThisSkill(int skillid) {

		skillBodyTool.deletSkillBody(skillid, fact);
		list.removeIf(x -> x.getId() == skillid);
	}

	public void riseThisSkillLvls(int skillid, int[] neededLevels, int[] levelCosts, boolean[] levelbenef) {

		int indexOfOutMaxLvl = Arrays.asList(neededLevels).indexOf(11);
		if (indexOfOutMaxLvl != -1) {
			neededLevels = Arrays.copyOf(neededLevels, indexOfOutMaxLvl + 1);
			levelCosts = Arrays.copyOf(levelCosts, indexOfOutMaxLvl + 1);
			levelbenef = Arrays.copyOf(levelbenef, indexOfOutMaxLvl + 1);
		}
		if (neededLevels.length == 0)
			return;

		SkillBodyModel sbm = list.stream().filter(x -> x.getId() == skillid).findFirst().get();
		sbm = skillBodyTool.riseSkillLevel(sbm, neededLevels, levelCosts, levelbenef, fact);
		if (sbm == null)
			list.removeIf(x -> x.getId() == skillid);

	}

	public void removeThisSkillLvls(int skillid, int[] neededLevels) {

		if (Arrays.asList(neededLevels).contains(1)) {
			deleteThisSkill(skillid);
			return;
		} else {

			SkillBodyModel oldsbm = findThisSkillInList(skillid);
			SkillBodyModel newsbm = skillBodyTool.removeSomeNormLevel(oldsbm, neededLevels, fact);
			renewThisSkillInList(oldsbm, newsbm);
		}
	}

	public void addNewSpecialis(int skillid, int specidentif, int specrequir, String specname, String specnote,
			int lvlcost) {

		SpecialSkillModel ssm = new SpecialSkillModel(specidentif, specrequir, specname, specnote);
		SpecLevelModel slm = new SpecLevelModel(ssm, 1, lvlcost);
		ssm.getSpeclvl().add(slm);

		SkillBodyModel oldsbm = findThisSkillInList(skillid);
		SkillBodyModel newsbm = oldsbm;
		ssm.setSkId(newsbm);

		// newsbm.getSpecial().add(ssm);
		// newsbm = skillSpecialTool.updateThisSkill(newsbm, fact);

		ssm = skillSpecialTool.addNewSpecial(ssm, slm, fact);
		newsbm.getSpecial().add(ssm);

		renewThisSkillInList(oldsbm, newsbm);

	}

	public void deleteThisSpecialis(int skillid, int specid) {

		SkillBodyModel oldsbm = findThisSkillInList(skillid);

		List<SpecialSkillModel> ssmlist = oldsbm.getSpecial();
		SpecialSkillModel deletessm = ssmlist.stream()
				.filter(x -> x.getSpecid() == specid).findFirst().get();


		SkillBodyModel newsbm = oldsbm;
		ssmlist.remove(deletessm);
		newsbm.setSpecial(ssmlist);

		skillSpecialTool.deleteSpecialisation(deletessm, fact);
		renewThisSkillInList(oldsbm, newsbm);
	}

	public void riseThisSpecialisLvl(int skillid, int specid, int levelcost) {

		SkillBodyModel oldsbm = findThisSkillInList(skillid);

		List<SpecialSkillModel> ssmlist = oldsbm.getSpecial();

		SpecialSkillModel ssm = ssmlist.stream().filter(x -> x.getSpecid() == specid).findFirst().get();
		SpecLevelModel slm = new SpecLevelModel(ssm, 2, levelcost);

		ssmlist.stream().filter(x -> x.getSpecid() == specid).findFirst().get().getSpeclvl().add(slm);

		SkillBodyModel newsbm = oldsbm;
		newsbm.setSpecial(ssmlist);

		newsbm = skillSpecialTool.updateThisSpecSkill(newsbm, fact);
		renewThisSkillInList(oldsbm, newsbm);

	}

	public void removeSomeSpecialisLvl(int skillid, int specid, int lvldelet) {

		if (lvldelet == 1) {

			deleteThisSpecialis(skillid, specid);

		} else {

			SkillBodyModel oldsbm = findThisSkillInList(skillid);
			List<SpecialSkillModel> ssmlist = oldsbm.getSpecial();

			SpecialSkillModel changingssm = ssmlist.stream()
					.filter(x -> x.getSpecid() == specid).findFirst().get();
			int indexOfOld = ssmlist.indexOf(changingssm);
			SpecLevelModel slm = changingssm.getSpeclvl().stream()
					.filter(x -> x.getLevelmark() == 2).findFirst().get();
			skillSpecialTool.removeALevel(slm, fact);

			SpecialSkillModel newssm = changingssm;
			newssm.getSpeclvl().remove(slm);
			ssmlist.set(indexOfOld, newssm);

			SkillBodyModel newsbm = oldsbm;
			newsbm.setSpecial(ssmlist);

			renewThisSkillInList(oldsbm, newsbm);
		}
	}

	public void printOutSkills() {

		System.out.println("Skills of the database");
		if (list.size() == 0)
			System.out.println("No data to present");
		else {
			try {

				for (SkillBodyModel sm : list) {
					System.out.println(sm.toString());
					if (sm.getLevels().size() > 0) {
						for (NormLevelModel lm : sm.getLevels())
							System.out.println(String.format("  ->%s", lm.toString()));
					}
					if (sm.getSpecial().size() > 0) {
						for (SpecialSkillModel ssm : sm.getSpecial()) {
							System.out.println(String.format("  +%s", ssm.toString()));
							if (ssm.getSpeclvl().size() > 0) {
								for (SpecLevelModel slm : ssm.getSpeclvl())
									System.out.println(String.format("  ->%s", slm.toString()));
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}

	public SkillBodyModel findThisSkillInList(SkillBodyModel sbm) {
		return findThisSkillInList(sbm.getIdent(), sbm.getAttrib(), sbm.getRequir(), sbm.getBenef(),
				sbm.getName(), sbm.getNote());
	}

	public SkillBodyModel findThisSkillInList(int skillidentif, int attribId, int requir, boolean benefit,
			String name,
			String note) {
		if (list.size() != 0) {
			try {
				SkillBodyModel temp = list.stream()
						.filter(x -> x.getIdent() == skillidentif && x.getAttrib() == attribId
								&& x.getRequir() == requir && x.getBenef() == benefit && x.getName().equals(name)
								&& x.getNote().equals(note))
						.findFirst().get();
				return temp;
			} catch (NoSuchElementException e) {
				return null;
			}
		} else
			return null;
	}

	public SkillBodyModel findThisSkillInList(int skillid) {
		if (list.size() != 0) {
			try {
				SkillBodyModel temp = list.stream().filter(x -> x.getId() == skillid).findFirst().get();
				return temp;
			} catch (NoSuchElementException e) {
				return null;
			}
		} else
			return null;
	}

	private void renewThisSkillInList(SkillBodyModel oldsbm, SkillBodyModel newsbm) {

		int index = list.indexOf(oldsbm);

		list.set(index, newsbm);

	}
}
