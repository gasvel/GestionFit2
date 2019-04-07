package app.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import app.model.Routine;

@Repository
public class RoutineDAO extends GenericDAO<Routine> {

	public RoutineDAO() {
		super(Routine.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Routine> getAllTemplates(){
	
		Session session = getSessionFactory().openSession();
		List<Routine> result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Routine.class);
		criteria.setMaxResults(50);
		Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("isTemplate",true));
		result = (List<Routine>) criteria.add(criterion).list();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(session!= null) {
				session.close();
			}
		}
		return result;

	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Routine> getAll() {
		
		Session session = getSessionFactory().openSession();
		List<Routine> result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Routine.class);
		criteria.setMaxResults(50);
		Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("isTemplate",false));

		result = (List<Routine>) criteria.add(criterion).list();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(session!= null) {
				session.close();
			}
		}
		return result;

	}
}
