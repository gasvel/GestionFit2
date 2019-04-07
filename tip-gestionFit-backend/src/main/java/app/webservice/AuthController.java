package app.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.model.User_Student;
import app.service.EmailService;
import app.service.UserService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private UserService userServ = new UserService();
	
	@Autowired
	private EmailService emailServ = new EmailService();
	
	
	@GetMapping("/authenticate/student")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void auth() {
		//Este metodo sirve solo para validar el token y autorizar o no al cliente

	}
	
	@GetMapping("/authenticate/instructor")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void authInstructor() {
		//Este metodo sirve solo para validar el token y autorizar o no al cliente

	}
	
	@PostMapping(value = "/auth/signup", produces = "application/json")   
	public ResponseEntity<Void> createUser(@RequestBody User_Student user) throws Exception {
			System.out.println(user.getNameAndSurname());
			this.userServ.saveStudent(user);

//			this.emailServ.sendEmailToUser(user, EmailService.WELCOME);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
