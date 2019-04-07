package app.persistence;

import org.springframework.stereotype.Repository;

import app.model.Class_Calendar;

@Repository
public class CalendarDAO extends GenericDAO<Class_Calendar> {
	
	public CalendarDAO(){
		super(Class_Calendar.class);
	}

}
