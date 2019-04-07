package app.persistence;

import org.springframework.stereotype.Repository;

import app.model.DayStudent;
@Repository
public class UserScheduleDAO extends GenericDAO<DayStudent> {
	
	
	public UserScheduleDAO(){
		super(DayStudent.class);
	}

}
