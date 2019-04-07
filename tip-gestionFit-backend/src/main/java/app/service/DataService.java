package app.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.model.Class_Calendar;
import app.model.DayInstructor;
import app.model.Exercise;
import app.model.Exercise_Type;
import app.model.Gym;
import app.model.Payment;
import app.model.StartEndHour;
import app.model.User_Admin;
import app.model.User_Instructor;
import app.model.User_Student;
import app.persistence.GymDAO;
import app.persistence.UserDAO;


@Service
public class DataService {
	
	@Autowired
	RoutineService routineServ;
	@Autowired
	UserDAO userDAO ;
	@Autowired
	UserService userServ;
	@Autowired
	ExerciseService exerServ;
	@Autowired
	CalendarService calServ;
	
	@Autowired
	GymDAO gymDAO;
	
	public DataService(){
		this.routineServ = new RoutineService();
		this.userDAO = new UserDAO();
		this.exerServ = new ExerciseService();
		this.calServ = new CalendarService();
		this.userServ = new UserService();
		this.gymDAO = new GymDAO();
	}

	
	public void createInitialData() {
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Gym plank= new Gym("Plank Pilates");
		
		User_Admin admin= new User_Admin("lovemylife",encoder.encode("1234"),"LoveMyLife","lovemylife@gmail.com");
		
		
		User_Instructor inst2 = new User_Instructor("ZairaF",encoder.encode("1234"),"Zaira Ferreira", "zairamicaelaferreira@gmail.com");
		
		User_Student user1= new User_Student("", "alumno1", encoder.encode("1234"), "Ruperto", "gaz_95@live.com", "", "", "", new Date(), "42566456", 83);


		this.gymDAO.save(plank);

		
		plank.addUser(admin);
		plank.addUser(inst2);
		
		plank.addUser(user1);
		
		Payment payIn= new Payment("Roberto","Pilates",650,"Zaira",true);
		plank.addPayment(LocalDate.now(),payIn);
		
		Payment payOut= new Payment("Serv. Agua","Bidon",200,"Zaira",false);
		plank.addPayment(LocalDate.now(),payOut);

		
		Exercise ex1 = new Exercise("Curl de biceps con mancuernas", "3(series)x10(repeticiones)x15(Pausa)", Exercise_Type.Arms);
		Exercise ex2 = new Exercise("Sentadillas sostenidas", "4(series)x10(repeticiones)x15(pausa)", Exercise_Type.Lower_Body);
		Exercise ex3 = new Exercise("Traccion de pecho con sogas", "4(series)x10(repeticiones)x15(pausa)", Exercise_Type.Upper_Body);
		Exercise ex4 = new Exercise("Bicicleta fija", "10 minutos", Exercise_Type.Warm_Up);
		Exercise ex5 = new Exercise("Pies a la barra posicion V", "6(series)x10(repeticiones)x10(pausa)", Exercise_Type.Warm_Up);
		Exercise ex6 = new Exercise("Mov. articulares de brazos", "5 minutos", Exercise_Type.Warm_Up);
		Exercise ex7 = new Exercise("Traccion de biceps con sogas", "4(series)x10(repeticiones)x15(pausa)", Exercise_Type.Arms);
		Exercise ex8 = new Exercise("Estocadas", "4(series)x5(repeticiones)x20(pausa) cada pierna", Exercise_Type.Lower_Body);
		Exercise ex9 = new Exercise("Espinales en barrel", "3(series)x10(repeticiones)x15(pausa)", Exercise_Type.Upper_Body);

		plank.addExercise(ex1);
		plank.addExercise(ex2);
		plank.addExercise(ex3);
		plank.addExercise(ex4);
		plank.addExercise(ex5);
		plank.addExercise(ex6);
		plank.addExercise(ex7);
		plank.addExercise(ex8);
		plank.addExercise(ex9);
		
		DayInstructor dayI1 = new DayInstructor();
		StartEndHour sten= new StartEndHour();
		sten.setStartHour(13);
		sten.setEndHour(17);
		Set<StartEndHour> stens= new HashSet<>();
		stens.add(sten);
		dayI1.setStartEndHours(stens);
		dayI1.setDay("THURSDAY");

		
		DayInstructor dayI2 = new DayInstructor();
		StartEndHour sten2= new StartEndHour();
		sten2.setStartHour(13);
		sten2.setEndHour(17);
		Set<StartEndHour> stens2= new HashSet<>();
		stens2.add(sten2);
		dayI2.setStartEndHours(stens2);
		dayI2.setDay("TUESDAY");


		
		Set<DayInstructor> classes2 = new HashSet<>();
		classes2.add(dayI2);
		classes2.add(dayI1);
		
		this.gymDAO.update(plank);

//		this.userServ.addDaysInstructor(inst2.id, classes2);
		

		
			
	}
	
	
}
