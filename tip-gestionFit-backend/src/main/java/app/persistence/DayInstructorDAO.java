package app.persistence;

import org.springframework.stereotype.Repository;

import app.model.DayInstructor;

@Repository
public class DayInstructorDAO extends GenericDAO<DayInstructor> {
	
	public DayInstructorDAO() {
		super(DayInstructor.class);
	}

}
