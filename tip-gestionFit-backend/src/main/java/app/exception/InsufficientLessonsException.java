package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE,reason="El usuario no posee mas clases")
public class InsufficientLessonsException extends Exception {

	private static final long serialVersionUID = 7575247300386222770L;
	
	public InsufficientLessonsException(){
		super();
	}

}
