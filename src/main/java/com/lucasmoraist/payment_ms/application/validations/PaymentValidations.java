package com.lucasmoraist.payment_ms.application.validations;

import com.lucasmoraist.payment_ms.domain.model.Payment;

public interface PaymentValidations {
    void validate(Payment payment);
}
