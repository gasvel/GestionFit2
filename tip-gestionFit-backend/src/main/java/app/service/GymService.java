package app.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Class_Calendar;
import app.model.Gym;
import app.model.GymDTO;
import app.model.UserInfo;
import app.model.User_Instructor;
import app.model.User_Student;
import app.persistence.GymDAO;

@Service
public class GymService {
	
	@Autowired
	private GymDAO gymDAO;
	
	public GymService() {
		this.gymDAO = new GymDAO();
	}
	
	@Transactional
	public List<GymDTO> getAll(){
		return this.gymDAO.getAllGyms();
	}

	@Transactional
	public List<UserInfo> getAllAdmins(Long gym_id) {
		return this.gymDAO.getAllAdmins(gym_id);
	}

	@Transactional
	public List<UserInfo> getAllStudents(Long gymId) {
		return this.gymDAO.getAllStudents(gymId);
	}

	@Transactional
	public long save(Gym gym) {
		return this.gymDAO.save(gym);
	}
	
	@Transactional
	public Gym getGym(Long id) {
		return this.gymDAO.getById(id);
	}

	@Transactional
	public void update(Gym gym) {
		this.gymDAO.update(gym);
		
	}
	
	@Transactional
	public void addStudent(Gym gym, User_Student user) {
		Calendar birthCal = GregorianCalendar.getInstance();
        birthCal.setTime(user.getBirthday());
		int age = (GregorianCalendar.getInstance().get(Calendar.YEAR) - birthCal.get(Calendar.YEAR));
		user.setAge(age);
		
		ArgumentsValidator.validateStudent(user);
		gym.addUser(user);
		this.gymDAO.update(gym);
	}

	@Transactional
	public void addInstructor(Gym gym, User_Instructor user) {
		ArgumentsValidator.validateInstructor(user);
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("INSTRUCTOR");
		gym.addUser(user);
		this.gymDAO.update(gym);
	}

	public Class_Calendar getCalendar(long id) {
		return this.gymDAO.getCalendar(id);
	}
}
