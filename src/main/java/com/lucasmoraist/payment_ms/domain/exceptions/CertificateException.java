package com.lucasmoraist.payment_ms.domain.exceptions;

public class CertificateException extends RuntimeException{

    public CertificateException(String message) {
        super(message);
    }

    public CertificateException(String message, Throwable ex) {
        super(message, ex);
    }

}
