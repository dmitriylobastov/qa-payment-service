package ru.stepup.payments;

public interface PaymentGateway {

    boolean charge(String cardNumber, double amount);
}
