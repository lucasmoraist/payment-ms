package com.lucasmoraist.payment_ms.application.usecases.payment;

import com.lucasmoraist.payment_ms.application.validations.PaymentValidations;
import com.lucasmoraist.payment_ms.domain.model.Payment;

import java.util.List;
import java.util.logging.Logger;

public record ProcessPaymentCase(List<PaymentValidations> paymentValidations, Logger log) {

    public void execute(Payment payment) {
        this.paymentValidations.forEach(v -> v.validate(payment));
        log.info("Payment processed successfully for payment id");
    }

}
