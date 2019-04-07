package app.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import app.model.Class_Calendar;
import app.model.Gym;
import app.model.GymDTO;
import app.model.User;
import app.model.UserInfo;
import app.model.User_Instructor;

@Repository
public class GymDAO extends GenericDAO<Gym> {
	
	public GymDAO() {
		super(Gym.class);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<GymDTO> getAllGyms(){
		List<GymDTO> result = null;
		Session session =getSessionFactory().openSession();
		try {	

			Criteria criteria = session.createCriteria(GymDTO.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.ge("id", 0L);
			result = (List<GymDTO>) criteria.add(criterion).list();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}
	
	@SuppressWarnings({ "deprecation" })
	public GymDTO getGymDTO(long gymId){
		GymDTO result = null;
		Session session =getSessionFactory().openSession();
		try {	

			Criteria criteria = session.createCriteria(GymDTO.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.eq("id", gymId);
			result = (GymDTO) criteria.add(criterion).uniqueResult();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<UserInfo> getAllAdmins(Long gym_id) {
		List<UserInfo> result = null;
		Session session =getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Gym.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.eq("id", gym_id);
			Criterion criterion2= Restrictions.eq("role","INSTRUCTOR");
			result = (List<UserInfo>) criteria.add(criterion).setProjection(Projections.property("admins")).add(criterion2).list();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getAllStudents(Long gym_id) {
		List<UserInfo> result = null;
		Session session =getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.eq("gym", gym_id);
			Criterion criterion2= Restrictions.eq("role","STUDENT");
			result = (List<UserInfo>) criteria.add(criterion).add(criterion2).list();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;	
		}

	public Class_Calendar getCalendar(long gym_id) {
		Class_Calendar result = null;
		Session session =getSessionFactory().openSession();
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Gym.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.eq("id", gym_id);
			result = (Class_Calendar) criteria.add(criterion).setProjection(Projections.property("calendar")).uniqueResult();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;	
	}

}
