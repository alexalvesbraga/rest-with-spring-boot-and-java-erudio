package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}
	
	public BadRequestException() {
		super("Não é permitido um objeto nulo!");
		// TODO Auto-generated constructor stub
	}
	
	

}
