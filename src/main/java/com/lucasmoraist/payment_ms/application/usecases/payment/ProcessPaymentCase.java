package com.lucasmoraist.payment_ms.application.usecases.payment;

import com.lucasmoraist.payment_ms.application.validations.PaymentValidations;
import com.lucasmoraist.payment_ms.domain.model.Payment;

import java.util.List;

public record ProcessPaymentCase(List<PaymentValidations> paymentValidations) {

    public void execute(Payment payment) {
        this.paymentValidations.forEach(v -> v.validate(payment));
    }

}
