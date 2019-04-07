package app.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class GenericDAO<T> {
	


	private Class<T> entityType;
	private static StandardServiceRegistry registry;
	protected static SessionFactory sessionFactory ;
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null){
			Configuration cfg = new Configuration().configure();

		
		Map<String,String> jdbcUrlSettings = new HashMap<>();
		String jdbcDbUrl = System.getenv("JDBC_DATABASE_URL");
		if (null != jdbcDbUrl) {
		  jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
		  jdbcUrlSettings.put("hibernate.connection.username", System.getenv("JDBC_DATABASE_USERNAME"));
		  jdbcUrlSettings.put("hibernate.connection.password", System.getenv("JDBC_DATABASE_PASSWORD"));
		  jdbcUrlSettings.put("hibernate.hbm2dll.auto", "create-drop");
		}


		registry = new StandardServiceRegistryBuilder().
		    configure("hibernate.cfg.xml").
		    applySettings(jdbcUrlSettings).
		    build();
		sessionFactory=cfg.buildSessionFactory(registry);
		}
		return sessionFactory;
	}
	
	@Autowired
	public GenericDAO() {

	}
	
	public GenericDAO(Class<T> entityType) {
		this.entityType = entityType;
	}

	public long save(T object) {
		Session session = getSessionFactory().openSession();
		Long lastId = null;
		try {
			session.beginTransaction();
			lastId =(Long)session.save(object);
			session.getTransaction().commit();
			System.out.println(lastId +"ACA ESTA LA IDDDDDD");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
		return lastId;

	}

	public void update(T object) {
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	public void delete(T object) {
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		Session session = getSessionFactory().openSession();
		T result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(entityType);
		Criterion criterion = Restrictions.eq("id", id);

	    result =(T) criteria.add(criterion).uniqueResult();
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
	public List<T> getAll() {
		
		Session session = getSessionFactory().openSession();
		List<T> result = null;

		try {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(entityType);
		criteria.setMaxResults(50);
		result = (List<T>) criteria.list();
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
