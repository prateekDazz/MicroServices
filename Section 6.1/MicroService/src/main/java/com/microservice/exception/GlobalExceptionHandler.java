package com.microservice.exception;

import com.microservice.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       Map<String,String> validationErrors = new HashMap<>();
       List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
       validationErrorList.forEach((error)->{
           String fieldName  = ((FieldError)error).getField();
           String validationMsg = error.getDefaultMessage();
           validationErrors.put(fieldName,validationMsg);
       });

        return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto>handleglobalException(Exception e, WebRequest request)
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST,e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto>customerAlreadyExist(CustomerAlreadyExistsException e, WebRequest request)
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST,e.getMessage(), LocalDateTime.now());
return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>customerResponseNotFound(ResourceNotFoundException e, WebRequest request)
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.NOT_FOUND,e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
