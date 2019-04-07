package app.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import app.model.Exercise;

@Repository
public class ExerciseDAO extends GenericDAO<Exercise>{

	public ExerciseDAO() {
		super(Exercise.class);
	}

	@SuppressWarnings("unchecked")
	public List<Exercise> getAllTemplates(){
	
		Session session = getSessionFactory().openSession();
		List<Exercise> result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Exercise.class);
		criteria.setMaxResults(50);
		Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("isTemplate",true));
		result = (List<Exercise>) criteria.add(criterion).list();
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
	public List<Exercise> getAll() {
		
		Session session = getSessionFactory().openSession();
		List<Exercise> result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Exercise.class);
		criteria.setMaxResults(50);
		Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("isTemplate",false));

		result = (List<Exercise>) criteria.add(criterion).list();
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
