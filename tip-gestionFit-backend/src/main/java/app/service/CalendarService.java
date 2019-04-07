package app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Class_Calendar;
import app.model.Class_Day;
import app.model.DayInstructor;
import app.model.DayStudent;
import app.model.User;
import app.model.User_Instructor;
import app.persistence.CalendarDAO;
import app.persistence.DayInstructorDAO;
import app.persistence.UserDAO;

@Service
public class CalendarService {
	
	@Autowired
	private CalendarDAO calendarDAO;
	
	@Autowired
	private DayInstructorDAO dayInstDAO;
	
	@Autowired
	private GymService gymServ;
	
	@Autowired
	private UserDAO userDAO;
	
	public CalendarService(){
		this.calendarDAO = new CalendarDAO();
		this.userDAO = new UserDAO();
	}
	
	@Transactional
	public void save(Class_Calendar newExercise){
		this.calendarDAO.save(newExercise);
	}
	
	@Transactional
	public void update(Class_Calendar newExercise){
		this.calendarDAO.update(newExercise);
	}
	
	@Transactional
	public void delete(Class_Calendar exercise){
		this.calendarDAO.delete(exercise);
	}
	
	@Transactional
	public Class_Calendar getById(Long id){
		return this.calendarDAO.getById(id);
	}
	
	@Transactional
	public List<Class_Calendar> getAll(){
		return this.calendarDAO.getAll();
	}
	
	@Transactional
	public Class_Calendar get(){
		Class_Calendar calendar = this.calendarDAO.getById((long) 1);
		if(LocalDate.now().minusDays(15).isAfter(calendar.firstClass())){
			calendar.clearCalendar();
			this.update(calendar);
		}

		return calendar;
	}

	@Transactional
	public void addDays(List<DayStudent> days,String name, long id,long gymId) {
		Class_Calendar calendar = this.gymServ.getCalendar(gymId);
		LocalDate startDate = LocalDate.now();
	    long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, startDate.plusMonths(1));
		for(DayStudent day : days){
			User_Instructor instructor = this.userDAO.getInstructorForDay(day);
		    for(int i = 0 ; i < numOfDaysBetween ;i++){
		    	if(startDate.plusDays(i).getDayOfWeek().name().equals(day.getDay()) ){
		    			calendar.addClass(startDate.plusDays(i),day.getStartHour(),day.getEndHour(),name,id,instructor.getNameAndSurname());

		    	}
		    }
		}
		
		this.update(calendar);



		
	}

	@Transactional
	public Set<Class_Day> getClasses(User user) {
		Class_Calendar calendar =  this.gymServ.getCalendar(user.getGym());
		Set<Class_Day> classes =  calendar.getClasses();
		Set<Class_Day> classesUser = classes.stream().filter(day -> day.hasStudent(user.id)).collect(Collectors.toSet());
		return classesUser;
	}

	@Transactional
	public void markAssist(User user) {
		Class_Calendar calendar =  this.gymServ.getCalendar(user.getGym());
		calendar.markAssist(user.id);
		this.update(calendar);
		
		
	}
	
	@Transactional
	public List<DayInstructor> getInstructorDays(){
		return this.dayInstDAO.getAll();
	}

}
