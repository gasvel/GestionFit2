package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Usuario no encontrado")
public class UserNotFoundException extends HttpClientErrorException {
	
	private static final long serialVersionUID = -4639670188454662727L;

	public UserNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}