package com.lucasmoraist.payment_ms.infrastructure.usecases;

import com.lucasmoraist.payment_ms.application.usecases.payment.ProcessPaymentCase;
import com.lucasmoraist.payment_ms.application.validations.PaymentValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PaymentUseCase {

    @Bean
    public ProcessPaymentCase processPaymentCase(List<PaymentValidations> paymentValidations) {
        return new ProcessPaymentCase(paymentValidations);
    }

}
