package com.lucasmoraist.payment_ms.infrastructure.web.dto;

import com.lucasmoraist.payment_ms.domain.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequest(
        @NotBlank(message = "User name is required")
        String userName,
        @NotBlank(message = "User email is required")
        String userEmail,
        @NotNull(message = "Transaction value is required")
        BigDecimal transactionValue,
        @NotNull(message = "Payment type is required")
        PaymentType type,
        @NotNull(message = "Payment date is required")
        LocalDateTime paymentDate
) {

}
