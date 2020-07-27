package com.heliustech.orderservice.exception;


import com.heliustech.orderservice.config.Utility;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(
            CustomerNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Utility.getDate());
        body.put("message", "CustomerNotFoundException");
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<Object> handleInvalidOrderException(
            InvalidOrderException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Utility.getDate());
        body.put("message", "InvalidOrderException");
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(
            OrderNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Utility.getDate());
        body.put("message", "OrderNotFoundException");
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderFailedException.class)
    public ResponseEntity<Object> handleOrderFailedException(
            OrderFailedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Utility.getDate());
        body.put("message", "OrderFailedException");
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoProductFoundException.class)
    public ResponseEntity<Object> handleItemOrProductNotFoundException(
            NoProductFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Utility.getDate());
        body.put("message", "NoProductFoundException");
        body.put("error", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp",Utility.getDate());
        body.put("status", status.value());
        body.put("error", ex.getMessage());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("request",new String(e.request().body(), StandardCharsets.UTF_8));
        body.put("timestamp",Utility.getDate());
        body.put("response-status", response.getStatus());
        body.put("error-message",e.getMessage());
        body.put("response-body",new String(e.responseBody().get().array(),StandardCharsets.UTF_8));
        body.put("feign-statu",e.status());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
