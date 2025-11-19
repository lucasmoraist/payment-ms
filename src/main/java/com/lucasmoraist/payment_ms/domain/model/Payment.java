package com.lucasmoraist.payment_ms.domain.model;

import com.lucasmoraist.payment_ms.domain.enums.PaymentStatus;
import com.lucasmoraist.payment_ms.domain.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(
        String userName,
        String userEmail,
        BigDecimal transactionValue,
        PaymentStatus status,
        PaymentType type,
        LocalDateTime createdAt
) {

}
