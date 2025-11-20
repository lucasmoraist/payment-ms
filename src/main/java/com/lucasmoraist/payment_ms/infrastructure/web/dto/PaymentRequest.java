package com.lucasmoraist.payment_ms.infrastructure.web.dto;

import com.lucasmoraist.payment_ms.domain.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequest(
        String userName,
        String userEmail,
        BigDecimal transactionValue,
        PaymentType type,
        LocalDateTime paymentDate
) {

}
