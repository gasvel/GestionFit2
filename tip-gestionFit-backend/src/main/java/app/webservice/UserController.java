package app.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import app.exception.NotFoundException;
import app.model.Class_Calendar;
import app.model.Class_Day;
import app.model.DayInstructor;
import app.model.DayStudent;
import app.model.Gym;
import app.model.MeasurementsAdapter;
import app.model.MeasuringTable;
import app.model.Routine;
import app.model.User;
import app.model.UserInfo;
import app.model.User_Admin;
import app.model.User_Instructor;
import app.model.User_Student;
import app.service.CalendarService;
import app.service.EmailService;
import app.service.GymService;
import app.service.UserService;


@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class UserController {
	
	@Autowired
	private EmailService emailServ = new EmailService();
	
	@Autowired
	private UserService userServ = new UserService();
	
	@Autowired
	private GymService gymServ = new GymService();
	
	@Autowired
	private CalendarService calServ = new CalendarService();

	
	
	@GetMapping(value = "/users", produces = "application/json")   
	public List<User> getUsers() throws Exception{
		return this.userServ.getAll();

	}
	

	
	@GetMapping(value = "/calendar/classes/{id}", produces = "application/json")
	public Set<Class_Day> getClasses(@PathVariable("id") Long idUser) throws Exception {
		User user = this.userServ.getById(idUser);
		return this.calServ.getClasses(user);
	}

	
	@GetMapping(value = "/alumnos/{idGym}", produces = "application/json")   
	public List<UserInfo> getAlumnos(@PathVariable("idGym")long gymId) {
		return this.gymServ.getAllStudents(gymId);

	}
	
//	@PutMapping(value = "/instructor/addDays/{idInstructor}", produces = "application/json")
//	public ResponseEntity<Void> addDaysToInstructor(@PathVariable("idInstructor") long id,@RequestBody Set<DayInstructor> days) throws Exception{
//		this.userServ.addDaysInstructor(id,days);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//		
//	}
	
	@GetMapping(value = "/instructores/{idGym}", produces = "application/json")   
	public List<UserInfo> getInstructors(@PathVariable("idGym")long gymId) {
		return this.gymServ.getAllAdmins(gymId);

	}
	
	@PostMapping(value = "/instructor/{idGym}", produces = "application/json")   
	public ResponseEntity<Void> createInstructor(@RequestBody User_Instructor user,@PathVariable("idGym")long gymId) throws Exception {
		Gym gym = this.gymServ.getGym(gymId);
		if(gym == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		else {
			this.gymServ.addInstructor(gym,user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@PostMapping(value = "/admin/{idGym}", produces = "application/json")   
	public ResponseEntity<Void> createAdmin(@RequestBody User_Admin user,@PathVariable("idGym")long gymId) throws Exception {
		Gym gym = this.gymServ.getGym(gymId);
		if(gym == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		else {
			this.gymServ.addAdmin(gym,user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value = "/instructor", produces = "application/json")   
	public ResponseEntity<Void> updateInstructor(@RequestBody User_Instructor user) throws Exception {
			this.userServ.updateInstructor(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	
//	@PutMapping(value = "/user/{id}/nuevaMedicion",produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<Void> newMeasurements(@PathVariable("id") Long idUser,@RequestBody MeasurementsAdapter newMeasurements){
//		this.userServ.newMeasurement(idUser, newMeasurements);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
	
//	@PutMapping(value = "/user/{id}/nuevasRutinas",produces = "application/json")
//	@ResponseStatus(HttpStatus.OK)
//	public ResponseEntity<Void> newRutines(@PathVariable("id") Long idUser,@RequestBody List<Routine> newRoutines) throws Exception{
//		this.userServ.newRutines(idUser, newRoutines);
//
////		this.emailServ.sendEmailToUser(this.userServ.getById(idUser),EmailService.ROUTINE);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}


	
	@GetMapping(value= "/user/{id}",produces= "application/json")
	public User getUser(@PathVariable("id") Long idUser){
		User user = this.userServ.getById(idUser);
		if(user == null) {
			throw new NotFoundException("Usuario no encontrado");
		}
		
		return user;
	}
	
	@GetMapping(value= "/userDesktop/{mail}",produces= "application/json")
	public User getUserByEmail(@PathVariable("mail") String idUser){
		User user = this.userServ.getByMail(idUser);
		if(user == null) {
			throw new NotFoundException("Usuario no encontrado");
		}
		
		return user;
	}
	
//	@GetMapping(value="/user/{id}/rutinas",produces= "application/json")
//	public Set<Routine> getRutines(@PathVariable("id") Long idUser) {
//		Set<Routine> rutines = this.userServ.getStudentRutines(idUser);
//		return rutines;
//	}
	
//	@GetMapping(value="/user/{id}/table",produces= "application/json")
//	public MeasuringTable getTable(@PathVariable("id") Long idUser) {
//		MeasuringTable table = this.userServ.getStudentTable(idUser);
//		if(table == null) {
//			throw new NotFoundException("Usuario no encontrado");
//		}
//		return table;
//	}
	
	@GetMapping(value="/studentPromedio/{id}", produces= "application/json")
	public int getPromedioStudent(@PathVariable("id") Long id){
		return this.userServ.getPromedioAsistencia(id);
	}
	
	@PutMapping(value = "/user/{id}",produces = "application/json")
	public ResponseEntity<Void> updateUser(@PathVariable("id") Long idUser,@RequestBody User_Student user){
		User existingUser = this.userServ.getById(idUser);
		if(existingUser == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		user.setId(idUser);
		this.userServ.updateStudent(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/user/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long idUser){
		this.userServ.delete(this.userServ.getById(idUser));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/addLessons/{idStudent}/{nLessons}", produces = "application/json")
	public ResponseEntity<Void> addLessonsToStudent(@PathVariable("idStudent") long id, @PathVariable("nLessons") int numLessons,@RequestBody List<DayStudent> days) throws Exception{
		//REGISTRAR PAGO?
		this.userServ.addLessons(id,numLessons);
		this.userServ.addDays(id,days);
		User user = this.userServ.getById(id);
		this.calServ.addDays(days,user.getNameAndSurname(),id,user.getGym());
//		this.emailServ.sendEmailToUser(this.userServ.getById(id), EmailService.PAID);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/addLessonsDesktop/{mail}/{nLessons}", produces = "application/json")
	public ResponseEntity<User> addLessonsToStudentDesktop(@PathVariable("mail") String mail, @PathVariable("nLessons") int numLessons ,@RequestBody List<DayStudent> days) throws Exception{
		User user = this.userServ.getByMail(mail);
		this.userServ.addLessons(user.getId(),numLessons);
		this.userServ.addDays(user.id,days);
		this.calServ.addDays(days,user.getNameAndSurname(),user.id,user.getGym());
//		this.emailServ.sendEmailToUser(this.userServ.getByMail(mail), EmailService.PAID);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/assist/student/{id}",produces="application/json")
	public ResponseEntity<User> studentAssist(@PathVariable("id") long id) throws Exception{
		User_Student user;
		this.userServ.studentAssistById(id);
		user = (User_Student) this.userServ.getById(id);
		this.calServ.markAssist(user);

//		@Transactional
//		public Set<Routine> getStudentRutines(Long idUser){
//			return this.userDAO.getStudent(idUser).getRoutines();
//		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping(value="/addRfid/{mail}/{rfid}",produces = "application/json")
	public ResponseEntity<User> addRfid(@PathVariable("mail") String mail,@PathVariable("rfid") String rfid) throws Exception{
		User_Student user =(User_Student) this.userServ.getByMail(mail);
		user.setRfid(rfid);
		this.userServ.updateStudent(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/daysInstructor",produces = "application/json")
	public List<DayInstructor> getInstructorDays() {
		return this.calServ.getInstructorDays();
	}
	
//	@PostMapping(value = "/promo",produces="application/json")
//	public ResponseEntity<Void> promoStudents(@RequestBody Promo promo) throws Exception{
//		List<User_Student> students =  this.getAlumnos();
//		this.emailServ.sendPromoToUsers(students, promo);
//
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
	
	@GetMapping(value = "/checkUsername/{username}", produces="application/json")
	public ResponseEntity<Boolean> checkUsername(@PathVariable("username") String username){
		return new ResponseEntity<Boolean>(this.userServ.checkUsername(username),HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkEmail/{email}", produces="application/json")
	public ResponseEntity<Boolean> checkEmail(@PathVariable("email") String email){
		return new ResponseEntity<Boolean>(this.userServ.checkEmail(email),HttpStatus.OK);
	}
	
	@GetMapping(value= "/user/assistReport/{idUser}")
	public ResponseEntity<byte[]> getAssistReport(@PathVariable("idUser") Long id) throws DocumentException, IOException{
		String userName = this.userServ.getById(id).getNameAndSurname();
		if(userName == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		byte[] document =this.userServ.assistReport(id);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    // Here you have to set the actual filename of your pdf
	    String filename =userName + "-Asistencia.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<>(document, headers, HttpStatus.OK);
	    return response;
	}

}
