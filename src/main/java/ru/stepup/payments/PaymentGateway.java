package ru.stepup.payments;

public interface PaymentGateway {

    /**
     * Проводит платёж через внешний шлюз.
     * <p>
     * Метод синхронный: возвращает результат сразу после ответа шлюза.
     *
     * @param cardNumber номер карты (16 цифр)
     * @param amount     сумма платежа в рублях, больше нуля
     * @return {@code true}, если платёж прошёл; {@code false} — если шлюз отказал
     */
    boolean charge(String cardNumber, double amount);
}
