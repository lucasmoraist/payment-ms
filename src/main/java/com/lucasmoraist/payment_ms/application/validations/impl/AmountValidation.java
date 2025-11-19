package com.lucasmoraist.payment_ms.application.validations.impl;

import com.lucasmoraist.payment_ms.application.validations.PaymentValidations;
import com.lucasmoraist.payment_ms.domain.exceptions.PaymentException;
import com.lucasmoraist.payment_ms.domain.model.Payment;

import java.math.BigDecimal;

public class AmountValidation implements PaymentValidations {

    @Override
    public void validate(Payment payment) {
        if (payment.transactionValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException("Transaction value must be greater than zero");
        }
    }

}
