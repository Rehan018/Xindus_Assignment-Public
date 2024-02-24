package com.xinduswishlistmanagement.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.xinduswishlistmanagement.Model.MyErrorDetails;

@ControllerAdvice
public class GlobalException {

	// Exception handler for UserException
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> usersExceptionHandler(UserException userException,WebRequest req){
		// Create MyErrorDetails object with error details
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), userException.getMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<MyErrorDetails>(myErr,HttpStatus.BAD_REQUEST);
	}

	// Exception handler for ProductException
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productsExceptionHandler(ProductException productException,WebRequest req){
		// Create MyErrorDetails object with error details
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), productException.getMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<MyErrorDetails>(myErr,HttpStatus.BAD_REQUEST);
	}

	// Exception handler for WishlistException
	@ExceptionHandler(WishlistException.class)
	public ResponseEntity<MyErrorDetails> wishlistsExceptionHandler(WishlistException wishlistException,WebRequest req){
		// Create MyErrorDetails object with error details
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), wishlistException.getMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<MyErrorDetails>(myErr,HttpStatus.BAD_REQUEST);
	}

	// Generic exception handler for any other exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception exception,WebRequest req){
		// Create MyErrorDetails object with error details
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<MyErrorDetails>(myErr,HttpStatus.BAD_REQUEST);
	}

	// Exception handler for NoHandlerFoundException (404 Not Found)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException noHandlerFoundException,WebRequest req)  {
		// Create MyErrorDetails object with error details
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), noHandlerFoundException.getMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<>(myErr,HttpStatus.BAD_REQUEST);
	}

	// Exception handler for MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,WebRequest req)  {
		// Create MyErrorDetails object with error details from the validation message
		MyErrorDetails myErr = new MyErrorDetails(LocalDateTime.now(), methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(), req.getDescription(false));
		// Return ResponseEntity with MyErrorDetails and HTTP status
		return new ResponseEntity<>(myErr,HttpStatus.BAD_REQUEST);
	}
}
