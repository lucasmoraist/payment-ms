package com.lucasmoraist.payment_ms.application.validations.impl;

import com.lucasmoraist.payment_ms.application.validations.PaymentValidations;
import com.lucasmoraist.payment_ms.domain.exceptions.PaymentException;
import com.lucasmoraist.payment_ms.domain.model.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeValidation implements PaymentValidations {

    @Override
    public void validate(Payment payment) {
        LocalDate today = LocalDate.now();
        LocalDateTime paymentDate = payment.createdAt();

        if (paymentDate.toLocalDate().isAfter(today) || paymentDate.toLocalDate().isBefore(today)) {
            throw new PaymentException("Payment date cannot be in the future");
        }
    }

}
