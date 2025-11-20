package com.lucasmoraist.payment_ms.infrastructure.web.handler;

import com.lucasmoraist.payment_ms.domain.exceptions.CertificateException;
import com.lucasmoraist.payment_ms.domain.exceptions.PaymentException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    protected ResponseEntity<Void> buildResponseEntity(PaymentException ex) {
        log.error("PaymentException: [{}]", ex.getMessage(), ex);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(CertificateException.class)
    protected ResponseEntity<Void> buildResponseEntity(CertificateException ex) {
        log.error("CertificateException: [{}]", ex.getMessage(), ex);
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<DataException>> handleDataRequestException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors()
                .stream()
                .map(DataException::new)
                .toList();
        log.warn("Data request exception");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Void> handleGenericException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private record DataException(String label, String message) {
        public DataException(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
