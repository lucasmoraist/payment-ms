package com.lucasmoraist.payment_ms.infrastructure.service;

public interface CertificateService {
    void validate(String payloadHash, Object payload);
}
