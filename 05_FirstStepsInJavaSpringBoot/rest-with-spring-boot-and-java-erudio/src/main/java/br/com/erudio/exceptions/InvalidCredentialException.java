package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidCredentialException extends AuthenticationException{
	
	public InvalidCredentialException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;
}
