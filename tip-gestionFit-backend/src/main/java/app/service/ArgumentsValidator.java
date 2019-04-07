package app.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import app.exception.InvalidModelObjectException;
import app.model.Exercise;
import app.model.Exercise_Type;
import app.model.Routine;
import app.model.Routine_Type;
import app.model.User;
import app.model.User_Admin;
import app.model.User_Instructor;
import app.model.User_Student;

public class ArgumentsValidator {
	
	public static void isNullOrEmptyString(String... strArr) {
	    for (String st : strArr) {
	        if  (st==null || st.isEmpty())
				throw new InvalidModelObjectException("Not a valid string");
	    }
	}
	
	public static void isNegativeInt(Integer... intArr) {
	    for (Integer i : intArr) {
	        if  (i==null || i < 0)
				throw new InvalidModelObjectException("Not a valid int");
	    }
	}
	
	public static void isNotAValidMailAddress(String... strArr) {
		isNullOrEmptyString(strArr);
	    
		EmailValidator mailValid = EmailValidator.getInstance();
		
		for (String st : strArr) {
	        if  (!mailValid.isValid(st))
				throw new InvalidModelObjectException("Not a valid mail");

	    } 
	}
	
	public static void isInvalidFullName(String name) {
		isNullOrEmptyString(name);
		if(name.length() < 4 || name.length() > 50)
			throw new IllegalArgumentException("Not a valid name");

	}
	
	public static void isInvalidDescription(String description) {
		isNullOrEmptyString(description);
		if(description.length() < 5 || description.length() > 60)
			throw new InvalidModelObjectException("Not a valid description");

	}
	
	public static void isInvalidTelephone(String number) {
		isNullOrEmptyString(number);
		if( number.length() < 8)
			throw new InvalidModelObjectException("Not a valid telephone");

	}
	
	public static void isInvalidDate(Date date) {
		int greater = date.compareTo(new GregorianCalendar(1900,01,01).getTime());
		int less = date.compareTo(new GregorianCalendar(2006,01,01).getTime());
		if(greater < 0 || less >0) 
			throw new InvalidModelObjectException("Not a valid date");

	}

	public static void validateStudent(User_Student newUser) {
		User_Student user = newUser;
		isInvalidDate(user.getBirthday());
		isInvalidFullName(user.getNameAndSurname());
		isInvalidTelephone(user.getTelephone());
		isNullOrEmptyString(user.getObjective()); 
		isNegativeInt(user.getAge());
		isNotAValidMailAddress(user.getMail());
		if(user.getWeigth() < 30f)
			throw new InvalidModelObjectException("Not a valid weigth");
		
		
	}

	public static void validateInstructor(User_Instructor user) {
		User_Instructor newUser = user;
		isInvalidFullName(newUser.getNameAndSurname());
		isNotAValidMailAddress(newUser.getMail());
		isNullOrEmptyString(newUser.getUsername(),newUser.getPassword());

		
	}
	
	public static void validateRoutines(List<Routine> list) {
		for(Routine routine : list) {
			validateRoutine(routine);
		}
	}

	public static void validateRoutine(Routine newRutines) {
		isNullOrEmptyString(newRutines.getName());
		isInvalidListExercises(newRutines.getExercises());
		isInvalidTypeRoutine(newRutines.getType());
	}


	private static void isInvalidTypeRoutine(Routine_Type type) {
		if(type == null){
			throw new InvalidModelObjectException("Not a valid type");
		}
	}

	private static void isInvalidListExercises(List<Exercise> exercises) {
		if(exercises.isEmpty()){
			throw new InvalidModelObjectException("Not a valid list of exercises");
		}
	}

	public static void validateExercise(Exercise newExercise) {
		isNullOrEmptyString(newExercise.getName());
		isInvalidTyperExercise(newExercise.getType());
		isInvalidDescription(newExercise.getDescription());
	}

	private static void isInvalidTyperExercise(Exercise_Type type) {
		if(type == null){
			throw new InvalidModelObjectException("Not a valid type");
		}
	}

	public static void validateAdmin(User_Admin admin) {
		User_Admin newUser = admin;
		isInvalidFullName(newUser.getNameAndSurname());
		isNotAValidMailAddress(newUser.getMail());
		isNullOrEmptyString(newUser.getUsername(),newUser.getPassword());
		
	}

}
