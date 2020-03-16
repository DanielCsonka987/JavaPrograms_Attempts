package dbmanagers;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.SkillBodyModel;
import model.SpecLevelModel;
import model.SpecialSkillModel;

@ApplicationScoped
public class SpecialSkillTools implements ISkillToolboxSpec{

	@Override
	public SpecialSkillModel addNewSpecial(SpecialSkillModel ssm, SpecLevelModel slm , SessionFactory fact) {
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {

			sess.saveOrUpdate(ssm);
			sess.saveOrUpdate(slm);
			tr.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
			ssm = null;
		} finally {
			sess.close();
		}
		return ssm;
	}

	@Override
	public void deleteSpecialisation(SpecialSkillModel ssm, SessionFactory fact) {
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {
			
			Query query1 = sess.createQuery("delete from SpecialSkillModel where specid = :id");
			query1.setParameter("id", new Integer(ssm.getSpecid()));
			int res = query1.executeUpdate();
			
			
			Query query2 = sess.createQuery("delete from SpecLevelModel where specid = :id");
			query2.setParameter("id", ssm);
			res += query2.executeUpdate();
			
			tr.commit();
			
			if(res < 2)
				throw new Exception("No correct deletion happened");
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
	}
	
	@Override
	public void removeALevel(SpecLevelModel slm, SessionFactory fact) {
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {

			sess.remove(slm);
			tr.commit();
			
		} catch(HibernateException e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			sess.close();
		}
	}
	
	
	@Override
	public SkillBodyModel updateThisSpecSkill(SkillBodyModel sbm, SessionFactory fact) {
		Session sess = fact.openSession();
		Transaction tr = sess.beginTransaction();
		try {

			sess.saveOrUpdate(sbm);
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


}
