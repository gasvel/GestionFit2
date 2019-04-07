package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE,reason="Membresia vencida")
public class ExpiredLessonsException extends Exception {

	private static final long serialVersionUID = 6877379716974623887L;
	
	public ExpiredLessonsException() {
		super();
	}


}
