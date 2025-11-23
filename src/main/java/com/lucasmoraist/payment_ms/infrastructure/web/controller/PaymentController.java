package com.lucasmoraist.payment_ms.infrastructure.web.controller;

import com.lucasmoraist.payment_ms.application.usecases.payment.ProcessPaymentCase;
import com.lucasmoraist.payment_ms.domain.enums.PaymentStatus;
import com.lucasmoraist.payment_ms.domain.model.Payment;
import com.lucasmoraist.payment_ms.infrastructure.service.CertificateService;
import com.lucasmoraist.payment_ms.infrastructure.web.dto.PaymentRequest;
import com.lucasmoraist.payment_ms.infrastructure.web.routes.PaymentRoutes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PaymentController implements PaymentRoutes {

    private final ProcessPaymentCase processPaymentCase;
    private final CertificateService certificateService;

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
        log.info("Payment processed successfully for payment id");
        return ResponseEntity.status(201).build();
    }

}
