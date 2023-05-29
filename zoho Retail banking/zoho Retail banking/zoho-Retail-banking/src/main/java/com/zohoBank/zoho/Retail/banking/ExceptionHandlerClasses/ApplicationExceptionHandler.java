package com.zohoBank.zoho.Retail.banking.ExceptionHandlerClasses;
import com.zohoBank.zoho.Retail.banking.ExceptionsForThisApplication.AccessDeniedException;
import com.zohoBank.zoho.Retail.banking.ExceptionsForThisApplication.InsufficiantAmountException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DeserializationException;
import jakarta.servlet.ServletException;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String,String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
                error->{
                    errors.put(error.getField(),error.getDefaultMessage());
                });
        System.out.println(errors);
        return errors;
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String,String> userNotFound(UsernameNotFoundException exception)
    {
        Map<String,String> error =new HashMap<>();
        error.put("errormessage",exception.getMessage());
        return error;
    }

    @ExceptionHandler(InsufficiantAmountException.class)
    public Map<String, String > insufficientAmountExceptionHandle(InsufficiantAmountException exception){
        Map<String, String> error = new HashMap<>();
        error.put("errormessage", exception.getMessage());
        return error;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String, String > jwtExceptionHandler(ExpiredJwtException exception){
        Map<String, String> error = new HashMap<>();
        error.put("errormessage", exception.getMessage());
        return error;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String > jwtExceptionHandler1(AccessDeniedException exception){
        Map<String, String> error = new HashMap<>();
        error.put("errormessage", exception.getMessage());
        return error;
    }

    @ExceptionHandler(AuthenticationException.class)
    public Map<String,String> loginUserNotFound(AuthenticationException exception)
    {
        Map<String,String> error =new HashMap<>();
        error.put("errormessage",exception.getMessage());
        return error;
    }


}
