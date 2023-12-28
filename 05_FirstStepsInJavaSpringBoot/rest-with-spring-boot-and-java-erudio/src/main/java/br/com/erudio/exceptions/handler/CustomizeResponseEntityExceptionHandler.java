package br.com.erudio.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exceptions.BadRequestException;
import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.ForbiddenException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import jakarta.servlet.ServletRequest;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundRequestExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestRequestExceptions(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public final ResponseEntity<ExceptionResponse> handleForbiddenExceptionException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.FORBIDDEN);
	}
	
	
	public final ResponseEntity<ExceptionResponse> handleIErrorFilterJwtException(Exception ex, ServletRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getServletContext().getContextPath());
		return new ResponseEntity<>(exceptionResponse,HttpStatus.FORBIDDEN);
		
	}

}
