package dbmanagers;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.id.PostInsertIdentifierGenerator;

import model.NormLevelModel;
import model.SkillBodyModel;

@ApplicationScoped
public class GeneralSkillTools implements ISkillToolboxNorm{

	public SkillBodyModel addNewSkillWithLvlOne(SkillBodyModel sbm, SessionFactory fact ) {
		
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {

			sess.persist(sbm);
			tr.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
			sbm = null;
		} finally {
			sess.close();
		}
		return sbm;
	}
	
	public void deletSkillBody(int skillid, SessionFactory fact) {
		
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {
			
			SkillBodyModel sm = sess.find(SkillBodyModel.class, skillid);
			sess.remove(sm);
			tr.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			sess.close();
		}
	}
	
	public SkillBodyModel riseSkillLevel(SkillBodyModel sbm, int[] lvlmark, int[] costing, boolean[] benef,
			SessionFactory fact ) {
		
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		NormLevelModel lm = null;
		try {

			//sm = sess.find(SkillBodyModel.class, skillid);
			for(int i = 0; i<lvlmark.length; i++) {
				
				lm = new NormLevelModel(sbm, lvlmark[i], costing[i], benef[i]);
				sess.saveOrUpdate(lm);
				
				sbm.getLevels().add(lm);
			}
			tr.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			sess.close();
		}
		return sbm;
	}
	
	
	public SkillBodyModel removeSomeNormLevel(SkillBodyModel sbm, int[] pointedLvls, SessionFactory fact ) {
		
		if(Arrays.asList(pointedLvls).contains(1)) {
			deletSkillBody(sbm.getId(), fact);
			return null;
		}
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();

		int min = pointedLvls[0];
		int max = pointedLvls[pointedLvls.length-1];
		try {
			
			for (int i = 0; i < pointedLvls.length; i++) {

				NormLevelModel toRemove = sbm.getLevels().stream()
						.filter(x -> x.getLevelmark() >= min && x.getLevelmark() <= max)
						.findFirst().get();
				sess.remove(toRemove);
				sbm.getLevels().remove(toRemove);

			}
			tr.commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			sess.close();
		}
		return sbm;
	}
}
