package ceu.dam.ad.users.exception;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handle(UserNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handle(UserException e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handle(DuplicateUserException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(UserUnauthorizedException.class)
	public ResponseEntity<String> handle(UserUnauthorizedException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login no autorizado. Causa: "+ e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e){
		return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.joining("\n")));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e){
		return ResponseEntity.internalServerError().body("Error inesperado en el servidor. Consulte el log del servidor si tiene acceso");
	}
	
}
