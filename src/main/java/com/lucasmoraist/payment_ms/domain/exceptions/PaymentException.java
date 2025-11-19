package com.lucasmoraist.payment_ms.domain.exceptions;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }

}
