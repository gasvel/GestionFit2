package app.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import app.model.DayStudent;
import app.model.User;
import app.model.User_Instructor;
import app.model.User_Priv;
import app.model.User_Student;

@Repository
public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super(User.class);
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User_Student> getAllStudents(){
		List<User_Student> result = null;
		Session session =getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("role", "STUDENT"));
			result = (List<User_Student>) criteria.add(criterion).list();
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
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User_Instructor> getAllInstructors(){
		List<User_Instructor> result = null;
		Session session =getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("role", "INSTRUCTOR"));
			result = (List<User_Instructor>) criteria.add(criterion).list();
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
	
	@SuppressWarnings("deprecation")
	public User_Priv getByUsername(String username) {
		User_Priv result = null;
		Session session = getSessionFactory().openSession();
		try {
		Criteria criteria = session.createCriteria(User_Priv.class);
		Criterion criterion = Restrictions.eq("username", username);
		result = (User_Priv) criteria.add(criterion).uniqueResult();
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
	
	@SuppressWarnings("deprecation")
	public User getByMail(String mail) {
		User result = null;
		Session session = getSessionFactory().openSession();
		try {
		Criteria criteria = session.createCriteria(User.class);
		Criterion criterion = Restrictions.eq("mail", mail);
		result = (User) criteria.add(criterion).uniqueResult();
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

	@SuppressWarnings("deprecation")
	public User_Student getStudent(Long idUser) {
		User_Student result = null;
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			Criterion criterion = Restrictions.and(Restrictions.eq("role", "STUDENT"),Restrictions.eq("id",idUser));
			result = (User_Student) criteria.add(criterion).uniqueResult();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean checkUsername(String username) {
		List<User_Student> result = null;
		Session session =getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("username", username));
			result = (List<User_Student>) criteria.add(criterion).list();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result.size() != 0;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean checkEmail(String email) {
		List<User_Student> result = null;
		Session session =getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Criterion criterion = Restrictions.and(Restrictions.ge("id", 0L),Restrictions.eq("mail", email));
			result = (List<User_Student>) criteria.add(criterion).list();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result.size() != 0;
	}


	@SuppressWarnings("deprecation")
	public User getByRfid(String id) {
		User result = null;
		Session session = getSessionFactory().openSession();
		try {
		Criteria criteria = session.createCriteria(User.class);
		Criterion criterion = Restrictions.eq("rfid", id);
		result = (User) criteria.add(criterion).uniqueResult();
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


	@SuppressWarnings("deprecation")
	public User_Instructor getInstructorForDay(DayStudent day) {
		Session session = getSessionFactory().openSession();
		User_Instructor result = null;

		try {
			String date = day.getDay();
			int start = day.getStartHour();
			int end = day.getEndHour();
			result = (User_Instructor) session.createCriteria(User.class)
			.createAlias("classes", "class")
			.add(Restrictions.eq("class.day", date))
			.createAlias("class.startEndHours","hour")
			.add(Restrictions.and(Restrictions.le("hour.startHour", start),Restrictions.ge("hour.endHour", end)))
			.uniqueResult();

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
