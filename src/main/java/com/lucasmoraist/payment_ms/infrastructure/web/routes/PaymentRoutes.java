package com.lucasmoraist.payment_ms.infrastructure.web.routes;

import com.lucasmoraist.payment_ms.infrastructure.web.dto.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/payments")
public interface PaymentRoutes {

    @PostMapping("create")
    ResponseEntity<Void> createPayment(
            @Valid @RequestBody PaymentRequest request,
            @RequestHeader(value = "x-payload-hash", required = false) String payloadHash
    );

}
