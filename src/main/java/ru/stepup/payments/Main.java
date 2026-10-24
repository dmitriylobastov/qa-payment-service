package ru.stepup.payments;

public class Main {
    public static void main(String[] args) {

        System.out.println(PaymentStatus.CREATED);

        System.out.println(PaymentStatus.valueOf("SUCCESS").getDescription());

        for (PaymentStatus s : PaymentStatus.values()) {
            System.out.println(s);
        }
    }
}