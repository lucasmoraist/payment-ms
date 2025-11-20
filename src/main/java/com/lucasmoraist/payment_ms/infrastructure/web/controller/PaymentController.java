package com.lucasmoraist.payment_ms.infrastructure.web.controller;

import com.lucasmoraist.payment_ms.application.usecases.payment.ProcessPaymentCase;
import com.lucasmoraist.payment_ms.domain.enums.PaymentStatus;
import com.lucasmoraist.payment_ms.domain.model.Payment;
import com.lucasmoraist.payment_ms.infrastructure.service.CertificateService;
import com.lucasmoraist.payment_ms.infrastructure.web.dto.PaymentRequest;
import com.lucasmoraist.payment_ms.infrastructure.web.routes.PaymentRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController implements PaymentRoutes {

    private final ProcessPaymentCase processPaymentCase;
    private final CertificateService certificateService;

    public PaymentController(ProcessPaymentCase processPaymentCase, CertificateService certificateService) {
        this.processPaymentCase = processPaymentCase;
        this.certificateService = certificateService;
    }

    @Override
    public ResponseEntity<Void> createPayment(PaymentRequest request, String payloadHash) {
        this.certificateService.validate(payloadHash, request);

        Payment payment = new Payment(
                request.userName(),
                request.userEmail(),
                request.transactionValue(),
                PaymentStatus.PENDING,
                request.type(),
                request.paymentDate()
        );

        this.processPaymentCase.execute(payment);
        return ResponseEntity.status(201).build();
    }

}
